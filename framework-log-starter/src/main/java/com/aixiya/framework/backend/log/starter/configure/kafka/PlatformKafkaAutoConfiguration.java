package com.aixiya.framework.backend.log.starter.configure.kafka;

import com.aixiya.framework.backend.log.starter.service.PlatformAuditLogService;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.LoggingProducerListener;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.kafka.support.converter.RecordMessageConverter;

import java.util.Map;

/**
 * @Author wangqun865@163.com
 */

//@Configuration
@EnableKafka
@EnableConfigurationProperties(KafkaProperties.class)
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(KafkaTemplate.class)
@ConditionalOnProperty(
        value = {"aixiya.platform.log.audit.enable"},
        havingValue= "true",
        matchIfMissing = false
)
public class PlatformKafkaAutoConfiguration {

    private final KafkaProperties properties;

    public PlatformKafkaAutoConfiguration(KafkaProperties properties) {
        this.properties = properties;
    }

    @Bean
    //@ConditionalOnMissingBean(KafkaTemplate.class)    //(1这里日后测 2还测 如果加了这个 是否会不注入，service里是否会注入spring默认的)
    public KafkaTemplate<?, ?> kafkaTemplate(@Qualifier("kafkaProducerFactory") ProducerFactory<Object, Object> kafkaProducerFactory,
                                             ProducerListener<Object, Object> kafkaProducerListener,
                                             ObjectProvider<RecordMessageConverter> messageConverter) {
        KafkaTemplate<Object, Object> kafkaTemplate = new KafkaTemplate<>(kafkaProducerFactory);
        messageConverter.ifUnique(kafkaTemplate::setMessageConverter);
        kafkaTemplate.setProducerListener(kafkaProducerListener);
        kafkaTemplate.setDefaultTopic(this.properties.getTemplate().getDefaultTopic());
        return kafkaTemplate;
    }

    @Bean
    //@ConditionalOnMissingBean(ProducerListener.class)
    public ProducerListener<Object, Object> kafkaProducerListener() {
        return new LoggingProducerListener<>();
    }


    @Bean
    //@ConditionalOnMissingBean(ProducerFactory.class)
    public ProducerFactory<Object, Object> kafkaProducerFactory() {
        DefaultKafkaProducerFactory<Object, Object> factory = new DefaultKafkaProducerFactory<>(
                this.properties.buildProducerProperties());
        String transactionIdPrefix = this.properties.getProducer().getTransactionIdPrefix();
        if (transactionIdPrefix != null) {
            factory.setTransactionIdPrefix(transactionIdPrefix);
        }
        return factory;
    }




    @Bean(name = "auditLogKafkaTemplate")
    public KafkaTemplate<?, ?> auditLogKafkaTemplate(@Qualifier("auditLogKafkaProducerFactory") ProducerFactory<Object, Object> kafkaProducerFactory,
                                             ProducerListener<Object, Object> kafkaProducerListener,
                                             ObjectProvider<RecordMessageConverter> messageConverter) {
        KafkaTemplate<Object, Object> kafkaTemplate = new KafkaTemplate<>(kafkaProducerFactory);
        messageConverter.ifUnique(kafkaTemplate::setMessageConverter);
        kafkaTemplate.setProducerListener(kafkaProducerListener);
        kafkaTemplate.setDefaultTopic(this.properties.getTemplate().getDefaultTopic());
        return kafkaTemplate;
    }


    @Bean(name = "auditLogKafkaProducerFactory")
    public ProducerFactory<Object , Object> auditLogKafkaProducerFactory() {
        Map<String, Object> auditLogProducerMap =  this.properties.buildProducerProperties();
       // auditLogProducerMap.put(ProducerConfig.ACKS_CONFIG , "all");
        //auditLogProducerMap.put(ProducerConfig.RETRIES_CONFIG , 1);
        auditLogProducerMap.put(ProducerConfig.ACKS_CONFIG , 0);
        auditLogProducerMap.put(ProducerConfig.RETRIES_CONFIG , 0);
        DefaultKafkaProducerFactory<Object , Object> factory = new DefaultKafkaProducerFactory<>(auditLogProducerMap);
        String transactionIdPrefix = this.properties.getProducer().getTransactionIdPrefix();
        if (transactionIdPrefix != null) {
            factory.setTransactionIdPrefix(transactionIdPrefix);
        }
        return factory;
    }

    @Bean
    @ConditionalOnBean(name = "auditLogKafkaTemplate")
    public PlatformAuditLogService platformAuditLogService(){
        return new PlatformAuditLogService();
    }



}

package com.expense.expenseService.config;

import com.expense.expenseService.consumer.ExpenseDeserializer; // Import your custom deserializer
//import com.expense.service.dto.ExpenseDto; // Import your DTO (Adjust package if needed!)
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import com.expense.expenseService.dto.ExpenseDto;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;


import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Bean
    public ConsumerFactory<String, ExpenseDto> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        
        // Use your custom ExpenseDeserializer here
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ExpenseDeserializer.class);
        
        // Trusted packages config (critical for JSON mapping)
        props.put("spring.json.trusted.packages", "*");

        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ExpenseDto> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ExpenseDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
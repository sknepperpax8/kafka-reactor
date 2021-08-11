package com.pro.kafka.producer;

import com.pro.kafka.producer.preference.Preference;
import com.pro.kafka.producer.user.MicrosoftUser;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.UUIDSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.serializer.JsonSerializer;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

import java.util.UUID;

@SpringBootApplication
public class ProKafkaProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProKafkaProducerApplication.class, args);
    }

    @Bean("MicrosoftUser")
    KafkaSender<UUID, MicrosoftUser> kafkaSenderMicrosoftUser() {
        return KafkaSender.create(SenderOptions.<UUID, MicrosoftUser>create()
                .producerProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
                .withKeySerializer(new UUIDSerializer())
                .withValueSerializer(new JsonSerializer<>()));
    }

    @Bean("UserPreference")
    KafkaSender<UUID, Preference> kafkaSenderUserPreference() {
        return KafkaSender.create(SenderOptions.<UUID, Preference>create()
                .producerProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
                .withKeySerializer(new UUIDSerializer())
                .withValueSerializer(new JsonSerializer<>()));
    }
}

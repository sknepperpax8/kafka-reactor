package com.pro.kafka.producer.preference.stream;

import com.pro.kafka.producer.preference.Preference;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.kafka.sender.KafkaSender;

import java.util.UUID;

@Component
public class UserPreferenceProducer {
    private final String TOPIC = "user-preferences";

    private final KafkaSender<UUID, Preference> kafkaSender;

    @Autowired
    public UserPreferenceProducer(KafkaSender<UUID, Preference> kafkaSender) {
        this.kafkaSender = kafkaSender;
    }

    public Flux<Void> send(Preference preference) {
        return kafkaSender.createOutbound()
                .send(Flux.just(preference)
                        .map(p -> new ProducerRecord<>(TOPIC, p.getUserId(), p)))
                .then()
                .doOnError(Throwable::printStackTrace)
                .doOnSuccess(s -> System.out.println("Sends succeeded"))
                .flux();
    }
}

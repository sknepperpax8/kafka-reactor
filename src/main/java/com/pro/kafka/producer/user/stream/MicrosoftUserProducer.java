package com.pro.kafka.producer.user.stream;

import com.pro.kafka.producer.user.MicrosoftUser;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.kafka.sender.KafkaSender;

import java.util.UUID;

@Component("microsoftProducer")
public class MicrosoftUserProducer {
    private final String TOPIC = "microsoft-user";

    private final KafkaSender<UUID, MicrosoftUser> kafkaSender;

    @Autowired
    public MicrosoftUserProducer(KafkaSender<UUID, MicrosoftUser> kafkaSender) {
        this.kafkaSender = kafkaSender;
    }

    public Flux<Void> send(MicrosoftUser user) {
        return kafkaSender.createOutbound()
                .send(Flux.just(user)
                        .map(u -> new ProducerRecord<>(TOPIC, u.getId(), u)))
                .then()
                .doOnError(Throwable::printStackTrace)
                .doOnSuccess(s -> System.out.println("Sends succeeded"))
                .flux();
    }
}

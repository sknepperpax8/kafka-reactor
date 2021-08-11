package com.pro.kafka.producer.rest;

import com.pro.kafka.producer.preference.Preference;
import com.pro.kafka.producer.preference.stream.UserPreferenceProducer;
import com.pro.kafka.producer.user.MicrosoftUser;
import com.pro.kafka.producer.user.stream.MicrosoftUserProducer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/producer")
public class KafkaProducerController {

    final MicrosoftUserProducer microsoftUserProducer;
    final UserPreferenceProducer userPreferenceProducer;

    public KafkaProducerController(MicrosoftUserProducer microsoftUserProducer, UserPreferenceProducer userPreferenceProducer) {
        this.microsoftUserProducer = microsoftUserProducer;
        this.userPreferenceProducer = userPreferenceProducer;
    }

    @PostMapping(path = "/users", consumes = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sendUserToKafka(@RequestBody MicrosoftUser user) {
        microsoftUserProducer.send(user).subscribe();
    }

    @PostMapping(path = "/preferences", consumes = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sendPreferenceToKafka(@RequestBody Preference preference) {
        userPreferenceProducer.send(preference).subscribe();
    }
}

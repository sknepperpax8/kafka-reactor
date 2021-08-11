package com.pro.kafka.producer.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class MicrosoftUser {
    private UUID id;
    private String name;
    private String email;
}

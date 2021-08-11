package com.pro.kafka.producer.preference;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Preference {
    private UUID userId;
    private Boolean ignore;
}

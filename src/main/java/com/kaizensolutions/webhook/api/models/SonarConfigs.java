package com.kaizensolutions.webhook.api.models;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class SonarConfigs {

    @Value("${sonar.token}")
    private String token;

}

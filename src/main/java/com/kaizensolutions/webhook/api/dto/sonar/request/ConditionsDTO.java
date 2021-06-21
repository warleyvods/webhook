package com.kaizensolutions.webhook.api.dto.sonar.request;

import lombok.Data;

@Data
public class ConditionsDTO {

    private String operator;
    private String value;
    private String errorThreshold;

}

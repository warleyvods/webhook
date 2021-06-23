package com.kaizensolutions.webhook.api.dto.sonar.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConditionsDTO {

    private String metric;
    private String operator;
    private String value;
    private String status;
    private String errorThreshold;

}

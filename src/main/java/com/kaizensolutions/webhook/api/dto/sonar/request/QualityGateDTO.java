package com.kaizensolutions.webhook.api.dto.sonar.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QualityGateDTO {

    private String name;
    private String status;
    private List<ConditionsDTO> conditions;

}

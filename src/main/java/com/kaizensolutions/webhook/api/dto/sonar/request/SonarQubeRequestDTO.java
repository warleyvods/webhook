package com.kaizensolutions.webhook.api.dto.sonar.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SonarQubeRequestDTO {

    private String status;
    private ProjectDTO project;
    private BranchDTO branch;
    private QualityGateDTO qualityGate;

}

package com.kaizensolutions.webhook.api.dto.sonar.response;

import lombok.Data;

import java.util.List;

@Data
public class SonarResponseDTO {

    private List<Issues> issues;

}

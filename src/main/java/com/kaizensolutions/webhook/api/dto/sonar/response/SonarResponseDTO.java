package com.kaizensolutions.webhook.api.dto.sonar.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SonarResponseDTO {

    private List<Issues> issues;

}

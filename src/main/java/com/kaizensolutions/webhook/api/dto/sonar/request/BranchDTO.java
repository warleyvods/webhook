package com.kaizensolutions.webhook.api.dto.sonar.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BranchDTO {

    private String name;
    private String type;
    private Boolean isMain;

}

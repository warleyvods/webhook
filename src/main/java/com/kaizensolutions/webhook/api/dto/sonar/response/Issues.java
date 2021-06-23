package com.kaizensolutions.webhook.api.dto.sonar.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Issues {

    private String key;
    private String severity;
    private String component;
    private String line;
    private String message;
    private String author;

}

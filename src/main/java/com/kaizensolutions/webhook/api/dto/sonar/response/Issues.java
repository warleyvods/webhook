package com.kaizensolutions.webhook.api.dto.sonar.response;

import lombok.Data;

@Data
public class Issues {

    private String key;
    private String severity;
    private String component;
    private String line;
    private String mensage;
    private String author;

}

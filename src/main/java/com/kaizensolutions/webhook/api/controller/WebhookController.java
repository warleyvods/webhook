package com.kaizensolutions.webhook.api.controller;

import com.kaizensolutions.webhook.api.dto.sonar.request.SonarQubeRequestDTO;
import com.kaizensolutions.webhook.api.service.WebhookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web")
public class WebhookController {

    private final WebhookService webhookService;

    public WebhookController(WebhookService webhookService) {
        this.webhookService = webhookService;
    }

    @PostMapping("/sonar")
    public ResponseEntity<Void> sonarQubeWebhookRequest(@RequestBody SonarQubeRequestDTO sonarQubeRequestDTO) {
        webhookService.sonarQubeRequestProcess(sonarQubeRequestDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

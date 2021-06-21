package com.kaizensolutions.webhook.api.network;

import com.kaizensolutions.webhook.api.dto.sonar.response.SonarResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "sonar", url = "${sonar.url}")
public interface SonarClient {

    @GetMapping("api/issues/search")
    SonarResponseDTO sonarResponseIssue(@RequestParam String componentKeys,
                                        @RequestParam String statuses,
                                        @RequestParam String types,
                                        @RequestHeader(HttpHeaders.AUTHORIZATION) String token);


}

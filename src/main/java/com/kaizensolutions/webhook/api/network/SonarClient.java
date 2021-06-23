package com.kaizensolutions.webhook.api.network;

import com.kaizensolutions.webhook.api.dto.sonar.response.SonarResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "sonar", url = "${sonar.url}")
public interface SonarClient {

    /**
     * Realiza a consulta diretamente da api do SonarQube
     *
     * @param componentKeys nome do projeto (esse padrao ja vem no payload do webhook de entrada no post.
     * @param statuses separados por virgula ex: OPEN,CONFIRMED,REOPENED
     * @param types tipos das issues, por padrao esta hardcored como BUG
     * @param token basic64 do token com : pois não há senha
     * @return um token de Autorização para consultas na api.
     */
    @GetMapping("api/issues/search")
    SonarResponseDTO sonarResponseIssue(@RequestParam String componentKeys,
                                        @RequestParam String statuses,
                                        @RequestParam String types,
                                        @RequestHeader(HttpHeaders.AUTHORIZATION) String token);


}

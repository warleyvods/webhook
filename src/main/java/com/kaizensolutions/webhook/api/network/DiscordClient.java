package com.kaizensolutions.webhook.api.network;

import com.kaizensolutions.webhook.api.dto.discord.response.DiscordResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "discord", url = "${url.discord.webhook}")
public interface DiscordClient {

    @PostMapping
    void discordPostWebhook(@RequestBody DiscordResponseDTO discordResponseDTO,
                            @RequestHeader(HttpHeaders.USER_AGENT) String agent);

}

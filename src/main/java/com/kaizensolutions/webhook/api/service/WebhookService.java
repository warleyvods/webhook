package com.kaizensolutions.webhook.api.service;

import com.kaizensolutions.webhook.api.dto.discord.response.DiscordResponseDTO;
import com.kaizensolutions.webhook.api.dto.discord.response.EmbedsDTO;
import com.kaizensolutions.webhook.api.network.DiscordNotificationClient;
import com.kaizensolutions.webhook.api.dto.sonar.request.SonarQubeRequestDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WebhookService {

    private final DiscordNotificationClient discordNotificationClient;

    public WebhookService(DiscordNotificationClient discordNotificationClient) {
        this.discordNotificationClient = discordNotificationClient;
    }

    public void sonarQubeRequestProcess(SonarQubeRequestDTO sonarQubeRequestDTO) {
        if (sonarQubeRequestDTO.getStatus().equals("SUCCESS")) {

            DiscordResponseDTO discord = new DiscordResponseDTO();
            EmbedsDTO embedsDTO = new EmbedsDTO();
            List<EmbedsDTO> embedlist = new ArrayList<>();

            embedsDTO.setTitle("O SonarQube identificou um novo bug! :detective:");
            embedsDTO.setColor(13294336);
            embedsDTO.setDescription("Um Bug foi adicionado");
            embedlist.add(embedsDTO);

            discord.setContent(null);
            discord.setEmbeds(embedlist);
            discord.setUsername("SonarQube");
            discord.setAvatar_url("https://user-images.githubusercontent.com/15386828/118396592-e331c880-b658-11eb-8fdc-7426520c691f.png");


            discordNotificationClient.discordPostWebhook(discord, "phz-bot-discordWebhook");
        }

    }

}

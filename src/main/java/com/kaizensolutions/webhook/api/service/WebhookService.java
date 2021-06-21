package com.kaizensolutions.webhook.api.service;

import com.kaizensolutions.webhook.api.dto.discord.response.DiscordResponseDTO;
import com.kaizensolutions.webhook.api.dto.discord.response.EmbedsDTO;
import com.kaizensolutions.webhook.api.dto.sonar.request.ConditionsDTO;
import com.kaizensolutions.webhook.api.dto.sonar.response.Issues;
import com.kaizensolutions.webhook.api.dto.sonar.response.SonarResponseDTO;
import com.kaizensolutions.webhook.api.models.SonarConfigs;
import com.kaizensolutions.webhook.api.network.DiscordNotificationClient;
import com.kaizensolutions.webhook.api.dto.sonar.request.SonarQubeRequestDTO;
import com.kaizensolutions.webhook.api.network.SonarClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

@Service
public class WebhookService {

    private final DiscordNotificationClient discordNotificationClient;
    private final SonarClient sonarClient;
    private final SonarConfigs sonarConfigs;

    public WebhookService(DiscordNotificationClient discordNotificationClient, SonarClient sonarClient, SonarConfigs sonarConfigs) {
        this.discordNotificationClient = discordNotificationClient;
        this.sonarClient = sonarClient;
        this.sonarConfigs = sonarConfigs;
    }

    public void sonarQubeRequestProcess(SonarQubeRequestDTO sonarQubeRequestDTO) {
        if (sonarQubeRequestDTO.getQualityGate().getStatus().equals("ERROR")) {

            DiscordResponseDTO discord = new DiscordResponseDTO();
            List<EmbedsDTO> embedlist = new ArrayList<>();
            int bugs = 0;

            bugs = getBugs(sonarQubeRequestDTO, bugs);

            SonarResponseDTO sonarResponse = getBug(sonarQubeRequestDTO);

            EmbedsDTO embedsDTO = new EmbedsDTO();
            embedsDTO.setColor(13294336);
            if (bugs > 1) {

                embedlist.add(new EmbedsDTO("O SonarQube identificou " + bugs + " novos BUG's! :detective:", "Veja os bugs abaixo:", 13294336));

                List<Issues> issues = sonarResponse.getIssues();
                for (int i = 0; i < issues.size(); i++) {
                    Issues issue = issues.get(i);
                    String text = "\n" + (i + 1) + "º" + " Bug\n" + " Classe: " + "**" + issue.getComponent().replaceAll("^(.*[\\\\\\/])", "") + "**"
                            + "\n" + "Linha: " + "**" + issue.getLine() + "**" + "\n" + "Autor do Bug: " + "<@" + authors().get(issue.getAuthor()) + ">" +
                            "\n```css\n Favor corrigir o bug o mais rápido possível! \n```";

                    embedlist.add(new EmbedsDTO("Bug " + (i + 1), text, 13294336));
                }

            } else {
                embedsDTO.setTitle("O SonarQube identificou " + bugs + " novo BUG! :detective:");
                embedsDTO.setDescription(bugs + "foi adicionado");
            }

            discord.setContent(null);
            discord.setEmbeds(embedlist);
            discord.setUsername("SonarQube - Kaizen Solutions");
            discord.setAvatar_url("https://user-images.githubusercontent.com/15386828/118396592-e331c880-b658-11eb-8fdc-7426520c691f.png");


            discordNotificationClient.discordPostWebhook(discord, "warley-bot-discordWebhook");
        }

    }

    private int getBugs(SonarQubeRequestDTO sonarQubeRequestDTO, int bugs) {
        for (ConditionsDTO condition : sonarQubeRequestDTO.getQualityGate().getConditions()) {
            if (condition.getMetric().equals("new_bugs") && condition.getStatus().equals("ERROR")) {
                bugs = Integer.parseInt(condition.getValue());
            }
        }
        return bugs;
    }

    private SonarResponseDTO getBug(SonarQubeRequestDTO sonarQubeRequestDTO) {
        return sonarClient.sonarResponseIssue(sonarQubeRequestDTO.getProject().getKey(),
                "OPEN,CONFIRMED,REOPENED",
                "BUG",
                sonarToken());
    }

    private String sonarToken() {
        String tokenBase = sonarConfigs.getToken() + ":";
        return "Basic " + new String(Base64.getEncoder().encode(tokenBase.getBytes()));
    }

    private HashMap<String, String> authors() {
        HashMap<String, String> autores = new HashMap<>();
        autores.put("camposedipo@gmail.com", "741369513072918528");
        autores.put("claudineycj@gmail.com", "242755398874955776");
        autores.put("danillofortuna@gmail.com", "722507914946019432");
        autores.put("jessiica.l91@gmail.com", "741372627242713180");
        autores.put("lucas.alves.correa.e@gmail.com", "739141517330874398");
        autores.put("talitarodriguessouz@gmail.com", "776092299011948554");
        autores.put("timdao02@hotmail.com", "771542789464129547");
        autores.put("warleyvods@gmail.com", "230886803333316608");
        autores.put("williamcp10@gmail.com", "230887386681311232");
        return autores;
    }

}

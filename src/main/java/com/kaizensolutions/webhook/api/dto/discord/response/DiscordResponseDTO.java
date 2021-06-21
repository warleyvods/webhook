package com.kaizensolutions.webhook.api.dto.discord.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DiscordResponseDTO {

    private String content;
    private List<EmbedsDTO> embeds;
    private String username;
    private String avatar_url;

}

package com.kaizensolutions.webhook.api.dto.discord.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmbedsDTO {

    private String title;
    private String description;
    private Integer color;

}

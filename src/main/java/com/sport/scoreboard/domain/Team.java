package com.sport.scoreboard.domain;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Team {

    private String teamName;
    private String teamCountry;

}

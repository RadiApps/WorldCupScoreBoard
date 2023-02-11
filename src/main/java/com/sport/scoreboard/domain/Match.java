package com.sport.scoreboard.domain;

import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Match {
    private Team homeTeam;
    private Team awayTeam;
    private int homeTeamScore;
    private int awayTeamScore;
    private Date dateTime;
    private MatchStatus matchStatus;

}

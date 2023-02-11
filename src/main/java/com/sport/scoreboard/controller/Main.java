package com.sport.scoreboard.controller;

import com.sport.scoreboard.domain.Match;
import com.sport.scoreboard.domain.Team;

import javax.xml.bind.ValidationException;
import java.util.Calendar;

public class Main {
    public static void main(String[] args){
        GameController controller=new GameController();
        Team ahlyTeam=Team.builder().teamName("Al Ahly").teamCountry("Egypt").build();
        Team zamalek=Team.builder().teamName("Zamalek").teamCountry("Egypt").build();
        Team real=Team.builder().teamName("Real Madrid").teamCountry("Spain").build();

        Match match1=Match.builder().homeTeam(ahlyTeam).awayTeam(zamalek).homeTeamScore(0).awayTeamScore(0).dateTime(Calendar.getInstance().getTime()).build();
        Match match2=Match.builder().homeTeam(real).awayTeam(zamalek).homeTeamScore(0).awayTeamScore(0).dateTime(Calendar.getInstance().getTime()).build();


        controller.startMatch(match1);
        controller.startMatch(match2);

        controller.endMatch(match1);

        controller.getSummary().stream().forEach(o->{
            System.out.println(o.toString());
        });


    }
}


package com.sport.scoreboard.controller;


import com.sport.scoreboard.domain.Match;
import com.sport.scoreboard.domain.MatchStatus;
import com.sport.scoreboard.domain.Team;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static  org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class GameTest {
    private GameController gameController;
    private Team team1,team2;
    private Match match;


    @Before
    public void setUp(){
        gameController = new GameController();
        team1=Team.builder().teamName("Al Ahly").teamCountry("Egypt").build();
        team2=Team.builder().teamName("Zamalek").teamCountry("Egypt").build();
        match=Match.builder().homeTeam(team1).awayTeam(team2).homeTeamScore(0).awayTeamScore(0).dateTime(Calendar.getInstance().getTime()).build();
    }

    @Test
    public void isMatchStarted(){
        gameController.startMatch(match);
        assertEquals(MatchStatus.STARTED,match.getMatchStatus());
    }
    @Test
    public void isMatchFinished(){
        gameController.startMatch(match);
        gameController.endMatch(match);
        assertEquals(MatchStatus.FINISHED,match.getMatchStatus());
    }
    @Test
    public void getSummary(){
        assertTrue(gameController.getSummary().size() <= 0);
    }
}

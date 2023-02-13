package com.sport.scoreboard.controller;


import com.sport.scoreboard.domain.Match;
import com.sport.scoreboard.domain.MatchStatus;
import com.sport.scoreboard.domain.Team;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.ValidationException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static  org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class GameTest {
    private GameController gameController;
    private Team team1,team2,team3,team4;
    private Match match1,match2,match3;
    private Set<Match> matchs;

    @Before
    public void setUp(){
        gameController = new GameController();
        team1=Team.builder().teamName("Al Ahly").teamCountry("Egypt").build();
        team2=Team.builder().teamName("FC Barcelona").teamCountry("Spain").build();
        team3=Team.builder().teamName("Real Madrid").teamCountry("Spain").build();
        team4=Team.builder().teamName("Malaga F.C").teamCountry("Spain").build();
        match1=Match.builder().homeTeam(team1).awayTeam(team2).homeTeamScore(0).awayTeamScore(0)
                .dateTime(Calendar.getInstance().getTime()).matchStatus(MatchStatus.NOT_STARTED).build();
        match2=Match.builder().homeTeam(team3).awayTeam(team4).homeTeamScore(0).awayTeamScore(0)
                .dateTime(Calendar.getInstance().getTime()).matchStatus(MatchStatus.NOT_STARTED).build();
        match3=Match.builder().homeTeam(team1).awayTeam(team4).homeTeamScore(0).awayTeamScore(0)
                .dateTime(Calendar.getInstance().getTime()).matchStatus(MatchStatus.NOT_STARTED).build();
        matchs =new HashSet<Match>();

    }

    @Test
    public void isMatchStarted(){
        gameController.startMatch(match1);
        assertEquals(MatchStatus.STARTED,match1.getMatchStatus());
    }
    @Test
    public void isMatchFinished(){
        gameController.startMatch(match1);
        gameController.endMatch(match1);
        assertEquals(MatchStatus.FINISHED,match1.getMatchStatus());
    }
    @Test
    public void isMatchNotStarted(){
        assertEquals(MatchStatus.NOT_STARTED,match1.getMatchStatus());
    }
    @Test
    public void canTeamPlayTwiceAtSameTime(){
        gameController.startMatch(match1);
        gameController.startMatch(match1);
        assertEquals(1,gameController.getAllLiveMatchs().size());
    }
    @Test
    public void canTeamPlayInDifferentDays(){
        gameController.startMatch(match1);
        gameController.endMatch(match1);
        gameController.startMatch(match3);
        gameController.endMatch(match3);

        assertEquals(2,gameController.getAllSummaryMatchs().size());
    }
    @Test
    public void isTeamWin(){
        gameController.startMatch(match1);
        gameController.updateMatchScore(match1,2,1);
        gameController.endMatch(match1);

        Match m=gameController.getAllSummaryMatchs().iterator().next();
        assertTrue(m.getHomeTeamScore() > m.getAwayTeamScore());
    }
    @Test
    public  void isTeamLose(){
        gameController.startMatch(match1);
        gameController.updateMatchScore(match1,0,3);
        gameController.endMatch(match1);

        Match m=gameController.getAllSummaryMatchs().iterator().next();
        assertFalse(m.getHomeTeamScore() > m.getAwayTeamScore());
    }
    @Test
    public void isSummaryOrderedByTotalScoreAndMostRecentlyAdded(){
        gameController.startMatch(match1);
        gameController.updateMatchScore(match1,2,3);
        gameController.endMatch(match1);

        match2.setDateTime(Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        gameController.startMatch(match2);
        gameController.updateMatchScore(match2,4,1);
        gameController.endMatch(match2);

        Set<Match> expected =new HashSet<Match>();
        expected.add(match2);
        expected.add(match1);
        matchs =gameController.getSummaryOrdered();

        assertTrue(matchs.containsAll(expected));
        assertEquals(expected,matchs);

    }
}

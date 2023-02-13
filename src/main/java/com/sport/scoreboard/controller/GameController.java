package com.sport.scoreboard.controller;

import com.sport.scoreboard.domain.Match;
import com.sport.scoreboard.domain.MatchStatus;
import com.sport.scoreboard.service.LiveScoreboard;
import com.sport.scoreboard.service.SummaryScoreboard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Set;

public class GameController  {
    Logger logger= LoggerFactory.getLogger(GameController.class);
    private final LiveScoreboard liveScoreboard;
    private final SummaryScoreboard summaryScoreboard;

    public GameController(){
        liveScoreboard = new LiveScoreboard();
        summaryScoreboard= new SummaryScoreboard();
    }

    public void startMatch(Match match) {
        try {
            if (!liveScoreboard.checkTeamPlay(match.getHomeTeam()) && !liveScoreboard.checkTeamPlay(match.getAwayTeam())){
                match.setMatchStatus(MatchStatus.STARTED);
                liveScoreboard.addMatch(match);
                logger.info("Match has been started successfully");
            }else {
                throw new ValidationException("Home team or Away team is already play");
            }
        } catch (ValidationException e) {
            logger.info("Home team or Away team is already play",e.getMessage());
        }

    }
    public void endMatch(Match match) {
        try {
            Match liveMatch = liveScoreboard.getByHomeTeamAndAwayTeam(match.getHomeTeam(),match.getAwayTeam())
                    .orElseThrow(()->new ValidationException("Match is Not live"));
            liveScoreboard.finishMatch(liveMatch);

            liveMatch.setMatchStatus(MatchStatus.FINISHED);
            summaryScoreboard.addMatch(liveMatch);

            logger.info("Match ended");
        } catch (ValidationException e) {
            logger.info("Match is Not live",e.getMessage());
        }
    }
    public Set<Match> getSummaryOrdered(){
        return summaryScoreboard.getSummaryOrderedByTotalScoreAndDateDesc();
    }
    public void updateMatchScore(Match updatedMatch,int homeTeamScore,int awayTeamScore){
        try {

        Match liveMatch = liveScoreboard.getByHomeTeamAndAwayTeam(updatedMatch.getHomeTeam(),updatedMatch.getAwayTeam())
                .orElseThrow(()->new ValidationException("Match is Not live to update the score"));

        liveMatch.setHomeTeamScore(homeTeamScore);
        liveMatch.setAwayTeamScore(awayTeamScore);
        logger.info("Match score has been updated");

        } catch (ValidationException e) {
            logger.info("Match is Not live to update score",e.getMessage());
        }
    }
    public List<Match> getAllLiveMatchs(){
        return liveScoreboard.getAll();
    }
    public List<Match> getAllSummaryMatchs(){
        return summaryScoreboard.getAll();
    }
}

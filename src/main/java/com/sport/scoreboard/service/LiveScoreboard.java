package com.sport.scoreboard.service;

import com.sport.scoreboard.domain.Match;
import com.sport.scoreboard.domain.Team;

import java.util.*;
import java.util.stream.Collectors;

public class LiveScoreboard implements  ScoreBoardService{

    private final List<Match> liveMatches=new ArrayList<Match>();

    @Override
    public List<Match> getAll() {
        return liveMatches;
    }

    @Override
    public List<Match> getByTeam(Team team) {
         return liveMatches.stream().filter(o->o.getAwayTeam().equals(team)||o.getHomeTeam().equals(team)).collect(Collectors.toList());
    }

    @Override
    public Optional<Match> getByHomeTeamAndAwayTeam(Team homeTeam, Team awayTeam) {
        return liveMatches.stream().filter(o->o.getHomeTeam().equals(homeTeam) && o.getAwayTeam().equals(awayTeam)).findFirst();
    }

    @Override
    public void addMatch(Match match) {
        liveMatches.add(match);
    }
    public void finishMatch(Match match){
        for (Iterator<Match> iterator = liveMatches.iterator(); iterator.hasNext();) {
            Match m =  iterator.next();
            if (m.getHomeTeam().equals(match.getHomeTeam())&&m.getAwayTeam().equals(match.getAwayTeam())) {
                iterator.remove();
            }
        }
    }

    public  boolean checkTeamPlay(Team team){
        List<Match> matches= liveMatches.stream().filter(o->o.getAwayTeam().equals(team)||o.getHomeTeam().equals(team)).collect(Collectors.toList());
        if(matches.size() > 0 )
            return true;
        else return false;
    }

}

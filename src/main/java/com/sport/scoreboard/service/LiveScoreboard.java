package com.sport.scoreboard.service;

import com.sport.scoreboard.domain.Match;
import com.sport.scoreboard.domain.Team;

import java.util.*;
import java.util.stream.Collectors;

public class LiveScoreboard implements  ScoreBoardService{

    private final Set<Match> liveMatches=new HashSet<Match>();

    @Override
    public Set<Match> getAll() {
        return liveMatches;
    }

    @Override
    public Set<Match> getByTeam(Team team) {
         return liveMatches.stream().filter(o->o.getAwayTeam().equals(team)||o.getHomeTeam().equals(team)).collect(Collectors.toSet());
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
        liveMatches.remove(match);
    }

    public  boolean checkTeamPlay(Team team){
        List<Match> matches= liveMatches.stream().filter(o->o.getAwayTeam().equals(team)||o.getHomeTeam().equals(team)).collect(Collectors.toList());
        if(matches.size() > 0 )
            return true;
        else return false;
    }

}

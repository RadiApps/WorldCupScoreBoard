package com.sport.scoreboard.service;

import com.sport.scoreboard.domain.Match;
import com.sport.scoreboard.domain.Team;
import com.sport.scoreboard.utils.ComparatorMatch;

import java.util.*;
import java.util.stream.Collectors;

public class SummaryScoreboard implements ScoreBoardService {
    private final List<Match> summaryMatches=new ArrayList<Match>();

    @Override
    public List<Match> getAll() {
        return summaryMatches;
    }

    @Override
    public List<Match> getByTeam(Team team) {
        return summaryMatches.stream().filter(o->o.getAwayTeam().equals(team)||o.getHomeTeam().equals(team)).collect(Collectors.toList());
    }

    @Override
    public Optional<Match> getByHomeTeamAndAwayTeam(Team homeTeam, Team awayTeam) {
        return summaryMatches.stream().filter(o->o.getHomeTeam().equals(homeTeam) && o.getAwayTeam().equals(awayTeam)).findFirst();
    }

    @Override
    public void addMatch(Match match) {
        summaryMatches.add(match);
    }

    public List<Match> getSummaryOrderedByTotalScore(){
        return summaryMatches.stream().sorted(Comparator.comparingInt(o->o.getHomeTeamScore()+o.getAwayTeamScore())).collect(Collectors.toList());
    }
    public Set<Match> getSummaryOrderedByTotalScoreAndDateDesc(){
       return summaryMatches.stream().sorted((Comparator<Match>) new ComparatorMatch()).collect(Collectors.toSet());
    }
}

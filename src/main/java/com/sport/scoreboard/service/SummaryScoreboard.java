package com.sport.scoreboard.service;

import com.sport.scoreboard.domain.Match;
import com.sport.scoreboard.domain.Team;
import com.sport.scoreboard.utils.ComparatorMatch;

import java.util.*;
import java.util.stream.Collectors;

public class SummaryScoreboard implements ScoreBoardService {
    private final Set<Match> summaryMatches=new HashSet<Match>();

    @Override
    public Set<Match> getAll() {
        return summaryMatches;
    }

    @Override
    public Set<Match> getByTeam(Team team) {
        return summaryMatches.stream().filter(o->o.getAwayTeam().equals(team)||o.getHomeTeam().equals(team)).collect(Collectors.toSet());
    }

    @Override
    public Optional<Match> getByHomeTeamAndAwayTeam(Team homeTeam, Team awayTeam) {
        return summaryMatches.stream().filter(o->o.getHomeTeam().equals(homeTeam) && o.getAwayTeam().equals(awayTeam)).findFirst();
    }

    @Override
    public void addMatch(Match match) {
        summaryMatches.add(match);
    }

    public Set<Match> getSummaryOrderedByTotalScore(){
        return summaryMatches.stream().sorted(Comparator.comparingInt(o->o.getHomeTeamScore()+o.getAwayTeamScore())).collect(Collectors.toSet());
    }
    public Set<Match> getSummaryOrderedByTotalScoreAndDateDesc(){
        summaryMatches.stream().sorted((Comparator<Match>) new ComparatorMatch()).collect(Collectors.toSet());
        return summaryMatches;
    }
}

package com.sport.scoreboard.service;

import com.sport.scoreboard.domain.Match;
import com.sport.scoreboard.domain.Team;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ScoreBoardService {
   List<Match> getAll();
   List<Match> getByTeam(Team team);
   Optional<Match> getByHomeTeamAndAwayTeam(Team homeTeam, Team awayTeam);
   void addMatch(Match match);

}

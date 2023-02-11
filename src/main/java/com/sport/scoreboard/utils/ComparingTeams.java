package com.sport.scoreboard.utils;

import com.sport.scoreboard.domain.Team;

import java.util.Comparator;

public class ComparingTeams implements Comparator<Team> {
    @Override
    public int compare(Team o1, Team o2) {
        if(o1.getTeamName() == null || o2.getTeamName() == null) {
            throw new IllegalArgumentException("Unnamed team found in the system");
        }
        return o1.getTeamName().compareToIgnoreCase(o2.getTeamName());
    }
}

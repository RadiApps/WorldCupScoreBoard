package com.sport.scoreboard.utils;

import com.sport.scoreboard.domain.Match;

import java.util.Comparator;

public class ComparatorMatch implements Comparator<Match>{

    @Override
    public int compare(Match o1, Match o2) {
        if(getMatchScore(o1) > getMatchScore(o2))
            return 1;
        else if (getMatchScore(o1) < getMatchScore(o2))
            return -1;
        else {
           return o1.getDateTime().compareTo(o2.getDateTime());
        }
    }
    private int getMatchScore(Match m){
        return m.getHomeTeamScore()+m.getAwayTeamScore();
    }

}

package com.yhoboo.ipldashboard.repository;

import com.yhoboo.ipldashboard.model.Match;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MatchRepostory extends CrudRepository<Match, Long> {

    List<Match> getByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2, Pageable pageable);

    @Query("SELECT m from Match m WHERE (m.team1 = :teamName OR m.team2 = :teamName)"
            + " AND m.date BETWEEN :dateStart AND :dateEnd ORDER BY date DESC")
    List<Match> getMatchesByTeamBetweenDates(
            @Param("teamName") String teamName1, @Param("dateStart") LocalDate dateStart,
            @Param("dateEnd") LocalDate dateEnd);

    @Deprecated
    List<Match> getByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(
            String teamName1, LocalDate date1, LocalDate date2,
            String teamName2, LocalDate date3, LocalDate date4);

    default List<Match> findLatestMatchesByTeam(String teamName, int count) {
        return getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, PageRequest.of(0, count));
    }

}

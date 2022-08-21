package com.yhoboo.ipldashboard.controller;

import com.yhoboo.ipldashboard.model.Match;
import com.yhoboo.ipldashboard.model.Team;
import com.yhoboo.ipldashboard.repository.MatchRepostory;
import com.yhoboo.ipldashboard.repository.TeamRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
public class TeamController {

    private TeamRepository teamRepository;
    private MatchRepostory matchRepostory;

    public TeamController(TeamRepository teamRepository, MatchRepostory matchRepostory) {
        this.teamRepository = teamRepository;
        this.matchRepostory = matchRepostory;
    }

    @GetMapping("/team")
    public Iterable<Team> getAllTeam() {
        return this.teamRepository.findAll();
    }

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName) {
        Team team = this.teamRepository.findByTeamName(teamName);
        team.setMatches(this.matchRepostory.findLatestMatchesByTeam(teamName, 4));

        return team;
    }

    @GetMapping("/team/{teamName}/matches")
    public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year + 1, 1, 1);
        return this.matchRepostory.getMatchesByTeamBetweenDates(teamName, startDate, endDate);
    }

}

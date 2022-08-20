package com.yhoboo.ipldashboard.controller;

import com.yhoboo.ipldashboard.model.Team;
import com.yhoboo.ipldashboard.repository.MatchRepostory;
import com.yhoboo.ipldashboard.repository.TeamRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

    private TeamRepository teamRepository;
    private MatchRepostory matchRepostory;

    public TeamController(TeamRepository teamRepository, MatchRepostory matchRepostory) {
        this.teamRepository = teamRepository;
        this.matchRepostory = matchRepostory;
    }

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName) {
        Team team = this.teamRepository.findByTeamName(teamName);
        team.setMatches(this.matchRepostory.findLatestMatchesByTeam(teamName, 4));

        return team;
    }

}

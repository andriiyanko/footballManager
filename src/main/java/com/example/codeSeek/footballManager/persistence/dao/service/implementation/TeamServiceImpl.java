package com.example.codeSeek.footballManager.persistence.dao.service.implementation;

import com.example.codeSeek.footballManager.persistence.dao.repositories.TeamRepository;
import com.example.codeSeek.footballManager.persistence.dao.service.interfaces.TeamService;
import com.example.codeSeek.footballManager.persistence.model.Team;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    private TeamRepository teamRepository;

    @Autowired
    public void setTeamRepository(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Team> findTeamByName(String name) {
        return teamRepository.findTeamByName(name);
    }

    @Override
    public List<Team> findTeamByCountry(String country) {
        return teamRepository.findTeamByCountry(country);
    }

    @Override
    public Optional<Team> findTeamById(Integer id) {
        return teamRepository.findById(id);
    }

    @Override
    public List<Team> findAllTeams() {
        return Lists.newArrayList(teamRepository.findAll());
    }

    @Override
    public List<Team> findTeamByNameAndCountry(String name, String country) {
        return teamRepository.findTeamByNameAndCountry(name,country);
    }

    @Override
    public Team addTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public void removeTeamById(Integer id) {
        teamRepository.deleteById(id);
    }
}

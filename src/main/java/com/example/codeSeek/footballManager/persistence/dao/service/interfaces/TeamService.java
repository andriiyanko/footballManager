package com.example.codeSeek.footballManager.persistence.dao.service.interfaces;

import com.example.codeSeek.footballManager.persistence.model.Team;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    List<Team> findTeamByName (String name);
    List<Team> findTeamByCountry(String country);
    Optional<Team> findTeamById(Integer id);
    List<Team> findTeamByNameAndCountry(String name, String country);
    List<Team> findAllTeams();
    Team addTeam(Team team);
    void removeTeamById(Integer id);


}

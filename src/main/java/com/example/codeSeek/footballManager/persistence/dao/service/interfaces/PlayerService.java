package com.example.codeSeek.footballManager.persistence.dao.service.interfaces;

import com.example.codeSeek.footballManager.persistence.model.Player;
import com.example.codeSeek.footballManager.persistence.model.Team;

import java.util.List;
import java.util.Optional;

public interface PlayerService {
    List<Player> findPlayersByFirstName(String name);
    List<Player> findPlayersByTeamName(String name);
    List<Player> findAllPlayers();
    List<Player> findPLayerByFirstNameAndLastName(String firstName, String lastName);
    Player addPlayer(Player player);
    void removePlayerById(Integer id);
    Optional<Player> findPlayerById(Integer id);

}

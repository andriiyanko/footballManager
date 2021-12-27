package com.example.codeSeek.footballManager.persistence.dao.service.implementation;

import com.example.codeSeek.footballManager.persistence.dao.repositories.PlayerRepository;
import com.example.codeSeek.footballManager.persistence.dao.service.interfaces.PlayerService;
import com.example.codeSeek.footballManager.persistence.model.Player;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {
    private PlayerRepository playerRepository;

    @Autowired
    public void setPlayerRepository(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public List<Player> findPlayersByFirstName(String name) {
        return playerRepository.findPlayersByFirstName(name);
    }

    @Override
    public List<Player> findPlayersByTeamName(String name) {
        return playerRepository.findPlayersByTeamName(name);
    }

    @Override
    public List<Player> findAllPlayers() {
        return Lists.newArrayList(playerRepository.findAll());
    }

    @Override
    public List<Player> findPLayerByFirstNameAndLastName(String firstName, String lastName) {
        return playerRepository.findPlayerByFirstNameAndLastName(firstName,lastName);
    }

    @Override
    public Player addPlayer(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public void removePlayerById(Integer id) {
        playerRepository.deleteById(id);
    }

    @Override
    public Optional<Player> findPlayerById(Integer id) {
        return playerRepository.findById(id);
    }
}


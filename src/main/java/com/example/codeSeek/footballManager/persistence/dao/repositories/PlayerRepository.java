package com.example.codeSeek.footballManager.persistence.dao.repositories;

import com.example.codeSeek.footballManager.persistence.model.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PlayerRepository extends CrudRepository<Player,Integer> {
    List<Player> findPlayersByFirstName (String name);
    List<Player> findPlayersByTeamName (String name);
    List<Player> findPlayerByFirstNameAndLastName (String firstName, String lastName);

}

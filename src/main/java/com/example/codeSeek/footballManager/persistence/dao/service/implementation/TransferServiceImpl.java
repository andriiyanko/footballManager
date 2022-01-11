package com.example.codeSeek.footballManager.persistence.dao.service.implementation;

import com.example.codeSeek.footballManager.persistence.dao.repositories.PlayerRepository;
import com.example.codeSeek.footballManager.persistence.dao.repositories.TeamRepository;
import com.example.codeSeek.footballManager.persistence.dao.repositories.TransferRepository;
import com.example.codeSeek.footballManager.persistence.dao.service.interfaces.TransferService;
import com.example.codeSeek.footballManager.persistence.model.Player;
import com.example.codeSeek.footballManager.persistence.model.Team;
import com.example.codeSeek.footballManager.persistence.model.Transfer;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TransferServiceImpl implements TransferService {
    private TransferRepository transferRepository;
    private TeamRepository teamRepository;
    private PlayerRepository playerRepository;

    @Autowired
    public void setTeamRepository(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Autowired
    public void setPlayerRepository(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Autowired
    public void setTransferRepository(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @Override
    public Optional<Transfer> findTransferById(Integer id) {
        return transferRepository.findById(id);
    }

    @Override
    public List<Transfer> findAllTransfers() {
        return Lists.newArrayList(transferRepository.findAll());
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Transfer makeTransfer(String firstName, String lastName, String transferToTeam, double commission, LocalDate transferDate) {
        Transfer transfer = new Transfer();
        Team toTeam = teamRepository.findTeamByName(transferToTeam).stream().findFirst().orElseThrow(NoSuchElementException::new);

        Player player = playerRepository.findPlayerByFirstNameAndLastName(firstName, lastName).stream().findFirst().orElseThrow(NoSuchElementException::new);
        Team oldTeam = player.getTeam();

        int transferCost = player.getExperience() * 100000 / player.getAge();
        int finalCommission = (int) (commission * transferCost);
        int transferSum = transferCost + finalCommission;

        toTeam.setBalance(toTeam.getBalance() - transferSum);
        oldTeam.setBalance(oldTeam.getBalance() + transferSum);

        player.setTeam(toTeam);
        toTeam.addPlayer(player);
        oldTeam.removePlayer(player);

        transfer.setPlayer(player);
        transfer.setTransferDate(transferDate);
        transfer.setTeamTo(toTeam);

        toTeam.addTransfer(transfer);
        oldTeam.addTransfer(transfer);

        return transfer;
    }

    @Override
    public Transfer addTransfer(Transfer transfer) {
        return transferRepository.save(transfer);
    }
}

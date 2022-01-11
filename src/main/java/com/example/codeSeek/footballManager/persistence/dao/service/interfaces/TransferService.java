package com.example.codeSeek.footballManager.persistence.dao.service.interfaces;

import com.example.codeSeek.footballManager.persistence.model.Transfer;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TransferService {
    Optional<Transfer> findTransferById(Integer id);
    List<Transfer> findAllTransfers();
    Transfer addTransfer (Transfer transfer);
    Transfer makeTransfer (String firstName, String lastName, String transferToTeam, double commission, LocalDate transferDate);
}

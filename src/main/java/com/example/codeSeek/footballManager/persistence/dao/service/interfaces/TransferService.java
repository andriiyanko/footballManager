package com.example.codeSeek.footballManager.persistence.dao.service.interfaces;

import com.example.codeSeek.footballManager.persistence.model.Transfer;

import java.util.List;
import java.util.Optional;

public interface TransferService {
    Optional<Transfer> findTransferById(Integer id);
    List<Transfer> findAllTransfers();
    Transfer doTransfer(Transfer transfer);
}

package com.example.codeSeek.footballManager.persistence.dao.service.implementation;

import com.example.codeSeek.footballManager.persistence.dao.repositories.TransferRepository;
import com.example.codeSeek.footballManager.persistence.dao.service.interfaces.TransferService;
import com.example.codeSeek.footballManager.persistence.model.Transfer;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransferServiceImpl implements TransferService {
    private TransferRepository transferRepository;

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
    public Transfer doTransfer(Transfer transfer) {
        return transferRepository.save(transfer);
    }
}

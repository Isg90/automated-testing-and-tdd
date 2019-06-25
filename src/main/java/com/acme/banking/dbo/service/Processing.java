package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.repo.AccountRepository;
import com.acme.banking.dbo.repo.ClientRepository;

import javax.jnlp.PersistenceService;
import java.util.Collection;
import java.util.UUID;

//TODO impl
public class Processing {
    private TxLog txLog;
    private ClientRepository clientRepo;
    private AccountRepository accountRepo;

    public Processing(TxLog txLog) {
        this.txLog = txLog;
    }

    public Processing(TxLog txLog, ClientRepository clientRepo, AccountRepository accRepo) {
        this.txLog = txLog;
        this.clientRepo = clientRepo;
        this.accountRepo = accRepo;
    }

    public UUID createClient(String name) {
        return clientRepo.create(name);
    }

    public Collection<Account> getAccountsByClientId(UUID clientId) {
        return accountRepo.getAccountsByClientId(clientId);
    }

    public void transfer(double amount, UUID fromAccountId, UUID toAccountId) {
        Account accountFrom = accountRepo.findById(fromAccountId);
        Account accountTo = accountRepo.findById(toAccountId);

        if (accountFrom.getAmount() < amount)
            throw new IllegalStateException("Amount of account with id " + fromAccountId + " less then transfer amount!");

        accountFrom.addAmount(-amount);
        accountTo.addAmount(amount);
    }

    public void cash(double amount, UUID fromAccountId) {
        txLog.log(amount, fromAccountId);
    }
}

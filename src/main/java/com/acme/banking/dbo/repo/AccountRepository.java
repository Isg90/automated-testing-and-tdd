package com.acme.banking.dbo.repo;

import com.acme.banking.dbo.domain.Account;

import java.util.Collection;
import java.util.UUID;

public interface AccountRepository {
    Account findById(UUID accountId);

    Collection<Account> getAccountsByClientId(UUID clientId);
}

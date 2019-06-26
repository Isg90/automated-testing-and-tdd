package com.acme.banking.dbo.builders;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.SavingAccount;

import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountBuilder {

    UUID id;
    double amount;

    public AccountBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public AccountBuilder withAmount(double amount){
        this.amount = amount;
        return this;
    }

    public Account build() {
        Account mockAccount = mock(Account.class);
        when(mockAccount.getAmount()).thenReturn(amount);
        when(mockAccount.getId()).thenReturn(id);
        return mockAccount;
    }

}

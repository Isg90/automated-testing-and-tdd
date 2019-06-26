package com.acme.banking.dbo.domain;

import java.util.UUID;

public interface Account {
    UUID getId();
    UUID getClientId(); //TODO reference integrity
    double getAmount();
    double addAmount(double amount);
    void setId(UUID id);
    void setAmount(double amount);
}

package com.acme.banking.dbo.repo;

import java.util.UUID;

public interface ClientRepository {
    UUID create(String name);
}

package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.repo.AccountRepository;
import com.acme.banking.dbo.repo.ClientRepository;
import com.acme.banking.dbo.service.Processing;
import com.acme.banking.dbo.service.TxLog;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

public class ProcessingTest {
    private static final double ANY_AMOUNT = 1.0;
    private static final UUID ANY_UUID = UUID.randomUUID();

    private TxLog mockTxLog;
    private Processing sut;
    private AccountRepository mockAccountRepo;
    private ClientRepository mockClientRepo;


    @Before
    public void before() {
        mockTxLog = mock(TxLog.class);
        mockAccountRepo = mock(AccountRepository.class);
        mockClientRepo = mock(ClientRepository.class);
        sut = new Processing(mockTxLog, mockClientRepo, mockAccountRepo);
    }

    @Test
    public void shouldLogTxWithTxLogWhenCacheOperation() {
        sut.cash(ANY_AMOUNT, ANY_UUID);

        verify(mockTxLog, times(1))
                .log(anyDouble(), any(UUID.class));
    }

    @Test
    public void shouldCountAddAmountOneTimeWhenTransferAmountFromOneAccountToAnother() {
        final double testAmount = 100;

        UUID fromAccountUUID = UUID.randomUUID();
        final double FROM_ACCOUNT_AMOUNT = 101;
        UUID toAccountUUID = UUID.randomUUID();
        final double TO_ACCOUNT_AMOUNT = 102;

        Account fromAccountStub = mock(Account.class);
        Account toAccountStub = mock(Account.class);

        when(mockAccountRepo.findById(fromAccountUUID)).thenReturn(fromAccountStub);
        when(mockAccountRepo.findById(toAccountUUID)).thenReturn(toAccountStub);

        when(fromAccountStub.getAmount()).thenReturn(FROM_ACCOUNT_AMOUNT);
        when(toAccountStub.getAmount()).thenReturn(TO_ACCOUNT_AMOUNT);

        sut.transfer(testAmount, fromAccountUUID, toAccountUUID);

        verify(fromAccountStub, times(1)).addAmount(-testAmount);
        verify(toAccountStub, times(1)).addAmount(testAmount);
    }
}

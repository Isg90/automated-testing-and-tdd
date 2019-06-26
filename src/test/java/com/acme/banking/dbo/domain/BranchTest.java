package com.acme.banking.dbo.domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

import static java.util.Collections.EMPTY_SET;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class BranchTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldBranchContainsAccountWhenAccountAddedToBranch() {
        Branch sut = new Branch(EMPTY_SET);
        Account testAccount = mock(Account.class);

        sut.addAccount(testAccount);

        assertTrue(sut.getAccounts().contains(testAccount));
    }

    @Test
    public void shouldThrowExceptionWhenAccountAlreadyExistsInChildBranchAndAddedToParentBranch() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Account already exists in children branch");

        Branch sut = new Branch(EMPTY_SET);
        Account testAccount = mock(Account.class);
        Branch childBranch = new Branch(Arrays.asList(testAccount));

        sut.addChildBranch(childBranch);
        sut.addAccount(testAccount);
    }

}
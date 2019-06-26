package com.acme.banking.dbo.domain;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class BranchTest {

    Branch sut;

    @Before
    public void before() {
        sut = new Branch(new ArrayList<>(0));
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldBranchContainsAccountWhenAccountAddedToBranch() {
        Account testAccount = mock(Account.class);

        sut.addAccount(testAccount);

        assertTrue(sut.getAccounts().contains(testAccount));
    }

    @Test
    public void shouldThrowExceptionWhenAccountAlreadyExistsInChildBranchAndAddedToParentBranch() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Account already exists in children branch");

        Account testAccount = mock(Account.class);
        Branch childBranch = new Branch(Arrays.asList(testAccount));

        sut.addChildBranch(childBranch);
        sut.addAccount(testAccount);
    }

    @Test
    public void shouldChildBranchExistsWhenParentGetBranchesCalled() {
        Branch dummy = new Branch(emptyList());
        sut.addChildBranch(dummy);

        assertTrue(sut.getChildren().contains(dummy));
    }

    @Test
    public void shouldContainsAllAccountsFromParentBranchAndChildrenWhenGetAccountsCalled() {
        Account sutAccount = new SavingAccount();
        Account childAccount = new SavingAccount();
        Branch childBranch = new Branch(new ArrayList<>());
        childBranch.addAccount(childAccount);
        sut.addAccount(sutAccount);
        sut.addChildBranch(childBranch);

        assertTrue(sut.getAccounts().contains(childAccount));
        assertTrue(sut.getAccounts().contains(sutAccount));
    }

}
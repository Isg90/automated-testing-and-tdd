package com.acme.banking.dbo.domain;

import java.util.Collection;
import java.util.HashSet;

import static java.util.Collections.unmodifiableCollection;

public class Branch {
    private Collection<Account> accounts; //TODO impl
    private Collection<Branch> childrenBranch;

    public static final String ACCOUNT_ALREADY_EXISTS_IN_CHILD_BRANCH = "Account already exists in children branch";

    public Branch(Collection<Account> accounts) {
        this.accounts = accounts;
    }

    public Collection<Account> getAccounts() {
        return unmodifiableCollection(accounts);
    }

    public Collection<Branch> getChildren() {
        //TODO impl
        return null;
    }

    public void addAccount(Account account) {
        if (childrenBranch != null)
            for (Branch branch : childrenBranch)
                if( branch.getAccounts().contains(account))
                    throw new IllegalStateException(ACCOUNT_ALREADY_EXISTS_IN_CHILD_BRANCH);
        accounts.add(account);
    }

    public void addChildBranch(Branch childBranch) {
        if (childrenBranch == null)
            childrenBranch = new HashSet<>();

        childrenBranch.add(childBranch);
    }
}

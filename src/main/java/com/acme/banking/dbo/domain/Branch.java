package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import static java.util.Collections.unmodifiableCollection;

public class Branch {
    private Collection<Account> accounts; //TODO impl
    private Collection<Branch> childrenBranch = new ArrayList<>();

    public static final String ACCOUNT_ALREADY_EXISTS_IN_CHILD_BRANCH = "Account already exists in children branch";

    public Branch(Collection<Account> accounts) {
        this.accounts = accounts;
    }

    public Collection<Account> getAccounts() {
        ArrayList accs = new ArrayList<Account>();
        for (Branch branch: childrenBranch)
            accs.addAll(branch.getAccounts());

        accs.addAll(accounts);

        return unmodifiableCollection(accs);
    }

    public Collection<Branch> getChildren() {
        return childrenBranch;
    }

    public void addAccount(Account account) {
        if (childrenBranch != null)
            for (Branch branch : childrenBranch)
                if( branch.getAccounts().contains(account))
                    throw new IllegalStateException(ACCOUNT_ALREADY_EXISTS_IN_CHILD_BRANCH);
        accounts.add(account);
    }

    public void addChildBranch(Branch childBranch) {
        childrenBranch.add(childBranch);
    }
}

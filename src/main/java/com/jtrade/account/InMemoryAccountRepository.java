package com.jtrade.account;
import com.jtrade.exception.AccountNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryAccountRepository implements AccountRepository{
    private final Map<String, Account> accountsRepo = new HashMap<>();

    @Override
    public void save (Account account) {
        if (account == null) throw new AccountNotFoundException("Account cannot be null.");
        if (accountsRepo.containsKey(account.getAccountId())) throw new AccountNotFoundException("Duplicate Account ID: " + account.getAccountId());
        
        accountsRepo.put(account.getAccountId(), account);
    }

    @Override
    public Optional<Account> findById (String accountId) {
        if (accountId == null) throw new NullPointerException("Account ID cannot be null.");
        if (accountId.isBlank()) throw new IllegalArgumentException("Account ID cannot be blank.");
        return Optional.ofNullable(accountsRepo.get(accountId));
    }

    @Override
    public boolean exists (String accountId) {
        if (accountId == null) throw new NullPointerException("Account ID cannot be null.");
        if (accountId.isBlank()) throw new IllegalArgumentException("Account ID cannot be blank.");

        if (accountsRepo.containsKey(accountId)) return true;
        return false;
    }
}

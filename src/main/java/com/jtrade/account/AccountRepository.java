package com.jtrade.account;
import java.util.Optional;

public interface AccountRepository {
    void save(Account account);
    Optional<Account> findById(String accountId);
    boolean exists(String accountId);
}

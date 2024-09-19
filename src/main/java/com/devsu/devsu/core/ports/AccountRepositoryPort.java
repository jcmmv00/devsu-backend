package com.devsu.devsu.core.ports;

import com.devsu.devsu.core.model.Account;
import java.util.Optional;
public interface AccountRepositoryPort {

    String create(Account account);

    Optional<Account> getAccountByAccountNumber(String accountNumber);

    boolean updateAccount(Account account);

	boolean deleteAccount(String accountNumber);
}

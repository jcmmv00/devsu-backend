package com.devsu.devsu.core.services.handlers;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.devsu.devsu.core.exceptions.AccountNotFoundException;
import com.devsu.devsu.core.exceptions.DailyLimitExceeded;
import com.devsu.devsu.core.exceptions.MovementBalanceNotAvailableException;
import com.devsu.devsu.core.model.Account;
import com.devsu.devsu.core.model.Movement;
import com.devsu.devsu.core.model.enums.MovementType;
import com.devsu.devsu.core.ports.MovementRepositoryPort;
import com.devsu.devsu.core.services.AccountService;
import com.devsu.devsu.core.services.MovementService;

import jakarta.transaction.Transactional;

@Service
public class MovementServiceHandler implements MovementService {

    @Value("${limit.withdrawal.daily}")
    private double limitWithdrawalDaily;

    private final MovementRepositoryPort movementRepository;
    private final AccountService accountService;

    public MovementServiceHandler(MovementRepositoryPort movementRepository, AccountService accountService) {
        this.movementRepository = movementRepository;
        this.accountService = accountService;
    }

    @Transactional
    @Override
    public Long createMovement(Movement movement, String accountNumber)
            throws AccountNotFoundException, DailyLimitExceeded, MovementBalanceNotAvailableException {

        Account account = accountService.getAccountByAccountNumber(accountNumber);

        if (movement.getMovementType() == (MovementType.WITHDRAWAL)) {
            if (account.getBalance() - movement.getQuantity() < 0) {
                throw new MovementBalanceNotAvailableException(account.getBalance());
            }
            List<Movement> movements = movementRepository.getMovementsByDateAndType(new Date(),
                    MovementType.WITHDRAWAL, account.getAccountId());
            Double sumWithDrawals = movements.stream().mapToDouble(Movement::getQuantity).sum();

            if (sumWithDrawals >= limitWithdrawalDaily) {
                throw new DailyLimitExceeded();
            }

            account.setBalance(account.getBalance() - movement.getQuantity());
        } else {
            account.setBalance(account.getBalance() + movement.getQuantity());
        }

        movement.setAccount(account);
        movement.setDate(new Date());

        Long idMovement = movementRepository.create(movement);
        accountService.updateAccount(account);
        return idMovement;
    }

    @Override
    public List<Movement> queryMovementsBydateRange(Date dateA, Date dateB, String accountNumber)
            throws AccountNotFoundException {
        Account account = this.accountService.getAccountByAccountNumber(accountNumber);

        return this.movementRepository.getMovementsByRangeDate(dateA, dateB, account.getAccountId());
    }
}

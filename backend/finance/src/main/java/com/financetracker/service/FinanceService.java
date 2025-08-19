package com.financetracker.service;

import com.financetracker.model.Account;
import com.financetracker.model.Transaction;
import com.financetracker.model.User;
import com.financetracker.repository.AccountRepository;
import com.financetracker.repository.TransactionRepository;
import com.financetracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FinanceService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public FinanceService(UserRepository userRepository, AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public User register(User u) {
        return userRepository.save(u);
    }

    public User login(String email, String password) {
        return userRepository.findByEmail(email).filter(u -> u.getPassword().equals(password)).orElse(null);
    }

    public Account addAccount(Account a) {
        return accountRepository.save(a);
    }

    public List<Account> listAccounts(Long userId) {
        return accountRepository.findByUserId(userId);
    }

    public Transaction addTransaction(Transaction t) {
        // update account balance
        Account acc = accountRepository.findById(t.getAccountId()).orElseThrow();
        BigDecimal bal = acc.getBalance();
        if (Boolean.TRUE.equals(t.getIncome())) {
            bal = bal.add(t.getAmount());
        } else {
            bal = bal.subtract(t.getAmount());
        }
        acc.setBalance(bal);
        accountRepository.save(acc);
        return transactionRepository.save(t);
    }

    public List<Transaction> recent(Long userId) {
        return transactionRepository.findTop10ByUserIdOrderByCreatedAtDesc(userId);
    }

    public Map<String, Object> monthlyReport(Long userId, int year, int month) {
        YearMonth ym = YearMonth.of(year, month);
        LocalDateTime start = ym.atDay(1).atStartOfDay();
        LocalDateTime end = ym.atEndOfMonth().atTime(23,59,59);
        List<Transaction> txns = transactionRepository.findByUserIdAndCreatedAtBetween(userId, start, end);

        BigDecimal income = BigDecimal.ZERO;
        BigDecimal expense = BigDecimal.ZERO;
        for (Transaction t : txns) {
            if (Boolean.TRUE.equals(t.getIncome())) income = income.add(t.getAmount());
            else expense = expense.add(t.getAmount());
        }
        Map<String,Object> res = new HashMap<>();
        res.put("income", income);
        res.put("expense", expense);
        res.put("net", income.subtract(expense));
        res.put("count", txns.size());
        return res;
    }
}

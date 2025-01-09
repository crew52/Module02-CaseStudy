package service.account;

import entity.account.Account;

import java.util.List;
import java.util.Map;

public interface AccountService {
    void save(Account account);

    List<Account> findByCustomerId(String customerId);

    void update(Account account);

    void delete(String customerId);

    boolean exists(String customerId);

    Map<String, List<Account>> findAll();

    void clear();
}

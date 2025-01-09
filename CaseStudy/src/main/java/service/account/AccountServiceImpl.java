package service.account;

import entity.account.Account;
import repository.account.AccountRepositoryImpl;

import java.util.List;
import java.util.Map;

public class AccountServiceImpl implements AccountService {
    private final AccountRepositoryImpl accountRepository;

    public AccountServiceImpl() {
        // Khởi tạo AccountRepositoryImpl để tương tác với dữ liệu
        this.accountRepository = new AccountRepositoryImpl();
    }

    @Override
    public void save(Account account) {
        // Kiểm tra và lưu tài khoản thông qua Repository
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null.");
        }
        accountRepository.save(account);
    }

    @Override
    public List<Account> findByCustomerId(String customerId) {
        // Tìm danh sách tài khoản của khách hàng theo customerId
        return accountRepository.findById(customerId);
    }

    @Override
    public void update(Account account) {
        // Cập nhật tài khoản qua Repository
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null.");
        }
        accountRepository.update(account);
    }

    @Override
    public void delete(String customerId) {
        // Xóa tất cả tài khoản của khách hàng theo customerId
        if (customerId == null || customerId.isEmpty()) {
            throw new IllegalArgumentException("CustomerId cannot be null or empty.");
        }
        accountRepository.delete(customerId);
    }

    @Override
    public boolean exists(String customerId) {
        // Kiểm tra xem khách hàng có tài khoản hay không
        return accountRepository.exists(customerId);
    }

    @Override
    public Map<String, List<Account>> findAll() {
        // Lấy tất cả tài khoản của tất cả khách hàng
        return accountRepository.findAll();
    }

    @Override
    public void clear() {
        // Xóa tất cả tài khoản trong hệ thống
        accountRepository.clear();
    }
}

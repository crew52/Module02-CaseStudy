package repository.account;

import entity.account.Account;

import java.util.List;
import java.util.Map;

public interface AccountRepository {
    // Lưu một tài khoản vào repository
    void save(Account account);

    // Tìm tài khoản theo customerId
    List<Account> findById(String customerId);

    // Cập nhật tài khoản trong repository
    void update(Account account);

    // Xóa tài khoản theo customerId
    void delete(String customerId);

    // Kiểm tra sự tồn tại của tài khoản theo customerId
    boolean exists(String customerId);

    // Trả về tất cả các tài khoản của tất cả khách hàng
    Map<String, List<Account>> findAll();

    void clear();
}

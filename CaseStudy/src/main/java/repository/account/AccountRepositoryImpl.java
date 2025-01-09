package repository.account;

import entity.account.Account;
import utility.FileManagerImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AccountRepositoryImpl implements AccountRepository {

    private static final String FILE_NAME = "accounts.dat";
    private final FileManagerImpl<Account> fileManager = new FileManagerImpl<>();
    private final Map<String, List<Account>> accounts;

    public AccountRepositoryImpl() {
        this.accounts = loadAccountsFromFile();
    }

    private Map<String, List<Account>> loadAccountsFromFile() {
        List<Account> accountList = fileManager.readFromFile(FILE_NAME);
        if (accountList == null || accountList.isEmpty()) {
            return new HashMap<>();
        }

        // Chuyển danh sách Account thành Map, sử dụng customerId là key
        return accountList.stream()
                .collect(Collectors.groupingBy(account -> account.getOwner().getId()));
    }

    @Override
    public void save(Account account) {
        if (account != null) {
            // Thêm Account vào danh sách tài khoản của customerId tương ứng
            accounts.computeIfAbsent(account.getOwner().getId(), k -> new ArrayList<>()).add(account);
            saveAccountsToFile();  // Ghi lại dữ liệu vào file
        } else {
            throw new IllegalArgumentException("Account cannot be null.");
        }
    }

    @Override
    public List<Account> findById(String customerId) {
        return accounts.getOrDefault(customerId, new ArrayList<>());  // Trả về danh sách tài khoản theo customerId
    }

    @Override
    public void update(Account account) {
        if (account != null && accounts.containsKey(account.getOwner().getId())) {
            // Tìm tài khoản trong danh sách của customerId và cập nhật
            List<Account> customerAccounts = accounts.get(account.getOwner().getId());
            int index = customerAccounts.indexOf(account);
            if (index != -1) {
                customerAccounts.set(index, account);  // Cập nhật tài khoản
                saveAccountsToFile();  // Ghi lại dữ liệu vào file
            }
        } else {
            throw new IllegalArgumentException("Account does not exist.");
        }
    }

    @Override
    public void delete(String customerId) {
        if (accounts.containsKey(customerId)) {
            accounts.remove(customerId);  // Xóa tất cả tài khoản của customerId
            saveAccountsToFile();  // Ghi lại dữ liệu vào file
        } else {
            throw new IllegalArgumentException("Account not found.");
        }
    }

    @Override
    public boolean exists(String customerId) {
        return accounts.containsKey(customerId) && !accounts.get(customerId).isEmpty();  // Kiểm tra xem có tài khoản nào không
    }

    @Override
    public Map<String, List<Account>> findAll() {
        return accounts;  // Trả về tất cả tài khoản của tất cả khách hàng
    }

    @Override
    public void clear() {
        accounts.clear();
    }

    private void saveAccountsToFile() {
        // Lưu lại tất cả tài khoản vào file dưới dạng danh sách
        List<Account> allAccounts = accounts.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        fileManager.writeToFile(allAccounts, FILE_NAME);
    }
}

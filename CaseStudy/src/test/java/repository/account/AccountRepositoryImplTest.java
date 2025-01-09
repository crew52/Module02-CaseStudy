package repository.account;

import entity.Customer;
import entity.account.Account;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountRepositoryImplTest {
    private AccountRepository accountRepository;

    @BeforeEach
    public void setUp() {
        accountRepository = new AccountRepositoryImpl();
        accountRepository.clear();
    }

    @Order(1)
    @Test
    public void testSaveAccount() {
        Customer customer = new Customer("C101", "John Doe", "dung@gmail.com", null);
        Account account = new Account("AC01", customer);
        account.deposit(500.0);
        accountRepository.save(account);

        List<Account> accounts = accountRepository.findById("C101");
        assertFalse(accounts.isEmpty(), "Account should be saved and retrieved.");
        assertEquals(1, accounts.size(), "There should be one account for customer C101.");
        assertEquals(account, accounts.getFirst(), "The saved account should match the retrieved account.");
    }

    @Order(2)
    @Test
    public void testFindById() {
        Customer customer = new Customer("C102", "Jane Doe", "jane@gmail.com", null);
        Account account1 = new Account("AC01", customer);
        account1.deposit(500.0);
        Account account2 = new Account("AC02", customer);
        account2.deposit(1500.0);
        accountRepository.save(account1);
        accountRepository.save(account2);

        List<Account> accounts = accountRepository.findById("C102");
        assertNotNull(accounts, "Account list should not be null.");
        assertEquals(2, accounts.size(), "There should be two accounts for customer C102.");
    }

    @Order(3)
    @Test
    public void testUpdateAccount() {
        Customer customer = new Customer("C103", "Bob Smith", "bob@gmail.com", null);
        Account account = new Account("AC01", customer);
        account.deposit(2000.0);  // Thực hiện gửi tiền vào tài khoản
        accountRepository.save(account);

        account.deposit(1000.0);  // Cập nhật số dư bằng cách nạp thêm 1000

        // Cập nhật tài khoản trong hệ thống
        accountRepository.update(account);

        // Kiểm tra xem tài khoản mới đã được lưu và có số dư đúng chưa
        List<Account> accounts = accountRepository.findById("C103");
        assertEquals(1, accounts.size(), "There should be one account for customer C103.");
        assertEquals(3000.0, accounts.getFirst().getBalance(), "The balance should be updated to 3000.");
    }

    @Order(4)
    @Test
    public void testDeleteAccount() {
        Customer customer = new Customer("C104", "Alice Brown", "alice@gmail.com", null);
        Account account = new Account("AC01", customer);
        account.deposit(4000.0);
        accountRepository.save(account);

        accountRepository.delete("C104");

        List<Account> accounts = accountRepository.findById("C104");
        assertTrue(accounts.isEmpty(), "Account should be deleted.");
    }

    @Order(5)
    @Test
    public void testExists() {
        Customer customer = new Customer("C105", "Charlie White", "charlie@gmail.com", null);
        Account account = new Account("AC01", customer);
        account.deposit(5000.0);
        accountRepository.save(account);

        assertTrue(accountRepository.exists("C105"), "Account should exist for customer C105.");
        assertFalse(accountRepository.exists("nonexistent"), "Account should not exist for nonexistent customer.");
    }

    @Order(6)
    @Test
    public void testFindAll() {
        Customer customer1 = new Customer("C106", "David Green", "david@gmail.com", null);
        Customer customer2 = new Customer("C107", "Eve Black", "eve@gmail.com", null);
        Account account1 = new Account("AC01", customer1);
        account1.deposit(6000.0);
        Account account2 = new Account("AC02", customer2);
        account2.deposit(7000.0);
        accountRepository.save(account1);
        accountRepository.save(account2);

        Map<String, List<Account>> allAccounts = accountRepository.findAll();
        assertEquals(2, allAccounts.size(), "There should be accounts for two customers.");
        assertTrue(allAccounts.containsKey("C106"), "Accounts map should contain customer C106.");
        assertTrue(allAccounts.containsKey("C107"), "Accounts map should contain customer C107.");
    }

}
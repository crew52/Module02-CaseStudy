package service.account;

import eNum.Term;
import entity.Customer;
import entity.account.Account;
import entity.account.CheckingAccount;
import entity.account.SavingsAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.account.AccountRepositoryImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceImplTest {
    private static AccountServiceImpl accountService;
    private static AccountRepositoryImpl accountRepository;

    @BeforeAll
    public static void setUpBeforeClass() {
        // Thiết lập và khởi tạo trước khi tất cả các test case chạy
        accountRepository = new AccountRepositoryImpl();
        accountService = new AccountServiceImpl();
    }

    @BeforeEach
    public void setUp() {
        // Xóa dữ liệu trước mỗi lần kiểm thử
        accountRepository.clear();
    }

    @Test
    public void testSaveAccount() {
        Customer customer = new Customer("C001", "John Doe", "jone@gamil.com", null);
        Account account = new CheckingAccount("ACO1", customer);
        account.deposit(1000);

        // Kiểm tra lưu tài khoản
        accountService.save(account);
        List<Account> accounts = accountService.findByCustomerId("C001");

        // Kiểm tra tài khoản đã được lưu chưa
        assertEquals(1, accounts.size());
        assertInstanceOf(CheckingAccount.class, accounts.getFirst());
    }

    @Test
    public void testFindByCustomerId() {
        Customer customer = new Customer("C001", "John Doe", "jone@gamil.com", null);
        Account account1 = new CheckingAccount("AC002", customer);
        account1.deposit(1000);
        Account account2 = new SavingsAccount("AC003", customer, Term.SIX_MONTHS);
        account2.deposit(2000);

        accountService.save(account1);
        accountService.save(account2);

        // Kiểm tra tìm tài khoản theo customerId
        List<Account> accounts = accountService.findByCustomerId("C001");
        assertEquals(3, accounts.size());
        // Kiểm tra tài khoản có phải là CheckingAccount hay SavingsAccount
        assertTrue(accounts.stream().anyMatch(a -> a instanceof CheckingAccount));
        assertTrue(accounts.stream().anyMatch(a -> a instanceof SavingsAccount));
    }
}
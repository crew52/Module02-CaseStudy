package entity.account;

import entity.Customer;
import entity.transaction.DepositTransaction;
import entity.transaction.WithdrawTransaction;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountTest {
    private Account account;
    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer("C001", "John Doe", "dung@gmail.com", null);
        account = new CheckingAccount("A001", customer);
    }

    @Order(1)
    @Test
    void testAccountInitialization() {
        assertEquals("A001", account.getAccountId());
        assertEquals(0.0, account.getBalance());
        assertEquals(customer, account.getOwner());
        assertNotNull(account.getTransactions());
        assertTrue(account.getTransactions().isEmpty());
    }

    @Order(2)
    @Test
    void testDepositValidAmount() {
        account.deposit(100.0);
        assertEquals(100.0, account.getBalance());
    }

    @Order(3)
    @Test
    void testDepositInvalidAmount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> account.deposit(-50.0));
        assertEquals("Amount must be positive.", exception.getMessage());
    }

    @Order(4)
    @Test
    void testWithdrawValidAmount() {
        account.deposit(200.0);
        account.withdraw(100.0);
        assertEquals(100.0, account.getBalance());
    }

    @Order(5)
    @Test
    void testWithdrawInvalidAmount() {
        account.deposit(50.0);

        // Withdraw amount greater than balance
        Exception exception = assertThrows(IllegalArgumentException.class, () -> account.withdraw(100.0));
        assertEquals("Invalid amount.", exception.getMessage());

        // Withdraw negative amount
        exception = assertThrows(IllegalArgumentException.class, () -> account.withdraw(-50.0));
        assertEquals("Invalid amount.", exception.getMessage());
    }

    @Order(6)
    @Test
    void testTransactionsDetails() {
        // Perform deposit and withdraw
        account.deposit(150.0);
        account.withdraw(50.0);

        // Ensure that there are exactly 2 transactions
        assertEquals(2, account.getTransactions().size());

        // Check that the first transaction is a DepositTransaction
        assertInstanceOf(DepositTransaction.class, account.getTransactions().getFirst());
        DepositTransaction depositTransaction = (DepositTransaction) account.getTransactions().get(0);
        assertEquals(150.0, depositTransaction.getAmount());

        // Check that the second transaction is a WithdrawTransaction
        assertInstanceOf(WithdrawTransaction.class, account.getTransactions().get(1));
        WithdrawTransaction withdrawTransaction = (WithdrawTransaction) account.getTransactions().get(1);
        assertEquals(50.0, withdrawTransaction.getAmount());
    }
}
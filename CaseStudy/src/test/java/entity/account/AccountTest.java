package entity.account;

import entity.Customer;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountTest {
    private Account account;
    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer("C001", "John Doe", "123-456-7890", null);
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

}
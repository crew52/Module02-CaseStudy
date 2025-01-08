package entity.account;

import eNum.Term;
import entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SavingsAccountTest {

    private SavingsAccount account;
    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer("C001", "John Doe", "123-456-7890", null);
        account = new SavingsAccount("A001", customer, Term.SIX_MONTHS);
        account.deposit(1000.0);
    }

    @Test
    void testCreateSavingsAccount() {
        assertNotNull(account);
        assertEquals("A001", account.getAccountId());
        assertEquals(customer, account.getOwner());
        assertEquals(1000.0, account.getBalance());
        assertEquals(Term.SIX_MONTHS, account.getTerm());
        assertNotNull(account.getStartDate());
    }

    @Test
    void testCalculateCompoundInterest() {
        double interest = account.calculateCompoundInterest();
        assertTrue(interest > 0, "Interest should be positive");
    }

    @Test
    void testCanWithdrawBeforeTermEnds() {
        assertFalse(account.canWithdraw(), "Should not allow withdrawal before term ends");
    }

    @Test
    void testCanWithdrawAfterTermEnds() {
        account.setStartDate(LocalDate.now().minusMonths(6));
        assertTrue(account.canWithdraw(), "Should allow withdrawal after term ends");
    }

    @Test
    void testWithdrawBeforeTermEndsThrowsException() {
        Exception exception = assertThrows(IllegalStateException.class, () -> account.withdraw(500));
        assertEquals("Cannot withdraw before term ends.", exception.getMessage());
    }

    @Test
    void testWithdrawAfterTermEnds() {
        account.setStartDate(LocalDate.now().minusMonths(6));
        account.withdraw(500);
        assertEquals(500.0, account.getBalance());
    }

    @Test
    void testWithdrawEarlyWithPenalty() {
        account.withdrawEarly(500);
        double expectedBalance = 1000.0 - 500 - (500 * 0.015); // Penalty for SIX_MONTHS term
        assertEquals(expectedBalance, account.getBalance(), 0.01);
    }

    @Test
    void testWithdrawEarlyWithDefaultPenalty() {
        SavingsAccount accountWithNoTerm = new SavingsAccount("A002", customer, null);
        accountWithNoTerm.deposit(1000.0);
        accountWithNoTerm.withdrawEarly(500);
        double expectedBalance = 1000.0 - 500 - (500 * 0.02); // Default penalty
        assertEquals(expectedBalance, accountWithNoTerm.getBalance(), 0.01);
    }

    @Test
    void testApplyInterest() {
        account.applyInterest();
        assertTrue(account.getBalance() > 1000.0, "Balance should increase after applying interest");
    }
}
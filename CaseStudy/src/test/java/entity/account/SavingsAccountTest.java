package entity.account;

import eNum.Term;
import entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class SavingsAccountTest {

//    private Customer customer;
//    private SavingsAccount account;
//
//    @BeforeEach
//    public void setUp() {
//        customer = new Customer("C001", "John Doe", "123-456-7890", null);
//    }
//
//    @Test
//    public void testCalculateCompoundInterest() {
//        account = new SavingsAccount("A001", customer, Term.ONE_YEAR);
//
//        account.deposit(1000); // Initial deposit
//
//        double expectedInterest = 1000 * Math.pow(1 + 0.04 / 12, 12 * 1) - 1000; // Compound interest calculation
//        account.applyInterest();
//        assertEquals(1000 + expectedInterest, account.getBalance(), 0.01);
//    }
//
//    @Test
//    public void testCanWithdrawBeforeTermEnds() {
//        account = new SavingsAccount("A002", customer, Term.SIX_MONTHS);
//        assertFalse(account.canWithdraw(), "Should not allow withdrawal before term ends");
//    }
//
//    @Test
//    public void testCanWithdrawAfterTermEnds() {
//        account = new SavingsAccount("A003", customer, Term.THREE_MONTHS);
//        account.deposit(1000);
//
//        // Simulate time passing: 3 months
//        LocalDate startDate = account.getStartDate();
//        LocalDate currentDate = startDate.plusMonths(3);
//
//        assertEquals(3, ChronoUnit.MONTHS.between(startDate, currentDate));
//        assertTrue(currentDate.isAfter(startDate.plusMonths(3)) || currentDate.isEqual(startDate.plusMonths(3)));
//    }
//
//    @Test
//    public void testWithdrawAfterTermEnds() {
//        account = new SavingsAccount("A004", customer, Term.THREE_MONTHS);
//        account.deposit(2000);
//
//        // Simulate time passing to allow withdrawal
//        LocalDate startDate = account.getStartDate().minusMonths(3);
//
//        assertTrue(account.canWithdraw(), "Withdrawal should be allowed after term ends");
//        account.withdraw(1000);
//        assertEquals(1000, account.getBalance(), 0.01);
//    }
//
//    @Test
//    public void testEarlyWithdrawal() {
//        account = new SavingsAccount("A005", customer, Term.SIX_MONTHS);
//        account.deposit(2000);
//
//        double penaltyRate = 0.015;
//        double expectedPenalty = 1000 * penaltyRate;
//
//        account.withdrawEarly(1000);
//        assertEquals(2000 - 1000 - expectedPenalty, account.getBalance(), 0.01);
//    }
//
//    @Test
//    public void testEarlyWithdrawalDefaultPenalty() {
//        account = new SavingsAccount("A006", customer, null);
//        account.deposit(1500);
//
//        double defaultPenaltyRate = 0.02;
//        double expectedPenalty = 500 * defaultPenaltyRate;
//
//        account.withdrawEarly(500);
//        assertEquals(1500 - 500 - expectedPenalty, account.getBalance(), 0.01);
//    }
//
//    @Test
//    public void testApplyInterestMultipleTerms() {
//        account = new SavingsAccount("A007", customer, Term.THREE_MONTHS);
//        account.deposit(5000);
//
//        double expectedInterest = 5000 * Math.pow(1 + 0.03 / 12, 12 * 0.25) - 5000; // 3 months term
//        account.applyInterest();
//        assertEquals(5000 + expectedInterest, account.getBalance(), 0.01);
//    }

}
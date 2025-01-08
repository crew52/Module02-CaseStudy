package entity.account;

import eNum.Term;
import entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SavingsAccountTest {

    private SavingsAccount savingsAccount;

    @BeforeEach
    void setUp() {
        // Thiết lập khách hàng mẫu cho tài khoản
        Customer customer = new Customer("C001", "John Doe", "123-456-7890", null);
        // Khởi tạo tài khoản tiết kiệm với kỳ hạn 6 tháng
        savingsAccount = new SavingsAccount("A001", customer, Term.SIX_MONTHS);
        savingsAccount.deposit(1000.0);
    }

    @Test
    void testCalculateCompoundInterest() {

        // Kiểm tra tính toán lãi suất kép cho tài khoản tiết kiệm
        double expectedInterest = 1000 * Math.pow(1 + Term.SIX_MONTHS.getInterestRate() / 12, 12 * (Term.SIX_MONTHS.getMonths() / 12.0)) - 1000;
        double actualInterest = savingsAccount.calculateCompoundInterest();

        // So sánh kết quả lãi suất
        assertEquals(expectedInterest, actualInterest, 0.01, "The calculated compound interest should be correct.");
    }

    @Test
    void testCanWithdrawBeforeTermEnds() {
        // Cập nhật ngày bắt đầu là 3 tháng trước
        savingsAccount.setStartDate(LocalDate.now().minusMonths(3));

        // Kiểm tra khả năng rút tiền khi chưa hết kỳ hạn
        assertFalse(savingsAccount.canWithdraw(), "Should not be able to withdraw before term ends.");
    }

    @Test
    void testCanWithdrawAfterTermEnds() {
        // Cập nhật ngày bắt đầu là 6 tháng trước
        savingsAccount.setStartDate(LocalDate.now().minusMonths(6));

        // Kiểm tra khả năng rút tiền sau khi hết kỳ hạn
        assertTrue(savingsAccount.canWithdraw(), "Should be able to withdraw after term ends.");
    }

    @Test
    void testWithdrawBeforeTermThrowsException() {
        // Cập nhật ngày bắt đầu là 3 tháng trước (chưa đủ 6 tháng)
        savingsAccount.setStartDate(LocalDate.now().minusMonths(3));

        // Kiểm tra việc rút tiền trước khi hết kỳ hạn sẽ gây ra exception
        assertThrows(IllegalStateException.class, () -> savingsAccount.withdraw(500.0), "Cannot withdraw before term ends.");
    }

    @Test
    void testWithdrawAfterTermWithoutPenalty() {
        // Cập nhật ngày bắt đầu để tài khoản đã đủ kỳ hạn (6 tháng)
        savingsAccount.setStartDate(LocalDate.now().minusMonths(6));

        // Rút tiền sau khi hết kỳ hạn và không có phí phạt
        double withdrawalAmount = 500.0;

        // Thực hiện rút tiền sau kỳ hạn
        assertDoesNotThrow(() -> savingsAccount.withdraw(withdrawalAmount), "Should be able to withdraw without penalty after term ends.");
    }

    @Test
    void testApplyInterest() {

        // Lưu giá trị số dư trước khi áp dụng lãi suất
        double balanceBeforeInterest = savingsAccount.getBalance();

        // Áp dụng lãi suất
        savingsAccount.applyInterest();

        // So sánh số dư sau khi áp dụng lãi suất
        assertTrue(savingsAccount.getBalance() > balanceBeforeInterest, "Balance should increase after applying interest.");
    }
}
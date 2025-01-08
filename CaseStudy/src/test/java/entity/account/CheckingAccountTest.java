package entity.account;

import entity.Customer;
import entity.transaction.TransferTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckingAccountTest {
    private CheckingAccount account1;
    private CheckingAccount account2;

    @BeforeEach
    void setUp() {
        Customer customer = new Customer("C001", "John Doe", "123-456-7890", null);
        account1 = new CheckingAccount("A001", customer);
        account2 = new CheckingAccount("A002", customer);
    }

    @Test
    void testTransferValidAmount() {
        account1.deposit(200.0);
        account1.transfer(account2, 100.0);

        assertEquals(100.0, account1.getBalance(), "Account 1 balance after transfer");
        assertEquals(100.0, account2.getBalance(), "Account 2 balance after transfer");

        // Kiểm tra số giao dịch trong tài khoản nguồn (Account 1)
        assertEquals(2, account1.getTransactions().size(), "Number of transactions in Account 1");
        assertEquals(1, account2.getTransactions().size(), "Number of transactions in Account 2");

        // Kiểm tra giao dịch trong tài khoản nguồn
        assertInstanceOf(TransferTransaction.class, account1.getTransactions().get(1));
        TransferTransaction transferTransaction1 = (TransferTransaction) account1.getTransactions().get(1);
        assertEquals(100.0, transferTransaction1.getAmount());
        assertEquals(account2.getAccountId(), transferTransaction1.getTargetAccountId());

        // Kiểm tra giao dịch trong tài khoản đích
        assertInstanceOf(TransferTransaction.class, account2.getTransactions().getFirst());
        TransferTransaction transferTransaction2 = (TransferTransaction) account2.getTransactions().getFirst();
        assertEquals(100.0, transferTransaction2.getAmount());
        assertEquals(account1.getAccountId(), transferTransaction2.getTargetAccountId());
    }
}
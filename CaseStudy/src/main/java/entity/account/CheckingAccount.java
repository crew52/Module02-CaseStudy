package entity.account;

import entity.Customer;
import entity.transaction.DepositTransaction;
import entity.transaction.TransferTransaction;
import entity.transaction.WithdrawTransaction;

public class CheckingAccount extends Account {

    public CheckingAccount(String accountId, Customer owner) {
        super(accountId, owner);
    }

    public void transfer(Account targetAccount, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }

        if (this == targetAccount) {
            throw new IllegalArgumentException("Cannot transfer money to the same account.");
        }

        // Kiểm tra tài khoản đích có hợp lệ không
        if (targetAccount == null) {
            throw new IllegalArgumentException("Target account cannot be null.");
        }

        // Kiểm tra số dư tài khoản nguồn
        if (amount > this.balance) {
            throw new IllegalArgumentException("Insufficient balance.");
        }

        // Thực hiện rút tiền từ tài khoản nguồn
        withdraw(amount, true);

        // Thực hiện nạp tiền vào tài khoản đích
        targetAccount.deposit(amount, true);

        // Tạo giao dịch chuyển khoản cho tài khoản nguồn
        addTransaction(new TransferTransaction(amount, targetAccount.getAccountId()));

        // Tạo giao dịch chuyển khoản cho tài khoản đích
        targetAccount.addTransaction(new TransferTransaction(amount, this.getAccountId()));

    }

}

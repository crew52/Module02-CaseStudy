package entity.account;

import entity.Customer;
import entity.transaction.AbstractTransaction;
import entity.transaction.TransferTransaction;
import utility.TransactionUtil;
import validation.AccountValidator;

public class CheckingAccount extends Account {

    public CheckingAccount(String accountId, Customer owner) {
        super(accountId, owner);
    }

    public void transfer(Account targetAccount, double amount) {
        AccountValidator.validatePositiveAmount(amount);
        AccountValidator.validateDifferentAccounts(this, targetAccount);
        AccountValidator.validateTargetAccount(targetAccount);
        AccountValidator.validateSufficientBalance(this, amount);

        // Thực hiện rút tiền từ tài khoản nguồn
        withdraw(amount, true);

        // Thực hiện nạp tiền vào tài khoản đích
        targetAccount.deposit(amount, true);

        // Tạo giao dịch chuyển khoản cho tài khoản nguồn
        AbstractTransaction transaction = new TransferTransaction(amount, targetAccount.getAccountId());
        addTransaction(transaction);
        TransactionUtil.addTransactionToSystem(transaction);

        // Tạo giao dịch chuyển khoản cho tài khoản đích
        targetAccount.addTransaction(new TransferTransaction(amount, this.getAccountId()));

    }

}

package entity.account;

import entity.Customer;
import entity.transaction.AbstractTransaction;
import entity.transaction.DepositTransaction;
import entity.transaction.WithdrawTransaction;
import utility.TransactionUtil;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account implements IAccount, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    protected String accountId;
    protected double balance;
    protected Customer owner;
    protected List<AbstractTransaction> transactions;

    public Account(String accountId, Customer owner) {
        this.accountId = accountId;
        this.balance = 0.0d;
        this.owner = owner;
        this.transactions = new ArrayList<>();
    }

    public Account() {
    }

    public String getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public Customer getOwner() {
        return owner;
    }

    protected void addTransaction(AbstractTransaction transaction) {
        if (transaction != null) {
            transactions.add(transaction); // Add the transaction to the list
        } else {
            throw new IllegalArgumentException("Transaction cannot be null.");
        }
    }

    public List<AbstractTransaction> getTransactions() {
        // Return an unmodifiable list to prevent external modification
        return Collections.unmodifiableList(transactions);

    }

    @Override
    public void deposit(double amount, boolean isTransfer) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive.");

        if (!isTransfer) {
            AbstractTransaction transaction = new DepositTransaction(amount);
            addTransaction(transaction);  // Tạo giao dịch nạp tiền
            TransactionUtil.addTransactionToSystem(transaction);
        }

        balance += amount;  // Cập nhật số dư
    }

    @Override
    public void withdraw(double amount, boolean isTransfer) {
        if (amount <= 0 || amount > balance) throw new IllegalArgumentException("Invalid amount.");

        // Nếu không phải là chuyển khoản, mới tạo giao dịch WithdrawTransaction
        if (!isTransfer) {
            AbstractTransaction transaction = new WithdrawTransaction(amount);
            addTransaction(transaction);  // Tạo giao dịch rút tiền
            TransactionUtil.addTransactionToSystem(transaction);
        }

        balance -= amount;  // Cập nhật số dư
    }

    public void withdraw(double amount) {
        withdraw(amount, false);
    }

    public void deposit(double amount) {
        deposit(amount, false);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                ", balance=" + balance +
                ", owner=" + owner.getId() +
                ", numberOfTransactions=" + (transactions != null ? transactions.size() : 0) +
                '}';
    }
}

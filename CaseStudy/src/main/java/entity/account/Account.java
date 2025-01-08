package entity.account;

import entity.Customer;
import entity.transaction.AbstractTransaction;
import entity.transaction.DepositTransaction;
import entity.transaction.WithdrawTransaction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Account implements IAccount{
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
    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive.");
        balance += amount;
        addTransaction(new DepositTransaction(amount));
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0 || amount > balance) throw new IllegalArgumentException("Invalid amount.");
        balance -= amount;
        addTransaction(new WithdrawTransaction(amount));
    }

}

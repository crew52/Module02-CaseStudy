package entity.account;

import entity.Customer;
import entity.transaction.AbstractTransaction;

import java.util.ArrayList;
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
        // TODO
    }

    public List<AbstractTransaction> getTransactions() {
        // TODO
        return transactions;
    }

    @Override
    public void deposit(double amount) {
        // TODO
    }

    @Override
    public void withdraw(double amount) {
        // TODO
    }

}

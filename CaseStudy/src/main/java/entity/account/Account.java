package entity.account;

import entity.Customer;
import entity.transaction.AbstractTransaction;

import java.util.List;

public class Account implements IAccount{
    private String accountId;
    private double balance;
    private Customer owner;
    private List<AbstractTransaction> transactions;

    public void addTransaction(AbstractTransaction transaction) {
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

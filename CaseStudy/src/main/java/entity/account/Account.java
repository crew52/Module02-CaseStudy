package entity.account;

import entity.Customer;

public class Account implements IAccount{
    private String accountId;
    private double balance;
    private Customer owner;
    // transactions : List<AbstractTransaction>

    @Override
    public void deposit(double amount) {
        // TODO
    }

    @Override
    public void withdraw(double amount) {
        // TODO
    }

}

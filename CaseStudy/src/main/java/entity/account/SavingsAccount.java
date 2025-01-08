package entity.account;

import entity.Customer;

public class SavingsAccount extends Account{
    private double interestRate;

    public SavingsAccount(String accountId, Customer owner, double interestRate) {
        super(accountId, owner);
        this.interestRate = interestRate;
    }

    public void calculateInterest() {
        // TODO
    }
}

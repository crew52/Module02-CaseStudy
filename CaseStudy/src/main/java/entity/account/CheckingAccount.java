package entity.account;

import entity.Customer;

public class CheckingAccount extends Account {
    public CheckingAccount(String accountId, Customer owner) {
        super(accountId, owner);
    }

    public void transfer(Account targetAccount, double amount) {
        // TODO
    }
}

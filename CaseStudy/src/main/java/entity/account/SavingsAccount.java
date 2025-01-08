package entity.account;

import entity.Customer;
import entity.transaction.DepositTransaction;
import entity.transaction.InterestTransaction;

public class SavingsAccount extends Account{
    private double interestRate;

    public SavingsAccount(String accountId, Customer owner, double interestRate) {
        super(accountId, owner);
        if (interestRate < 0) throw new IllegalArgumentException("Interest rate must be non-negative.");
        this.interestRate = interestRate;
    }

    public void calculateInterest() {
        if (balance <= 0) {
            System.out.println("No interest is calculated for zero or negative balance.");
            return;
        }

        // Tính lãi hàng tháng
        double monthlyInterest = balance * (interestRate / 12);

        // Cộng lãi vào số dư
        balance += monthlyInterest;

        // Thêm giao dịch tiền lãi vào danh sách giao dịch
        addTransaction(new InterestTransaction(monthlyInterest));

        System.out.printf("Interest of %.2f has been added to account %s. New balance: %.2f%n",
                monthlyInterest, accountId, balance);
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        if (interestRate < 0) throw new IllegalArgumentException("Interest rate must be non-negative.");
        this.interestRate = interestRate;
    }
}

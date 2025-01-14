package entity.account;

import eNum.Term;
import entity.Customer;
import validation.AccountValidator;
import validation.SavingsAccountValidator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.EnumMap;
import java.util.Map;

public class SavingsAccount extends Account{
    private Term term;
    private LocalDate startDate;
    private static final Map<Term, Double> penalties = new EnumMap<>(Term.class);

    static {
        penalties.put(Term.THREE_MONTHS, 0.01);
        penalties.put(Term.SIX_MONTHS, 0.015);
        penalties.put(Term.ONE_YEAR, 0.02);
    }

    public SavingsAccount(String accountId, Customer owner, Term term) {
        super(accountId, owner);
        this.term = term;
        this.startDate = LocalDate.now();
    }

    public double calculateCompoundInterest() {
        if (term == null) throw new IllegalStateException("Term not set.");
        double rate = term.getInterestRate();
        double principal = getBalance();
        int n = 12; // Compounded monthly
        double t = term.getMonths() / 12.0; // Time in years
        return (principal * Math.pow(1 + rate / n, n * t)) - principal;
    }

    public boolean canWithdraw() {
        if (term == null) {
            return false; // Không cho phép rút nếu không có kỳ hạn
        }
        long monthsElapsed = ChronoUnit.MONTHS.between(startDate, LocalDate.now());
        return monthsElapsed >= term.getMonths();
    }

    @Override
    public void withdraw(double amount) {
        SavingsAccountValidator.validateTerm(this);
        SavingsAccountValidator.validateCanWithdraw(this);
        AccountValidator.validatePositiveAmount(amount);
        super.withdraw(amount);
    }

    public void withdrawEarly(double amount) {
        AccountValidator.validatePositiveAmount(amount);
        if (term == null) {
            System.out.println("Default penalty applied due to no term.");
            double penalty = amount * 0.02; // Default penalty
            super.withdraw(amount + penalty);
            return;
        }
        if (canWithdraw()) {
            super.withdraw(amount);
        } else {
            double penaltyRate = penalties.getOrDefault(term, 0.02); // Default penalty 2%
            double penalty = amount * penaltyRate;
            System.out.println("Early withdrawal penalty: " + penalty);
            super.withdraw(amount + penalty);
        }
    }

    public void applyInterest() {
        double interest = calculateCompoundInterest();
        deposit(interest);
        System.out.println("Interest applied: " + interest);
    }

    public Term getTerm() {
        return term;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}

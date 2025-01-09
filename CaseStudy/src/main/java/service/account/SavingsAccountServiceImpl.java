package service.account;

import entity.account.SavingsAccount;
import repository.account.AccountRepository;
import validation.AccountValidator;

public class SavingsAccountServiceImpl implements SavingsAccountService {

    private final AccountRepository accountRepository;

    public SavingsAccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void applyInterest(SavingsAccount account) {
        double interest = account.calculateCompoundInterest();
        account.deposit(interest);
        accountRepository.update(account); // Cập nhật lại tài khoản sau khi áp dụng lãi suất
        System.out.println("Interest applied: " + interest);
    }

    @Override
    public boolean canWithdraw(SavingsAccount account) {
        return account.canWithdraw();
    }

    @Override
    public void withdrawEarly(SavingsAccount account, double amount) {
        AccountValidator.validatePositiveAmount(amount);
        account.withdrawEarly(amount);
        accountRepository.update(account); // Cập nhật lại tài khoản sau khi rút tiền sớm
    }
}

package validation;

import entity.account.Account;

public class AccountValidator {
    public static void validatePositiveAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
    }

    public static void validateSufficientBalance(Account account, double amount) {
        if (amount > account.getBalance()) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
    }

    public static void validateDifferentAccounts(Account sourceAccount, Account targetAccount) {
        if (sourceAccount == targetAccount) {
            throw new IllegalArgumentException("Cannot transfer money to the same account.");
        }
    }

    public static void validateTargetAccount(Account targetAccount) {
        if (targetAccount == null) {
            throw new IllegalArgumentException("Target account cannot be null.");
        }
    }
}

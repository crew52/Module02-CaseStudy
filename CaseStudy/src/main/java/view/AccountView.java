package view;

import eNum.Term;
import entity.account.Account;
import entity.Customer;
import entity.account.CheckingAccount;
import entity.account.SavingsAccount;
import service.account.AccountServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AccountView {
    private final AccountServiceImpl accountService;

    public AccountView(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Account");
            System.out.println("2. View All Accounts");
            System.out.println("3. Update Account");
            System.out.println("4. Delete Account");
            System.out.println("5. Check Account Existence");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (option) {
                case 1:
                    addAccount(scanner);
                    break;
                case 2:
                    viewAllAccounts();
                    break;
                case 3:
                    System.out.println("dang hoan thien");
//                    updateAccount(scanner);
                    break;
                case 4:
                    deleteAccount(scanner);
                    break;
                case 5:
                    checkAccountExistence(scanner);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    private void addAccount(Scanner scanner) {
        System.out.print("Enter account ID: ");
        String accountId = scanner.nextLine();
        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();
        // Assuming you have a method to get the customer by ID
        Customer owner = new Customer(customerId, "Name", "email@example.com", null); // Mock customer
        System.out.print("Enter account type (Checking/Savings): ");
        String accountType = scanner.nextLine();

        Account account = null;
        if (accountType.equalsIgnoreCase("Checking")) {
            account = new CheckingAccount(accountId, owner);
        } else if (accountType.equalsIgnoreCase("Savings")) {
            account = new SavingsAccount(accountId, owner, Term.ONE_YEAR); // Example term
        }

        accountService.save(account);
        System.out.println("Account added successfully!");
    }

    private void viewAllAccounts() {
        Map<String, List<Account>> accounts = accountService.findAll();
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            accounts.forEach((customerId, accountList) -> {
                System.out.println("Customer ID: " + customerId);
                accountList.forEach(account -> System.out.println(account.toString()));
            });
        }
    }

//    private void updateAccount(Scanner scanner) {
//        System.out.print("Enter account ID to update: ");
//        String accountId = scanner.nextLine();
//        Account oldAccount = accountService.findByCustomerId(accountId).get(0); // Example to fetch account
//        if (oldAccount == null) {
//            System.out.println("Account not found.");
//        } else {
//            // Simulate updating account fields
//            System.out.print("Enter new account balance: ");
//            double newBalance = scanner.nextDouble();
//            oldAccount.setBalance(newBalance);
//            accountService.update(oldAccount);
//            System.out.println("Account updated successfully!");
//        }
//    }

    private void deleteAccount(Scanner scanner) {
        System.out.print("Enter customer ID to delete accounts: ");
        String customerId = scanner.nextLine();
        accountService.delete(customerId);
        System.out.println("Accounts deleted successfully!");
    }

    private void checkAccountExistence(Scanner scanner) {
        System.out.print("Enter customer ID to check account existence: ");
        String customerId = scanner.nextLine();
        boolean exists = accountService.exists(customerId);
        if (exists) {
            System.out.println("The customer has accounts.");
        } else {
            System.out.println("The customer has no accounts.");
        }
    }
}

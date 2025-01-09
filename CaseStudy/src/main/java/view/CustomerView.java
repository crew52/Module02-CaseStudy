package view;

import entity.Customer;
import service.CustomerServiceImpl;

import java.util.List;
import java.util.Scanner;

public class CustomerView {
    private final CustomerServiceImpl customerService;

    public CustomerView(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Customer");
            System.out.println("2. View All Customers");
            System.out.println("3. Update Customer");
            System.out.println("4. Delete Customer");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (option) {
                case 1:
                    addCustomer(scanner);
                    break;
                case 2:
                    viewAllCustomers();
                    break;
                case 3:
                    updateCustomer(scanner);
                    break;
                case 4:
                    deleteCustomer(scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    private void addCustomer(Scanner scanner) {
        System.out.print("Enter customer ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();

        Customer customer = new Customer(id, name, email, null);
        customerService.saveCustomer(customer);
        System.out.println("Customer added successfully!");
    }

    private void viewAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            customers.forEach(c -> System.out.println(c.toString()));
        }
    }

    private void updateCustomer(Scanner scanner) {
        System.out.print("Enter customer ID to update: ");
        String id = scanner.nextLine();
        Customer oldCustomer = customerService.getCustomerById(id);

        if (oldCustomer == null) {
            System.out.println("Customer not found.");
        } else {
            System.out.print("Enter new name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new email: ");
            String email = scanner.nextLine();

            Customer newCustomer = new Customer(id, name, email, null);
            customerService.updateCustomerById(id, newCustomer);
            System.out.println("Customer updated successfully!");
        }
    }

    private void deleteCustomer(Scanner scanner) {
        System.out.print("Enter customer ID to delete: ");
        String id = scanner.nextLine();
        customerService.deleteCustomerById(id);
        System.out.println("Customer deleted successfully!");
    }
}

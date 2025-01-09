package repository;

import entity.Customer;
import utility.FileManagerImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerRepositoryImpl implements CustomerRepository {
    private static final String FILE_NAME = "customers.dat";
    private final FileManagerImpl<Customer> fileManager = new FileManagerImpl<>();

    public <T> CustomerRepositoryImpl(FileManagerImpl<T> tFileManager) {
    }

    @Override
    public void save(Customer customer) {
        List<Customer> customers = fileManager.readFromFile(FILE_NAME);
        if (customers == null) {
            customers = new ArrayList<>();
        }
        customers.add(customer);
        fileManager.writeToFile(customers, FILE_NAME);
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = fileManager.readFromFile(FILE_NAME);
        return customers != null ? customers : new ArrayList<>();
    }

    @Override
    public Customer findById(String id) {
        List<Customer> customers = fileManager.readFromFile(FILE_NAME);
        if (customers != null) {
            for (Customer customer : customers) {
                if (customer.getId().equals(id)) {
                    return customer;
                }
            }
        }
        return null;
    }

    @Override
    public void update(Customer oldCustomer, Customer newCustomer) {
        List<Customer> customers = fileManager.readFromFile(FILE_NAME);
        if (customers != null) {
            int index = customers.indexOf(oldCustomer);
            if (index != -1) {
                customers.set(index, newCustomer);  // Update the customer
                fileManager.writeToFile(customers, FILE_NAME);  // Save updated list to file
            }
        }
    }

    @Override
    public void deleteById(String id) {
        List<Customer> customers = fileManager.readFromFile(FILE_NAME);
        if (customers != null) {
            customers = customers.stream()
                    .filter(customer -> !customer.getId().equals(id))  // Remove customer by id
                    .collect(Collectors.toList());
            fileManager.writeToFile(customers, FILE_NAME);  // Save updated list to file
        }
    }
}

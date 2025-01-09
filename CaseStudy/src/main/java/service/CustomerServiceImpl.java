package service;

import entity.Customer;
import repository.CustomerRepositoryImpl;

import java.util.List;

public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepositoryImpl repository;

    public CustomerServiceImpl(CustomerRepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public void saveCustomer(Customer customer) {
        repository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    @Override
    public Customer getCustomerById(String id) {
        return repository.findById(id);
    }

    @Override
    public void updateCustomer(Customer oldCustomer, Customer newCustomer) {
        repository.update(oldCustomer, newCustomer);
    }

    @Override
    public void deleteCustomerById(String id) {
        repository.deleteById(id);
    }

    @Override
    public boolean isCustomerExist(String id) {
        Customer customer = repository.findById(id);
        return customer != null;
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public Customer findById(String id) {
        return repository.findById(id);
    }

    @Override
    public void updateCustomerById(String id, Customer newCustomer) {
        Customer oldCustomer = repository.findById(id);
        if (oldCustomer != null) {
            repository.update(oldCustomer, newCustomer);
        } else {
            System.out.println("Customer with ID " + id + " does not exist.");
        }
    }
}

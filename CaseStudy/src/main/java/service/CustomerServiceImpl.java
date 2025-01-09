package service;

import entity.Customer;

import java.util.List;

public class CustomerServiceImpl implements CustomerService{

    @Override
    public boolean addCustomer(Customer customer) {
        return false;
    }

    @Override
    public boolean updateCustomer(String customerId, Customer updatedCustomer) {
        return false;
    }

    @Override
    public boolean deleteCustomer(String customerId) {
        return false;
    }

    @Override
    public Customer getCustomerById(String customerId) {
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return List.of();
    }
}

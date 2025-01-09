package service;

import entity.Customer;

import java.util.List;

public interface CustomerService {

    boolean addCustomer(Customer customer);

    boolean updateCustomer(String customerId, Customer updatedCustomer);

    boolean deleteCustomer(String customerId);

    Customer getCustomerById(String customerId);

    List<Customer> getAllCustomers();
}

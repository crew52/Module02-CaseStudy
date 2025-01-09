package repository;

import entity.Customer;

import java.util.List;

public interface CustomerRepository {
    void save(Customer customer);
    List<Customer> findAll();
    Customer findById(String id);
    void update(Customer oldCustomer, Customer newCustomer);
    void deleteById(String id);
}

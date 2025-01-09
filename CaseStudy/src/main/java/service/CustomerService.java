package service;

import entity.Customer;

import java.util.List;

public interface CustomerService {

    void saveCustomer(Customer customer);

    // Lấy tất cả khách hàng
    List<Customer> getAllCustomers();

    // Tìm khách hàng theo ID
    Customer getCustomerById(String id);

    // Cập nhật thông tin khách hàng
    void updateCustomer(Customer oldCustomer, Customer newCustomer);

    // Xóa khách hàng theo ID
    void deleteCustomerById(String id);
    boolean isCustomerExist(String id);
    List<Customer> findAll();
    Customer findById(String id);
    void updateCustomerById(String id, Customer newCustomer);
}

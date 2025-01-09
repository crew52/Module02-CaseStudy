package repository;

import entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.FileManagerImpl;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerRepositoryImplTest {
    private CustomerRepositoryImpl repository;

    @BeforeEach
    public void setUp() {
        // Tạo đối tượng FileManagerImpl cho Customer
        FileManagerImpl<Customer> fileManager = new FileManagerImpl<>();
        repository = new CustomerRepositoryImpl(fileManager);
        // Xóa dữ liệu cũ trước khi bắt đầu các test case
        repository.deleteById("1");
        repository.deleteById("2");
    }

    @Test
    public void testSaveCustomer() {
        Customer customer = new Customer("1", "John Doe", "john@example.com", null);
        repository.save(customer);

        Customer savedCustomer = repository.findById("1");
        assertNotNull(savedCustomer, "Customer should be saved.");
        assertEquals("John Doe", savedCustomer.getName(), "Customer name should match.");
    }

    @Test
    public void testFindAll() {
        Customer customer1 = new Customer("1", "John Doe", "john@example.com", null);
        Customer customer2 = new Customer("2", "Jane Doe", "jane@example.com", null);
        repository.save(customer1);
        repository.save(customer2);

        List<Customer> customers = repository.findAll();
        assertEquals(2, customers.size(), "There should be two customers.");
    }

    @Test
    public void testFindById() {
        Customer customer = new Customer("1", "John Doe", "john@example.com", null);
        repository.save(customer);

        Customer foundCustomer = repository.findById("1");
        assertNotNull(foundCustomer, "Customer should be found.");
        assertEquals("John Doe", foundCustomer.getName(), "Customer name should match.");
    }

    @Test
    public void testUpdateCustomer() {
        Customer oldCustomer = new Customer("1", "John Doe", "john@example.com", null);
        Customer newCustomer = new Customer("1", "John Smith", "johnsmith@example.com", null);
        repository.save(oldCustomer);

        repository.update(oldCustomer, newCustomer);

        Customer updatedCustomer = repository.findById("1");
        assertNotNull(updatedCustomer, "Updated customer should be found.");
        assertEquals("John Smith", updatedCustomer.getName(), "Customer name should be updated.");
    }

    @Test
    public void testDeleteById() {
        Customer customer = new Customer("1", "John Doe", "john@example.com", null);
        repository.save(customer);

        repository.deleteById("1");
        Customer deletedCustomer = repository.findById("1");

        assertNull(deletedCustomer, "Customer should be deleted.");
    }

    @Test
    public void testDeleteNonExistentCustomer() {
        repository.deleteById("999");  // Customer doesn't exist
        List<Customer> customers = repository.findAll();

        // Ensure that the delete operation does not affect other customers
        assertTrue(customers.isEmpty(), "The list should be empty after deletion attempt on non-existent customer.");
    }

}
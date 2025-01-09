package service;

import entity.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.CustomerRepositoryImpl;
import utility.FileManagerImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceImplTest {
    private CustomerRepositoryImpl customerRepository;
    private CustomerServiceImpl customerService;

    private static final String FILE_NAME = "customers.dat";  // Đường dẫn tệp tin

    @BeforeEach
    public void setUp() {
        // Sử dụng FileManagerImpl giả lập hoặc một tệp tin nhỏ để kiểm tra
        customerRepository = new CustomerRepositoryImpl(new FileManagerImpl<>());
        customerService = new CustomerServiceImpl(customerRepository);
    }

    @AfterEach
    public void tearDown() {
        // Xóa tệp tin sau mỗi bài kiểm tra
        try {
            Files.deleteIfExists(Path.of(FILE_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSaveCustomer() {
        Customer customer = new Customer("1", "John Doe", "jone@gamil.com", null);
        customerService.saveCustomer(customer);

        // Kiểm tra nếu customer đã được thêm vào danh sách
        List<Customer> customers = customerService.getAllCustomers();
        assertEquals(1, customers.size());
        assertEquals("John Doe", customers.getFirst().getName());
    }

    @Test
    public void testGetAllCustomers() {
        // Lưu 2 khách hàng
        customerService.saveCustomer(new Customer("1", "John Doe","jone@gamil.com", null));
        customerService.saveCustomer(new Customer("2", "Jane Doe 2", "jone2@gamil.com", null));

        List<Customer> customers = customerService.getAllCustomers();
        assertEquals(2, customers.size());
    }

    @Test
    public void testGetCustomerById() {
        Customer customer = new Customer("1", "John Doe","jone@gamil.com", null);
        customerService.saveCustomer(customer);

        Customer result = customerService.getCustomerById("1");
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
    }

    @Test
    public void testUpdateCustomer() {
        Customer oldCustomer = new Customer("1", "John Doe", "jone@gamil.com", null);
        customerService.saveCustomer(oldCustomer);

        Customer newCustomer = new Customer("1", "John Smith", "jone@gamil.com", null);
        customerService.updateCustomerById("1", newCustomer);

        Customer updatedCustomer = customerService.getCustomerById("1");
        assertNotNull(updatedCustomer);
        assertEquals("John Smith", updatedCustomer.getName());
    }

    @Test
    public void testDeleteCustomerById() {
        Customer customer = new Customer("1", "John Doe", "jone@gamil.com", null);
        customerService.saveCustomer(customer);

        customerService.deleteCustomerById("1");
        Customer deletedCustomer = customerService.getCustomerById("1");
        assertNull(deletedCustomer);
    }

    @Test
    public void testIsCustomerExist() {
        Customer customer = new Customer("1", "John Doe", "jone@gamil.com", null);
        customerService.saveCustomer(customer);

        assertTrue(customerService.isCustomerExist("1"));
        assertFalse(customerService.isCustomerExist("2"));
    }
}
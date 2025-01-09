package entity;

import entity.account.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    private String id;
    private String name;
    private String contactInfo;
    private List<Account> accounts;

    @BeforeEach
    public void setUp() {
        // Thiết lập các giá trị chuẩn để dùng trong các test case
        id = "12345";
        name = "John Doe";
        contactInfo = "john.doe@example.com";
        accounts = new ArrayList<>();
    }

    @Test
    public void testConstructorValidData() {
        // Kiểm tra constructor với dữ liệu hợp lệ
        Customer customer = new Customer(id, name, contactInfo, accounts);

        assertNotNull(customer);
        assertEquals(id, customer.getId());
        assertEquals(name, customer.getName());
        assertEquals(contactInfo, customer.getContactInfo());
        assertEquals(0, customer.getAccounts().size());
    }

    @Test
    public void testConstructorInvalidId() {
        // Kiểm tra constructor với id không hợp lệ
        assertThrows(IllegalArgumentException.class, () -> {
            new Customer(null, name, contactInfo, accounts);
        });
    }

    @Test
    public void testConstructorInvalidName() {
        // Kiểm tra constructor với name không hợp lệ
        assertThrows(IllegalArgumentException.class, () -> {
            new Customer(id, null, contactInfo, accounts);
        });
    }

    @Test
    public void testConstructorInvalidContactInfo() {
        // Kiểm tra constructor với contactInfo không hợp lệ
        assertThrows(IllegalArgumentException.class, () -> {
            new Customer(id, name, null, accounts);
        });
    }

    @Test
    public void testSetIdValid() {
        // Kiểm tra setter cho id
        Customer customer = new Customer(id, name, contactInfo, accounts);
        String newId = "54321";
        customer.setId(newId);
        assertEquals(newId, customer.getId());
    }

    @Test
    public void testSetIdInvalid() {
        // Kiểm tra setter cho id với giá trị không hợp lệ
        Customer customer = new Customer(id, name, contactInfo, accounts);
        assertThrows(IllegalArgumentException.class, () -> {
            customer.setId(null);
        });
    }

    @Test
    public void testSetNameValid() {
        // Kiểm tra setter cho name
        Customer customer = new Customer(id, name, contactInfo, accounts);
        String newName = "Jane Doe";
        customer.setName(newName);
        assertEquals(newName, customer.getName());
    }

    @Test
    public void testSetNameInvalid() {
        // Kiểm tra setter cho name với giá trị không hợp lệ
        Customer customer = new Customer(id, name, contactInfo, accounts);
        assertThrows(IllegalArgumentException.class, () -> {
            customer.setName("");
        });
    }

    @Test
    public void testSetContactInfoValid() {
        // Kiểm tra setter cho contactInfo
        Customer customer = new Customer(id, name, contactInfo, accounts);
        String newContactInfo = "jane.doe@example.com";
        customer.setContactInfo(newContactInfo);
        assertEquals(newContactInfo, customer.getContactInfo());
    }

    @Test
    public void testSetContactInfoInvalid() {
        // Kiểm tra setter cho contactInfo với giá trị không hợp lệ
        Customer customer = new Customer(id, name, contactInfo, accounts);
        assertThrows(IllegalArgumentException.class, () -> {
            customer.setContactInfo("");
        });
    }

    @Test
    public void testEqualsAndHashCode() {
        // Kiểm tra phương thức equals và hashCode
        Customer customer1 = new Customer(id, name, contactInfo, accounts);
        Customer customer2 = new Customer(id, name, contactInfo, accounts);

        assertEquals(customer1, customer2);
        assertEquals(customer1.hashCode(), customer2.hashCode());
    }

    @Test
    public void testGetAccountsEmpty() {
        // Kiểm tra phương thức getAccounts với danh sách tài khoản rỗng
        Customer customer = new Customer(id, name, contactInfo, accounts);
        assertTrue(customer.getAccounts().isEmpty());
    }

    @Test
    public void testGetAccountsUnmodifiable() {
        // Kiểm tra tính không thể thay đổi của danh sách trả về từ getAccounts
        Customer customer = new Customer(id, name, contactInfo, accounts);
        assertThrows(UnsupportedOperationException.class, () -> {
            customer.getAccounts().add(new Account());
        });
    }

}
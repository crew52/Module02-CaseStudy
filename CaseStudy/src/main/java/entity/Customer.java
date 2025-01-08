package entity;

import entity.account.Account;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Customer {
    private String id;
    private String name;
    private String contactInfo;
    private List<Account> accounts;

    public Customer() {
    }

    public Customer(String id, String name, String contactInfo, List<Account> accounts) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (contactInfo == null || contactInfo.trim().isEmpty()) {
            throw new IllegalArgumentException("Contact information cannot be null or empty");
        }
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.accounts = accounts != null ? new ArrayList<>(accounts) : new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        if (contactInfo == null || contactInfo.trim().isEmpty()) {
            throw new IllegalArgumentException("Contact information cannot be null or empty");
        }
        this.contactInfo = contactInfo;
    }

    public List<Account> getAccounts() {
        return accounts != null ? Collections.unmodifiableList(accounts) : Collections.emptyList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        // So sánh cả id
        return id.equals(customer.id);
    }

    @Override
    public int hashCode() {
        // Tạo mã hash dựa trên cả id và contactInfo
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", numberOfAccounts=" + (accounts != null ? accounts.size() : 0) +
                '}';
    }
}

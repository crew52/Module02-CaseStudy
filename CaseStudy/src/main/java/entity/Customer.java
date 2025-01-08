package entity;

import entity.account.Account;
import validation.CustomerValidator;

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
        CustomerValidator.validate(id, name, contactInfo);
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.accounts = accounts != null ? new ArrayList<>(accounts) : new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        CustomerValidator.validateId(id);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        CustomerValidator.validateName(name);
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        CustomerValidator.validateContactInfo(contactInfo);
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

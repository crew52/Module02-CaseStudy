package entity;

import entity.account.Account;

import java.util.List;

public class Customer {
    private String id;
    private String name;
    private String contactInfo;
    private List<Account> accounts;

    public Customer() {
    }

    public Customer(String id, String name, String contactInfo, List<Account> accounts) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.accounts = accounts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}

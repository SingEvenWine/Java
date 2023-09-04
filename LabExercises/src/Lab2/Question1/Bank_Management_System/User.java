package Lab2.Question1.Bank_Management_System;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private List<Account> accounts;

    public User() {
    }

    public User(String name, String ID) {
        this.name = name;
        accounts=new ArrayList<>();
    }

    public User(String name) {
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
    }
}

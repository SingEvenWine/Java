package IO_Honework.Homework_5;

import java.io.Serial;
import java.io.Serializable;

public abstract class Account implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String accountNumber;
    private double balance;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
//    public void Interest(){}

    public void Deposit(double amount, String type, int period, double rate){}

    public abstract void Withdrawal(double money);

    public void calculateInterest(){}

    public void RePay(double repaymentAmount){}

    public double getCreditLimit() {
        return 0;
    }
}

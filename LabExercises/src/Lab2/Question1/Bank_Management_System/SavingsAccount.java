package Lab2.Question1.Bank_Management_System;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class SavingsAccount extends Account {
    private static double interestRate;
    private final List<FixedDeposit> fixedDeposits;
    private double balance;

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void setBalance(double balance) {
        this.balance = balance;
    }


    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        SavingsAccount.interestRate = interestRate;
    }

    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
        fixedDeposits = new ArrayList<>();
    }

    @Override
    public void Deposit(double amount, String type, int period, double rate) {
        if (type.equals("活期")) {
            balance += amount;
        } else if (type.equals("定期")) {
            FixedDeposit fd = new FixedDeposit(amount, period, rate, new Date());
            fixedDeposits.add(fd);
            balance += amount;
        }
    }


    @Override
    public void Withdrawal(double money) {
        if (money > balance) {
            System.out.println("Insufficient balance");
        } else {
            balance -= money;
        }
    }

    public void calculateInterest() {
        for (FixedDeposit fd : fixedDeposits) {
            double interest = fd.getAmount() * fd.getInterestRate() * fd.getPeriod();
            balance += interest;
        }
        balance += balance * interestRate;
    }

    private class FixedDeposit {
        private double amount;
        private int period;
        private double interestRate;
        private Date startDate;

        public FixedDeposit(double amount, int period, double interestRate, Date startDate) {
            this.amount = amount;
            this.period = period;
            this.interestRate = interestRate;
            this.startDate = startDate;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public int getPeriod() {
            return period;
        }

        public void setPeriod(int period) {
            this.period = period;
        }

        public double getInterestRate() {
            return interestRate;
        }

        public void setInterestRate(double interestRate) {
            this.interestRate = interestRate;
        }

        public Date getStartDate() {
            return startDate;
        }

        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }
    }
}

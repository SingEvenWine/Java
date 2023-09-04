package Lab2.Question1.Bank_Management_System;

import java.time.Month;
import java.util.Date;

public class CreditCardAccount extends Account {
    private double creditLimit = 20000;
    private double balance;
    private Date date1,date2;

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public CreditCardAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }


    @Override
    public void Withdrawal(double money) {
        if (money > creditLimit) {
            System.out.println("Insufficient creditLimit");
        } else {
            creditLimit -= money;
            date1 = new Date();
            date2=date1;
            date2.setMonth(date2.getMonth()+1);
        }
    }
    public void RePay(){
        if(date2.after(date1)){
            System.out.println("未到还款日,还需还款"+(20000-creditLimit));
        }else {
            System.out.println("请立即还款"+(20000-creditLimit)+"元，否则将影响信誉！");
        }
    }


}

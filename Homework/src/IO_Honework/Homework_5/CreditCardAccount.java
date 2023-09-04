package IO_Honework.Homework_5;

import java.io.Serial;
import java.util.Date;

public class CreditCardAccount extends Account {
    @Serial
    private static final long serialVersionUID = 1L;
    private double creditLimit = 20000;
    private double balance;
    private Date date1,date2;
    private Date lastRepaymentDate;

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
    public void RePay(double repaymentAmount) {
        Date currentDate = new Date();
        if (lastRepaymentDate == null || date2.after(lastRepaymentDate)) {
            double amountDue = 20000 - creditLimit;
            if (repaymentAmount >= amountDue) {
                creditLimit += amountDue;
                System.out.println("已还款 " + amountDue + " 元，信用卡余额已恢复。");
            } else {
                creditLimit += repaymentAmount;
                double remainingAmount = amountDue - repaymentAmount;
                System.out.println("已还款 " + repaymentAmount + " 元，还需还款 " + remainingAmount + " 元。");
            }
            lastRepaymentDate = currentDate;
        } else {
            System.out.println("请在下一个还款日 " + date2 + " 进行还款。");
        }
    }
}

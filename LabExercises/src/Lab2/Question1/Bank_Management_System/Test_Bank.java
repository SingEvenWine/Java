package Lab2.Question1.Bank_Management_System;

public class Test_Bank {
    public static void main(String[] args) {
        User user1=new User("刘京","411381200201316713");
        Account account1=new SavingsAccount("211210400209",0);
        Account account2=new CreditCardAccount("211210400209",0);
        user1.addAccount(account1);
        user1.addAccount(account2);
        System.out.println("账户1余额"+account1.getBalance());
        System.out.println("账户2余额"+account2.getBalance());
        System.out.println("向账户1定期3年存款1000元");
        account1.Deposit(1000,"定期",3,0.035);
        account1.calculateInterest();
        System.out.println("账户1余额"+account1.getBalance());
        account1.Deposit(500,"活期",2,0);
        System.out.println("向账户1活期2年存款500元");
        System.out.println(account1.getBalance());
        System.out.println("信用卡划走5000元后还剩余额度");
        account2.Withdrawal(5000);
        System.out.println(account2.getCreditLimit());
        account2.RePay();
        System.out.println(user1.getAccounts().get(0));
        System.out.println(user1.getAccounts().get(1));
    }
}

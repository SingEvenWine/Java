package IO_Honework.Homework_5;

public class Test_Bank {
    public static void main(String[] args) {
        User user1 = FileUtil.readUserFromFile("E:\\Project\\Java\\Homework\\src\\IO_Honework\\Homework_5\\user1.dat");

        System.out.println("账户1余额" + user1.getAccounts().get(0).getBalance());
        System.out.println("向账户1定期3年存款1000元");
        user1.getAccounts().get(0).Deposit(1000, "定期", 3, 0.035);
        user1.getAccounts().get(0).calculateInterest();
        System.out.println("账户1余额" + user1.getAccounts().get(0).getBalance());
        user1.getAccounts().get(0).Deposit(500, "活期", 2, 0);
        System.out.println("向账户1活期2年存款500元");
        System.out.println("账户1余额" + user1.getAccounts().get(0).getBalance());

        System.out.println("信用卡划走5000元");
        user1.getAccounts().get(1).Withdrawal(5000);
        System.out.println("剩余额度:" + user1.getAccounts().get(1).getCreditLimit());
        user1.getAccounts().get(1).RePay(6000);
        // 保存 User 对象到文件

        FileUtil.saveUserToFile(user1, "E:\\Project\\Java\\Homework\\src\\IO_Honework\\Homework_5\\user1.dat");
    }
}

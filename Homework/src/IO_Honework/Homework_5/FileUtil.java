package IO_Honework.Homework_5;

import java.io.*;

public class FileUtil {
    public static void saveUserToFile(User user, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(user);
        } catch (IOException e) {
            System.err.println("保存用户信息到文件失败：" + e.getMessage());
        }
    }

    public static User readUserFromFile(String fileName) {
        User user = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            user = (User) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("从文件读取用户信息失败：" + e.getMessage());
        }
        return user;
    }
}

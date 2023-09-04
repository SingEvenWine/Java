package Campaign_Management_System.DAO;

import Campaign_Management_System.Entity.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);//添加用户
    User getUserById(int id);//通过ID获取用户信息
    User getUserByUsername(String username);//通过名字获取用户信息
    List<User> getAllUsers();//获取所有用户信息
    void updateUser(User user);//更新用户信息
    void deleteUser(int id);//删除用户信息
}

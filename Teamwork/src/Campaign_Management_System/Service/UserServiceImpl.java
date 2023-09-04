package Campaign_Management_System.Service;

import Campaign_Management_System.DAO.UserDAOImpl;
import Campaign_Management_System.Entity.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDAOImpl userDAO;

    @Override
    public void registerUser(User user) {
        userDAO.addUser(user);
    }

    @Override
    public boolean login(String username, String password) {
        //实现验证
        return true;
    }

    @Override
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }

    @Override
    public void applyForJudge(int userId) {
    }

    @Override
    public void applyForVolunteer(int userId) {

    }
}

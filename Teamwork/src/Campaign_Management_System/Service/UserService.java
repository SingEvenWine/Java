package Campaign_Management_System.Service;

import Campaign_Management_System.Entity.User;

import java.util.List;

public interface UserService {
    void registerUser(User user);

    boolean login(String username, String password);

    User getUserById(int id);

    User getUserByUsername(String username);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUser(int id);

    void applyForJudge(int userId);

    void applyForVolunteer(int userId);
}

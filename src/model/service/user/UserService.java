package model.service.user;

import model.dto.User;

import java.util.List;

public interface UserService {
    User getUserId(int idUser);
    User getUserName(String name, String surname);
    User getUserLogin(String login, String password);
    List<User> listUsers();
    boolean addUser(User user);
    boolean deleteUserId(int idUser);
    boolean updateUser(User user);
}

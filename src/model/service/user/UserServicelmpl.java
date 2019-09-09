package model.service.user;

import model.dao.user.UserDao;
import model.dao.user.UserDaolmpl;
import model.dto.User;

import java.util.List;

public class UserServicelmpl implements UserService {
    private UserDao userDao = new UserDaolmpl();
    @Override
    public User getUserId(int idUser){return userDao.getUserId(idUser);}
    @Override
    public User getUserName(String name, String surname){return userDao.getUserName(name, surname);}
    @Override
    public User getUserLogin(String login, String password){return userDao.getUserLogin(login, password);}
    @Override
    public List<User> listUsers() {return userDao.listUsers();}
    @Override
    public boolean addUser(User user){return userDao.addUser(user);}
    @Override
    public boolean deleteUserId(int idUser){return userDao.deleteUserId(idUser);}
    @Override
    public boolean updateUser(User user){return userDao.updateUser(user);}
}

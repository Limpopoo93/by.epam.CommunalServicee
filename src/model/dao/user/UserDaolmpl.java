package model.dao.user;

import model.dto.Role;
import model.dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static model.dao.Connect.getConnection;

public class UserDaolmpl implements UserDao {
    @Override
    public User getUserId(int idUser) {
        String query = "SELECT* FROM user WHERE idUser= ?";
        User user = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idUser);


            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user =  getUserFromResultSet(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
    @Override
    public User getUserLogin(String login, String password) {
        String query = "SELECT* FROM user WHERE login = ? AND password = ?";
        User user = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);
            statement.setString(2, password);


            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user =  getUserFromResultSet(resultSet);
                }else {
                    user = null;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  user;
    }

    @Override
    public User getUserName(String name, String surname) {
        String query = "SELECT* FROM user WHERE (name, surname) = (?,?)";
        User user = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, surname);


            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user =  getUserFromResultSet(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
    @Override
    public List<User> listUsers() {
        List<User> users = new ArrayList<>();

        String query = "SELECT * FROM user ";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                users.add(getUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    @Override
    public boolean addUser(User user) {

        String query = "INSERT INTO user(login, password, name, surname, role) VALUES(?,?,?,?,?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getSurname());
            statement.setString(5, String.valueOf(user.getRole()));

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    @Override
    public boolean deleteUserId(int idUser) {
        String query = "DELETE FROM user WHERE idUser = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, idUser);
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    @Override
    public boolean updateUser(User user) {
        String query = "UPDATE user SET login = ?, password = ?, name = ?, surname = ?, role = ? WHERE idUser = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getSurname());
            statement.setString(5, String.valueOf(user.getRole()));
            statement.setInt(6, user.getIdUser());
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setIdUser(resultSet.getInt("idUser"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setRole(Role.valueOf(resultSet.getString("role")));
        return user;
    }
}

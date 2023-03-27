package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        String sql = "create table IF NOT EXISTS users\n" +
                "(\n" +
                "    ID        BIGINT auto_increment,\n" +
                "    NAME      VARCHAR(45) not null,\n" +
                "    LAST_NAME VARCHAR(45) null,\n" +
                "    AGE       TINYINT(3)  not null,\n" +
                "    constraint users_pk\n" +
                "        primary key (ID)\n" +
                ")";

        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sql)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";

        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sql)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (NAME, LAST_NAME, AGE) VALUES(?, ?, ?)";

        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE ?";

        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();

        String sql = "SELECT ID, NAME, LAST_NAME, AGE FROM users";
        //Statement statement = null;

        try (Statement statement = Util.getConnection().createStatement()){

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LAST_NAME"));
                user.setAge(resultSet.getByte("AGE"));

                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usersList;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM users";

        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sql)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

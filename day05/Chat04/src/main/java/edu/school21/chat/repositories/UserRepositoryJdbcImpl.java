package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepository {
    private final Connection dataSource;

    public UserRepositoryJdbcImpl(Connection dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<User> findById(Long id) throws SQLException {
        final String SQL_STR = "SELECT * FROM chat.user WHERE id=?";
        User ret = null;

        try {
            PreparedStatement state = dataSource.prepareStatement(SQL_STR);
            state.setLong(1, id);
            ResultSet res = state.executeQuery();
            if (res.next()) {
                ret = new User(res.getLong("id"),
                        res.getString("login"),
                        res.getString("password"),
                        new ArrayList<>(),
                        new ArrayList<>());
            }
        } catch (Exception exc) {
            System.err.println(exc.getMessage());
        }
        return Optional.ofNullable(ret);
    }

    @Override
    public List<User> findAll(int page, int size) {
        final String QUERY = "SELECT * FROM chat.user LIMIT ? OFFSET ?";
        List<User> userList = new ArrayList<>();

        try (Connection connection = dataSource;
        PreparedStatement statement = connection.prepareStatement(QUERY)) {
            statement.setLong(1, size);
            statement.setLong(2, (long) page * size);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    userList.add(new User(
                            resultSet.getLong("id"),
                            resultSet.getString("login"),
                            resultSet.getString("password"),
                            new ArrayList<Chatroom>(),
                            new ArrayList<Chatroom>()));
                }
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }

        return userList;
    }
}

package edu.school21.chat.repositories;

import edu.school21.chat.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepository{
    private final Connection dataSource;

    public UserRepositoryJdbcImpl(Connection dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<User> findById(Long id) throws SQLException {
        final String    SQL_STR = "SELECT * FROM chat.user WHERE id=?";
        User            ret = null;

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
}

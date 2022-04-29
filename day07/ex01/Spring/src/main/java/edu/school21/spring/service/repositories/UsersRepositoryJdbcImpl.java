package edu.school21.spring.service.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.spring.service.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    private final Connection dataSource;

    public UsersRepositoryJdbcImpl(HikariDataSource dataSource) throws SQLException {
        this.dataSource = dataSource.getConnection();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        final String    SQL_STR = "SELECT * FROM service.user WHERE email=?";
        User            ret = null;

        try {
            PreparedStatement state = dataSource.prepareStatement(SQL_STR);
            state.setString(1, email);
            ResultSet res = state.executeQuery();
            if (res.next()) {
                ret = new User(res.getLong("id"),
                        res.getString("email"));
            }
        } catch (Exception exc) {
            System.err.println(exc.getMessage());
        }
        return Optional.ofNullable(ret);
    }

    @Override
    public User findById(Long id) {
        final String    SQL_STR = "SELECT * FROM service.user WHERE id=?";
        User            ret = null;

        try {
            PreparedStatement state = dataSource.prepareStatement(SQL_STR);
            state.setLong(1, id);
            ResultSet res = state.executeQuery();
            if (res.next()) {
                ret = new User(res.getLong("id"),
                        res.getString("email"));
            }
        } catch (Exception exc) {
            System.err.println(exc.getMessage());
        }
        return ret;
    }

    @Override
    public List<User> findAll() {
        Long            id = 1L;
        final String    SQL_STR = "SELECT * FROM service.user";
        List<User>      ret = new ArrayList<>();

        try {
            PreparedStatement state = dataSource.prepareStatement(SQL_STR);
            ResultSet res = state.executeQuery();
            while (res.next()) {
                ret.add(new User(res.getLong("id"),
                        res.getString("email")));
            }
        } catch (Exception exc) {
            System.err.println(exc.getMessage());
        }
        return ret;
    }

    @Override
    public void save(User user) {
        final String QUERY_TEMPLATE = "INSERT INTO service.user (email) VALUES (?) RETURNING *";

        ResultSet resultSet;
        try {
            PreparedStatement query = dataSource.prepareStatement(QUERY_TEMPLATE);
            query.setString(1, user.getEmail());
            resultSet = query.executeQuery();
            resultSet.next();
            user.setId(resultSet.getLong("id"));
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        final String QUERY_TEMPLATE = "UPDATE service.user SET " +
                "email = ? "
                +" WHERE id = ?";
        try {
            PreparedStatement query = dataSource.prepareStatement(QUERY_TEMPLATE);
            query.setString(1, user.getEmail());
            query.setLong(2, user.getId());
            query.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        final String QUERY_TEMPLATE = "DELETE FROM service.user WHERE id = ?";
        try {
            PreparedStatement query = dataSource.prepareStatement(QUERY_TEMPLATE);
            query.setLong(1, id);
            query.executeUpdate();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }
}

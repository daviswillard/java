package edu.school21.spring.service.repositories;

import edu.school21.spring.service.models.User;
import java.sql.PreparedStatement;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setEmail(resultSet.getString("email"));

        return user;
    }
}

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    private final JdbcTemplate                  jdbcTemplate;
    private final DriverManagerDataSource       dataSource;

    public UsersRepositoryJdbcTemplateImpl(DriverManagerDataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    @Override
    public User findById(Long id) {
        final String SQL_STR = "SELECT * FROM service.user WHERE id = ?";
        return jdbcTemplate
                .queryForObject(SQL_STR,
                                new Object[]{id},
                                new UserMapper());
    }

    @Override
    public List<User> findAll() {
        List<User>  ret;
        final String SQL_STR = "SELECT * FROM service.user";

        ret = jdbcTemplate.query(SQL_STR, new UserMapper());
        return ret;
    }

    @Override
    public void save(User entity) {
        final String QUERY_TEMPLATE = "INSERT INTO service.user VALUES (?, ?)";

        jdbcTemplate.update(QUERY_TEMPLATE, entity.getEmail(), entity.getId());
    }

    @Override
    public void update(User entity) {
        final String QUERY_TEMPLATE = "UPDATE service.user SET " +
                "email = ? WHERE id = ?";
        jdbcTemplate.update(QUERY_TEMPLATE, entity.getEmail(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        final String QUERY_TEMPLATE = "DELETE FROM service.user WHERE id = ?";
        jdbcTemplate.update(QUERY_TEMPLATE, id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        final String SQL_STR = "SELECT * FROM service.user WHERE email = ?";
        User ret = jdbcTemplate
                .queryForObject(SQL_STR,
                new UserMapper(),
                email);
        return Optional.ofNullable(ret);
    }
}

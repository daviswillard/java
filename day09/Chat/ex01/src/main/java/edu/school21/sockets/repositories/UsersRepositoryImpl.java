package edu.school21.sockets.repositories;

import edu.school21.sockets.models.User;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UsersRepositoryImpl implements UsersRepository {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public UsersRepositoryImpl(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  @Override
  public User findById(Long id) {
    final String SQL_STR = "SELECT * FROM chat.user WHERE id = ?";
    return jdbcTemplate.
        queryForObject(SQL_STR, new BeanPropertyRowMapper<>(User.class), id);
  }

  @Override
  public List<User> findAll() {
    List<User>  ret;
    final String SQL_STR = "SELECT * FROM chat.user";

    ret = jdbcTemplate.query(SQL_STR, new BeanPropertyRowMapper<>(User.class));
    return ret;
  }

  @Override
  public void save(User entity) {
    final String QUERY_TEMPLATE = "INSERT INTO chat.user (login, password) VALUES (?, ?)";

    jdbcTemplate.update(QUERY_TEMPLATE, entity.getUsername(), entity.getPassword());
  }

  @Override
  public void update(User entity) {
    final String QUERY_TEMPLATE = "UPDATE chat.user SET " +
        "login = ?, password = ? WHERE id = ?";
    jdbcTemplate.update(QUERY_TEMPLATE, entity.getUsername(), entity.getPassword());
  }

  @Override
  public void delete(Long id) {
    jdbcTemplate.update("DELETE FROM chat.user WHERE id = ?", id);
  }

  @Override
  public Optional<User> findByName(String username) {
    return jdbcTemplate.query("SELECT * FROM chat.user WHERE login = ?",
        new BeanPropertyRowMapper<>(User.class)).stream().findAny();
  }
}

package edu.school21.sockets.repositories;

import edu.school21.sockets.models.Message;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessagesRepositoryImpl implements CrudRepository<Message> {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public MessagesRepositoryImpl(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  @Override
  public Message findById(Long id) {
    final String SQL_STR = "SELECT * FROM chat.message WHERE id = ?";
    return jdbcTemplate.
        queryForObject(SQL_STR, new BeanPropertyRowMapper<>(Message.class), id);
  }

  @Override
  public List<Message> findAll() {
    List<Message>  ret;
    final String SQL_STR = "SELECT * FROM chat.message";

    ret = jdbcTemplate.query(SQL_STR, new BeanPropertyRowMapper<>(Message.class));
    return ret;
  }

  @Override
  public void save(Message entity) {
    final String QUERY_TEMPLATE =
        "INSERT INTO chat.message (author, body, timestamp) VALUES (?, ?, ?)";

    jdbcTemplate.update(
        QUERY_TEMPLATE,
        entity.getAuthor(),
        entity.getBody(),
        entity.getTimestamp()
    );
  }

  @Override
  public void update(Message entity) {
    final String QUERY_TEMPLATE = "UPDATE chat.message SET " +
        "author = ?, body = ?, timestamp = ? WHERE id = ?";
    jdbcTemplate.update(
        QUERY_TEMPLATE,
        entity.getAuthor(),
        entity.getBody(),
        entity.getTimestamp()
    );
  }

  @Override
  public void delete(Long id) {
    jdbcTemplate.update("DELETE FROM chat.message WHERE id = ?", id);
  }
}

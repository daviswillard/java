package edu.school21.chat.repositories;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.*;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private final DataSource    dataSource;

    public MessagesRepositoryJdbcImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message>    findById(Long id) throws SQLException {
        final String    SQL_STR = "SELECT * FROM chat.message WHERE id=?";
        Message         ret = null;

        try (Connection con = dataSource.getConnection()) {
            PreparedStatement state = con.prepareStatement(SQL_STR);
            state.setLong(1, id);
            ResultSet res = state.executeQuery();
            if (res.next()) {
                ret = new Message(res.getLong("id"),
                        new User(Long.getLong("-1"), "placeholder", "null", null, null),
                        new Chatroom(Long.getLong("-1"), "placeholder", null, null),
                        res.getString("text"),
                        res.getTimestamp("timestamp").toLocalDateTime());
            }
        }
        return Optional.ofNullable(ret);
    }
}

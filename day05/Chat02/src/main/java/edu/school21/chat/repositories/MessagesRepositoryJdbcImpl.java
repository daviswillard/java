package edu.school21.chat.repositories;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.*;
import edu.school21.chat.exceptions.NotSavedSubEntityException;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private final Connection                    dataSource;
    private final ChatroomRepositoryJdbcImpl    chatroomRepository;
    private final UserRepositoryJdbcImpl        userRepository;

    public MessagesRepositoryJdbcImpl(Connection dataSource, ChatroomRepositoryJdbcImpl chatroomRepository,
                                      UserRepositoryJdbcImpl userRepository) {
        this.dataSource = dataSource;
        this.chatroomRepository = chatroomRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Message>    findById(Long id) throws SQLException {
        final String        SQL_STR = "SELECT * FROM chat.message WHERE id=?";
        Message             ret = null;

        try {
            PreparedStatement state = dataSource.prepareStatement(SQL_STR);
            state.setLong(1, id);
            ResultSet res = state.executeQuery();
            if (res.next()) {
                ret = new Message(res.getLong("id"),
                        userRepository.findById(res.getLong("author")).orElse(null),
                        chatroomRepository.findById(res.getLong("room")).orElse(null),
                        res.getString("text"),
                        res.getTimestamp("timestamp").toLocalDateTime());
            }
        } catch (Exception exc) {
            System.err.println(exc.getMessage());
        }
        return Optional.ofNullable(ret);
    }

    @Override
    public void save(Message message) {
        final String QUERY_TEMPLATE = "INSERT INTO chat.message (author, room, text, timestamp) VALUES (?, ?, ?, ?) RETURNING *";

        ResultSet resultSet;
        try {
            if (userRepository.findById(message.getAuthor().getUserID()).isPresent()
                    && chatroomRepository.findById(message.getRoom().getChatroomID()).isPresent()) {
                PreparedStatement query = dataSource.prepareStatement(QUERY_TEMPLATE);
                query.setLong(1, message.getAuthor().getUserID());
                query.setLong(2, message.getRoom().getChatroomID());
                query.setString(3, message.getText());
                query.setTimestamp(4, Timestamp.valueOf(message.getDateTime()));
                resultSet = query.executeQuery();
                resultSet.next();
                message.setId(resultSet.getLong("id"));
            } else {
                throw new NotSavedSubEntityException();
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }
}

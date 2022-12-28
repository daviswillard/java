package edu.school21.chat.repositories;

import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Message;

import java.sql.*;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private final Connection dataSource;
    private final ChatroomRepositoryJdbcImpl chatroomRepository;
    private final UserRepositoryJdbcImpl userRepository;

    public MessagesRepositoryJdbcImpl(Connection dataSource, ChatroomRepositoryJdbcImpl chatroomRepository,
                                      UserRepositoryJdbcImpl userRepository) {
        this.dataSource = dataSource;
        this.chatroomRepository = chatroomRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Message>    findById(Long id) {
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

    @Override
    public void update(Message message) {
        final String QUERY_TEMPLATE = "UPDATE chat.message SET text = ?, timestamp = ? WHERE (author=? AND room=?)";

        try {
            PreparedStatement query = dataSource.prepareStatement(QUERY_TEMPLATE);
            query.setLong(3, message.getAuthor().getUserID());
            query.setLong(4, message.getRoom().getChatroomID());
            query.setString(1, message.getText());
            if (message.getDateTime() != null) {
                query.setTimestamp(2, Timestamp.valueOf(message.getDateTime()));
            } else {
                query.setTimestamp(2, null);
            }
            if (query.executeUpdate() > 0) {
                System.out.println("Message(s) updated");
            } else {
                System.err.println("Couldn't update any message");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

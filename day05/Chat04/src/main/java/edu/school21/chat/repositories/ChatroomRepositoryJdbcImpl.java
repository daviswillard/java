package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ChatroomRepositoryJdbcImpl implements ChatroomRepository {
    private final Connection dataSource;
    private final UserRepository userRepository;

    public ChatroomRepositoryJdbcImpl(Connection dataSource, UserRepository userRepository) {
        this.dataSource = dataSource;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Chatroom> findById(Long id) throws SQLException {
        final String    SQL_STR = "SELECT * FROM chat.chatroom WHERE id=?";
        Chatroom        ret = null;

        try {
            PreparedStatement state = dataSource.prepareStatement(SQL_STR);
            state.setLong(1, id);
            ResultSet res = state.executeQuery();
            if (res.next()) {
                ret = new Chatroom(res.getLong("id"),
                        res.getString("name"),
                        userRepository.findById(res.getLong("owner")).orElse(null),
                        new ArrayList<>());
            }
        } catch (SQLException exc) {
            System.err.println(exc.getMessage());
        }
        return Optional.ofNullable(ret);
    }
}

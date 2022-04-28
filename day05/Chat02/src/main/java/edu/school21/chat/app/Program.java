package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariConfig;
import edu.school21.chat.models.*;
import edu.school21.chat.repositories.*;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Program {
    private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASS = "postgres";

    private static HikariDataSource HikariConnect() {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(DB_URL);
        config.setUsername(DB_USER);
        config.setPassword(DB_PASS);
        config.addDataSourceProperty("cachePrepStmts", "true");
        return new HikariDataSource(config);
    }

    public static void main(String[] argv) {

        try (HikariDataSource dataSource = new HikariDataSource(HikariConnect())) {
            Class.forName("org.postgresql.Driver");
            Connection con = dataSource.getConnection();
            UserRepositoryJdbcImpl userRep = new UserRepositoryJdbcImpl(con);
            ChatroomRepositoryJdbcImpl chatRep = new ChatroomRepositoryJdbcImpl(con, userRep);
            MessagesRepositoryJdbcImpl msgRep = new MessagesRepositoryJdbcImpl(con, chatRep, userRep);
            User creator = new User(4L, "user", "user", new ArrayList<>(), new ArrayList<>());
            Chatroom room = new Chatroom(4L, "room", creator, new ArrayList<>());
            Message message = new Message(null, creator, room, "Hello!", LocalDateTime.now());
            msgRep.save(message);
            System.out.println(message.getId());
            con.close();
        } catch (Exception exc) {
            System.err.println(exc.getMessage());
        }
    }
}

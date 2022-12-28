package edu.school21.chat.app;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.ChatroomRepositoryJdbcImpl;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import edu.school21.chat.repositories.UserRepositoryJdbcImpl;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

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

        try (HikariDataSource dataSource = new HikariDataSource(HikariConnect());
             Connection con = dataSource.getConnection()) {

            UserRepositoryJdbcImpl userRep = new UserRepositoryJdbcImpl(con);
            ChatroomRepositoryJdbcImpl chatRep = new ChatroomRepositoryJdbcImpl(con, userRep);
            MessagesRepositoryJdbcImpl msgRep = new MessagesRepositoryJdbcImpl(con, chatRep, userRep);

            {
                List<User> users = userRep.findAll(0, 2);
                System.out.println(users);
            }
        } catch (Exception exc) {
           exc.printStackTrace();
        }
    }
}

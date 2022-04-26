package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariConfig;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.*;
import java.util.Optional;
import java.util.Scanner;

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
            Scanner console = new Scanner(System.in);
            System.out.println("Enter id of searched message");
            long id = console.nextLong();
            Class.forName("org.postgresql.Driver");
            MessagesRepositoryJdbcImpl obj = new MessagesRepositoryJdbcImpl(dataSource);
            Optional<Message> message = obj.findById(id);
            System.out.println(message.orElse(null));
        } catch (Exception exc) {
            System.err.println(exc.getMessage());
        }
    }
}

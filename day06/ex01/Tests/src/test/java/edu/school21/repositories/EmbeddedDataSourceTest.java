package edu.school21.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import static org.junit.jupiter.api.Assertions.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class EmbeddedDataSourceTest {

    private DataSource dataSource;

    @BeforeEach
    public void init() throws SQLException {
        EmbeddedDatabaseBuilder base = new EmbeddedDatabaseBuilder();;
        dataSource = base.setType(EmbeddedDatabaseType.HSQL)
                .addScript("/schema.sql")
                .addScript("/data.sql").build();
    }

    @Test
    public void getConnectionShouldReturnVal() throws SQLException {
        Connection con = dataSource.getConnection();
        assertNotNull(con);
    }
}

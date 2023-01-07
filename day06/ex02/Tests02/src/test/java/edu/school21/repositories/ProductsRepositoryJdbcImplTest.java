package edu.school21.repositories;

import models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import repository.ProductsRepositoryJdbcImpl;

import static org.junit.jupiter.api.Assertions.*;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductsRepositoryJdbcImplTest {

    private DataSource dataSource;
    ProductsRepositoryJdbcImpl productsRepositoryJdbc;

    private final List<Product> EXPECTED_PRODUCT_LIST =
           Stream.of(
                        new Product(0L, "pc", 100000),
                        new Product(1L, "keyboard", 7000),
                        new Product(2L, "display", 30000),
                        new Product(3L, "mouse", 1000),
                        new Product(4L, "tablet", 5000)
                   )
                   .collect(Collectors.toList());
    private final Product EXPECTED_FIND_BY_ID_PRODUCT =
            new Product(0L, "pc", 100000);
    private final Product EXPECTED_UPDATE_PRODUCT =
            new Product(1L, "keyboard", 8500);
    private final Product EXPECTED_DELETE_PRODUCT =
            new Product(2L, "display", 30000);
    private final Product EXPECTED_SAVE_PRODUCT =
            new Product(5L, "cord", 500);

    static void assertProducts(Product expected, Product actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getPrice(), actual.getPrice());
    }

    @BeforeEach
    public void init() {
        EmbeddedDatabaseBuilder base = new EmbeddedDatabaseBuilder();
        dataSource = base.setType(EmbeddedDatabaseType.HSQL)
                .addScript("/schema.sql")
                .addScript("/data.sql").build();
        productsRepositoryJdbc = new ProductsRepositoryJdbcImpl(dataSource);
    }

    @Test
    void getConnectionTEst() throws SQLException {
        assertNotNull(dataSource.getConnection());
    }

    @Test
    void findAllTest() {
        List<Product> list = productsRepositoryJdbc.findAll();
        for (int i = 0; i < list.size(); i++) {
            assertProducts(EXPECTED_PRODUCT_LIST.get(i), list.get(i));
        }
    }

    @Test
    void findByIdTest() {
        assertProducts(EXPECTED_FIND_BY_ID_PRODUCT, productsRepositoryJdbc.findById(0L).get());
    }

    @Test
    void updateTest() {
        productsRepositoryJdbc.update(EXPECTED_UPDATE_PRODUCT);
        assertProducts(EXPECTED_UPDATE_PRODUCT, productsRepositoryJdbc.findById(1L).get());
    }

    @Test
    void deleteTest() {
        Product temp = productsRepositoryJdbc.findById(EXPECTED_DELETE_PRODUCT.getId()).get();
        productsRepositoryJdbc.delete(temp.getId());
        assert(!productsRepositoryJdbc.findById(EXPECTED_DELETE_PRODUCT.getId()).isPresent());
    }

    @Test
    void saveTest() {
        productsRepositoryJdbc.save(EXPECTED_SAVE_PRODUCT);
        assertProducts(EXPECTED_SAVE_PRODUCT, productsRepositoryJdbc.findById(5L).get());
    }
}

package repository;

import models.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {

    private final DataSource dataSource;

    public ProductsRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> findAll() {
        final String SQL_STR = "SELECT * FROM products";
        List<Product> list = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_STR)) {
            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) {
                    list.add(new Product(
                            set.getLong("id"),
                            set.getString("name"),
                            set.getInt("price")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Optional<Product> findById(Long id) {
        final String SQL_STR = "SELECT * FROM products WHERE id=?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_STR)) {
            statement.setLong(1, id);
            try (ResultSet set = statement.executeQuery()) {
                if (set.next()) {
                    return Optional.of(
                            new Product(
                                    id,
                                    set.getString("name"),
                                    set.getInt("price")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void update(Product product) {
        final String QUERY_TEMPLATE = "UPDATE products SET price = ? WHERE name = ?";

        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE)) {
            statement.setInt(1, product.getPrice());
            statement.setString(2, product.getName());
            if (statement.executeUpdate() > 0) {
                System.out.println("Price successfully updated!");
            } else {
                System.err.println("Failed to update the price");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void save(Product product) {
        final String QUERY_TEMPLATE = "INSERT INTO products (name, price) VALUES (?, ?)";

        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE)) {
            statement.setString(1, product.getName());
            statement.setInt(2, product.getPrice());
            int i = statement.executeUpdate();
            System.out.println(i + " rows were updated!");
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        final String QUERY_TEMPLATE = "DELETE FROM products WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE)) {
            statement.setLong(1, id);
            int res = statement.executeUpdate();
            System.out.println(res + " rows affected!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}


package com.example.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtils {

    private static final String URL =
            "jdbc:h2:mem:hcl_ecommerce;DB_CLOSE_DELAY=-1";

    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void createProductsTable() {

        String sql = "CREATE TABLE IF NOT EXISTS products ("
                + "product_id INT PRIMARY KEY,"
                + "product_name VARCHAR(100) NOT NULL,"
                + "price DECIMAL(10,2) CHECK (price >= 0),"
                + "stock INT CHECK (stock >= 0))";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(sql);

            System.out.println("Products table created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


package com.example.tests;

import com.example.utilities.DatabaseUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTest {

    @Test
    public void verifyProductCRUD() throws Exception {

        DatabaseUtils.createProductsTable();

        try (Connection connection = DatabaseUtils.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(
                "INSERT INTO products VALUES (1, 'Laptop', 55000, 10)");
            System.out.println("INSERT PASSED");

            ResultSet result = statement.executeQuery(
                "SELECT * FROM products WHERE product_id = 1");

            Assert.assertTrue(result.next());
            Assert.assertEquals(result.getString("product_name"), "Laptop");
            Assert.assertEquals(result.getInt("stock"), 10);
            System.out.println("SELECT PASSED");

            statement.executeUpdate(
                "UPDATE products SET price = 60000, stock = 8 WHERE product_id = 1");

            result = statement.executeQuery(
                "SELECT * FROM products WHERE product_id = 1");

            Assert.assertTrue(result.next());
            Assert.assertEquals(result.getDouble("price"), 60000.0);
            Assert.assertEquals(result.getInt("stock"), 8);
            System.out.println("UPDATE PASSED");

            statement.executeUpdate(
                "DELETE FROM products WHERE product_id = 1");

            result = statement.executeQuery(
                "SELECT * FROM products WHERE product_id = 1");

            Assert.assertFalse(result.next());
            System.out.println("DELETE PASSED");
        }
    }

    @Test(dependsOnMethods = "verifyProductCRUD")
    public void verifyDatabaseValidation() throws Exception {

        try (Connection connection = DatabaseUtils.getConnection();
             Statement statement = connection.createStatement()) {

            // Insert valid product
            statement.executeUpdate(
                "INSERT INTO products VALUES (2, 'Mouse', 500, 20)");

            // Duplicate primary key validation
            Assert.assertThrows(SQLException.class, () ->
                statement.executeUpdate(
                    "INSERT INTO products VALUES (2, 'Keyboard', 1000, 15)")
            );
            System.out.println("DUPLICATE ID VALIDATION PASSED");

            // Negative stock validation
            Assert.assertThrows(SQLException.class, () ->
                statement.executeUpdate(
                    "INSERT INTO products VALUES (3, 'Monitor', 10000, -5)")
            );
            System.out.println("NEGATIVE STOCK VALIDATION PASSED");

            // Missing product validation
            ResultSet result = statement.executeQuery(
                "SELECT * FROM products WHERE product_id = 999");

            Assert.assertFalse(result.next());
            System.out.println("MISSING PRODUCT VALIDATION PASSED");
        }
    }
}

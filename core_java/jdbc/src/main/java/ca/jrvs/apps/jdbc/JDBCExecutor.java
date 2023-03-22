package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExecutor {
    public static void main(String... args) {
        DatabaseConnectionManager databaseConnectionManager = new DatabaseConnectionManager("localhost", "jdbc_db", "postgres", "password");
        try {
            Connection connection = databaseConnectionManager.getConnection();
            CustomerDAO customerDAO = new CustomerDAO(connection);

            OrderDAO orderDAO = new OrderDAO(connection);
            Order order = orderDAO.findById(1000);
            System.out.println(order.getId() + " " + order.getCustomer_firstName());
            order.getOrderItemsList().stream().forEach(i -> System.out.println(
                    i.getProduct_code() + " " + i.getProduct_name()
            ));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

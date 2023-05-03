package ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.util.DataAccessObject;
import ca.jrvs.apps.jdbc.util.DataTransferObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends DataAccessObject<Order> {
    private static final String GET_ORDER = "SELECT  o.order_id, c.first_name, c.last_name, c.email, s.first_name," +
            "s.last_name, s.email, o.status, o.creation_date, o.total_due, d.quantity, p.code, p.name," +
            "p.size, p.price FROM orders o JOIN customer c ON o.customer_id = c.customer_id" +
            " JOIN salesperson s ON o.salesperson_id = s.salesperson_id" +
            " JOIN order_item d ON d.order_id = o.order_id" +
            " JOIN product p ON d.product_id = p.product_id" +
            " WHERE o.order_id = ?;";

    public OrderDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Order findById(long id) {
        Order order = new Order();

        List<OrderItems> orderItemsList = new ArrayList<OrderItems>();

        try {
            PreparedStatement statement = this.connection.prepareStatement(GET_ORDER);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                if (order.getId() == 0) {
                    order.setId(rs.getLong(1));
                    order.setCustomer_firstName(rs.getString(2));
                    order.setCustomer_lastName(rs.getString(3));
                    order.setCustomer_email(rs.getString(4));
                    order.setSalesperson_firstName(rs.getString(5));
                    order.setSalesperson_lastName(rs.getString(6));
                    order.setSalesperson_email(rs.getString(7));
                    order.setStatus(rs.getString(8));
                    order.setCreation_date(rs.getDate(9));
                    order.setTotal_due(rs.getBigDecimal(10));
                }
                OrderItems orderItems = new OrderItems();
                orderItems.setQuantity(rs.getInt(11));
                orderItems.setProduct_code(rs.getString(12));
                orderItems.setProduct_name(rs.getString(13));
                orderItems.setProduct_size(rs.getInt(14));
                orderItems.setProduct_price(rs.getBigDecimal(15));
                orderItemsList.add(orderItems);
            }
            order.setOrderItemsList(orderItemsList);
            return order;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public Order update(Order dto) {
        return null;
    }

    @Override
    public Order create(Order dto) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}

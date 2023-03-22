package ca.jrvs.apps.jdbc;

import java.math.BigDecimal;

public class OrderItems {
    private int quantity;
    private String product_code;
    private String product_name;
    private int product_size;
    private BigDecimal product_price;

    public OrderItems() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getProduct_size() {
        return product_size;
    }

    public void setProduct_size(int product_size) {
        this.product_size = product_size;
    }

    public BigDecimal getProduct_price() {
        return product_price;
    }

    public void setProduct_price(BigDecimal product_price) {
        this.product_price = product_price;
    }

    @Override
    public String toString() {
        return "OrderItems{" +
                "quantity=" + quantity +
                ", product_code='" + product_code + '\'' +
                ", product_name='" + product_name + '\'' +
                ", product_size=" + product_size +
                ", product_price=" + product_price +
                '}';
    }
}

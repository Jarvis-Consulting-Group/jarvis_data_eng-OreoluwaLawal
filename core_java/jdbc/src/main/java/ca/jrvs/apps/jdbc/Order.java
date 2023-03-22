package ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.util.DataTransferObject;

import java.util.Date;
import java.math.BigDecimal;
import java.util.List;

public class Order implements DataTransferObject {
    private long id;
    private String customer_firstName;
    private String customer_lastName;
    private String customer_email;
    private String salesperson_firstName;
    private String salesperson_lastName;
    private String salesperson_email;
    private Date creation_date;
    private BigDecimal total_due;
    private String status;
    private List<OrderItems> orderItemsList;


    public Order() {
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomer_firstName() {
        return customer_firstName;
    }

    public void setCustomer_firstName(String customer_firstName) {
        this.customer_firstName = customer_firstName;
    }

    public String getCustomer_lastName() {
        return customer_lastName;
    }

    public void setCustomer_lastName(String customer_lastName) {
        this.customer_lastName = customer_lastName;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getSalesperson_firstName() {
        return salesperson_firstName;
    }

    public void setSalesperson_firstName(String salesperson_firstName) {
        this.salesperson_firstName = salesperson_firstName;
    }

    public String getSalesperson_lastName() {
        return salesperson_lastName;
    }

    public void setSalesperson_lastName(String salesperson_lastName) {
        this.salesperson_lastName = salesperson_lastName;
    }

    public String getSalesperson_email() {
        return salesperson_email;
    }

    public void setSalesperson_email(String salesperson_email) {
        this.salesperson_email = salesperson_email;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public BigDecimal getTotal_due() {
        return total_due;
    }

    public void setTotal_due(BigDecimal total_due) {
        this.total_due = total_due;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderItems> getOrderItemsList() {
        return orderItemsList;
    }

    public void setOrderItemsList(List<OrderItems> orderItemsList) {
        this.orderItemsList = orderItemsList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer_firstName='" + customer_firstName + '\'' +
                ", customer_lastName='" + customer_lastName + '\'' +
                ", customer_email='" + customer_email + '\'' +
                ", salesperson_firstName='" + salesperson_firstName + '\'' +
                ", salesperson_lastName='" + salesperson_lastName + '\'' +
                ", salesperson_email='" + salesperson_email + '\'' +
                ", creation_date=" + creation_date +
                ", total_due=" + total_due +
                ", status='" + status + '\'' +
                ", orderItemsList=" + orderItemsList +
                '}';
    }
}

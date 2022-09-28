package Exercici1;

import Exercici1.Customer;

import java.util.Date;

public class Visit {
    private Customer customer;
    private Date date;
    private double serviceExpense;
    private double productExpense;

    public Visit(Customer customer, Date date) {
        this.customer = customer;
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getServiceExpense() {
        double discount = serviceExpense * DiscountRate.getServiceDiscountRate(customer.getMemberType());
        return serviceExpense - discount;
    }

    public void setServiceExpense(double serviceExpense) {
        this.serviceExpense = serviceExpense;
    }

    public double getProductExpense() {
        double discount = productExpense * DiscountRate.getProductDiscountRate(customer.getMemberType());
        return productExpense - discount;
    }

    public void setProductExpense(double productExpense) {
        this.productExpense = productExpense;
    }

    public double getTotalExpense() {
        return this.getProductExpense() + this.getServiceExpense();
    }

    @Override
    public String toString() {
        String s = "Compra hecha por el cliente " + this.customer.getName() + " a dia de " + this.date;
        s += String.format("\nPrecio Producto: %.2f", this.getProductExpense());
        s += String.format("\nPrecio Servicio: %.2f", this.getServiceExpense());
        s += String.format("\nTotal: %.2f", this.getTotalExpense());
        return s;
    }
}
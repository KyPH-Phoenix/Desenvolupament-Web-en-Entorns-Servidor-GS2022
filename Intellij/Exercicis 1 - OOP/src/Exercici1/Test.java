package Exercici1;

import java.util.Date;

public class Test {
    public static void main(String[] args) {

        System.out.println();

        Customer customer = new Customer("Manolo Lamas");
        customer.setMemberType("Premium");
        System.out.println(customer.toString());

        Visit visit = new Visit(customer, new Date());
        visit.setProductExpense(102.33);
        visit.setServiceExpense(21.00);

        System.out.println(visit);


    }
}
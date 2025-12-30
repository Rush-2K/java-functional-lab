package functionalinterface;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

// takes one argument, return nothing/void
public class _Consumer {

    public static void main(String[] args) {
        Customer customer1 = new Customer("Maria", "9999");
        Customer customer2 = new Customer("Leo", "1234");

        // Normal java function
        greetCustomer(customer1);

        // Consumer Functional interface
        greetCustomerConsumer.accept(customer2);

        greetCustomerConsumerV2.accept(customer2, false);

    }

    static BiConsumer<Customer, Boolean> greetCustomerConsumerV2 = (customer, showPhoneNumber)
            -> System.out.println("Hello " + customer.customerName +
            ", thanks for registering phone number "
            + (showPhoneNumber ? customer.customerPhoneNumber : "********"));

    static Consumer<Customer> greetCustomerConsumer = customer ->
            System.out.println("Hello " + customer.customerName +
                    ", thanks for registering phone number "
                    + customer.customerPhoneNumber);

    static void greetCustomer(Customer customer) {
        System.out.println("Hello " + customer.customerName +
                ", thanks for registering phone number "
                + customer.customerPhoneNumber);
    }

    static class Customer {
        private final String customerName;
        private final String customerPhoneNumber;

        public Customer(String customerName, String customerPhoneNumber) {
            this.customerName = customerName;
            this.customerPhoneNumber = customerPhoneNumber;
        }
    }
}

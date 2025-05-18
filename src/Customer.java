public class Customer extends User {
    int customerId;

    public Customer(String name, String email, int id) {
        super(name, email);
        this.customerId = id;
    }

    @Override
    public void displayInfo() {
        System.out.println("Customer: " + name);
    }

    @Override
    public void accessSystem() {
        System.out.println("Customer access granted");
    }
}
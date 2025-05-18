public class Admin extends User {
    int adminId;

    public Admin(String name, String email, int id) {
        super(name, email);
        this.adminId = id;
    }

    @Override
    public void displayInfo() {
        System.out.println("Admin: " + name);
    }

    @Override
    public void accessSystem() {
        System.out.println("Admin access granted");
    }
}
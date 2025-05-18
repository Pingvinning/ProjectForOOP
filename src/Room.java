public abstract class Room {
    protected int number;
    protected double price;

    public Room(int number, double price) {
        this.number = number;
        this.price = price;
    }

    // 1st abstract method
    public abstract String getDetails();

    // 2nd abstract method
    public abstract int getCapacity();

    @Override
    public String toString() {
        return "Room: " + number + ", Price: " + price + ", Capacity: " + getCapacity();
    }
}
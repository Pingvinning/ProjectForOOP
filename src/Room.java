public abstract class Room {
    protected int number;
    protected double price;

    public Room(int number, double price) {
        this.number = number;
        this.price = price;
    }


    public abstract String getDetails();


    public abstract int getCapacity();

    @Override
    public String toString() {
        return "Room: " + number + ", Price: " + price + ", Capacity: " + getCapacity();
    }
}
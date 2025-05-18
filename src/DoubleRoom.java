public class DoubleRoom extends Room {
    public DoubleRoom(int number, double price) {
        super(number, price);
    }

    @Override
    public String getDetails() {
        return "Double room " + number + " costs " + price;
    }

    @Override
    public int getCapacity() {
        return 2;
    }
}
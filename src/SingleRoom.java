public class SingleRoom extends Room {
    public SingleRoom(int number, double price) {
        super(number, price);
    }

    @Override
    public String getDetails() {
        return "Single room " + number + " costs " + price;
    }

    @Override
    public int getCapacity() {
        return 1;
    }
}
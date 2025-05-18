import java.util.Objects;

public class Reservation {
    Customer customer;
    Room room;
    String date;

    public Reservation(Customer customer, Room room, String date) {
        this.customer = customer;
        this.room = room;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Reservation)) return false;
        Reservation r = (Reservation) o;
        return date.equals(r.date) && customer.equals(r.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, customer);
    }

    @Override
    public String toString() {
        return "Reservation for " + customer.name + " in room " + room.number + " on " + date;
    }
}

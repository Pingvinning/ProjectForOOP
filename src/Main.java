import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<User> users = new ArrayList<>();
        Set<User> userSet = new HashSet<>();
        List<Room> allRooms = new ArrayList<>();
        Map<Integer, Room> roomMap = new HashMap<>();
        List<Reservation> reservationQueue = new LinkedList<>();
        List<Reservation> reservations = new ArrayList<>();

        Hotel.totalRooms = 5;
        Hotel.showTotalRooms();

        Room[] rooms = new Room[5];
        double[] prices = new double[5];
        String[] types = new String[5];

        for (int i = 0; i < rooms.length; i++) {
            if (i % 2 == 0) {
                rooms[i] = new SingleRoom(i + 100, 50 + i * 10);
                types[i] = "Single";
            } else {
                rooms[i] = new DoubleRoom(i + 100, 70 + i * 15);
                types[i] = "Double";
            }
            prices[i] = rooms[i].price;
            allRooms.add(rooms[i]);
            roomMap.put(rooms[i].number, rooms[i]);
        }

        Utils.printArray(rooms);

        int choice;
        do {
            System.out.println("===== Hotel Reservation System =====");
            System.out.println("1. Add customer");
            System.out.println("2. Add admin");
            System.out.println("3. Show all rooms");
            System.out.println("4. Make reservation");
            System.out.println("5. Show reservations");
            System.out.println("6. Sort rooms by price");
            System.out.println("7. Find room by type");
            System.out.println("8. Find room by price");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // Add customer
                    System.out.print("Enter customer name: ");
                    String cname = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String cemail = scanner.nextLine();
                    System.out.print("Enter customer ID: ");
                    int cid = scanner.nextInt();
                    scanner.nextLine();
                    User customer = new Customer(cname, cemail, cid); // upcasting
                    users.add(customer);
                    userSet.add(customer);
                    customer.displayInfo();
                    customer.accessSystem();
                    System.out.println("Customer added.");
                    break;
                case 2:
                    // Add admin
                    System.out.print("Enter admin name: ");
                    String aname = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String aemail = scanner.nextLine();
                    System.out.print("Enter admin ID: ");
                    int aid = scanner.nextInt();
                    scanner.nextLine();
                    User admin = new Admin(aname, aemail, aid); // upcasting
                    users.add(admin);
                    userSet.add(admin);
                    System.out.println("Admin added.");
                    admin.displayInfo();
                    admin.accessSystem();
                    EmailNotifier emailNotifier = new EmailNotifier();
                    emailNotifier.sendNotification(aemail);
                    break;
                case 3:
                    // Show rooms
                    System.out.println("Available rooms:");
                    for (Room room : allRooms) {
                        System.out.println(room.getDetails());
                    }
                    break;
                case 4:
                    // Make reservation
                    System.out.print("Enter customer ID: ");
                    int searchId = scanner.nextInt();
                    scanner.nextLine();
                    Customer foundCustomer = null;
                    for (User u : users) {
                        if (u instanceof Customer) {
                            Customer c = (Customer) u; // downcasting
                        }
                    }
                    if (foundCustomer == null) {
                        System.out.println("Customer not found.");
                        break;
                    }
                    System.out.print("Enter room number: ");
                    int roomNumber = scanner.nextInt();
                    scanner.nextLine();
                    Room selectedRoom = roomMap.get(roomNumber);
                    if (selectedRoom == null) {
                        System.out.println("Room not found.");
                        break;
                    }
                    System.out.print("Enter reservation date (yyyy-mm-dd): ");
                    String date = scanner.nextLine();
                    try {
                        if (date.length() < 10) throw new InvalidDateException("Invalid date format");
                        if (selectedRoom.price <= 0) throw new IllegalArgumentException("Room price must be positive");
                        Reservation reservation = new Reservation(foundCustomer, selectedRoom, date);
                        reservations.add(reservation);
                        reservationQueue.add(reservation);
                        System.out.println(RoomStatus.AVAILABLE + " Reservation added.");
                    } catch (InvalidDateException ex) {
                        System.out.println("Date Error: " + ex.getMessage());
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Argument Error: " + ex.getMessage());
                    }
                    break;
                case 5:
                    // Show reservations
                    System.out.println("All reservations:");
                    for (Reservation r : reservations) {
                        System.out.println(r);
                    }
                    break;
                case 6:
                    // Sort rooms by price using anonymous Comparator
                    System.out.println("Sorting rooms by price (ascending)...");
                    Collections.sort(allRooms, new Comparator<Room>() {
                        @Override
                        public int compare(Room r1, Room r2) {
                            return Double.compare(r1.price, r2.price);
                        }
                    });
                    // Print sorted
                    for (Room r : allRooms) {
                        System.out.println(r);
                    }

                    // Sort rooms by number descending using anonymous Comparator
                    System.out.println("Sorting rooms by number (descending)...");
                    Collections.sort(allRooms, new Comparator<Room>() {
                        @Override
                        public int compare(Room r1, Room r2) {
                            return Integer.compare(r2.number, r1.number);
                        }
                    });
                    for (Room r : allRooms) {
                        System.out.println(r);
                    }
                    break;
                case 7:
                    System.out.print("Enter room type to search (Single/Double): ");
                    String searchType = scanner.nextLine();
                    for (Room r : allRooms) {
                        if (searchType.equalsIgnoreCase("Single") && r instanceof SingleRoom) {
                            System.out.println(r);
                        } else if (searchType.equalsIgnoreCase("Double") && r instanceof DoubleRoom) {
                            System.out.println(r);
                        }
                    }
                    break;
                case 8:

                    System.out.print("Enter max price: ");
                    double maxPrice = scanner.nextDouble();
                    scanner.nextLine();
                    for (Room r : allRooms) {
                        if (r.price <= maxPrice) {
                            System.out.println(r);
                        }
                    }
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (choice != 0);

        scanner.close();
    }
}
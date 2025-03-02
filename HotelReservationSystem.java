import java.util.*;

public class HotelReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();
        hotel.initializeRooms();

        boolean running = true;

        while (running) {
            System.out.println("\n==== Hotel Reservation System ====");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View All Reservations");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    hotel.viewAvailableRooms();
                    break;
                case 2:
                    System.out.print("Enter room number to book: ");
                    int roomNumber = scanner.nextInt();
                    System.out.print("Enter your name: ");
                    scanner.nextLine(); // Consume newline
                    String name = scanner.nextLine();
                    System.out.print("Enter payment amount: ");
                    double payment = scanner.nextDouble();
                    hotel.makeReservation(roomNumber, name, payment);
                    break;
                case 3:
                    hotel.viewAllReservations();
                    break;
                case 4:
                    running = false;
                    System.out.println("Thank you for using the Hotel Reservation System!");
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }

        scanner.close();
    }
}class Hotel {
    private List<Room> rooms = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();

    // Initialize rooms
    public void initializeRooms() {
        rooms.add(new Room(101, "Single", 100));
        rooms.add(new Room(102, "Double", 150));
        rooms.add(new Room(103, "Suite", 300));
        rooms.add(new Room(104, "Single", 100));
        rooms.add(new Room(105, "Double", 150));
    }

    // View available rooms
    public void viewAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (!room.isBooked()) {
                System.out.println(room);
            }
        }
    }

    // Make a reservation
    public void makeReservation(int roomNumber, String guestName, double payment) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                if (!room.isBooked()) {
                    if (payment >= room.getPrice()) {
                        room.setBooked(true);
                        reservations.add(new Reservation(guestName, room, payment));
                        System.out.println("Reservation successful for " + guestName + " in Room " + roomNumber);
                    } else {
                        System.out.println("Insufficient payment. Room costs: $" + room.getPrice());
                    }
                } else {
                    System.out.println("Room is already booked!");
                }
                return;
            }
        }
        System.out.println("Room not found!");
    }

    // View all reservations
    public void viewAllReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            System.out.println("\nAll Reservations:");
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }
}class Room {
    private int roomNumber;
    private String category;
    private double price;
    private boolean booked;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.booked = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + category + ") - $" + price;
    }
}class Reservation {
    private String guestName;
    private Room room;
    private double payment;

    public Reservation(String guestName, Room room, double payment) {
        this.guestName = guestName;
        this.room = room;
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Guest: " + guestName + ", Room: " + room.getRoomNumber() + " (" + room.getCategory() + "), Paid: $" + payment;
    }
}
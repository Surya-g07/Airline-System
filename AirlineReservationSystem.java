import java.util.*;

class Flight {
    int flightNo;
    String source, destination;
    double price;

    Flight(int flightNo, String source, String destination, double price) {
        this.flightNo = flightNo;
        this.source = source;
        this.destination = destination;
        this.price = price;
    }

    public String toString() {
        return flightNo + " - " + source + " to " + destination + " - Rs." + price;
    }
}

class Reservation {
    int bookingId;
    String passengerName;
    int age;
    Flight flight;

    Reservation(int bookingId, String passengerName, int age, Flight flight) {
        this.bookingId = bookingId;
        this.passengerName = passengerName;
        this.age = age;
        this.flight = flight;
    }

    public String toString() {
        return "BookingID: " + bookingId + ", Name: " + passengerName + ", Age: " + age +
               ", Flight: " + flight.toString();
    }
}

public class AirlineReservationSystem {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Flight> flights = new ArrayList<>();
    static ArrayList<Reservation> reservations = new ArrayList<>();
    static int bookingCounter = 1000;

    public static void main(String[] args) {
        flights.add(new Flight(101, "Chennai", "Delhi", 5000));
        flights.add(new Flight(102, "Coimbatore", "Bangalore", 2000));
        flights.add(new Flight(103, "Mumbai", "Goa", 3500));
        flights.add(new Flight(104, "Hyderabad", "Kolkata", 4500));

        while (true) {
            System.out.println("\n=== Airline Reservation System ===");
            System.out.println("1. View Flights");
            System.out.println("2. Book Ticket");
            System.out.println("3. Cancel Ticket");
            System.out.println("4. View Reservations");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1: viewFlights(); break;
                case 2: bookTicket(); break;
                case 3: cancelTicket(); break;
                case 4: viewReservations(); break;
                case 5: System.out.println("Thank you!"); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    static void viewFlights() {
        System.out.println("\nAvailable Flights:");
        for (Flight f : flights) {
            System.out.println(f);
        }
    }

    static void bookTicket() {
        System.out.print("\nEnter your name: ");
        sc.nextLine(); 
        String name = sc.nextLine();
        System.out.print("Enter your age: ");
        int age = sc.nextInt();

        viewFlights();
        System.out.print("Enter flight number to book: ");
        int fno = sc.nextInt();

        Flight selected = null;
        for (Flight f : flights) {
            if (f.flightNo == fno) {
                selected = f;
                break;
            }
        }

        if (selected == null) {
            System.out.println("Invalid flight number!");
            return;
        }

        bookingCounter++;
        Reservation r = new Reservation(bookingCounter, name, age, selected);
        reservations.add(r);
        System.out.println("Ticket booked successfully! Your Booking ID: " + bookingCounter);
    }

    static void cancelTicket() {
        System.out.print("\nEnter Booking ID to cancel: ");
        int id = sc.nextInt();

        Reservation toRemove = null;
        for (Reservation r : reservations) {
            if (r.bookingId == id) {
                toRemove = r;
                break;
            }
        }

        if (toRemove != null) {
            reservations.remove(toRemove);
            System.out.println("Booking cancelled successfully!");
        } else {
            System.out.println("Booking ID not found!");
        }
    }

    static void viewReservations() {
        if (reservations.isEmpty()) {
            System.out.println("\nNo reservations found!");
        } else {
            System.out.println("\nCurrent Reservations:");
            for (Reservation r : reservations) {
                System.out.println(r);
            }
        }
    }
}

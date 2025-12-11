import java.util.*;
import java.sql.*;
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
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/airline", "root", "");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM flights");

            while (rs.next()) {
                flights.add(new Flight(
                        rs.getInt("flightNo"),
                        rs.getString("source"),
                        rs.getString("destination"),
                        rs.getDouble("price")
                ));
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Database Error: " + e);
        }

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


      try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/airline", "root", "");

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO reservations(bookingId, name, age, flightNo) VALUES(?,?,?,?)"
            );

            ps.setInt(1, bookingCounter);
            ps.setString(2, name);
            ps.setInt(3, age);
            ps.setInt(4, selected.flightNo);

            ps.executeUpdate();
            con.close();

            System.out.println("Ticket booked successfully! Your Booking ID: " + bookingCounter);

        } catch (Exception e) {
            System.out.println("Booking Error: " + e);
        }
    }

    static void cancelTicket() {
        System.out.print("\nEnter Booking ID to cancel: ");
        int id = sc.nextInt();

        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/airline", "root", "");

            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM reservations WHERE bookingId = ?"
            );
            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            con.close();

            if (rows > 0)
                System.out.println("Booking cancelled successfully!");
            else
                System.out.println("Booking ID not found!");

        } catch (Exception e) {
            System.out.println("Cancel Error: " + e);
        }
    }

     static void viewReservations() {
        System.out.println("\nCurrent Reservations:");

        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/airline", "root", "");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM reservations");

            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println(rs.getInt("bookingId") + " | " +
                        rs.getString("name") + " | " +
                        rs.getInt("age") + " | Flight " +
                        rs.getInt("flightNo"));
            }

            if (!found) System.out.println("No reservations found!");

            con.close();
        } catch (Exception e) {
            System.out.println("View Error: " + e);
        }
    }

}

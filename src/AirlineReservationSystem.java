import dao.AirlineDAO;
import java.util.*;
import model.Flight;
import service.AirlineService;

public class AirlineReservationSystem {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<Flight> flights = AirlineDAO.loadFlights();


    public static void main(String args[]) {

        while (true) {

            System.out.println("\n===== AIRLINE RESERVATION SYSTEM =====");
            System.out.println("1. View Flights");
            System.out.println("2. Book Ticket");
            System.out.println("3. Cancel Ticket");
            System.out.println("4. View Reservations");
            System.out.println("5. Exit");

            System.out.print("Enter Choice : ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    AirlineService.viewFlights(flights);
                    break;

                case 2:
                    AirlineService.bookTicket(sc, flights);
                    
                    break;

                case 3:
                    AirlineService.cancelTicket(sc);
                    break;

                case 4:
                    AirlineService.viewReservations();
                    break;

                case 5:
                    System.out.println("Thank You...");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice");

            }

        }

    }

}
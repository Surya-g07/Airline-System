package service;

import dao.AirlineDAO;
import java.util.ArrayList;
import java.util.Scanner;
import model.Flight;

public class AirlineService {

    public static void viewFlights(ArrayList<Flight> flights) {

        System.out.println("\nAvailable Flights:");

        for (Flight f : flights) {
            System.out.println(f);
        }

    }

    public static void bookTicket(Scanner sc, ArrayList<Flight> flights) {

    System.out.print("\nEnter your name: ");
    sc.nextLine();
    String name = sc.nextLine();

    System.out.print("Enter your age: ");
    int age = sc.nextInt();

    viewFlights(flights);

    System.out.print("Enter Flight Number : ");
    int fno = sc.nextInt();

    Flight selected = null;

    for (Flight f : flights) {

        if (f.flightNo == fno) {
            selected = f;
            break;
        }

    }

    if (selected == null) {

        System.out.println("Invalid Flight Number!");
        return;

    }

    AirlineDAO.bookTicket(
        name,
        age,
        selected.flightNo
    );

    System.out.println("Ticket booked successfully!");

    }

    public static void cancelTicket(Scanner sc) {

        System.out.print("\nEnter Booking ID : ");
        int id = sc.nextInt();

        AirlineDAO.cancelTicket(id);

    }

    public static void viewReservations() {

        AirlineDAO.viewReservations();

    }

}
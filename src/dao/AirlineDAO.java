package dao;

import db.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import model.Flight;

public class AirlineDAO {

    public static void bookTicket(String name, int age, int flightNo) {

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO reservations(name,age,flightNo) VALUES(?,?,?)");

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setInt(3, flightNo);

            ps.executeUpdate();
            con.close();

        } catch (Exception e) {
            System.out.println("Booking Error : " + e);
        }
    }

    public static void cancelTicket(int bookingId) {

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM reservations WHERE bookingId=?");

            ps.setInt(1, bookingId);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Booking cancelled successfully!");
            else
                System.out.println("Booking ID not found!");

            con.close();

        } catch (Exception e) {
            System.out.println("Cancel Error : " + e);
        }
    }

    public static void viewReservations() {

        try {

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM reservations");

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println(
                        rs.getInt("bookingId") + " | "
                                + rs.getString("name") + " | "
                                + rs.getInt("age") + " | Flight "
                                + rs.getInt("flightNo"));

            }

            if (!found)
                System.out.println("No reservations found!");

            con.close();

        } catch (Exception e) {

            System.out.println("View Error : " + e);

        }

    }

    public static ArrayList<Flight> loadFlights() {

        ArrayList<Flight> flights = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM flights");

            while (rs.next()) {

                flights.add(new Flight(
                        rs.getInt("flightNo"),
                        rs.getString("source"),
                        rs.getString("destination"),
                        rs.getDouble("price")));

            }

            con.close();

        } catch (Exception e) {

            System.out.println("Database Error : " + e);

        }

        return flights;

    }

}
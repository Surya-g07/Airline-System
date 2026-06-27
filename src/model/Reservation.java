package model;
public class Reservation { 
    public int bookingId;
    public String passengerName;
    public int age;
    public Flight flight;

    public Reservation(int bookingId, String passengerName, int age, Flight flight) {
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

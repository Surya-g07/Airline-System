package model;
public class Flight {
    public int flightNo;
    public String source, destination;
    public double price;

    public Flight(int flightNo, String source, String destination, double price) {
        this.flightNo = flightNo;
        this.source = source;
        this.destination = destination;
        this.price = price;
    }

    public String toString() {
        return flightNo + " - " + source + " to " + destination + " - Rs." + price;
    }
}

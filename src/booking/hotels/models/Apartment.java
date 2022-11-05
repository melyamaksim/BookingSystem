package booking.hotels.models;

import java.util.ArrayList;

public class Apartment {
    private final double id;
    private final int amountOfBedrooms;
    private final double price;
    private final String locationName;
    private final ArrayList<String> busyDays;


    public Apartment(double id, int amountOfBedrooms, double price, String locationName, ArrayList<String> busyDays) {
        this.id = id;
        this.amountOfBedrooms = amountOfBedrooms;
        this.price = price;
        this.locationName = locationName;
        this.busyDays = busyDays;
    }

    @Override
    public String toString() {
        StringBuilder busyDaysString = new StringBuilder();

        for (String busyDay : busyDays){
            busyDaysString.append(busyDay).append(", ");
        }
        return id +
                "| Apartment: " +
                "| amountOfBedrooms=" + amountOfBedrooms +
                "| price=" + price + "$" +
                "| locationName='" + locationName +
                "| busyDays=" + busyDaysString +
                '|';
    }

    public double getId() {
        return id;
    }

    public int getAmountOfBedrooms() {
        return amountOfBedrooms;
    }

    public double getPrice() {
        return price;
    }

    public String getLocationName() {
        return locationName;
    }

    public ArrayList<String> getBusyDays() {
        return busyDays;
    }
}

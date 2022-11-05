package booking.trains.models;

import java.util.ArrayList;

public class Room {
    private final double id;
    private final int amountOfPeople;
    private final double price;
    private final String roomType;
    private final ArrayList<String> bookedDays;


    public Room(double id, int amountOfPeople, double price, String roomType, ArrayList<String> bookedDays) {
        this.id = id;
        this.amountOfPeople = amountOfPeople;
        this.price = price;
        this.roomType = roomType;
        this.bookedDays = bookedDays;
    }

    @Override
    public String toString() {
        StringBuilder bookedDaysString = new StringBuilder();

        for (String bookedDay : bookedDays){
            bookedDaysString.append(bookedDay).append(", ");
        }
        return "| ID: " + id +
                "| capacity: " + amountOfPeople +
                "| price: " + price +
                "| type of room: " + roomType + '\'' +
                "| booked days: " + bookedDays;

    }
    public double getId() {
        return id;
    }

    public int getAmountOfPeople() {
        return amountOfPeople;
    }

    public double getPrice() {
        return price;
    }

    public String getRoomType() {
        return roomType;
    }

    public ArrayList<String> getBookedDays() {
        return bookedDays;
    }
}

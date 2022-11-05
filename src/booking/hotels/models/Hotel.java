package booking.hotels.models;

import java.util.ArrayList;

public class Hotel {
    private final int id;

    public int getId() {
        return id;
    }

    private final String name;
    private final int stars;

    private final ArrayList<Apartment> apartments = new ArrayList<>();

    public Hotel(int id, String name, int stars) {
        this.id = id;
        this.name = name;
        this.stars = stars;
    }

    public String getName() {
        return name;
    }

    public int getStars() {
        return stars;
    }

    @Override
    public String toString() {
        StringBuilder myApartments = new StringBuilder();

        for (Apartment apartment : apartments) {
            myApartments.append(apartment.toString()).append("\n");
        }

        return id +
                "| Hotel name: " + name +
                "| Stars:" + stars + "*" +
                "| Apartments:\n" + myApartments;
    }

    public String toStringWithoutApartments() {


        return id +
                "| Hotel name: " + name +
                "| stars =" + stars + "*";

    }

    public void addApartment(Apartment apartment) {
        apartments.add(apartment);
    }

    public ArrayList<Apartment> getApartments() {
        return apartments;
    }
}

package booking.trains.models;

import java.util.ArrayList;

public class Train {
    private final int id;

    public int getId() {
        return id;
    }
    private final String name;
    private final int stars;

    private final String startOfWay;
    private final String arrivalTime;

    private final ArrayList<Room> rooms = new ArrayList<>();
    private final ArrayList<Destination> destinations;


    public Train(int id, String name, int stars, String startOfWay, String arrivalTime, ArrayList<Destination> tempDestinations) {
        this.id = id;
        this.name = name;
        this.destinations = tempDestinations;
        this.stars = stars;
        this.startOfWay = startOfWay;
        this.arrivalTime = arrivalTime;
    }

    public ArrayList<Destination> getDestinations() {
        return destinations;
    }
    public String getName() {
        return name;
    }
    public int getStars() {
        return stars;
    }

    public String getStartOfWay() {
        return startOfWay;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    @Override
    public String toString() {
        StringBuilder destinationsString = new StringBuilder();

        for (Destination destination : destinations){
            destinationsString.append(destination).append(", ");
        }

        return "| Train: " + name +
                "| ID: " + id +
                "| Destination: " + destinationsString + '\'' +
                "| Stars: " + stars + "*" +
                "| Start of way: " + startOfWay +
                "| Arrival time: " + arrivalTime +
                "| Rooms: " + rooms + " |";
    }
    public String toStringWithoutRooms() {
        StringBuilder destinationsString = new StringBuilder();

        for (Destination destination : destinations){
            destinationsString.append(destination).append(", ");
        }

        return id +
                "| Train destination: " + destinationsString +
                "| stars =" + stars + "*" +
                "| Start of way: " + startOfWay +
                "| Arrival time: " + arrivalTime;

    }

    public void addRoom(Room room) {
        rooms.add(room);
    }
    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public boolean containsDestinations(String startLocation, String endLocation) {
        boolean containsStartLocation = false;
        boolean containsEndLocation = false;

        for (Destination destination: destinations) {
            System.out.println("Destination name=" + destination.getName());
            if (destination.getName().equals(startLocation)) {
                containsStartLocation = true;
            }
            if (destination.getName().equals(endLocation)) {
                containsEndLocation = true;
            }
        }

        return containsStartLocation && containsEndLocation;


    }
}

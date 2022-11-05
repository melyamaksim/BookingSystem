package booking.trains.models;

public class Destination {
    private final String name;
    private final String startOfWay;
    private final String arrivalTime;

    public Destination(String name, String startOfWay, String arrivalTime) {
        this.name = name;
        this.startOfWay = startOfWay;
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return  "| Train destination: " + name +
                "| Start of way: " + startOfWay +
                "| Arrival time: " + arrivalTime;
    }


    public String getName() {
        return name;
    }

    public String getStartOfWay() {
        return startOfWay;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }
}

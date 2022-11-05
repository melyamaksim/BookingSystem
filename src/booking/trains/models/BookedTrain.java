package booking.trains.models;

public class BookedTrain {
    private final Train train;
    private final Room room;

    private final Destination pickUpDestination;
    private final Destination endDestination;
    private final String date;

    public Train getTrain() {
        return train;
    }

    public Room getRoom() {
        return room;
    }

    public Destination getPickUpDestination() {
        return pickUpDestination;
    }

    public Destination getEndDestination() {
        return endDestination;
    }

    public String getDate() {
        return date;
    }

    public BookedTrain(Train train, Room room, Destination pickUpDestination, Destination endDestination, String date) {
        this.train = train;
        this.room = room;
        this.pickUpDestination = pickUpDestination;
        this.endDestination = endDestination;
        this.date = date;
    }

    @Override
    public String toString() {
        return "BookedTrain{" +
                "train=" + train +
                ", room=" + room +
                ", pickUpDestination=" + pickUpDestination +
                ", endDestination=" + endDestination +
                ", date='" + date + '\'' +
                '}';
    }
}

package booking.hotels.models;



public class BookedHotel {
    private final Hotel hotel;
    private final Apartment apartment;
    private final String date;

    public Hotel getHotel() {
        return hotel;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public String getDate() {
        return date;
    }

    public BookedHotel(Hotel hotel, Apartment apartment, String date) {
        this.hotel = hotel;
        this.apartment = apartment;
        this.date = date;
    }

    @Override
    public String toString() {
        return "BookedHotel{" +
                "hotel=" + hotel +
                ", apartment=" + apartment +
                ", date='" + date + '\'' +
                '}';
    }
}

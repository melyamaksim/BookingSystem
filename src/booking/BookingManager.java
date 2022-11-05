package booking;

import authentication.Account;
import booking.hotels.HotelsHistoryManager;
import booking.hotels.HotelsManager;
import booking.hotels.models.BookedHotel;
import booking.trains.TrainsHistoryManager;
import booking.trains.TrainsManager;
import booking.trains.models.BookedTrain;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookingManager {
    private Account myAccount;
    private final Scanner scan = new Scanner(System.in);
    private static final ArrayList<List> lists = new ArrayList<>();
    private final String BookedTrainsPath;
    private final String BookedHotelsPath;
    private final TrainsHistoryManager myTrainsHistoryManager;
    private final HotelsHistoryManager myHotelsHistoryManager;

    public BookingManager(Account myAccount) {
        this.myAccount = myAccount;
        BookedTrainsPath = myAccount.getNamee() + "BookedTrains";
        BookedHotelsPath = myAccount.getNamee() + "BookedHotels";
        myTrainsHistoryManager = new TrainsHistoryManager(BookedTrainsPath);
        myHotelsHistoryManager = new HotelsHistoryManager(BookedHotelsPath);

    }

    public void Book() {
        System.out.println("Choose what you want to book, \n1 - Hotel\n2 - Train");
        int choice = scan.nextInt();
        switch (choice) {
            case 1 -> BookTheHotel();
            case 2 -> BookTheTrain();
        }
    }

    public void Cancel() {

    }


    public void SignOut() {
        myAccount = null;
        InterfaceManager.signOut();
    }

    public void BookingHistoryTrain() {
        myTrainsHistoryManager.historyOfBookedTrains();
    }

    public void BookingHistoryHotel() {
        myHotelsHistoryManager.historyOfBookedHotels();
    }
    public void BookTheHotel() {
        BookedHotel myBookedHotel = HotelsManager.bookHotel();
        if (myBookedHotel != null) {
            System.out.println(myBookedHotel);
            try {
                FileWriter fileWriter = new FileWriter(BookedHotelsPath, true);
                fileWriter.append(myBookedHotel.getHotel().getName()).append("#").append(String.valueOf(myBookedHotel.getHotel().getStars())).append("#").append(String.valueOf(myBookedHotel.getApartment().getAmountOfBedrooms())).append("#").append(myBookedHotel.getDate()).append("|");
                fileWriter.close();
            }
            catch (Exception e) {
                System.out.println("EXCEPTION" + e.getCause());
            }


        }
    }

    public void BookTheTrain()  {
        BookedTrain myBookedTrain = TrainsManager.bookTrain();
        if (myBookedTrain != null) {
            System.out.println(myBookedTrain);
            try {
                FileWriter fileWriter = new FileWriter(BookedTrainsPath, true);
                fileWriter.append(myBookedTrain.getTrain().getName()).append("#").append(String.valueOf(myBookedTrain.getTrain().getStars())).append("#").append(myBookedTrain.getTrain().getStartOfWay()).append("#").append(myBookedTrain.getTrain().getArrivalTime()).append("|").append(String.valueOf(myBookedTrain.getRoom().getAmountOfPeople())).append("#").append(myBookedTrain.getRoom().getRoomType()).append("|").append(myBookedTrain.getDate()).append("|").append(String.valueOf(myBookedTrain.getPickUpDestination().getName())).append("|").append(String.valueOf(myBookedTrain.getPickUpDestination().getStartOfWay())).append("|").append(String.valueOf(myBookedTrain.getPickUpDestination().getArrivalTime())).append("|").append(String.valueOf(myBookedTrain.getEndDestination().getName())).append("|").append(String.valueOf(myBookedTrain.getEndDestination().getStartOfWay()));
                fileWriter.close();
            }
            catch (Exception e) {
                System.out.println("EXCEPTION" + e.getCause());
            }


        }

        //@TODO booking logic
    }

}

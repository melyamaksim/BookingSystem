package booking.trains;

import java.io.File;
import java.util.Scanner;

public class TrainsHistoryManager {
    private final String bookedTrainsPath;
    public TrainsHistoryManager(String bookedTrainsPath) {
        this.bookedTrainsPath = bookedTrainsPath;
    }

    public void historyOfBookedTrains() {
        try {
            File myFile = new File(bookedTrainsPath);
            Scanner read = new Scanner(myFile);
            while (read.hasNextLine()) {
                String[] train = read.nextLine().split("[|#]");
                System.out.println("Train Destinations: " + train[0] + " Start of way: " + train[2] + " End of way: " + train[3] + " Room type: " + train[5]);
            }
        } catch (Exception exception) {
            System.out.println("History of booking trains is empty.");
        }
    }

}

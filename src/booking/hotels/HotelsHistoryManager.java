package booking.hotels;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class HotelsHistoryManager {
    private final String bookedHotelsPath;

    public HotelsHistoryManager(String bookedHotelsPath) {
        this.bookedHotelsPath = bookedHotelsPath;
    }

    public void historyOfBookedHotels() {
        try {
            File myFile = new File(bookedHotelsPath);
            Scanner read = new Scanner(myFile);
            while (read.hasNextLine()) {
                String[] hotel = read.nextLine().split("[|#]");
                System.out.println(Arrays.toString(hotel));
            }
        }
        catch (Exception exception) {
            System.out.println("No file");
        }
    }

}

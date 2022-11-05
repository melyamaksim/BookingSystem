package booking.hotels;

import booking.hotels.models.Apartment;
import booking.hotels.models.Hotel;
import booking.hotels.models.BookedHotel;
import java.io.File;
import java.util.*;

public class HotelsManager {
    private static final ArrayList<Hotel> hotels = new ArrayList<>();
    private static final Scanner scan = new Scanner(System.in);

    private static void getTheHotels() {
        try {
            String path = "hotels";
            File myFile = new File(path);
            Scanner read = new Scanner(myFile);
            while (read.hasNextLine()) {
                String[] hotel = read.nextLine().split("\\|");



                if (Double.parseDouble(hotel[0]) % 1 == 0) {
                    hotels.add(new Hotel(Integer.parseInt(hotel[0].trim()),hotel[1].trim(), Integer.parseInt(hotel[2].trim())));
                }
                else {
                    for (Hotel value : hotels) {
                        if (value.getId() == (int) Double.parseDouble(hotel[0])) {
                            ArrayList<String> busyDays = new ArrayList<>();
                            //String[] apartment =  hotel[4].trim().split(",");
                            //System.out.println(Arrays.toString(apartment));
                            //@TODO delete empty spaces in each busy days
                            Collections.addAll(busyDays, hotel[4].trim().split(","));
                            value.addApartment(new Apartment(Double.parseDouble(hotel[0].trim()), Integer.parseInt(hotel[1].trim()), Double.parseDouble(hotel[2].trim()), hotel[3].trim(), busyDays));
                        }
                    }
                }
                }



        }
        catch (Exception exception) {
            System.out.println("We don't have such a file");
        }
    }

    public static BookedHotel bookHotel() {
        getTheHotels();
        while (true) {
            System.out.println("Where do you want to book the hotel?");
            String location = scan.nextLine();
            System.out.println("Choose the option, by which you want to find a hotel:\n1 - by price\n2 - by stars\n3 - amount of bedrooms\n4 - print all hotels in this location");
            int choice = scan.nextInt();
            BookedHotel myBookedHotel;
            switch (choice) {
                case 1:
                    myBookedHotel =  findByPrice(location);
                    if (myBookedHotel != null) {
                        return myBookedHotel;
                    } else continue;
                case 2:
                    myBookedHotel = findByStars(location);
                    if (myBookedHotel != null) {
                        return myBookedHotel;
                    } else continue;
                case 3:
                    myBookedHotel = findByBedrooms(location);
                    if (myBookedHotel != null) {
                        return myBookedHotel;
                    } else continue;
                case 4:
                    myBookedHotel = printAllHotels(location);
                    if (myBookedHotel != null) {
                        return myBookedHotel;
                    } else continue;
                default:
                    return null;
            }

        }
    }

    private static BookedHotel findByBedrooms(String location) {

//        switch (choice) {
//            case 1 :
        while (true) {
                System.out.println("Input your bedrooms: ");
                int choiceBedrooms = scan.nextInt();
                System.out.println("Choose if you want to see higher bedroom or lower bedroom hotels: \n1 - higher\n2 - lower\n3 - equals\n4 - move back");
                int choice = scan.nextInt();

                if (choice > 0 && choice < 4) {
                    return printByBedroom(location, choiceBedrooms, choice);
                }
                else if (choice == 4) {
                    return null;
                }

        }

    }
    private static BookedHotel printByBedroom(String location, int bedrooms, int choice) {
        ArrayList<Hotel> myHotels = new ArrayList<>();
        for (Hotel hotel : hotels) {
            boolean printOrNot = false;
            for (Apartment apartment : hotel.getApartments()){
                if (apartment.getLocationName().equalsIgnoreCase(location)) {
                    switch (choice) {
                        case 1:
                            if (bedrooms <= apartment.getAmountOfBedrooms()) {
                                if (!printOrNot) {
                                    System.out.println(hotel.toStringWithoutApartments());
                                    myHotels.add(hotel);
                                }
                                printOrNot = true;
                                System.out.println(apartment);
                            }
                            break;
                        case 2:
                            if (bedrooms >= apartment.getAmountOfBedrooms()) {
                                if (!printOrNot) {
                                    System.out.println(hotel.toStringWithoutApartments());
                                    myHotels.add(hotel);
                                }
                                printOrNot = true;
                                System.out.println(apartment);
                            }
                            break;
                        case 3:
                            if (bedrooms == apartment.getAmountOfBedrooms()) {
                                if (!printOrNot) {
                                    System.out.println(hotel.toStringWithoutApartments());
                                    myHotels.add(hotel);
                                }
                                printOrNot = true;
                                System.out.println(apartment);
                            }
                            break;
                    }
                }

            }
        }
        System.out.println("Enter the id of the apartment that you want to book: ");
        for (Hotel hotel : myHotels) {
            for (Apartment apartment : hotel.getApartments()){
                return bookHotel(hotel, apartment);


            }
        }
        return null;
    }

    private static BookedHotel findByStars(String location) {
            while (true) {
                System.out.println("Input your amount of stars: ");
                int choiceStars = scan.nextInt();
                System.out.println("Choose if you want to see higher star or lower star hotels: \n1 - higher\n2 - lower\n3 - equals\n4 - move back");
                int choice = scan.nextInt();


                if (choice > 0 && choice < 4) {
                    return printByBedroom(location, choiceStars, choice);

                } else if (choice == 4) {
                    return null;
                }
            }
    }
    private static BookedHotel printByStars(String location, int stars, int choice) {
        ArrayList<Hotel> myHotels = new ArrayList<>();
        for (Hotel hotel : hotels) {
            boolean printOrNot = false;
            for (Apartment apartment : hotel.getApartments()){
                if (apartment.getLocationName().equalsIgnoreCase(location)) {
                    switch (choice) {
                        case 1:
                            if (stars <= hotel.getStars()) {
                                if (!printOrNot) {
                                    System.out.println(hotel.toStringWithoutApartments());
                                    myHotels.add(hotel);
                                }
                                printOrNot = true;
                                System.out.println(apartment);
                            }
                                break;
                        case 2:
                            if (stars >= hotel.getStars()) {
                                if (!printOrNot) {
                                    System.out.println(hotel.toStringWithoutApartments());
                                    myHotels.add(hotel);
                                }
                                printOrNot = true;
                                System.out.println(apartment);
                            }
                                break;
                        case 3:
                            if (stars == hotel.getStars()) {
                                if (!printOrNot) {
                                    System.out.println(hotel.toStringWithoutApartments());
                                    myHotels.add(hotel);
                                }
                                printOrNot = true;
                                System.out.println(apartment);
                            }
                                break;
                    }



                }

            }
        }
        System.out.println("Enter the id of the room that you want to book: ");
        String idChoice = scan.next();
        for (Hotel hotel : myHotels) {
            for (Apartment apartment : hotel.getApartments()){
                switch (choice) {
                    case 1:
                        if (idChoice.equals(String.valueOf(apartment.getId())) && (stars <= hotel.getStars())) {
                            return bookHotel(hotel, apartment);
                        }
                    case 2:
                        if (idChoice.equals(String.valueOf(apartment.getId())) && (stars >= hotel.getStars())) {
                            return bookHotel(hotel, apartment);
                        }
                    case 3:
                        if (idChoice.equals(String.valueOf(apartment.getId())) && (stars == hotel.getStars()) ) {
                            return bookHotel(hotel, apartment);
                        }
                }
            }
        }
        return null;
    }


    private static BookedHotel printAllHotels(String location) {
        for (Hotel hotel : hotels) {
            boolean printOrNot = false;
            for (Apartment apartment : hotel.getApartments()){
                if (apartment.getLocationName().equalsIgnoreCase(location)) {
                    if (!printOrNot) {
                        System.out.println(hotel.toStringWithoutApartments());
                    }
                    printOrNot = true;
                    System.out.println(apartment);

                }

            }
        }

        return null;
    }

    private static BookedHotel findByPrice(String location) {
//        System.out.println("Choose the option, by which you want to find a hotel:\n1 - by price\n2 - by stars\n3 - amount of bedrooms");

//        switch (choice) {
//            case 1 :
        while (true) {
                System.out.println("Input your price: ");
                int choicePrice = scan.nextInt();
                System.out.println("Choose if you want to see higher price or lower price hotels: \n1 - higher\n2 - lower\n3 - equals\n4 - move back");
                int choice = scan.nextInt();

                if (choice > 0 && choice < 4) {
                    return printByPrice(location, choicePrice, choice);
                }
                else if (choice == 4) {
                    return null;
                }

        }
    }
    private static BookedHotel printByPrice(String location, int price, int choice) {
        ArrayList<Hotel> myHotels = new ArrayList<>();
        for (Hotel hotel : hotels) {
                boolean printOrNot = false;
                for (Apartment apartment : hotel.getApartments()){
                    if (apartment.getLocationName().equalsIgnoreCase(location)) {
                        switch (choice) {
                            case 1:
                                if (price <= apartment.getPrice()) {
                                    if (!printOrNot) {
                                        System.out.println(hotel.toStringWithoutApartments());
                                        myHotels.add(hotel);
                                    }
                                    printOrNot = true;
                                    System.out.println(apartment);
                                }
                                break;
                            case 2:
                                if (price >= apartment.getPrice()) {
                                    if (!printOrNot) {
                                        System.out.println(hotel.toStringWithoutApartments());
                                        myHotels.add(hotel);
                                    }
                                    printOrNot = true;
                                    System.out.println(apartment);
                                }
                                break;
                            case 3:
                                if (Math.abs(price - apartment.getPrice()) <= 20.0) {
                                    if (!printOrNot) {
                                        System.out.println(hotel.toStringWithoutApartments());
                                        myHotels.add(hotel);
                                    }
                                    printOrNot = true;
                                    System.out.println(apartment);
                                }
                                break;
                        }



                    }

                }
            }
        System.out.println("Enter the id of the room that you want to book: ");
        String idChoice = scan.next();
        for (Hotel hotel : myHotels) {
            for (Apartment apartment : hotel.getApartments()){
                switch (choice) {
                    case 1:
                        if (idChoice.equals(String.valueOf(apartment.getId())) && (price <= apartment.getPrice())) {
                            return bookHotel(hotel, apartment);
                        }
                    case 2:
                        if (idChoice.equals(String.valueOf(apartment.getId())) && (price >= apartment.getPrice())) {
                            return bookHotel(hotel, apartment);
                        }
                    case 3:
                        if (idChoice.equals(String.valueOf(apartment.getId())) && (Math.abs(price - apartment.getPrice()) <= 20.0)) {
                            return bookHotel(hotel, apartment);
                        }
                }
            }
        }
        return null;
        }

        private static BookedHotel bookHotel(Hotel hotel, Apartment apartment) {
            System.out.println(hotel);
            System.out.println(apartment);
            String dateChoice;
            while (true) {
                System.out.println("What date do you want to go? (Format : 12.08) ");
                dateChoice = scan.next();
                //@TODO validate is the date this format: 10.08 asd
                boolean changeDateOrNot = false;
                for (String busyDay : apartment.getBusyDays()) {
                    if (Objects.equals(dateChoice, busyDay.trim())) {
                        System.out.println("This day is already busy. Do u want to change date or move back?\n1 - change date\n2 - move back");
                        int userChoice = scan.nextInt();
                        if (userChoice == 1) {
                            changeDateOrNot = true;
                            break;
                        } else {
                            return null;
                        }
                    }
                }
                if (!changeDateOrNot) {
                    break;
                }
            }

            System.out.println("Do you really want to book the hotel? " + "Name = " + hotel.getName() + "Rating = " + hotel.getStars() + "Price = " + apartment.getPrice() + "Amount of bedrooms = " + apartment.getAmountOfBedrooms());
            String choiceUser = scan.next();
            if (Objects.equals(choiceUser, "yes")) {
                //@TODO ask about confirmation
                return new BookedHotel(hotel, apartment, dateChoice);
            }
            return null;
        }







}

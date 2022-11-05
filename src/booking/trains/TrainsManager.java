package booking.trains;

import booking.trains.models.BookedTrain;
import booking.trains.models.Destination;
import booking.trains.models.Room;
import booking.trains.models.Train;

import java.io.File;
import java.util.*;

public class TrainsManager {
    private static final ArrayList<Train> trains = new ArrayList<>();
    private static final Scanner scan = new Scanner(System.in);

    private static void getTheTrains() {
        try {
            String path = "trains";
            File myFile = new File(path);
            Scanner read = new Scanner(myFile);
            while (read.hasNextLine()) {
                String[] train = read.nextLine().split("\\|");
                System.out.println("tempTrain = " + Arrays.toString(train));


                if (Double.parseDouble(train[0]) % 1 == 0) {

                    ArrayList<Destination> destinations = new ArrayList<>();
                    String[] textDestinations = train[5].split("#");

                    for (String textDestination : textDestinations) {
                        String[] tmpTextDestinations = textDestination.split(",");
                        if (tmpTextDestinations.length == 3) {
                            destinations.add(new Destination(tmpTextDestinations[0], tmpTextDestinations[1], tmpTextDestinations[2]));
                        }
                        else {
                            destinations.add(new Destination(tmpTextDestinations[0], tmpTextDestinations[1], null));
                        }
                    }



                    trains.add(new Train(Integer.parseInt(train[0].trim()),train[1].trim(), Integer.parseInt(train[2].trim()), train[3].trim(), train[4].trim(), destinations));
                }
                else {
                    for (Train value : trains) {
                        if (value.getId() == (int) Double.parseDouble(train[0])) {
                            ArrayList<String> bookedDays = new ArrayList<>();
                            Collections.addAll(bookedDays, train[4].trim().split(","));
                            value.addRoom(new Room(Double.parseDouble(train[0].trim()), Integer.parseInt(train[1].trim()), Double.parseDouble(train[2].trim()), train[3].trim(), bookedDays));
                        }
                    }
                }
            }

        }
        catch (Exception exception) {
            System.out.println("Error during reading from the file: " + exception.getMessage());
        }
    }
    public static BookedTrain bookTrain() {
        getTheTrains();
        while (true) {
            System.out.println("From where do you want to go?: ");
            String startLocation = scan.nextLine();
            System.out.println("Where do you want to go?: ");
            String endLocation = scan.nextLine();
            System.out.println("Choose the option, by which you want to find a train:\n1 - by price\n2 - by rating\n3 - by arrival time\n4 - by capacity\n5 - type of room\n6 - show all the trains");
            int choice = scan.nextInt();
            BookedTrain myBookedTrain;
            switch (choice) {
                case 1:
                    myBookedTrain = findByPrice(startLocation, endLocation);
                    if (myBookedTrain != null) {
                        return myBookedTrain;
                    } else continue;
                case 2:
                    myBookedTrain = findByStars(startLocation, endLocation);
                    if (myBookedTrain != null) {
                        return myBookedTrain;
                    } else continue;

                case 3:
                    myBookedTrain = findByArrivalTime(startLocation, endLocation);
                    if (myBookedTrain != null) {
                        return myBookedTrain;
                    } else continue;
                case 4:
                    myBookedTrain = findByCapacity(startLocation, endLocation);
                    if (myBookedTrain != null) {
                        return myBookedTrain;
                    } else continue;
                case 5:
                    myBookedTrain = findByType(startLocation, endLocation);
                    if (myBookedTrain != null) {
                        return myBookedTrain;
                    } else continue;
                case 6:
                    myBookedTrain = printAllTrains(startLocation, endLocation);
                    if (myBookedTrain != null) {
                        return myBookedTrain;
                    } else continue;
                default:
                    return null;
            }
        }
    }

    private static BookedTrain findByArrivalTime(String startLocation, String endLocation) {

        while (true) {
            System.out.println("Input your arrival time (15:15): ");
            String choiceArrivalTime = scan.next();
            return printByArrivalTime(startLocation, endLocation, choiceArrivalTime);

        }
    }
    private static BookedTrain printByArrivalTime(String startLocation, String endLocation, String arrivalTime) {
        ArrayList<Train> myTrains = new ArrayList<>();
        String[] userArrivalTimeDivided = arrivalTime.split(":");
        int userArrivalTimeInMinutes = Integer.parseInt(userArrivalTimeDivided[0]) * 60 + Integer.parseInt(userArrivalTimeDivided[1]);

        for (Train train : trains) {
            boolean printOrNot = false;
            if (train.containsDestinations(startLocation, endLocation)) {
                Destination startDestinationOfTrain = train.getDestinations().get(0);
                if (startDestinationOfTrain.getName().equals(startLocation)) {
                    String[] arrivalTimeDivided = startDestinationOfTrain.getArrivalTime().split(":");
                    System.out.println("arrivalTimeDivided = " + Arrays.toString(arrivalTimeDivided));
                    int arrivalTimeInMinutes = Integer.parseInt(arrivalTimeDivided[0]) * 60 + Integer.parseInt(arrivalTimeDivided[1]);
                    System.out.println("arrivalTimeInMinutes = " + arrivalTimeInMinutes);

                    if (Math.abs(arrivalTimeInMinutes - userArrivalTimeInMinutes) <= 60) {
                        for (Room room : train.getRooms()) {
                            if (!printOrNot) {
                                System.out.println(train.toStringWithoutRooms());
                                myTrains.add(train);
                            }
                            printOrNot = true;
                            System.out.println(room);


                        }

                    }

                }

            }
        }

        System.out.println("Enter the id of the room that you want to book: ");
        for (Train train : myTrains) {
            for (Room room : train.getRooms()){
                return bookTrain(train, room, startLocation, endLocation);


                }
            }
        return null;
    }

    private static BookedTrain findByCapacity(String startLocation, String endLocation) {

        while (true) {
            System.out.println("Input your capacity: ");
            int choiceCapacity = scan.nextInt();
            System.out.println("Choose if you want to see higher capacity or lower capacity trains: \n1 - higher\n2 - lower\n3 - equals\n4 - move back");
            int choice = scan.nextInt();

            if (choice > 0 && choice < 4) {
                BookedTrain myTrain = printByCapacity(startLocation, endLocation, choiceCapacity, choice);
                if (myTrain != null) {
                    return myTrain;
                }
            } else if (choice == 4) {
                return null;
            }
        }
    }
    private static BookedTrain printByCapacity(String startLocation, String endLocation, int people, int choice) {
        ArrayList<Train> myTrains = new ArrayList<>();
        for (Train train : trains) {
            boolean printOrNot = false;
            if (train.containsDestinations(startLocation, endLocation)) {
            for (Room room : train.getRooms()){

                    switch (choice) {
                        case 1:
                            if (Integer.toString(people).compareTo(Integer.toString(room.getAmountOfPeople())) < 1){
                                if (!printOrNot) {
                                    System.out.println(train.toStringWithoutRooms());
                                    myTrains.add(train);
                                }
                                printOrNot = true;
                                System.out.println(room);
                            }
                            break;
                        case 2:
                            if (Integer.toString(people).compareTo(Integer.toString(room.getAmountOfPeople())) > -1) {
                                if (!printOrNot) {
                                    System.out.println(train.toStringWithoutRooms());
                                    myTrains.add(train);
                                }
                                printOrNot = true;
                                System.out.println(room);
                            }
                            break;
                        case 3:
                            if (Integer.toString(people).compareTo(Integer.toString(room.getAmountOfPeople())) == 0) {
                                if (!printOrNot) {
                                    System.out.println(train.toStringWithoutRooms());
                                    myTrains.add(train);
                                }
                                printOrNot = true;
                                System.out.println(room);
                            }
                            break;
                    }



                }

            }
        }
        System.out.println("Enter the id of the room that you want to book: ");
        String idChoice = scan.next();
        for (Train train : myTrains) {
            for (Room room : train.getRooms()){
                switch (choice) {
                    case 1:
                        if (idChoice.equals(String.valueOf(room.getId())) && (people <= room.getAmountOfPeople())) {
                            return bookTrain(train, room, startLocation, endLocation);
                        }
                    case 2:
                        if (idChoice.equals(String.valueOf(room.getId())) && (people >= room.getAmountOfPeople())) {
                            return bookTrain(train, room, startLocation, endLocation);
                        }
                    case 3:
                        if (idChoice.equals(String.valueOf(room.getId())) && (people == room.getAmountOfPeople())) {
                            return bookTrain(train, room, startLocation, endLocation);
                        }
                }
            }
        }
        return null;
    }

    private static BookedTrain findByStars(String startLocation, String endLocation) {

        while (true) {
            System.out.println("Input your amount of stars: ");
            int choiceStars = scan.nextInt();
            System.out.println("Choose if you want to see higher star or lower star trains: \n1 - higher\n2 - lower\n3 - equals\n4 - move back");
            int choice = scan.nextInt();


            if (choice > 0 && choice < 4) {
                BookedTrain myTrain = printByStars(startLocation, endLocation, choiceStars, choice);
                if (myTrain != null) {
                    return myTrain;
                }
            } else if (choice == 4) {
                return null;
            }
        }

    }
    private static BookedTrain printByStars(String startLocation, String endLocation, int stars, int choice) {
        ArrayList<Train> myTrains = new ArrayList<>();
        for (Train train : trains) {
            boolean printOrNot = false;
            if (train.containsDestinations(startLocation, endLocation)) {
            for (Room room : train.getRooms()){
                    switch (choice) {
                        case 1:
                            if (Integer.toString(stars).compareTo(Integer.toString(train.getStars())) < 1) {
                                if (!printOrNot) {
                                    System.out.println(train.toStringWithoutRooms());
                                    myTrains.add(train);
                                }
                                printOrNot = true;
                                System.out.println(room);
                            }
                            break;
                        case 2:
                            if (Integer.toString(stars).compareTo(Integer.toString(train.getStars())) > -1) {
                                if (!printOrNot) {
                                    System.out.println(train.toStringWithoutRooms());
                                    myTrains.add(train);
                                }
                                printOrNot = true;
                                System.out.println(room);
                            }
                            break;
                        case 3:
                            if (Integer.toString(stars).compareTo(Integer.toString(train.getStars())) == 0) {
                                if (!printOrNot) {
                                    System.out.println(train.toStringWithoutRooms());
                                    myTrains.add(train);
                                }
                                printOrNot = true;
                                System.out.println(room);
                            }
                            break;
                    }



                }

            }
        }
        System.out.println("Enter the id of the room that you want to book: ");
        String idChoice = scan.next();
        for (Train train : myTrains) {
            for (Room room : train.getRooms()){
                switch (choice) {
                    case 1:
                        if (idChoice.equals(String.valueOf(room.getId())) && (stars <= train.getStars())) {
                            return bookTrain(train, room, startLocation, endLocation);
                        }
                    case 2:
                        if (idChoice.equals(String.valueOf(room.getId())) && (stars >=  train.getStars())) {
                            return bookTrain(train, room, startLocation, endLocation);
                        }
                    case 3:
                        if (idChoice.equals(String.valueOf(room.getId())) && (stars == train.getStars()) ) {
                            return bookTrain(train, room, startLocation, endLocation);
                        }
                }
            }
        }
        return null;
    }


    private static BookedTrain printAllTrains(String startLocation, String endLocation) {
        for (Train train : trains) {
            boolean printOrNot = false;
            for (Room room : train.getRooms()){
                if (train.getDestinations().contains(startLocation) && train.getDestinations().contains(endLocation)) {
                    if (!printOrNot) {
                        System.out.println(train.toStringWithoutRooms());
                    }
                    printOrNot = true;
                    System.out.println(room);

                }

            }
        }
        return null;
    }

    private static BookedTrain findByPrice(String startLocation, String endLocation) {

        while (true) {
            System.out.println("Input your price: ");
            int choicePrice = scan.nextInt();
            System.out.println("Choose if you want to see higher price or lower price trains: \n1 - higher\n2 - lower\n3 - equals\n4 - move back");
            int choice = scan.nextInt();

            if (choice > 0 && choice < 4) {
                BookedTrain myTrain = printByPrice(startLocation, endLocation, choicePrice, choice);
                if (myTrain != null) {
                    return myTrain;
                }
            } else if (choice == 4) {
                return null;
            }
        }
    }
    private static BookedTrain printByPrice(String startLocation, String endLocation, int price, int choice) {
        ArrayList<Train> myTrains = new ArrayList<>();

        System.out.println(trains.size());
        for (Train train : trains) {
            boolean printOrNot = false;
            if (train.containsDestinations(startLocation, endLocation)) {
                for (Room room : train.getRooms()) {

                        switch (choice) {
                            case 1:
                                if (Integer.toString(price).compareTo(Double.toString(room.getPrice())) < 1) {
                                    if (!printOrNot) {
                                        System.out.println(train.toStringWithoutRooms());
                                        myTrains.add(train);
                                    }
                                    printOrNot = true;
                                    System.out.println(room);

                                }
                                break;
                            case 2:
                                if (Integer.toString(price).compareTo(Double.toString(room.getPrice())) > -1) {
                                    if (!printOrNot) {
                                        System.out.println(train.toStringWithoutRooms());
                                        myTrains.add(train);
                                    }
                                    printOrNot = true;
                                    System.out.println(room);
                                }
                                break;
                            case 3:
                                if (Math.abs(price - room.getPrice()) <= 20.0) {
                                    if (!printOrNot) {
                                        System.out.println(train.toStringWithoutRooms());
                                        myTrains.add(train);
                                    }
                                    printOrNot = true;
                                    System.out.println(room);
                                }
                                break;
                        }

                    }
            }
        }

        System.out.println("Enter the id of the room that you want to book: ");
        String idChoice = scan.next();
        for (Train train : myTrains) {
            for (Room room : train.getRooms()){
                    switch (choice) {
                        case 1:
                            if (idChoice.equals(String.valueOf(room.getId())) && (price <= room.getPrice())) {
                                return bookTrain(train, room, startLocation, endLocation);
                            }
                        case 2:
                            if (idChoice.equals(String.valueOf(room.getId())) && (price >= room.getPrice())) {
                                return bookTrain(train, room, startLocation, endLocation);
                            }
                        case 3:
                            if (idChoice.equals(String.valueOf(room.getId())) && (Math.abs(price - room.getPrice()) <= 20.0)) {
                                return bookTrain(train, room, startLocation, endLocation);
                            }
                    }
            }
        }
        return null;
    }

    private static BookedTrain findByType(String startLocation, String endLocation) {
//        System.out.println("Choose the option, by which you want to find a hotel:\n1 - by price\n2 - by stars\n3 - amount of bedrooms");
        System.out.println("Input your type: ");
        String choiceType = scan.next();
        return printByType(startLocation, endLocation, choiceType);
    }


    private static BookedTrain printByType(String startLocation, String endLocation, String type) {
        ArrayList<Train> myTrains = new ArrayList<>();
        for (Train train : trains) {
            boolean printOrNot = false;
            for (Room room : train.getRooms()) {
                if (train.containsDestinations(startLocation, endLocation)) {
                    if (type.compareTo(room.getRoomType()) == 0) {
                        if (!printOrNot) {
                            System.out.println(train.toStringWithoutRooms());
                            myTrains.add(train);
                        }
                        printOrNot = true;
                        System.out.println(room);
                    }


                }


            }
        }
            System.out.println("Enter the id of the room that you want to book: ");
            String idChoice = scan.next();
            for (Train train : trains) {
                for (Room room : train.getRooms()) {
                    if (idChoice.equals(String.valueOf(room.getId())) && (type.equals(room.getRoomType()))) {
                        return bookTrain(train, room, startLocation, endLocation);
                    }
                }
            }
        return null;
    }

    private static BookedTrain bookTrain(Train train, Room room, String startLocation, String endLocation) {
        System.out.println(train);
        System.out.println(room);
        String dateChoice;
        while (true) {
            System.out.println("What date do you want to go? (Format : 12.08) ");
            dateChoice = scan.next();
            //@TODO validate is the date this format: 10.08 asd
            boolean changeDateOrNot = false;
            for (String bookedDay : room.getBookedDays()) {


                if (Objects.equals(dateChoice, bookedDay.trim())) {
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

        Destination startDestination = null;
        Destination endDestination = null;
        for (Destination destination: train.getDestinations() ) {
            if (destination.getName().equals(startLocation)) {
                startDestination = destination;
            }
            if (destination.getName().equals(endLocation)) {
                endDestination = destination;
            }
        }
        System.out.println("Do you really want to book the room? " + "Name = " + train.getName() + "Rating = " + train.getStars() + "Start of way = " + train.getStartOfWay() + "Arrival time = " + train.getArrivalTime() + ", Start location = " + startLocation + ", End location = " + endLocation + "Capacity = " + room.getAmountOfPeople() + "Price = " + room.getPrice() + "Type of room = " + room.getRoomType());
        //@TODO ask about confirmation
        return new BookedTrain(train, room, startDestination, endDestination, dateChoice);
    }


}







package booking;

import authentication.Account;
import authentication.LoginManager;
import authentication.RegistrationManager;
import authentication.models.EmailAccount;
import authentication.models.PhoneNumberAccount;
import java.util.Scanner;

public class InterfaceManager {
    private static final Scanner scanner = new Scanner(System.in);
    private static BookingManager myBookingManager;


    protected static void signOut() {
        myBookingManager = null;
        Start();
    }

    public static void Start() {
        System.out.println("Choose 1 to register or 2 to log in");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> {
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\t\t\t\tRegistration");
                System.out.println("Choose your type of registration:\n1 - By email\n2 - by phone number");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> registerByEmail();
                    case 2 -> registerByPhoneNumber();
                }
            }
            case 2 -> {
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\t\t\t\tLog in");
                login();
            }
        }
    }

    private static void registerByEmail() {
        System.out.println("email:");
        String email = scanner.next();
        System.out.println("password");
        String password = scanner.next();
        EmailAccount emailAccount = new EmailAccount(email,password);
        RegistrationManager.registerUser(emailAccount);
        myBookingManager = new BookingManager(emailAccount);
        PersonalAccountOptions();
    }


    private static void registerByPhoneNumber() {
        System.out.println("phone number:");
        String phoneNumber = scanner.next();
        System.out.println("password:");
        String password = scanner.next();
        PhoneNumberAccount phoneNumberAccount = new PhoneNumberAccount(phoneNumber, password);
        RegistrationManager.registerUser(phoneNumberAccount);
        myBookingManager = new BookingManager(phoneNumberAccount);
        PersonalAccountOptions();
    }

    private static void login() {
        Account myAccount = LoginManager.login();
        if (myAccount != null) {
            myBookingManager = new BookingManager(myAccount);
            PersonalAccountOptions();
        }
        else {
            Start();
        }
    }

    private static void PersonalAccountOptions() {
        while (true) {
            System.out.println("\n1 - book.. 2 - cancel 3 - sign out 4 - history of booking train 5 - history of booked hotels");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> myBookingManager.Book();
                case 2 -> myBookingManager.Cancel();
                case 3 -> myBookingManager.SignOut();
                case 4 -> myBookingManager.BookingHistoryTrain();
                case 5 -> myBookingManager.BookingHistoryHotel();
            }
        }
    }


}

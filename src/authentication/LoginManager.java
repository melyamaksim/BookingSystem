package authentication;
import authentication.models.EmailAccount;
import authentication.models.PhoneNumberAccount;
import booking.BookingManager;

import java.io.File;
import java.util.Scanner;

public class LoginManager {
    private static final Scanner scanner = new Scanner(System.in);
    private static BookingManager myBookingManager;
    public static Account login() {
        System.out.println("Enter your email/phone number:");
        String name = scanner.next();
        System.out.println("Enter your password:");
        String password = scanner.next();


        if (name.contains("@")) {
            //@TODO email
            System.out.println("login by email");
            EmailAccount myAccount = new EmailAccount(name, password);
            if (loginByEmail(myAccount)) {
                return myAccount;
            }
        }
        else
        {
            //@TODO phone
            System.out.println("login by phone number");
            PhoneNumberAccount myAccount = new PhoneNumberAccount(name, password);
            if (loginByPhoneNumber(myAccount)) {
                return myAccount;
            }
        }

        return null;
    }

    private static boolean loginByPhoneNumber(PhoneNumberAccount phoneNumberAccount) {
        try {
            String path = "PhoneNumberAccounts";
            File myFile = new File(path);
            Scanner read = new Scanner(myFile);

            while (read.hasNextLine()) {
                if (read.next().equals(phoneNumberAccount.phoneNumber)) {
                    read.next();
                    if (read.next().equals(phoneNumberAccount.password)) {
                        System.out.println("You logged in!");
                        myBookingManager = new BookingManager(phoneNumberAccount);
                        return true;
                    }
                    else {
                        System.out.println("error! Password is incorrect!");
                    }
                }
                else {
                    read.nextLine();
                }


                //@TODO if it is successfully = register booking manager

            }
            System.out.println("We don't have profile with such a phone number and password!");

        }
        catch (Exception exception) {
            System.out.println("We don't have such a file");
        }
        return false;
    }

    private static boolean loginByEmail(EmailAccount emailAccount) {
        try {
            String path = "EmailAccounts";
            File myFile = new File(path);
            Scanner read = new Scanner(myFile);
            while (read.hasNextLine()) {
                if (read.next().equals(emailAccount.email)) {
                    read.next();
                    if (read.next().equals(emailAccount.password)) {
                        System.out.println("You logged in!");
                        myBookingManager = new BookingManager(emailAccount);
                        return true;
                    }
                    else {
                        System.out.println("error! Password is incorrect!");
                    }
                }
                else {
                    read.nextLine();
                }
            }
            System.out.println("We don't have profile with such an email and password!");

            //@TODO if it is successfully = register booking manager

        }
        catch (Exception exception) {
            System.out.println("We don't have such a file");
        }
        return false;
    }
}

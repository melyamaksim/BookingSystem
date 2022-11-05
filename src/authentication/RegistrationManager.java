package authentication;

import authentication.models.EmailAccount;
import authentication.models.PhoneNumberAccount;

import java.io.FileWriter;


public class RegistrationManager {

    private static final String emailPath = "EmailAccounts";
    private static final String phonePath = "PhoneNumberAccounts";


    private RegistrationManager()  {}


    public static boolean registerUser(EmailAccount emailAccount) {


        try {
            FileWriter writer = new FileWriter(emailPath, true);
            String registrationInfo = emailAccount.email + " | "  + emailAccount.password + " |\n";
            writer.append(registrationInfo);
            writer.close();
            return true;
        }
        catch (Exception exception) {
            System.out.println("We don't have such a file");
            return false;
        }
    }

    public static boolean registerUser(PhoneNumberAccount phoneNumberAccount) {


        try {
            FileWriter writer = new FileWriter(phonePath, true);
            String registrationInfo = phoneNumberAccount.phoneNumber + " | "  + phoneNumberAccount.password + " |\n";
            writer.append(registrationInfo);
            writer.close();
            return true;
        }
        catch (Exception exception) {
            System.out.println("We don't have such a file");
            return false;
        }
    }


}

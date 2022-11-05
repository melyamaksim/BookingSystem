package authentication.models;

import authentication.Account;

public class PhoneNumberAccount extends Account {
    public String phoneNumber;

    public PhoneNumberAccount(String phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }


    public String getName() {
        return phoneNumber;
    }


    public String getNamee() {
        return phoneNumber;
    }
}

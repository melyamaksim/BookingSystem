package authentication.models;

import authentication.Account;

public class EmailAccount extends Account {
    public String email;

    public EmailAccount(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public String getName() {
        return email;
    }


    public String getNamee() {
        return email;
    }
}

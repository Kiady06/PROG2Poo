package org.HEI.kdot;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;

@Data
@AllArgsConstructor
public class Client {
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    public Client(int id,  String firstName, String lastName, String phoneNumber) {
        this(id, firstName,lastName,phoneNumber,null);
    }

    private boolean firstNameHasKeyWord(String keyword) {
        if (keyword == null) {
            return true;
        }

        return  this.getFirstName().toLowerCase().contains(keyword.toLowerCase());
    }

    private boolean lastNameHasKeyWord(String keyword) {
        if (keyword == null) {
            return true;
        }

        return  this.getLastName().toLowerCase().contains(keyword.toLowerCase());
    }

    private boolean emailHasKeyWord(String email) {
        if (email == null) {
            return true;
        }

        if (this.email == null) {
            return false;
        }

        return  this.getEmail().toLowerCase().contains(email.toLowerCase());
    }

    public boolean hasKeyWord(String keyword) {
        return firstNameHasKeyWord(keyword) || lastNameHasKeyWord(keyword) || emailHasKeyWord(keyword);
    }

    public Operator getOperator() {
        return Arrays.stream(Operator.values())
                .filter(op -> op.matches(phoneNumber))
                .findFirst()
                .orElse(null);
    }

}

package org.HEI.kdot;

public class Client {
    private int id;
    private String firstName;
    private String familyName;
    private String phoneNumber;

    public Client(int id, String firstName, String familyName, String phoneNumber) {
        setId(id);
        setFirstName(firstName);
        setFamilyName(familyName);
        setPhoneNumber(phoneNumber);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Client client)) return false;

        return getId() == client.getId() && getFirstName().equals(client.getFirstName()) && getFamilyName().equals(client.getFamilyName()) && getPhoneNumber().equals(client.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getFirstName().hashCode();
        result = 31 * result + getFamilyName().hashCode();
        result = 31 * result + getPhoneNumber().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

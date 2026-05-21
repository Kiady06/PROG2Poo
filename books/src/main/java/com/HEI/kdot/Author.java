package com.HEI.kdot;

import java.time.LocalDate;

public class Author {
    private int id;
    private String name;
    private String familyName;
    private String alias;
    private LocalDate birthDate;
    private Gender gender;
    private String country;

    public Author(int id, String name, String familyName, String alias, LocalDate birthDate, Gender gender, String country) {
        setId(id);
        setBirthDate(birthDate);
        this.name = name;
        this.familyName = familyName;
        this.alias = alias;
        this.gender = gender;
        this.country = country;
    }

    public Author(int id, String name, String familyName, LocalDate birthDate, Gender gender, String country) {
        this(id, name, familyName, null, birthDate, gender, country);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("ID <= 0? Congratulations, you managed to give this author a negative existence.");
        }
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        LocalDate maxBirthDate = LocalDate.now().minusYears(18);

        if (birthDate != null && !birthDate.isAfter(maxBirthDate)) {
            this.birthDate = birthDate;
        } else {
            throw new IllegalArgumentException("The author should at least be 18, we are running a publishing house, not a child labor syndicate.");
        }
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

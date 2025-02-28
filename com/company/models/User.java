package com.company.models;

import com.company.enums.Gender;
import com.company.interfaces.UserInterface;

public abstract class User implements UserInterface {
    private int id;
    private String name;
    private Gender gender;

    public User(String name, int id, Gender gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
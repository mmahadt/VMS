package com.bytexcite.alburhanvolunteers;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Volunteer {

    public String name;
    public String email;
    public String phone;
    public String expertise;
    public int weeklyHours;

    public Volunteer() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Volunteer(String name, String email, String phone, String expertise, int weeklyHours) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.expertise = expertise;
        this.weeklyHours = weeklyHours;
    }
}

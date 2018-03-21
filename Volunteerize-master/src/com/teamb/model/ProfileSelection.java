package com.teamb.model;

/**
 * Created by Sarah on 2017-11-21.
 */
public class ProfileSelection {

    String firstName;
    String lastName;
    boolean active;
    int volunteerID;

    public ProfileSelection(Profile p, boolean act){

        firstName = p.getFirstName();
        lastName = p.getLastName();
        volunteerID = p.getMemberID();
        active = act;

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getvolunteerID() {
        return volunteerID;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean a){
        active = a;
    }


}

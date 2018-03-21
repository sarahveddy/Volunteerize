package com.teamb.model;

public class Users {
    private String username;
    private String password;

    private boolean isStaff;

    private int profileID;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getProfileID() {
        return profileID;
    }

    public boolean getIsStaff() {
        return isStaff;
    }

    public void setIsStaff(boolean userType) {
        this.isStaff = userType;
    }

    public void setProfileID(int profileID) {
        this.profileID = profileID;
    }
}

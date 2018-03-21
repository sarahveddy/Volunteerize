package com.teamb.model;

import java.util.ArrayList;

/**
 * Represents all the information and functions of the volunteer profile.
 */
public class Profile {
    private String firstName;
    private String middleName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String emergencyContactPhoneNumber;
    private String emergencyContactFirst;
    private String emergencyContactMiddle;
    private String emergencyContactLast;
    private String emergencyContactPostalCode;
    private String emergencyContactAddress;
    private String email;
    private String photoPath;
    private String medicalInformation;
    private String postalCode;


    private int emergencyContactID;
    private int memberID;
    private int hoursWorked;

    private boolean contactByEmail;
    private boolean contactByPhone;
    private boolean criminalRecordCheck;

    private VolunteerGroup volunteerGroups;//Irene: maybe we don't need VolunteerGroup, if there is new group, we can add to the enum option in the table

    private Availability availability;

    private ArrayList<Integer> registeredEventIDs;



    public Profile(){
        this.volunteerGroups = new VolunteerGroup();
        this.registeredEventIDs = new ArrayList<Integer>(); //defaults to size 10
    }

//////////////////////////////Sean Start
    /**
     * sets values for a new volunteer profile object with the given information.
     *
     * @param firstName
     * @param middleName
     * @param lastName
     * @param address
     * @param phoneNumber
     * @param postalCode
     * @param emergencyContactPhoneNumber
     * @param emergencyContactFirst
     * @param emergencyContactMiddle
     * @param emergencyContactLast
     * @param emergencyContactPostalCode
     * @param emergencyContactAddress
     * @param postalCode
     * @param email
     * @param contactByPhone true if the volunteer wants to be contacted by phone
     * @param contactByEmail true if the volunteer wants to be contacted by email
     * @param memberID
     * @param criminalRecordCheck
     * @param medicalInformation short paragraph outlining any relevant medical information
     * @param hoursWorked
     * @param photoPath file path to the volunteer photo
     * @param availabilityArray availability in the morning and afternoon of each weekday
     */
    public void setAllBaseInformation(String firstName, String middleName, String lastName, String address, String phoneNumber,
                                      String postalCode,
                                      String emergencyContactPhoneNumber, String emergencyContactFirst,
                                      String emergencyContactMiddle, String emergencyContactLast, int emergencyContactID,
                                      String emergencyContactPostalCode, String emergencyContactAddress,
                                      String email, boolean contactByPhone, boolean contactByEmail,
                                      int memberID, boolean criminalRecordCheck, String medicalInformation,
                                      int hoursWorked, String photoPath, Availability availabilityArray){
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.postalCode = postalCode;
        this.emergencyContactFirst = emergencyContactFirst;
        this.emergencyContactMiddle = emergencyContactMiddle;
        this.emergencyContactLast = emergencyContactLast;
        this.emergencyContactPhoneNumber = emergencyContactPhoneNumber;
        this.emergencyContactID = emergencyContactID;
        this.emergencyContactPostalCode = emergencyContactPostalCode;
        this.emergencyContactAddress = emergencyContactAddress;

        this.email = email;
        this.contactByEmail = contactByEmail;
        this.contactByPhone = contactByPhone;
        this.memberID = memberID;
        this.criminalRecordCheck = criminalRecordCheck;
        this.medicalInformation = medicalInformation;
        this.hoursWorked = hoursWorked;
        this.photoPath = photoPath;
        this.availability = availabilityArray;
    }

    //////////////////////////////////////////////////////////
    // set specific fields of info

    /**
     * setMemberID(id)
     * @param  id - Member ID
     */
    public void setMemberID(int id) {
        this.memberID = id;
    }

    /**
    * setFirstName(name)
    * @param name - first name
    */
    public void setFirstName(String name){
        this.firstName = name;
    }
    /**
     * setMiddleName(name)
     * @param name - String, middle name
     */
    public void setMiddleName(String name){this.middleName = name; }
    /**
     *
     * @param last - last name
     */
    public void setLastName(String last){
        this.lastName = last;
    }
    /**
     *
     * @param streetAddress (eg. 1234 Albert.Ave)
     */
    public void setAddress(String streetAddress){
        this.address = streetAddress;
    }
    /**
     * setPostalCode(String code)
     * @param code - String postal code
     */
    public void setPostalCode(String code){ this.postalCode = code;}
    /**
     *
     * @param number - phone number
     */
    public void setPhoneNumber(String number){
        this.phoneNumber = number;
    }
    /**
     *
     * @param name - first name of emergency contact
     */
    public void setEmergencyContactFirstName(String name){
        this.emergencyContactFirst = name;
    }
    /**
     *
     * @param name - middle name of emergency contact
     */
    public void setEmergencyContactMiddleName(String name){
        this.emergencyContactMiddle = name;
    }
    /**
     *
     * @param name - last name of emergency contact
     */
    public void setEmergencyContactLastName(String name){
        this.emergencyContactLast = name;
    }
    /**
     *
     * @param number - phone number of emergency contact
     */
    public void setEmergencyContactPhoneNumber(String number){
        this.emergencyContactPhoneNumber = number;
    }
    /**
     * @param ID
     */
    public void setEmergencyContactID(int ID){this.emergencyContactID = ID;}
    /**
     * @param postalCode
     */
    public void setEmergencyContactPostalCode(String postalCode){this.emergencyContactPostalCode = postalCode;}
    /**
     * @param address
     */
    public void setEmergencyContactAddress(String address){this.emergencyContactAddress = address;}
    /**
     *
     * @param emailAddress (eg. foo@bar.com)
     */
    public void setEmail(String emailAddress){
        this.email = emailAddress;
    }
    /**
     *
     * @param value if the prefer to be contacted by email
     */
    public void setcontactByEmail(boolean value){
        this.contactByEmail = value;
    }
    /**
     *
     * @param value - if they have a criminal record check
     */
    public void setCriminalReccordCheck(boolean value){
        this.criminalRecordCheck = value;
    }
    /**
     *
     * @param info - applicable medical information
     */
    public void setMedicalInformation(String info){
        this.medicalInformation = info;
    }
    /**
     *
     * @param value - current hours worked
     */
    public void setHoursWorked(int value){
        this.hoursWorked = value;
    }

    /**
     *
     * @param time - Availability matrix 7x2
     */
    public void setAvailability(Availability time){
        this.availability = time;
    }
    /**
     *
     * @param path - location of image
     */
    public void setPhotoPath(String path){
        this.photoPath = path;
    }

    //////////////////////////////////////////////////////
    // add to specific fields of info

    /**
     *
     * @param ID - int, event IDl
     */
    public void addEventID(int ID){
        this.registeredEventIDs.add(ID);
    }
    /**
     *
     * @param value - int, additional hours worked
     */
    public void addHoursWorked(int value){
        this.hoursWorked += value;
    }
    /**
     *
     * @param groupName
     */
    public void addToGroup(String groupName){
        volunteerGroups.addGroup(groupName);
    }


    //////////////////////////////////////////////////////
    //get info in the specified field


    public String getFirstName(){
        return this.firstName;
    }
    public String getMiddleName(){return this.middleName;}
    public String getLastName(){
        return this.lastName;
    }
    public boolean getContactByMail(){
        return this.contactByEmail;
    }
    public String getAddress(){
        return this.address;
    }
    public String getPostalCode(){return this.postalCode;}
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public String getEmergencyContactFirst(){ return this.emergencyContactFirst;}
    public String getEmergencyContactMiddle(){return this.emergencyContactMiddle;}
    public String getEmergencyContactLast(){return this.emergencyContactLast;}
    public String getEmergencyContactPhoneNumber(){return this.emergencyContactPhoneNumber;}
    public String getEmergencyContactPostalCode(){return this.emergencyContactPostalCode;}
    public String getEmergencyContactAddress(){return  this.emergencyContactAddress;}
    public int getEmergencyContactID(){return this.emergencyContactID;}
    public String getEmail(){
        return this.email;
    }
    public boolean getContactByEmail(){
        return this.contactByEmail;
    }
    public boolean getContactByPhone() {
        return this.contactByPhone;
    }
    public int getMemberID(){
        return this.memberID;
    }
    public boolean getCriminalRecordCheck(){
        return this.criminalRecordCheck;
    }
    public String getMedicalInformation(){
        return this.medicalInformation;
    }
    public int getHoursWorked(){
        return this.hoursWorked;
    }
    public Availability getAvailability(){
        return this.availability;
    }
    public String getPhotoPath(){
        return this.photoPath;
    }

    //////////////////////////////////////////////
    // remove items

    public void removeFromGroup(String groupName){
        volunteerGroups.removeGroup(groupName);
    }

    public String toString(){
        return getLastName();
    }

}

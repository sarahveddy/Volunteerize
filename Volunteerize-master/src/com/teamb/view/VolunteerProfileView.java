package com.teamb.view;

import com.teamb.model.Availability;
import com.teamb.model.Profile;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class VolunteerProfileView extends BasicView {

    public Button home;
    public Button editProfile;

    protected GridPane gp;
    /**
     * Constructor.
     * Creates the root pane, and adds the children with the CreateChildren() method.
     * May have parameters based on what information is needed from the controller
     *
     * @param
     */
    public VolunteerProfileView() {
        super();
    }


    @Override
    public Pane GetRootPane() {
        return root;
    }

    @Override
    protected void CreateChildren(){
        gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setPadding(new Insets(30,30,30,30));
        gp.setHgap(10);
        gp.setVgap(10);

        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 170, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);
        columnOneConstraints.setHgrow(Priority.ALWAYS);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(100,170, Double.MAX_VALUE);
        columnTwoConstrains.setHalignment(HPos.LEFT);

        ColumnConstraints columnThreeConstrains = new ColumnConstraints(100,170, Double.MAX_VALUE);
        columnThreeConstrains.setHalignment(HPos.RIGHT);

        gp.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains,columnThreeConstrains);

        //Buttons
        home = new Button("Homepage");
        editProfile = new Button("Edit Profile");

        //Create Labels
        Label firstNameLabel = new Label("First Name:");
        Label lastNameLabel = new Label("Last Name:");
        Label addressLabel = new Label("Home Address:");
        Label phoneNumberLabel = new Label("Phone Number:");
        Label emergencyContact = new Label("Emergency Contact:");
        Label emergencyNumberLabel = new Label("Emergency Contact Number:");
        Label emergencyNameLabel = new Label("Emergency Contact Name:");
        Label emailLabel = new Label("Email:");
        Label memberIDLabel = new Label("Membership Number:");
        Label medicalInformationLabel = new Label("Medical Information:");
        Label contactPreference = new Label("Contact Preference:");
        Label volunteerGroup = new Label("Volunteer Group:");
        Label criminalRecordCheck = new Label("Criminal Record Checked?");
        Label availabilityLabel = new Label("Availability:");
        Label registeredEventsLabel = new Label("Registered Events:");
        Label phonePref = new Label("Prefer phone contact?");
        Label emailPref = new Label("Prefer email contact?");
        Label workedHourLabel = new Label("Hours worked:");
        Label basicInfoLabel = new Label("Basic Information:");

        //Add Header
        Label header = new Label("Profile");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gp.add(header, 1,0);
        gp.setHalignment(header, HPos.CENTER);
        gp.setMargin(header, new Insets(20,0,20,0));

        gp.add(home,0,0);
        gp.setHalignment(home,HPos.LEFT);
        gp.add(editProfile,2,0);


        gp.add(basicInfoLabel,0,1);
        gp.setHalignment(basicInfoLabel,HPos.LEFT);

        gp.add(memberIDLabel,0,2);
        gp.add(firstNameLabel,0,3);
        gp.add(lastNameLabel,0,4);
        gp.add(addressLabel,0,5);
        gp.add(phoneNumberLabel,0,6);
        gp.add(emailLabel,0,7);

        gp.add(contactPreference,0,8,3,1);
        gp.setHalignment(contactPreference,HPos.LEFT);

        gp.add(phonePref,0,9);
        gp.add(emailPref,0,10);
        gp.add(criminalRecordCheck,0,11);
        gp.add(volunteerGroup,0,12);
        gp.add(medicalInformationLabel,0,13);

        gp.add(emergencyContact,0,14,3,1);
        gp.setHalignment(emergencyContact,HPos.LEFT);

        gp.add(emergencyNameLabel,0,15);
        gp.add(emergencyNumberLabel,0,16);
        gp.add(availabilityLabel,0,17,3,1);
        gp.setHalignment(availabilityLabel,HPos.LEFT);
        gp.add(workedHourLabel,0,19);

        //Styling
        availabilityLabel.setPadding(new Insets(5,0,5,0));
        contactPreference.setPadding(new Insets(5,0,5,0));
        emergencyContact.setPadding(new Insets(5,0,5,0));
        basicInfoLabel.setPadding(new Insets(5,0,5,0));
        availabilityLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        contactPreference.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        emergencyContact.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        basicInfoLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));


        ScrollPane sp = new ScrollPane();
        sp.setContent(gp);

        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setPrefSize(600,600);


        root.getChildren().add(sp);


    }

    public void displayProfile(Profile profile){
        Label firstName = new Label(profile.getFirstName());
        Label lastName = new Label(profile.getLastName());
        Label address = new Label(profile.getAddress());
        Label phoneNumber = new Label(profile.getPhoneNumber());
        Label emergencyNumber = new Label(profile.getEmergencyContactPhoneNumber());
        Label emergencyName = new Label(profile.getEmergencyContactFirst()+profile.getEmergencyContactLast());
        Label email = new Label(profile.getEmail());
        Label memberID = new Label(String.valueOf(profile.getMemberID()));
        Label medicalInfo = new Label(profile.getMedicalInformation());
//        Label volGroup = new Label(profile.);
        Label phonepref = new Label((profile.getContactByPhone())?"Yes":"No");
        Label emailpref = new Label((profile.getContactByEmail())?"Yes":"No");
        Label workedHour = new Label(String.valueOf(profile.getHoursWorked()));


        GridPane availabilityGrid = new GridPane();
        availabilityGrid.setHgap(4);
        availabilityGrid.setVgap(2);
        Availability availability = profile.getAvailability();
        CheckBox[][] shiftCheckbox = new CheckBox[7][3];

        for(int day = 0; day < 7; day++) {
            for(int shift = 0; shift < 3; shift++) {
                shiftCheckbox[day][shift] = new CheckBox();
                shiftCheckbox[day][shift].setSelected(availability.GetAvailability(day,shift));
                shiftCheckbox[day][shift].setDisable(true);
                availabilityGrid.add(shiftCheckbox[day][shift],day,shift);
            }
        }



        gp.add(memberID,1,2);
        gp.add(firstName,1,3);
        gp.add(lastName,1,4);
        gp.add(address,1,5);
        gp.add(phoneNumber,1,6);
        gp.add(email,1,7);
        gp.add(phonepref,1,9);
        gp.add(emailpref,1,10);
//        gp.add(volunteerGroup,0,12);
        gp.add(medicalInfo,1,13);
        gp.add(emergencyName,1,15);
        gp.add(emergencyNumber,1,16);
        gp.add(availabilityGrid,0,18,3,1);
        gp.add(workedHour,1,19);
    }
}

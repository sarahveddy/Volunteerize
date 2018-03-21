package com.teamb.view;


import com.teamb.model.Availability;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class SignUpView extends BasicView {


    public Label header;
    public Button submit;
    public Button clear;
    public Button backButton;
    public Availability a;
    public GridPane availabilityGrid;
    public CheckBox[][] shiftCheckbox;
    public TextField firstNameField;
    public TextField middleNameField;
    public TextField lastNameField;
    public PasswordField passwordField;
    public TextField addressField;
    public TextField phoneNumberField;
    public TextField emergencyNumberField;
    public TextField emergencyFirstNameField;
    public TextField emergencyMiddleNameField;
    public TextField emergencyLastNameField;
    public TextField emergencyPostalCodeField;
    public TextField emergencyAddressField;
    public Spinner<Integer> workingHours;
    public TextField emailField;
    public TextField memberIDField;
    public TextArea medicalInformationField;
    public RadioButton phoneYes;
    public RadioButton emailYes;
    public RadioButton checked;
    public Availability availability;





    public SignUpView(){
        super();

    }

    @Override
    public Pane GetRootPane() {
        return root;
    }

    @Override
    protected void CreateChildren() {
        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setPadding(new Insets(30,30,30,30));
        gp.setHgap(10);
        gp.setVgap(10);

        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 180, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.LEFT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(100,170, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        ColumnConstraints columnThreeConstrains = new ColumnConstraints(100,170, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gp.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains, columnThreeConstrains);

        //Add Header
        header = new Label("Sign Up Form");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gp.add(header, 0,0,3,1);
        gp.setHalignment(header, HPos.CENTER);
        gp.setMargin(header, new Insets(20,0,20,0));

        //Create Labels
        Label firstNameLabel = new Label("First Name:");
        Label lastNameLabel = new Label("Last Name:");
        Label middleNameLabel = new Label("Middle Name:");
        Label passwordLabel = new Label("Password:");
        Label addressLabel = new Label("Home Address:");
        Label phoneNumberLabel = new Label("Phone Number:");
        Label emergencyContact = new Label("Emergency Contact Information:");
        Label emergencyNumberLabel = new Label("Emergency Contact Number:");
        Label emergencyNameLabel = new Label("Fiest Name:");
        Label emergencyMiddleNameLabel = new Label("Middle Name:");
        Label emergencyLastNameLabel = new Label("Last Name:");
        Label emergencyPostalCodeLabel= new Label("Postal Code:");
        Label emergencyAddressLabel = new Label("Address:");
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
        Label workingHourLabel = new Label("Working hours:");

        //Create textFields
        firstNameField = new TextField();
        middleNameField = new TextField();
        lastNameField  = new TextField();
        passwordField = new PasswordField();
        addressField  = new TextField();
        phoneNumberField = new TextField();
        emergencyNumberField  = new TextField();
        emergencyFirstNameField  = new TextField();
        emergencyMiddleNameField = new TextField();
        emergencyLastNameField = new TextField();
        emergencyPostalCodeField = new TextField();
        emergencyAddressField = new TextField();
        emailField  = new TextField();
        memberIDField  = new TextField();
        medicalInformationField = new TextArea();

        //Create Radio Buttons
        final ToggleGroup phonePrefer = new ToggleGroup();
        phoneYes = new RadioButton("Yes");
        RadioButton phoneNo = new RadioButton("No");
        phoneYes.setToggleGroup(phonePrefer);
        phoneNo.setToggleGroup(phonePrefer);

        final ToggleGroup emailPrefer = new ToggleGroup();
        emailYes = new RadioButton("Yes");
        RadioButton emailNo = new RadioButton("No");
        emailYes.setToggleGroup(emailPrefer);
        emailNo.setToggleGroup(emailPrefer);

        final ToggleGroup criminalCheckGroup = new ToggleGroup();
        checked = new RadioButton("Yes");
        RadioButton uncheck = new RadioButton("No");
        checked.setToggleGroup(criminalCheckGroup);
        uncheck.setToggleGroup(criminalCheckGroup);

        //set select default
        phoneYes.setSelected(true);
        emailNo.setSelected(true);
        checked.setSelected(true);

        //Create Dorpdown box
        ObservableList<String> volGrouplist =
                FXCollections.observableArrayList(
                        "Group1",
                        "Group2",
                        "Group3",
                        "Group4"
                );
        final ComboBox volGroupBox = new ComboBox(volGrouplist);


        /************Create Availability Table************/

        availabilityGrid = new GridPane();
        availabilityGrid.setHgap(4);
        availabilityGrid.setVgap(2);

        availability = new Availability();
        shiftCheckbox = new CheckBox[7][3]; /*7 days each with 3 shifts:morning,afternoon,evening*/
        for(int day = 0; day < 7; day++) {
            for(int shift = 0; shift < 3; shift++) {
                shiftCheckbox[day][shift] = new CheckBox();
                shiftCheckbox[day][shift].setSelected(false);
                availabilityGrid.add(shiftCheckbox[day][shift],day,shift);
            }
        }

        //Create Buttons
         submit = new Button("Submit");
         clear = new Button("Clear");
         backButton = new Button("<-Back");


        workingHours = new Spinner<>(0,100,1);


        //Add widgets onto gridPane
        gp.add(memberIDLabel,0,1);
        gp.add(memberIDField,1,1,2,1);

        gp.add(firstNameLabel, 0,2);
        gp.add(firstNameField, 1,2,2,1);

        gp.add(middleNameLabel, 0,3);
        gp.add(middleNameField, 1,3,2,1);

        gp.add(lastNameLabel, 0,4);
        gp.add(lastNameField, 1,4,2,1);

        gp.add(passwordLabel, 0,5);
        gp.add(passwordField, 1,5,2,1);

        gp.add(addressLabel,0,6);
        gp.add(addressField,1,6,2,1);

        gp.add(phoneNumberLabel,0,7);
        gp.add(phoneNumberField,1,7,2,1);

        gp.add(emailLabel,0,8);
        gp.add(emailField,1,8,2,1);

        gp.add(contactPreference,0,9);

        gp.add(phonePref,0,10);
        gp.add(phoneYes, 1,10);
        gp.add(phoneNo,2,10);

        gp.add(emailPref,0,11);
        gp.add(emailYes, 1,11);
        gp.add(emailNo,2,11);

        gp.add(criminalRecordCheck,0,12);
        gp.add(checked, 1,12);
        gp.add(uncheck,2,12);

        gp.add(volunteerGroup,0,13);
        gp.add(volGroupBox,1,13);

        gp.add(medicalInformationLabel,0,14);
        medicalInformationField.setMinHeight(50);
        gp.add(medicalInformationField,1,14,2,1);

        gp.add(emergencyContact,0,15);

        gp.add(emergencyNameLabel,0,16);
        gp.add(emergencyFirstNameField,1,16,2,1);

        gp.add(emergencyMiddleNameLabel,0,17);
        gp.add(emergencyMiddleNameField,1,17,2,1);

        gp.add(emergencyLastNameLabel,0,18);
        gp.add(emergencyLastNameField,1,18,2,1);

        gp.add(emergencyAddressLabel,0,19);
        gp.add(emergencyAddressField,1,19,2,1);

        gp.add(emergencyPostalCodeLabel,0,20);
        gp.add(emergencyPostalCodeField,1,20,2,1);

        gp.add(emergencyNumberLabel,0,21);
        gp.add(emergencyNumberField,1,21,2,1);

        gp.add(availabilityLabel,0,22);
        gp.add(availabilityGrid,0,23,3,1);

        gp.add(workingHourLabel,0,24);
        gp.add(workingHours,1,24);

        gp.add(submit,1,26);
        gp.add(clear,2,26);
        gp.add(backButton, 0,0 );
        //gp.setHalignment(backButton, HPos.LEFT);
        //gp.setMargin(backButton, new Insets(20,0,20,0));


        //Add Scroll Bar

        ScrollPane sp = new ScrollPane();
        sp.setContent(gp);

        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setPrefSize(600,600);


        root.getChildren().add(sp);
    }


}


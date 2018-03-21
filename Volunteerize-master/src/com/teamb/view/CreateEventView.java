package com.teamb.view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.time.LocalDate;
import java.util.Locale;

public class CreateEventView extends BasicView {


    //Instance Variables
    public TextField eventTitleField;
    public TextField locationField;
    public TextArea descriptionArea;
    public Button submit;
    public Button clear;
    public Button home;
    public Button back;
    public Spinner<Integer> startHour;
    public Spinner<Integer> startMin;
    public Spinner<Integer> endHour;
    public Spinner<Integer> endMin;
    public DatePicker startDatePicker, endDatePicker;


    /**
     * Constructor.
     * Creates the root pane, and adds the children with the CreateChildren() method.
     * May have parameters based on what information is needed from the controller
     *
     */
    public CreateEventView() {
        super();
    }

    @Override
    public Pane GetRootPane() {
        return root;
    }

    /**
     * Method used to create all objects
     * displayed on the view.
     *
     * Then formats the view nicely.
     */
    @Override
    protected void CreateChildren() {
        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setPadding(new Insets(30,30,30,30));
        gp.setHgap(10);
        gp.setVgap(10);

        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 200, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.LEFT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(100,150, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        ColumnConstraints columnThreeConstrains = new ColumnConstraints(100,150, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gp.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains, columnThreeConstrains);

        //Date
        startDatePicker = new DatePicker();
        endDatePicker = new DatePicker();
        Locale.setDefault(Locale.CANADA);
        startDatePicker.setValue(LocalDate.now());
        endDatePicker.setValue(startDatePicker.getValue().plusDays(1));



        //Add Header
        Label header = new Label("Create New Event");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gp.add(header, 0,0,3,1);
        GridPane.setHalignment(header, HPos.CENTER);
        GridPane.setMargin(header, new Insets(20,0,20,0));

        //Create Labels
        Label startDateLabel = new Label("Start Date : ");
        Label endDateLabel = new Label("End Date : ");
        Label startTimeLabel = new Label("Start Time : ");
        Label endTimeLabel = new Label("End Time : ");
        Label eventTitleLabel = new Label("Event Title : ");
        Label locationLabel = new Label("Location : ");
        Label description = new Label("Description : ");

        //Create textFields
        eventTitleField = new TextField();
        locationField = new TextField();
        descriptionArea = new TextArea();

        //Create Buttons
        submit = new Button("Submit");
        clear = new Button("Clear");
        home = new Button("Homepage");
        back = new Button("<- BACK");

        //Create Dorpdown box
        startHour = new Spinner<>(0,23,0);
        endHour = new Spinner<>(0,23,0);
        startMin = new Spinner<>(0,59,0);
        endMin = new Spinner<>(0,59,0);
        startHour.setEditable(true);
        startMin.setEditable(true);
        endHour.setEditable(true);
        endMin.setEditable(true);
        HBox startTime = new HBox();
        Label semicolen = new Label(" : ");
        semicolen.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        HBox endTime = new HBox();
        Label semicolen2 = new Label(" : ");
        semicolen2.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        startTime.getChildren().addAll(startHour,semicolen,startMin);
        endTime.getChildren().addAll(endHour,semicolen2,endMin);


        //Formatting Event View
        gp.add(back,0,0);

        gp.add(eventTitleLabel,0,1);
        gp.add(eventTitleField,1,1,2,1);

        gp.add(locationLabel,0,2);
        gp.add(locationField,1,2,2,1);

        gp.add(startTimeLabel,0,3);
        gp.add(startTime,1,3);

        gp.add(endTimeLabel,0,4);
        gp.add(endTime,1,4);

        gp.add(startDateLabel,0,5);
        gp.add(endDateLabel,1,5);

        gp.add(startDatePicker,0,6);
        gp.add(endDatePicker,1,6);

        gp.add(description,0,7);
        gp.add(descriptionArea,1,7,2,1);

        gp.add(home,0,9);
        gp.add(submit,1,9);
        GridPane.setHalignment(submit,HPos.RIGHT);
        gp.add(clear,2,9);

        //Add Scroll Bar
        ScrollPane sp = new ScrollPane();
        sp.setContent(gp);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setPrefSize(600,600);

        //Adding all newly created Children to the root
        root.getChildren().add(sp);
    }
}

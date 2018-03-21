package com.teamb.view;


import com.teamb.model.Event;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Created by David on 2017-11-20
 */

public class VolunteerEventProfileView extends BasicView{

    public Button addButton;
    public Button backButton;
    public Label eventName;
    public Label eventStartDate;
    public Label eventEndDate;
    public Label eventStartTime;
    public Label eventEndTime;
    public Label eventLocation;
    public Label eventDetail;
    public Label eventDescription;
    public Label eventMessageBoard;

    /**
     * Constructs the StaffEventProfile page.
     */
    public VolunteerEventProfileView(){
        super();
    }



    public void displayEvent(Event event){
        eventName.setText(event.getEventName());
        eventStartDate.setText(event.getStartDate());
        eventEndDate.setText(event.getEndDate());
        eventStartTime.setText(Integer.toString(event.getStartTime()));
        eventEndTime.setText(Integer.toString(event.getEndTime()));
        eventLocation.setText(event.getLocation());
        eventDescription.setText(event.getDescription());

    }

    @Override
    protected void CreateChildren() {

        eventName = new Label();
        Label eventStartDateLabel = new Label("START DATE:");
        eventStartDate = new Label();
        Label eventEndDateLabel = new Label("END DATE:");
        eventEndDate = new Label();
        Label eventStartTimeLabel = new Label("START TIME:");
        eventStartTime = new Label();
        Label eventEndTimeLabel = new Label("END TIME:");
        eventEndTime = new Label();
        Label eventLocationLabel = new Label("LOCATION");
        eventLocation = new Label();
        eventDescription = new Label ();
        eventDetail = new Label("[DETAILS]");
        eventMessageBoard = new Label("Message Board");

        //when this button is pressed view changes to event sign up page
        addButton = new Button("Sign Up For Event");

        backButton = new Button("<-Back");


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


        gp.add(backButton,0,0,3,1);

        //Add Header
        eventName.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        gp.add(eventName, 0,1,3,1);
        gp.setHalignment(eventName, HPos.CENTER);
        gp.setMargin(eventName, new Insets(20,0,20,0));


        eventStartDateLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        gp.add(eventStartDateLabel,0,2,3,1);
        gp.add(eventStartDate,1,2,3,1);

        eventEndDateLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        gp.add(eventEndDateLabel,0,3,3,1);
        gp.add(eventEndDate,1,3,3,1);

        eventStartTimeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        gp.add(eventStartTimeLabel,0,4,3,1);
        gp.add(eventStartTime,1,4,3,1);

        eventEndTimeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        gp.add(eventEndTimeLabel,0,5,3,1);
        gp.add(eventEndTime,1,5,3,1);

        eventLocationLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        gp.add(eventLocationLabel,0,6,3,1);
        gp.add(eventLocation,1,6,3,1);


        eventDetail.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        gp.add(eventDetail,0,7,3,1);
        gp.add(eventDescription,0,8,3,1);

        gp.add(addButton,1,9,3,1);

        eventMessageBoard.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gp.add(eventMessageBoard,0,10,3,1);
        gp.setHalignment(eventMessageBoard, HPos.LEFT);
        gp.setMargin(eventMessageBoard, new Insets(20,0,20,0));


        root.getChildren().add(gp);
    }



    @Override
    public Pane GetRootPane() {
        return root;
    }
}

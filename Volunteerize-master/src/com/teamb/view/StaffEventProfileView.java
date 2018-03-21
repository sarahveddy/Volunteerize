package com.teamb.view;



import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Created by David on 2017-11-20
 */

public class StaffEventProfileView extends BasicView {

    public Button addButton;
    public Button editButton;

    /**
     * Constructs the StaffEventProfile page.
     */
    public StaffEventProfileView(){
        super();
    }

    //@Override
    protected void CreateChildren() {
        addButton = new Button("Add Volunteers");

        //when this button is pressed the view changes to search result page
        editButton = new Button("Edit Event");


        /** Labels for Staff Event Profile view
         * details should  be gotten from Event
         *
         * */
        Label eventName = new Label("[Event Name]");
        Label eventDate = new Label("[Event Date]");
        Label eventLocation = new Label("[Event Location]"); /**Change to Location */
        Label eventDetail = new Label("[DETAILS]");
        Label eventDescription = new Label ("[DESCRIPTION]");
        Label eventMessageBoard = new Label("Message Board");



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


        //Add Header
        eventName.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        gp.add(eventName, 0,0,3,1);
        gp.setHalignment(eventName, HPos.CENTER);
        gp.setMargin(eventName, new Insets(20,0,20,0));


        gp.add(eventDate,0,1,3,1);
        gp.add(eventLocation,0,2,3,1);

        eventDetail.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        gp.add(eventDetail,0,3,3,1);

        gp.add(eventDescription,0,4,3,1);
        gp.add(addButton,1,5,3,1);
        gp.add(editButton,2,5,3,1);

        eventMessageBoard.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gp.add(eventMessageBoard,0,7,3,1);
        gp.setHalignment(eventMessageBoard, HPos.LEFT);
        gp.setMargin(eventMessageBoard, new Insets(20,0,20,0));


        root.getChildren().add(gp);

    }

    @Override
    public Pane GetRootPane() {
        return root;
    }
}

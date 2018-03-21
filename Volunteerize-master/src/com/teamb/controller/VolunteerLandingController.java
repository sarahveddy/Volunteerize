package com.teamb.controller;

import com.teamb.model.VolunteerizeModel;
import com.teamb.view.BasicView;
import com.teamb.view.VolunteerLandingView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/* CODE SMELLS
    Very similar to StaffLandingController
    Copied due to time constraints
 */



/**
 * Created by Sarah on 2017-11-19.
 */
public class VolunteerLandingController extends BasicController {

    VolunteerLandingView view;

    public VolunteerLandingController(Stage s, VolunteerizeModel m){
        super(s, m);
        view = new VolunteerLandingView();
        view.epButton.setOnAction(new epButtonEventHandler());
        view.ueButton.setOnAction(new ueButtonEventHandler());
        view.cpassButton.setOnAction(new cpassButtonEventHandler());
        view.helpButton.setOnAction(new helpButtonEventHandler());
        view.pButton.setOnAction(new pButtonEventHandler());
        view.logOutButton.setOnAction(new logOutButtonEventHandler());
    }

    class epButtonEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            ChangeToEditProfileView();
        }
    }


    class ueButtonEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            ChangeToEventsView();
        }
    }

    class cpassButtonEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            ChangeToChangePasswordView();
        }

    }

    class helpButtonEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            HelpPopUp();
        }
    }

    class pButtonEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            ChangeToProfileView();
        }
    }

    class logOutButtonEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            LogOutPopBox();
        }
    }





    public void LogOutPopBox(){
        Stage popupwindow=new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Log Out");


        Label logOutLabel= new Label("Are you sure you want to Log Out");
        Button yesButton= new Button("YES");
        Button noButton= new Button("NO");

        yesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                popupwindow.close();
                ChangeToMainLandingView();

            }
        });

        noButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                popupwindow.close();

            }
        });

        VBox layout= new VBox(10);
        layout.getChildren().addAll(logOutLabel, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 300, 250);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();

    }




    public void HelpPopUp(){
        //TODO
    }

    @Override
    protected VolunteerLandingView GetView() {
        return view;
    }
}

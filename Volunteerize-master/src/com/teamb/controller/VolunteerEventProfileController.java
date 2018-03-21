package com.teamb.controller;

/**
 * Created by David on 2017-11-20
 */

import com.teamb.model.Event;
import com.teamb.model.VolunteerizeModel;
import com.teamb.view.BasicView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.teamb.view.VolunteerEventProfileView;

public class VolunteerEventProfileController extends BasicController {
    VolunteerEventProfileView volunteerView;
    Event event;

    public VolunteerEventProfileController(Stage s, VolunteerizeModel m, Event e){
        super(s, m);
        event =e;
        volunteerView = new VolunteerEventProfileView();
        volunteerView.displayEvent(event);
        volunteerView.addButton.setOnAction(new addButtonEventHandler());
        volunteerView.backButton.setOnAction(new backButtonEventHandler());

    }

    class addButtonEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e){
            model.addEventParticipants(event, model.getProfile());
            AddToEvent(/*editEventView*/);
        }
    }

    class backButtonEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e){
            ChangeToEventView(stage);
        }
    }

    public void completePopUP() {
        Stage popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Sign Up Complete");


        Label completeInformationLabel = new Label("You have signed up for " + event.getEventName());
        Button profileButton = new Button("Go Back to Homepage");
        profileButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                popupwindow.close();

                ChangeToProfileView();
            }
        });
    }


    public void ChangeToEventView(Stage s){
        EventController vlc = new EventController(s, model);

        Scene scene = new Scene(vlc.GetView().GetRootPane(), 720, 540);
        s.setScene(scene);
        s.show();

    }

        @Override
    public VolunteerEventProfileView GetView() {
        return volunteerView;
    }



    public void AddToEvent(){
        /**Method that calls event sign up page and adds this Volunteer
        * to Event
        */
    }


}

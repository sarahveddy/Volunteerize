package com.teamb.controller;

import com.teamb.model.Event;
import com.teamb.model.EventSelection;
import com.teamb.model.VolunteerizeModel;
import com.teamb.view.BasicView;
import com.teamb.view.ManageEventView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ManageEventController extends BasicController {

    ManageEventView view;
    ArrayList<Event> events;

    public ManageEventController(Stage s, VolunteerizeModel m) {
        super(s, m);
        view = new ManageEventView();
        events = model.getUpcomingEvents();
        view.deleteEventsBtn.setOnAction(new deleteEventsBtnEventHandler());
        view.createNewEventBtn.setOnAction(new createNewEventBtnEventHandler());
        view.backButton.setOnAction(new backButtonEventHandler());
        view.PopulateEventList(events);
    }

    class deleteEventsBtnEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            for (EventSelection es:view.table.getItems()) {
                if(es.isActive()){
                    System.out.println(es.getEventTitle()+" is deleted.");
                }
            }
        }
    }

    class createNewEventBtnEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            handleCreateNewEventButtonClick();
        }
    }

    class backButtonEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            ChangeToStaffLandingView();
        }
    }

    @Override
    public ManageEventView GetView() {
        return view;
    }

    public void handleCreateNewEventButtonClick(){
        CreateEventController createEventController = new CreateEventController(stage, model);
        Scene scene = new Scene(createEventController.GetView().GetRootPane(),600,600);
        stage.setScene(scene);
        stage.show();
    }

}

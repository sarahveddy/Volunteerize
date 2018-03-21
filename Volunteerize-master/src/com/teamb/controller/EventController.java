package com.teamb.controller;

import com.teamb.model.VolunteerizeModel;
import com.teamb.view.EventView;
import com.teamb.model.Event;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 * This EventController gets Upcoming events from
 * the database.
 *
 * Then calls EventView to display the information
 * from database.
 *
 * @author Irene
 * @version 1.0
 * @since   2017-12-04
 */
public class EventController extends BasicController {

    //Instance Variables
    private ArrayList<Event> events;
    private EventView eventView;


    /**
     * Class constructor.
     */
    EventController(Stage s, VolunteerizeModel m) {
        super(s, m);
        events = model.getUpcomingEvents();
        eventView = new EventView();
        eventView.PopulateEventList(events);
        eventView.backButton.setOnAction(new backButtonEventHandler());
        loadButtons();
    }

    public void editEvent(Event event) {
        //TODO: CAll method that changes event in database

        VolunteerizeModel model = new VolunteerizeModel();
        //event passed in must already be altered.
        model.editEvent(event);

    }

    public void deleteEvent(Event event) {
        //TODO:Call method that deletes event from database

        VolunteerizeModel model = new VolunteerizeModel();
        //event passed in is one to be deleted.
        model.deleteEvent(event);
    }

    /**
     * Method that returns an arrayList
     * of type Event for all
     * upcoming events.
     */
    public ArrayList<Event> getEvents() {
        return events;

    }

    /**
     * Method that creates a "readMore" button
     * for all upcoming events gotten from the
     * database.
     *
     * setOnAction to know what button was clicked.
     * once button clicked it then changes to
     * VolunteerEventProfileView for that event.
     */
    private void loadButtons(){
        for(int i = 0; i < eventView.buttons.size(); i++){
            int temp = i;
            eventView.buttons.get(i).setOnAction((ActionEvent) -> {
                changeToVolunterEventProfileView(temp);
            });
        }
    }


    /**
     * Event handler method that handles back button action
     * and goes back to the previous page.
     */
    class backButtonEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            ChangeToVolunteerLandingView();
        }
    }


    /**
     * Method takes in a unique number
     * to identify what button was clicked.
     * It then uses these ID to identify the event
     * and display the Event page.
     * @param id;
     */
    private void changeToVolunterEventProfileView(int id){
        Event event = events.get(id);
        VolunteerEventProfileController vlc = new VolunteerEventProfileController(stage, model, event);
        Scene scene = new Scene(vlc.GetView().GetRootPane(), 720, 540);
        stage.setScene(scene);
        stage.show();

    }

    public EventView GetView(){
        return eventView;
    }

}

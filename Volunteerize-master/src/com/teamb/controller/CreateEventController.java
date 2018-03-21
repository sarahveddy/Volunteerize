package com.teamb.controller;

import com.teamb.model.Event;
import com.teamb.model.VolunteerizeModel;
import com.teamb.view.BasicView;
import com.teamb.view.CreateEventView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.time.LocalDate;


/**
 * This CreateEventController displays a page that
 * allows the user input details needed to create
 * new events
 *
 * @author  Irene
 * @version 1.0
 * @since   2017-12-04
 */
public class CreateEventController extends BasicController {
    CreateEventView view;
    Event event;


    /**
     * Class constructor.
     */
    CreateEventController(Stage s, VolunteerizeModel m) {
        super(s, m);
        view = new CreateEventView();
        view.submit.setOnAction(new submitEventHandler());
        view.clear.setOnAction(new clearEventHandler());
        view.home.setOnAction(new homeEventHandler());
        view.back.setOnAction(new backEventHandler());
    }

    /**
     * EventHandler methods to handle button clicks.
     * Each event handler does a special property on a
     * button click
     *
     */
    class submitEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            createNewEvent();
            completePopUP();
        }
    }

    class clearEventHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {
            view.eventTitleField.clear();
            view.locationField.clear();
            view.descriptionArea.clear();
            view.startDatePicker.setValue(LocalDate.now());
            view.endDatePicker.setValue(view.startDatePicker.getValue().plusDays(1));
        }
    }

    class homeEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            ChangeToStaffLandingView();

        }
    }

    class backEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            ChangeToManageEventsView();
        }
    }

    /**
     * This method gets the value from the fields of
     * the CreateEventView.
     *
     * Creates a new Event based on the values
     * gotten from the fields.
     */
    private void createNewEvent() {
        if(view.startHour.getValue()>23){
            view.startHour.getValueFactory().setValue(23);
        }
        if(view.endHour.getValue()>23){
            view.endHour.getValueFactory().setValue(23);
        }
        if(view.startMin.getValue()>59){
            view.startMin.getValueFactory().setValue(59);
        }
        if(view.endMin.getValue()>59){
            view.endMin.getValueFactory().setValue(59);
        }

        int startTime = (view.startHour.getValue()*100)+view.startMin.getValue();
        int endTime = (view.endHour.getValue()*100)+view.endMin.getValue();
        System.out.println(startTime);
        System.out.println(endTime);

        event = new Event();
        event.setEventName(view.eventTitleField.getText());
        event.setStartTime(startTime);
        event.setEndTime(endTime);
        event.setStartDate( view.startDatePicker.getEditor().getText());
        event.setEndDate( view.endDatePicker.getEditor().getText());
        event.setLocation(view.locationField.getText());
        event.setDescription(view.descriptionArea.getText());
        model.addEvent(event);
    }

    /**
     * Pop up box to alert user of newly created Event
     * and a button that goes back to Manage events
     *
     */
    private void completePopUP(){
        Stage popupwindow=new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Volunteerize");
        Label completeInformationLabel= new Label(event.getEventName() + " has now been created");
        Button profileButton= new Button("Go to Manage Events");
        profileButton.setOnAction(event -> {
            popupwindow.close();
            ChangeToManageEventsView();
        });
        VBox layout= new VBox(10);
        layout.getChildren().addAll(completeInformationLabel, profileButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 300, 250);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();

    }


    @Override
    public BasicView GetView() {
        return view;
    }

}

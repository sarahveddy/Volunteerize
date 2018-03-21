package com.teamb.view;


import com.teamb.model.Event;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import java.util.ArrayList;


/**
 * This EventView displays Upcoming Events
 *
 *
 * @author Irene
 * @version 1.0
 * @since   2017-12-04
 */
public class EventView extends BasicView {

    //Instance Variables.
    private VBox outside;
    private VBox eventListBox;
    public Button backButton;
    private Button readMore;
    public GridPane gp;
    public ArrayList<Button> buttons;

    /**
     * Class constructor.
     */
    public EventView(){
        super();
        buttons = new ArrayList<>();
    }

    /**
     * Information gotten from the parameter
     * is used to display results.
     *
     * @param events;
     *
     */
    public void PopulateEventList(ArrayList<Event> events){
        for(int i = 0; i < events.size(); i++){
            gp = new GridPane();
            ColumnConstraints column1 = new ColumnConstraints();
            column1.setPercentWidth(33);
            gp.getColumnConstraints().addAll(column1, column1, column1);
            Label title = new Label();

            title.setText(events.get(i).getEventName());
            Label description = new Label();
            description.setWrapText(true);
            description.setText(events.get(i).getDescription());
            readMore = new Button("Read More");
            buttons.add(readMore);

            gp.add(title, 1, 0 );
            gp.add(description, 1, 1, 2, 1);
            gp.add(readMore, 2, 2 );
            eventListBox.getChildren().add(gp);
        }
    }


    /**
     * Method used to create all objects used in
     * displaying the view.
     *
     * Then formats the view nicely.
     */
    @Override
    protected void CreateChildren() {
        backButton = new Button("<-Back");
        outside = new VBox(5);

        eventListBox = new VBox(5);
        Label title = new Label("Upcoming Events");
        title.setId("scenetitle");
        outside.getChildren().add(backButton);
        outside.getChildren().add(title);
        outside.getChildren().add(eventListBox);

        root.getChildren().add(outside);
    }


    @Override
    public Pane GetRootPane() {
        return root;
    }
}

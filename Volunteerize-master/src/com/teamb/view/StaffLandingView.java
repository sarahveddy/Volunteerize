package com.teamb.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


/* CODE SMELLS
    Very similar to VolunteerLandingView
    Copied due to time constraints
 */

/**
 * Created by Sarah on 2017-11-19.
 */
public class StaffLandingView extends BasicView {


    public Button mvButton;
    public Button meButton;
    public Button cpassButton;
    public Button helpButton;


    public StaffLandingView(){
        super();
    }

    /**
     * Returns the root pane.
     *
     * @return
     */
    @Override
    public Pane GetRootPane() {
        return root;
    }

    @Override
    protected void CreateChildren() {
        Label welcome = new Label("Welcome!");
        Label prompt = new Label("What would you like to do?");
        mvButton = new Button("Manage Volunteers");

        meButton = new Button("Manage Events");

        cpassButton = new Button("Change Password");

        helpButton = new Button ("Help");

        VBox mainContainer = new VBox();

        mainContainer.getChildren().addAll(welcome, prompt, mvButton, meButton, cpassButton, helpButton);
        root.getChildren().add(mainContainer);

    }
}

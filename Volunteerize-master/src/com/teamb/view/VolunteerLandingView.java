package com.teamb.view;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


/* CODE SMELLS
    Very similar to StaffLandingView
    Copied due to time constraints
 */

/**
 * Created by Sarah on 2017-11-19.
 */
public class VolunteerLandingView extends BasicView {


    public Button epButton;
    public Button ueButton;
    public Button cpassButton;
    public Button helpButton;
    public Button pButton;
    public Button logOutButton;

    public VolunteerLandingView(){
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

    /**
     * Creates the interface containers and elements, and adds them to the root pane.
     */
    @Override
    protected void CreateChildren() {

        Label welcome = new Label("Welcome!" /* TODO get name only */);
        Label prompt = new Label("What would you like to do?");
        epButton = new Button("Edit Profile");

        ueButton = new Button("See upcoming events");

        cpassButton = new Button("Change Password");

        helpButton = new Button ("Help");

        pButton = new Button("Look at my profile");

        logOutButton = new Button ("LOG OUT");


        VBox mainContainer = new VBox();
        mainContainer.setAlignment(Pos.CENTER);


        mainContainer.getChildren().addAll(welcome, prompt, pButton, epButton, ueButton, cpassButton, helpButton, logOutButton);
        root.getChildren().add(mainContainer);

    }
}

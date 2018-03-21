/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamb;

import com.teamb.controller.*;
import com.teamb.model.Availability;
import com.teamb.model.Profile;
import com.teamb.model.VolunteerizeModel;
import com.teamb.view.MainLandingView;
import com.teamb.view.VolunteerProfileView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author irene
 */
public class Volunteerize extends Application {

    @Override
    public void start(Stage primaryStage) {
        //LoginView loginView = new LoginView();

        //VolunteerProfileController mainController = new VolunteerProfileController(primaryStage);
       //CreateEventController mainController = new CreateEventController(primaryStage);
        VolunteerizeModel model = new VolunteerizeModel();
        LoginViewController mainController = new LoginViewController(primaryStage, model);
        Scene scene = new Scene(mainController.GetView().GetRootPane(), 600, 600);

        primaryStage.setTitle("Volunteerize");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

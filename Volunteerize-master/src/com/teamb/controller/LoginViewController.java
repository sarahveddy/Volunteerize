package com.teamb.controller;

import com.teamb.model.VolunteerizeModel;
import com.teamb.model.Users;
import com.teamb.view.LoginView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class LoginViewController extends BasicController {
    LoginView loginView;

    public LoginViewController(Stage s, VolunteerizeModel m){
        super(s, m);
        loginView = new LoginView();
        loginView.login.setOnAction(new loginEventHandler());
        loginView.Signup.setOnAction(new SignupEventHandler());
    }

    class loginEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (checkCredentials(loginView.userNameField.getText(), loginView.pwField.getText())) {
                loginView.loginOK = true;
            } else {
                Label loginError = new Label("username or password is wrong. Please try again.");
                loginView.gp.add(loginError, 0, 5, 3, 1);
            }
        }
    }

    class SignupEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            //TODO: check if staff
            ChangeToSignUpView();
        }
    }


    public boolean checkCredentials(String username, String password) {
            model.login(username, password);

            // Check to see if a user was returned, if so, credentials were
            // OK, return true.
            if(model.getUser().getUsername() != null) {
                this.ChangeToLandingView();
                return true;
            } else {
                return false;
            }
    }




    public LoginView GetView() {
        return loginView;
    }
}

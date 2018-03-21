package com.teamb.controller;


import com.teamb.model.ProfileSelection;
import com.teamb.model.VolunteerizeModel;
import com.teamb.model.Profile;
import com.teamb.view.BasicView;
import com.teamb.view.ManageVolunteersView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;


/**
 * Created by Sarah on 2017-11-19.
 */
public class ManageVolunteersController extends BasicController{



    ManageVolunteersView view;

    public ManageVolunteersController(Stage s, VolunteerizeModel m) {
        super(s, m);
        view = new ManageVolunteersView();
        view.loadProfilesIntoTable(model.getProfiles());
        view.createNewVolButton.setOnAction(new createNewVolButtonEventHandler());
        view.sendEmailButton.setOnAction(new sendEmailButtonEventHandler());
        view.printPhoneListButton.setOnAction(new printPhoneListButtonEventHandler());
        view.deleteProfilesButton.setOnAction(new deleteProfilesButtonEventHandler());
        view.searchBtn.setOnAction(new searchBtnEventHandler());
    }

    class createNewVolButtonEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            ChangeToSignUpView();
        }
    }

    class sendEmailButtonEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            SendEmails(/* TODO selected vol id's*/);
        }
    }

    class printPhoneListButtonEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            PrintPhoneList();
        }
    }

    class deleteProfilesButtonEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            DeleteProfiles();
        }

    }

    class searchBtnEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            ArrayList<Profile> temp;
            String key = view.searchKeyField.getText();
            if(key.compareTo("") == 0){
                temp = model.getProfiles();
            }else{
                temp = model.searchProfileName(key);
            }

            view.loadProfilesIntoTable(temp);
        }

    }



    @Override
    public ManageVolunteersView GetView() {
        return view;
    }



    public void SendEmails(/*TODO*/){
        //TODO will send Emails to given Volunteers (use volunteer id)
    }

    public void PrintPhoneList(/*TODO*/){
        //TODO will get phone numbers of given Volunteers (use volunteer id)
    }

    public void DeleteProfiles(/*TODO*/){
        int s = view.table.getItems().size();
        for(int i=0;i<s;i++){
            ProfileSelection ps = view.table.getItems().get(i);
           if(ps.isActive()) {
               model.deleteProfile(ps.getvolunteerID());
           }
        }
        view.loadProfilesIntoTable(model.getProfiles());
    }

    public void Search(String s){

        ArrayList<Profile> profileFound = model.searchProfileName(s);

        //TODO Given a string, search for volunteers
        //TODO Display new list of volunteers in view (create new view?)
        //Get input from input field
    }


}

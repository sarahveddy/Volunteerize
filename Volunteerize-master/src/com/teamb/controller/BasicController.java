package com.teamb.controller;

import com.teamb.Volunteerize;
import com.teamb.model.VolunteerizeModel;
import com.teamb.view.BasicView;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/* CODE SMELLS
    All controllers are very similar - have similar functions
    Tried to reduce code smell using this abstract class
    Could be used to do more controller functions
    Much more could be done to reduce this code duplication
 */

/**
 * Created by Sarah on 2017-11-07.
 * Abstract class all controllers extend
 */
public abstract class BasicController {

    Stage stage;
    VolunteerizeModel model;


    public BasicController(Stage s, VolunteerizeModel model){
        stage = s;
        this.model = model;
    }



    protected abstract BasicView GetView();


    /**
     * Methods to Change to different view
     * on the application.
     */

    public void ChangeToEditProfileView(){
        SignUpController editlc = new SignUpController(stage, model, model.getProfile());
        Scene scene = new Scene(editlc.GetView().GetRootPane(), 720, 540);
        stage.setScene(scene);
        stage.show();
    }

    public void ChangeToEventsView(){
        EventController ec = new EventController(stage, model);
        Scene scene = new Scene(ec.GetView().GetRootPane(), 600, 600);
        stage.setScene(scene);
        stage.show();
    }

    public void ChangeToLoginView(){
        LoginViewController lic = new LoginViewController(stage, model);

        Scene scene = new Scene(lic.GetView().GetRootPane(), 720, 540);
        stage.setScene(scene);
        stage.show();

    }

    public void ChangeToLandingView(){

        if(model.getUser().getIsStaff()){
            StaffLandingController slc = new StaffLandingController(stage, model);
            Scene scene = new Scene(slc.GetView().GetRootPane(), 720, 540);
            stage.setScene(scene);
            stage.show();
        }
        else{
            VolunteerLandingController vlc = new VolunteerLandingController(stage, model);
            Scene scene = new Scene(vlc.GetView().GetRootPane(), 720, 540);
            stage.setScene(scene);
            stage.show();
        }

    }

    public void ChangeToMainLandingView(){
        MainLandingController mlc = new MainLandingController(stage, model);
        Scene scene = new Scene(mlc.GetView().GetRootPane(), 600, 600);
        stage.setScene(scene);
        stage.show();
    }

    public void ChangeToManageEventsView(){
        ManageEventController mvc = new ManageEventController(stage, model);
        Scene scene = new Scene(mvc.GetView().GetRootPane(), 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public void ChangeToManageVolunteersView(){
        ManageVolunteersController mvc = new ManageVolunteersController(stage, model);

        Scene scene = new Scene(mvc.GetView().GetRootPane(), 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public void ChangeToProfileView(){
        VolunteerProfileController vlc = new VolunteerProfileController(stage, model);
        Scene scene = new Scene(vlc.GetView().GetRootPane(), 600, 600);
        stage.setScene(scene);
        stage.show();
    }

    public void ChangeToSignUpView(){
        SignUpController c = new SignUpController(stage, model);
        Scene scene = new Scene(c.GetView().GetRootPane(),600,600);
        stage.setScene(scene);
        stage.show();

    }

    public void ChangeToStaffLandingView(){
        StaffLandingController vlc = new StaffLandingController(stage, model);
        Scene scene = new Scene(vlc.GetView().GetRootPane(), 720, 540);
        stage.setScene(scene);
        stage.show();

    }


    public void ChangeToVolunteerLandingView(){
        VolunteerLandingController slc = new VolunteerLandingController(stage, model);
        Scene scene = new Scene(slc.GetView().GetRootPane(), 720, 540);
        stage.setScene(scene);
        stage.show();

    }



    public void ChangeToSearchReturnView(){
        //TODO
    }

    public void ChangeToEditEventView(){
        //TODO
    }




    public void ChangeToChangePasswordView(){
        //TODO
    }



}

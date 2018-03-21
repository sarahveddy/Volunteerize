package com.teamb.view;



import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.teamb.model.Event;

public class TestView extends Application{
    Stage newStage;

    @Override
    public void start(Stage primaryStage) {

        newStage = primaryStage;
        //LoginView lginView = new LoginView(newStage);
        Event event = new Event();
        //StaffEventProfileController sc =  new StaffEventProfileController(newStage);
        //CreateEventController sc = new CreateEventController(newStage);

        //Scene scene = new Scene(sc.GetView().GetRootPane(), 720, 540);

        primaryStage.setTitle("Volunteerize");
        //primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}


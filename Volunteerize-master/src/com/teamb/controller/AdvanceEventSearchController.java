package com.teamb.controller;

import com.teamb.model.VolunteerizeModel;
import com.teamb.view.AdvanceEventSearchView;
import com.teamb.view.BasicView;
import javafx.stage.Stage;

public class AdvanceEventSearchController extends BasicController {
    AdvanceEventSearchView view;

    public AdvanceEventSearchController(Stage s,VolunteerizeModel m) {
        super(s, m);
        view = new AdvanceEventSearchView();
    }

    @Override
    public BasicView GetView() {
        return view;
    }



}

package com.teamb.view;

import com.teamb.controller.BasicController;
import javafx.scene.layout.Pane;

/* CODE SMELLS
    All views are very similar - have similar functions
    Reason for duplication of code - time constraints, keeping views separate, so people can work separately on them,
    Tried to mitigate code smell using this abstract class
    Could be used to do more view functions
    Much more could be done to reduce this code duplication
 */


/**
 * Created by Sarah on 2017-11-07.
 * Abstract view class.
 */
public abstract class BasicView {


    protected Pane root;

    /**
     * Constructor.
     * Creates the root pane, and adds the children with the CreateChildren() method.
     * May have parameters based on what information is needed from the controller
     */
    public BasicView(){
        root = new Pane();
        this.CreateChildren();
    }

    /**
     * Returns the root pane.
     * @return
     */
    public abstract Pane GetRootPane();

    /**
     * Creates the interface containers and elements, and adds them to the root pane.
     */
    protected abstract void CreateChildren();
}

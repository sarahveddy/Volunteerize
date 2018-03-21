package com.teamb.model;

/**
 * Represents all the information and functions of the Jobs that participants do at events.
 */
public class Jobs {

    private String name;
    private String description;

    private int id;

    public Jobs() {
        this.id = 0;
        this.name = "Enter Name";
        this.description = "Enter Description";
    }

    public void SetBaseInformation(int id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public void setId (int id){
        this.id = id;
    }

    public int getId (){
        return this.id;
    }

    public void setName (String name){
        this.name = name;
    }

    public String getName () {
        return this.name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

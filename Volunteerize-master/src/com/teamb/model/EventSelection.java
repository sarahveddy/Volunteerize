package com.teamb.model;

public class EventSelection{
    String eventTitle;
    boolean active;
    int eventID;

    public EventSelection(Event e, boolean act){
        eventID = e.getEventID();
        eventTitle = e.getEventName();
        active = act;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public int getEventID() {
        return eventID;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean a){
        active = a;
    }

}

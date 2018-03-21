package com.teamb.model;

import java.util.List;

/**
 * Created by Sarah on 2017-10-10.
 */
public class VolunteerGroup {

    List<String> groupList;


//    VolunteerGroup(){
//    	groupList = new List<String>();
//    }

    public void addGroup(String groupName){

        groupList.add(groupName);
    }

    public void removeGroup(String groupName){
    	groupList.remove(groupName);
    }

}


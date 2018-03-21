package com.teamb.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

/**
 * Created by Sarah on 2017-10-10.
 */
public class Availability {
    public boolean[][] availabilityArray;

    public Availability()
    {
        availabilityArray = new boolean[7][3];
        for(int i=0;i<7;i++){
            for(int j=0;j<3;j++){
                availabilityArray[i][j] = false;
            }
        }
    }

    //0 = monday, 1 = tuesday etc
    //0 = morning, 1 = afternoon
    public void ChangeAvailability(int day, int shift, boolean isAvailable){
        availabilityArray[day][shift] = isAvailable;
    }

    public boolean GetAvailability(int day, int shift){
        return availabilityArray[day][shift];
   }


    public String toString(){
       String temp = "";
       System.out.println("Day  |  Morning  |  Afternon  |  Evening");
       for(int i=0; i<7;i++){
           temp += (i+1) + "          ";
           if(availabilityArray[i][0]){
               temp += "true       ";
           }else{
               temp += "false       ";
           }
           if(availabilityArray[i][1]){
               temp += "true       ";
           }else{
               temp += "false       ";
           }
           if(availabilityArray[i][2]){
               temp += "true       ";
           }else{
               temp += "false       ";
           }
           System.out.println(temp);
           temp = "";
       }
       return temp;
    }



}

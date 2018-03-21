package com.teamb.model;

public class Shift {

    public String shift;
    //public int shift; //1-morning 2-afternoon 3-evening
    public boolean mon, tue, wed, thur, fri, sat, sun;

    public Shift(String s, boolean m, boolean tu, boolean w, boolean th, boolean f, boolean sa, boolean su) {
        shift = s;
        mon = m;
        tue = tu;
        wed = w;
        thur = th;
        fri = f;
        sat = sa;
        sun = su;
    }

    public boolean getWeekdayAvailability(int day){
        if(day == 0 ){
            return isMonAvailable();
        }else if(day == 1){
            return isTueAvailable();
        }else if(day == 2){
            return isWedAvailable();
        }else if(day == 3){
            return isThurAvailable();
        }else if(day == 4){
            return isFriAvailable();
        }else if(day == 5){
            return isSatAvailable();
        }else if(day == 6){
            return isSunAvailable();
        }else{
            return false;
        }
    }

    public boolean isMonAvailable() {
        return mon;
    }

    public boolean isTueAvailable() {
        return tue;
    }

    public boolean isWedAvailable() {
        return wed;
    }

    public boolean isThurAvailable() {
        return thur;
    }

    public boolean isFriAvailable() {
        return fri;
    }

    public boolean isSatAvailable() {
        return sat;
    }

    public boolean isSunAvailable() {
        return sun;
    }

    public String getShift() {
        return shift;
    }

    public void reset(){
        mon = false;
        tue = false;
        wed = false;
        thur = false;
        fri = false;
        sat = false;
        sun = false;
    }


}

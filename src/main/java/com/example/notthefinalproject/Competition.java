package com.example.notthefinalproject;

import java.util.ArrayList;

public class Competition {


    ArrayList<Partecipant> partecipants = new ArrayList<>();
    String compName;
    String compUrl;
    String compDate;
    boolean single;

    Competition(String compName, String compDate, String compUrl, boolean single){
        this.compDate = compDate;
        this.compName = compName;
        this.compUrl = compUrl;
        this.single = single;
    }

    public  void addPartecipant(Partecipant p){
        this.partecipants.add(p);
    }

    @Override
    public String toString() {
        return compName ;
    }
}

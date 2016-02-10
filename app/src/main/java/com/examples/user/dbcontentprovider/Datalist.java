package com.examples.user.dbcontentprovider;

/**
 * Created by User on 2/8/2016.
 */
public class Datalist {
    String title;
    String date;
    int id;
    //int image;

    public Datalist(String title){
        this.title=title;
        this.date=date;
        //  this.image=image;
    }
    public String getTitle(){
        return title;
    }
    public String getDate(){
        return date;
    }

}



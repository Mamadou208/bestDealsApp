package com.example.tb_laota.volleydemo;

import java.util.ArrayList;

/**
 * Created by Mamadou on 9/12/2015.
 */
public class Item {
    public String title;
    public String image;
    public String description;



    public String createdAt;
    public int year;
    public double rate;
    //private ArrayList<String> genre;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {return description;}

    public void setImage(String image) {
        this.image = image;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /*public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }*/

   // public ArrayList<String> getGenre() {
       // return genre;
    //}

    public void setDescription(String description) {
        this.description = description;
    }
    public Item(){}

}

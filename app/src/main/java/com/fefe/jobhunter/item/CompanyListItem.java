package com.fefe.jobhunter.item;

/**
 * Created by USER on 2015/05/13.
 */
public class CompanyListItem {
    private String name;
    private String position;
    private String color;
    private String date;
    public CompanyListItem(){

    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPosition(){
        return this.position;
    }

    public void setPosition(String position){
        this.position = position;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}

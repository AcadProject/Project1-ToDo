package com.acadgild.jayadev.todoapp;

import android.graphics.drawable.Drawable;
import android.util.Log;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by jayad on 01-06-2016.
 */
public class ToDo implements Comparable<ToDo>{
    private int id;
    private String title;
    private String description;
    private String date;
    private int status;



    public ToDo(){
        super();
    }
    public ToDo(String title, String description, String date, int status){
        super();
        this.title=title;
        this.description=description;
        this.date=date;
        this.status=status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int compareTo(ToDo another) {
        Date lhs = null, rhs = null;
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/mm/yyyy");
        try {
            lhs=simpleDateFormat.parse(another.date);
            rhs=simpleDateFormat.parse(this.date);

            Log.i("lhs", lhs.toString());
            Log.i("rhs", rhs.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return lhs.compareTo(rhs);
    }
}

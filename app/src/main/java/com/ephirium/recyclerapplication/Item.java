package com.ephirium.recyclerapplication;

public class Item {

    public static int last_id = 0;

    private String text;

    private final int id = last_id++;

    public Item(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = (this.text == null) ? text : this.text;
    }

    public int getId(){
        return id;
    }
}

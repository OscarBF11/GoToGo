package com.example.proyecto.ui.categories.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class ObjectCategory extends ArrayList<ObjectCategory> implements Serializable {
    private ArrayList<ObjectItem> data;
    private String name;
    private int id;

    public ObjectCategory() {

    }

    public ObjectCategory(ArrayList<ObjectItem> data, String name, int id) {
        this.data = data;
        this.name = name;
        this.id = id;
    }


    public ArrayList<ObjectItem> getData() {
        return data;
    }

    public void setData(ArrayList<ObjectItem> datoes) {
        this.data = datoes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

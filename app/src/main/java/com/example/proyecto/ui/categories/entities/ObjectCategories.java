package com.example.proyecto.ui.categories.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class ObjectCategories implements Serializable {
    private ArrayList<ObjectCategory> category;

    public ObjectCategories() {

    }

    public ObjectCategories(ArrayList<ObjectCategory> category) {
        this.category = category;
    }

    public ArrayList<ObjectCategory> getCategory() {
        return category;
    }

    public void setCategory(ArrayList<ObjectCategory> category) {
        this.category = category;
    }
}

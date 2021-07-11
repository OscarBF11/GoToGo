package com.example.proyecto.ui.categories.entities;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Stream;

public class ObjectItem extends ArrayList<ObjectCategory> implements Serializable {
    private String name;
    private int rating;
    private int image;
    private double coordinateX;
    private double coordinateY;
    private double distance;
    private String category;

    public ObjectItem(String name, int rating, int image, double coordinateX, double coordinateY, double distance) {
        this.name = name;
        this.rating = rating;
        this.image = image;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.distance = distance;
    }

    public ObjectItem(String name, int rating, int image, double coordinateX, double coordinateY) {
        this.name = name;
        this.rating = rating;
        this.image = image;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public ObjectItem() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public double getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(double coordinateX) {
        this.coordinateX = coordinateX;
    }

    public double getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(double coordinateY) {
        this.coordinateY = coordinateY;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @NonNull
    @Override
    public Stream<ObjectCategory> stream() {
        return null;
    }

    @NonNull
    @Override
    public Stream<ObjectCategory> parallelStream() {
        return null;
    }
}

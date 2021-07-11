package com.example.proyecto.ui.home.entities;

public class ObjectDangerousArea {
    private double latitude;
    private double logitude;
    private String name;

    public ObjectDangerousArea() {
    }

    public ObjectDangerousArea(double latitude, double logitude, String name) {
        this.latitude = latitude;
        this.logitude = logitude;
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLogitude() {
        return logitude;
    }

    public void setLogitude(double logitude) {
        this.logitude = logitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

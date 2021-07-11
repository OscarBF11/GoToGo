package com.example.proyecto.ui.home.data;

import com.example.proyecto.ui.home.entities.ObjectDangerousArea;

import java.util.ArrayList;

public class DataHome {

    private static ArrayList<ObjectDangerousArea> objectDangerousArea = new ArrayList<>();
    public static  ArrayList<ObjectDangerousArea> generateArrayDangerousAreas(){
        //Generate a List with the dangerous areas
        objectDangerousArea.add(new ObjectDangerousArea(41.380208, 2.167623,"Raval"));
        objectDangerousArea.add(new ObjectDangerousArea(41.412205, 2.219980,"Mina"));
        objectDangerousArea.add(new ObjectDangerousArea(41.381251, 2.191036,"Barceloneta"));
        objectDangerousArea.add(new ObjectDangerousArea(41.369555, 2.134389,"LaBordeta"));
        objectDangerousArea.add(new ObjectDangerousArea(41.442165, 2.180765,"Prosperitat"));
        return objectDangerousArea;
    }

}

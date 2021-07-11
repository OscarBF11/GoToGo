package com.example.proyecto.ui.categories.logica;

import com.example.proyecto.ui.categories.entities.ObjectCategory;
import com.example.proyecto.ui.categories.entities.ObjectCategories;
import com.example.proyecto.ui.categories.entities.ObjectItem;


import java.util.ArrayList;

import static com.example.proyecto.ui.categories.logica.datosCategorias.*;

public class LogicCategory {

    //Temporary arrayList with categories
    private static String[][][] tempArray = {dataFilm, dataMusic, dataArchitecture, dataGastronomy, dataMonuments, dataPainting, dataUrbanArt, dataSports, dataLeisure};
    //Temporary arrayList with category names
    private static String[] tempCategoriesArray = {"Film Culture", "Music", "Architecture", "Gastronomy", "Monuments", "Painting", "Urban Art", "Sport", "Leisure", "Poser"};
    //falta where be poser

    public static ObjectCategories objectCategories = new ObjectCategories();

    /*------------------------------------------------------
    -----------------------LOGIC---------------------------
    --------------------------------------------------------*/

    public static ArrayList<ObjectItem> generateSelectList(boolean FILM, boolean MUSIC, boolean ARCHITECTURE, boolean GASTRONOMY, boolean MONUMENT, boolean PAINTING, boolean URBANART, boolean SPORTS, boolean LEISURE) {
        boolean[] categoriesBoolList = {FILM, MUSIC, ARCHITECTURE, GASTRONOMY, MONUMENT, PAINTING, URBANART, SPORTS, LEISURE};
        ArrayList<ObjectItem> objectItems = new ArrayList<>();

        //assign selected categories
        for (int i = 0; i < categoriesBoolList.length; i++) { //Browse categories to know selected
            if (categoriesBoolList[i]) { //If the category is selected, create it
                for (int j = 0; j < objectCategories.getCategory().get(i).getData().size(); j++) {//loop items within Data
                    ObjectItem objectItem = new ObjectItem();
                    objectItem.setName(objectCategories.getCategory().get(i).getData().get(j).getName());
                    objectItem.setRating(objectCategories.getCategory().get(i).getData().get(j).getRating());
                    objectItem.setImage(objectCategories.getCategory().get(i).getData().get(j).getImage());
                    objectItem.setCoordinateX(objectCategories.getCategory().get(i).getData().get(j).getCoordinateX());
                    objectItem.setCoordinateY(objectCategories.getCategory().get(i).getData().get(j).getCoordinateY());
                    objectItems.add(objectItem);
                }
            }
        }
        return objectItems;
    }

    public static void chargeAnyObject() {
        objectCategories = generateObjects();
    }

    public static ArrayList<ObjectItem> chargeObjectItem() {
        return generateItem();
    }

    private static ObjectCategories generateObjects() {

        ObjectCategories objectCategories = new ObjectCategories();
        ArrayList<ObjectCategory> categoriesArrayList = new ArrayList<>();
        for (int i = 0; tempArray.length > i; i++) {
            ObjectCategory objectCategory = new ObjectCategory();
            objectCategory.setId(i);
            ArrayList<ObjectItem> categoryArrayList = new ArrayList<>();
            for (int j = 0; tempArray[i].length > j; j++) {
                ObjectItem objectItem = new ObjectItem();
                objectItem.setName(tempArray[i][j][0]);
                objectItem.setRating(Integer.parseInt(tempArray[i][j][1]));
                objectItem.setImage(Integer.parseInt(tempArray[i][j][2]));
                objectItem.setCoordinateX(Double.parseDouble(tempArray[i][j][3]));
                objectItem.setCoordinateY(Double.parseDouble(tempArray[i][j][4]));
                categoryArrayList.add(objectItem);

            }
            objectCategory.setData(categoryArrayList);
            categoriesArrayList.add(objectCategory);

        }
        objectCategories.setCategory(categoriesArrayList);
        return objectCategories;
    }

    private static ArrayList<ObjectItem> generateItem() {
        ArrayList<ObjectItem> objectItemList = new ArrayList<>();

        for (int i = 0; i < tempArray.length; i++) {
            for (int j = 0; tempArray[i].length > j; j++) {
                ObjectItem objectItem = new ObjectItem();
                objectItem.setName(tempArray[i][j][0]);
                //item.setValoracion(Integer.parseInt(tempArray[i][j][1]));
                objectItem.setImage(Integer.parseInt(tempArray[i][j][2]));
                objectItem.setCoordinateX(Double.parseDouble(tempArray[i][j][3]));
                objectItem.setCoordinateY(Double.parseDouble(tempArray[i][j][4]));
                objectItem.setCategory(tempCategoriesArray[i]);
                objectItemList.add(objectItem);
            }

        }
        return objectItemList;

    }
}

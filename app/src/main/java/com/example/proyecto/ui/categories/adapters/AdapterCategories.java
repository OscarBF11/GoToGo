package com.example.proyecto.ui.categories.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.proyecto.R;
import com.example.proyecto.ui.categories.entities.ObjectCategory;
import com.example.proyecto.ui.categories.entities.ObjectItem;

import java.util.ArrayList;

public class AdapterCategories extends BaseAdapter {

    private static LayoutInflater inflater = null; // through it we can instantiate the xml design file

    private Context context; // Application environment, necessary to generate the adapter
    private ObjectCategory data; // Array that stores the data Architecture of the elements
    private ArrayList<ObjectItem> objectItemArrayList;


    public AdapterCategories(Context context, ObjectCategory data) {
        this.context = context;
        this.data = data;
        this.objectItemArrayList = data.getData();

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); // Create or instantiate the XML
    }


    public View getView(int i, View convertView, ViewGroup parent) {

        //declare direction of views to use findviewbyid
        final View elementView = inflater.inflate(R.layout.item_subcategorias, null);

        //Anchor class variables in XML
        TextView itemName = elementView.findViewById(R.id.textView_itemName);
        RatingBar itemReview = elementView.findViewById(R.id.ratingBar_itemReview);
        ImageView itemImage = elementView.findViewById(R.id.imageView_itemImage);

        //populate internal Architecture data using the String array declared in the fragment and copied here


        itemName.setText(objectItemArrayList.get(i).getName());
        itemReview.setProgress(objectItemArrayList.get(i).getRating());
        itemImage.setImageResource(objectItemArrayList.get(i).getImage());


        return elementView;
    }

    @Override
    public int getCount() {
        return objectItemArrayList.size();
    }

    @Override
    public Object getItem(int i) {

        return objectItemArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

}






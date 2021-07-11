package com.example.proyecto.ui.categories.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto.R;
import com.example.proyecto.ui.categories.adapters.AdapterCategories;
import com.example.proyecto.ui.categories.CategoriesFragment;
import com.example.proyecto.ui.categories.entities.ObjectCategory;
import com.example.proyecto.ui.categories.logica.LogicCategory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ActivityLeisure extends AppCompatActivity {
    private ListView list; //fragment_subcategories list
    private FloatingActionButton selectCategory;
    private ObjectCategory objectCategory = LogicCategory.objectCategories.getCategory().get(8);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Instantiate  the view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategories);

        //Declare the list where will appear the items
        list = findViewById(R.id.list);

        //Set directions and listener to the button
        selectCategory = findViewById(R.id.floatingActionButton_selectCategory);
        selectCategory.setOnClickListener(onClickChangeCategory);

        //Generate new adapter
        list.setAdapter(new AdapterCategories(this, objectCategory));

        if (CategoriesFragment.boolLeisure){
            selectCategory.setImageResource(R.drawable.delete);
        }else {
            selectCategory.setImageResource(R.drawable.add);
        }
    }

    //LISTENERS
    private View.OnClickListener onClickChangeCategory = new View.OnClickListener() { //Button add delete category
        public void onClick(View root) {
            CategoriesFragment.boolLeisure = !CategoriesFragment.boolLeisure; //Change the value of the boolean
            finish();
        }
    };


}

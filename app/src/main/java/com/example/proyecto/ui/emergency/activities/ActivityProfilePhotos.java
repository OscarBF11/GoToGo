package com.example.proyecto.ui.emergency.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto.R;
import com.example.proyecto.ui.emergency.adapters.AdapterProfilePhotos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.proyecto.ui.emergency.activities.ActivityAddContact.setProfileImage;

public class ActivityProfilePhotos extends AppCompatActivity {

    RecyclerView recyclerView;
    private List<Integer> imageList = new ArrayList<>();

    List<HashMap<String, Object>> furnitureList = new ArrayList<>();
    AdapterProfilePhotos adapterProfilePhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        imageList = setImageList(); //fill data

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilephotos);

        initialize();

    }

    private void initialize() {
        recyclerView = findViewById(R.id.recyclerView_profileImages);

        //image grid configuration parameters
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);


        //loop array and prepare map to pass to adapter
        for (int i = 0; i < imageList.size(); i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("Image", imageList.get(i));
            furnitureList.add(map);
        }

        //pass information to adapter
        adapterProfilePhotos = new AdapterProfilePhotos(furnitureList);

        adapterProfilePhotos.setOnClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int id = recyclerView.getChildAdapterPosition(v);//get item clicked
                int image = imageList.get(id);
                setProfileImage(image); //pass address (int) image, would be the same as R.drawable.image
                finish();
                return false;
            }
        });

        recyclerView.setAdapter(adapterProfilePhotos);

    }

    private static List<Integer> setImageList() {
        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.avatar01);
        imageList.add(R.drawable.avatar02);
        imageList.add(R.drawable.avatar03);
        imageList.add(R.drawable.avatar04);
        imageList.add(R.drawable.avatar05);
        imageList.add(R.drawable.avatar06);
        imageList.add(R.drawable.avatar07);
        imageList.add(R.drawable.avatar08);
        imageList.add(R.drawable.avatar09);
        imageList.add(R.drawable.avatar10);
        imageList.add(R.drawable.avatar11);
        imageList.add(R.drawable.avatar12);
        imageList.add(R.drawable.avatar13);
        imageList.add(R.drawable.avatar14);
        imageList.add(R.drawable.avatar15);
        imageList.add(R.drawable.avatar16);
        imageList.add(R.drawable.avatar17);
        imageList.add(R.drawable.avatar18);
        imageList.add(R.drawable.avatar19);
        imageList.add(R.drawable.avatar21);
        imageList.add(R.drawable.avatar22);
        imageList.add(R.drawable.avatar23);
        imageList.add(R.drawable.avatar24);
        imageList.add(R.drawable.avatar25);
        imageList.add(R.drawable.avatar26);
        imageList.add(R.drawable.avatar27);
        imageList.add(R.drawable.avatar28);
        imageList.add(R.drawable.avatar29);
        imageList.add(R.drawable.avatar30);
        imageList.add(R.drawable.avatar31);
        imageList.add(R.drawable.avatar32);
        imageList.add(R.drawable.avatar33);
        imageList.add(R.drawable.avatar34);
        imageList.add(R.drawable.avatar35);
        imageList.add(R.drawable.avatar36);
        imageList.add(R.drawable.avatar37);
        imageList.add(R.drawable.avatar38);
        imageList.add(R.drawable.avatar39);
        imageList.add(R.drawable.avatar40);
        imageList.add(R.drawable.avatar41);
        imageList.add(R.drawable.avatar42);
        imageList.add(R.drawable.avatar43);
        imageList.add(R.drawable.avatar44);
        imageList.add(R.drawable.avatar45);
        imageList.add(R.drawable.avatar46);
        imageList.add(R.drawable.avatar47);
        imageList.add(R.drawable.avatar48);
        imageList.add(R.drawable.avatar49);
        imageList.add(R.drawable.avatar50);
        imageList.add(R.drawable.avatar51);
        imageList.add(R.drawable.avatar52);
        imageList.add(R.drawable.avatar53);
        imageList.add(R.drawable.avatar54);
        imageList.add(R.drawable.avatar55);
        imageList.add(R.drawable.avatar56);
        imageList.add(R.drawable.avatar57);
        imageList.add(R.drawable.avatar59);
        imageList.add(R.drawable.avatar60);
        imageList.add(R.drawable.avatar61);
        imageList.add(R.drawable.avatar62);
        imageList.add(R.drawable.avatar63);
        imageList.add(R.drawable.avatar64);
        imageList.add(R.drawable.avatar65);
        imageList.add(R.drawable.avatar66);
        imageList.add(R.drawable.avatar67);
        imageList.add(R.drawable.avatar68);


        return imageList;
    }

}

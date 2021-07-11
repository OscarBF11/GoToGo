package com.example.proyecto.ui.categories;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.proyecto.R;
import com.example.proyecto.ui.categories.activities.ActivityArchitecture;
import com.example.proyecto.ui.categories.activities.ActivityFilmCulture;
import com.example.proyecto.ui.categories.activities.ActivityGastronomy;
import com.example.proyecto.ui.categories.activities.ActivityHistoricalMonuments;
import com.example.proyecto.ui.categories.activities.ActivityLeisure;
import com.example.proyecto.ui.categories.activities.ActivityMusic;
import com.example.proyecto.ui.categories.activities.ActivityPainting;
import com.example.proyecto.ui.categories.activities.ActivityPoser;
import com.example.proyecto.ui.categories.activities.ActivitySports;
import com.example.proyecto.ui.categories.activities.ActivityUrbanArt;
import com.example.proyecto.ui.categories.logica.LogicCategory;
import com.example.proyecto.ui.home.FragmentHome;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import static android.view.View.*;
import static com.example.proyecto.ui.categories.logica.LogicCategory.chargeAnyObject;

public class CategoriesFragment extends Fragment {

    public static boolean boolArchitecture;
    public static boolean boolFilmCulture;
    public static boolean boolSports;
    public static boolean boolHistoricalMonuments;
    public static boolean boolMusic;
    public static boolean boolLeisure;
    public static boolean boolUrbanArt;
    public static boolean boolGastronomy;
    public static boolean boolPainting;
    public static boolean boolPoser;

    private CardView cardViewArchitecture;
    private CardView cardViewFilmculture;
    private CardView cardViewSports;
    private CardView cardViewHistoricalmonuments;
    private CardView cardViewMusic;
    private CardView cardViewLeisure;
    private CardView cardViewUrbanArt;
    private CardView cardViewGastronomy;
    private CardView cardViewPainting;
    private CardView cardViewPoser;

    private ImageView imageViewArchitecture;
    private ImageView imageViewFilmCulture;
    private ImageView imageViewSports;
    private ImageView imageViewHistoricalMonuments;
    private ImageView imageViewMusic;
    private ImageView imageViewLeisure;
    private ImageView imageViewUrbanArt;
    private ImageView imageViewGastronomy;
    private ImageView imageViewPainting;
    private ImageView imageViewPoser;

    private TextView textViewArchitecture;
    private TextView textViewFilmCulture;
    private TextView textViewSports;
    private TextView textViewHistoricalMonuments;
    private TextView textViewMusic;
    private TextView textViewLeisure;
    private TextView textViewUrbanArt;
    private TextView textViewGastronomy;
    private TextView textViewPainting;
    private TextView textViewPoser;

    private ImageView imageViewArchitectureCheck;
    private ImageView imageViewFilmCultureCheck;
    private ImageView imageViewSportsCheck;
    private ImageView imageViewHistoricalMonumentsCheck;
    private ImageView imageViewMusicCheck;
    private ImageView imageViewLeisureCheck;
    private ImageView imageViewUrbanArtCheck;
    private ImageView imageViewGastronomyCheck;
    private ImageView imageViewPaintingCheck;
    private ImageView imageViewPoserCheck;

    private FloatingActionButton btnGenerarRuta;
    private ImageView imageViewBackground;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_new_categories, container, false);

        //Background
        imageViewBackground = root.findViewById(R.id.imageView_categoriesBackground);
        imageViewBackground.setImageResource(R.drawable.barcelonablur);


        cardViewArchitecture = root.findViewById(R.id.cardView_architecture);
        cardViewArchitecture.setOnClickListener(onClickArchitecture);
        cardViewArchitecture.setOnLongClickListener(onLongClickArchitecture);

        cardViewFilmculture = root.findViewById(R.id.cardView_filmCulture);
        cardViewFilmculture.setOnClickListener(onClickFilmCulture);
        cardViewFilmculture.setOnLongClickListener(onLongClickFilmCulture);

        cardViewSports = root.findViewById(R.id.cardView_sports);
        cardViewSports.setOnClickListener(onClickSports);
        cardViewSports.setOnLongClickListener(onLongClickSports);

        cardViewHistoricalmonuments = root.findViewById(R.id.cardView_historicalMonuments);
        cardViewHistoricalmonuments.setOnClickListener(onClickHistoricalMonuments);
        cardViewHistoricalmonuments.setOnLongClickListener(onLongClickHistoricalMonuments);

        cardViewMusic = root.findViewById(R.id.cardView_music);
        cardViewMusic.setOnClickListener(onClickMusic);
        cardViewMusic.setOnLongClickListener(onLongClickMusic);

        cardViewLeisure = root.findViewById(R.id.cardView_leisure);
        cardViewLeisure.setOnClickListener(onClickLeisure);
        cardViewLeisure.setOnLongClickListener(onLongClickLeisure);

        cardViewUrbanArt = root.findViewById(R.id.cardView_urbanArt);
        cardViewUrbanArt.setOnClickListener(onClickUrbanArt);
        cardViewUrbanArt.setOnLongClickListener(onLongClickUrbanArt);

        cardViewGastronomy = root.findViewById(R.id.cardView_gastronomy);
        cardViewGastronomy.setOnClickListener(onClickGastronomy);
        cardViewGastronomy.setOnLongClickListener(onLongClickGastronomy);

        cardViewPainting = root.findViewById(R.id.cardView_painting);
        cardViewPainting.setOnClickListener(onClickPainting);
        cardViewPainting.setOnLongClickListener(onLongClickPainting);

        cardViewPoser = root.findViewById(R.id.cardView_poser);
        cardViewPoser.setOnClickListener(onClickPoser);
        cardViewPoser.setOnLongClickListener(onLongClickPoser);

        btnGenerarRuta = root.findViewById(R.id.floatingActionButton_GenerarRuta);
        btnGenerarRuta.setOnClickListener(generarRutaListener);

        /*
        ---------------------------------------Assign image addresses------------------------------------------------
        */
        imageViewArchitecture = root.findViewById(R.id.architectureImage);
        imageViewFilmCulture = root.findViewById(R.id.filmCultureImage);
        imageViewSports = root.findViewById(R.id.sportsImage);
        imageViewHistoricalMonuments = root.findViewById(R.id.historicalMonumentsImage);
        imageViewMusic = root.findViewById(R.id.musicImage);
        imageViewLeisure = root.findViewById(R.id.leisureImage);
        imageViewUrbanArt = root.findViewById(R.id.urbanArtImage);
        imageViewGastronomy = root.findViewById(R.id.gastronomyImage);
        imageViewPainting = root.findViewById(R.id.paintingImage);
        imageViewPoser = root.findViewById(R.id.poserImage);

        /*
        -----------------------------------------Assign texts addresses------------------------------------------------
        */
        textViewArchitecture = root.findViewById(R.id.architectureText);
        textViewFilmCulture = root.findViewById(R.id.filmCultureText);
        textViewSports = root.findViewById(R.id.sportsText);
        textViewHistoricalMonuments = root.findViewById(R.id.historicalMonumentsText);
        textViewMusic = root.findViewById(R.id.musicText);
        textViewLeisure = root.findViewById(R.id.leisureText);
        textViewUrbanArt = root.findViewById(R.id.urbanArtText);
        textViewGastronomy = root.findViewById(R.id.gastronomyText);
        textViewPainting = root.findViewById(R.id.paintingText);
        textViewPoser = root.findViewById(R.id.poserText);

        /*
        ---------------------------------------Assign check addresses------------------------------------------------
        */
        imageViewArchitectureCheck = root.findViewById(R.id.architectureSelect);
        imageViewFilmCultureCheck = root.findViewById(R.id.filmCultureSelect);
        imageViewSportsCheck = root.findViewById(R.id.sportsSelect);
        imageViewHistoricalMonumentsCheck = root.findViewById(R.id.historicalMonumentsSelect);
        imageViewMusicCheck = root.findViewById(R.id.musicSelect);
        imageViewLeisureCheck = root.findViewById(R.id.leisureSelect);
        imageViewUrbanArtCheck = root.findViewById(R.id.urbanArtSelect);
        imageViewGastronomyCheck = root.findViewById(R.id.gastronomySelect);
        imageViewPaintingCheck = root.findViewById(R.id.paintingSelect);
        imageViewPoserCheck = root.findViewById(R.id.poserSelect);

        SelectedItems();
        chargeAnyObject();
        return root;
    }

    public void onResume() {
        super.onResume();
        SelectedItems();
    }



    /*-------------------------------------------------------LISTENERS----------------------------------------------------------------*/


    //BUTTON ARCHITECTURE
    private OnClickListener onClickArchitecture = new OnClickListener() {
        public void onClick(View root) {
            //Fragment to Activity
            Intent activity = new Intent(CategoriesFragment.this.getContext(), ActivityArchitecture.class);
            startActivity(activity);

            //alternativa con animación difuminado
            //startActivity(activity,ActivityOptions.makeSceneTransitionAnimation(CategoriesFragment.this.getActivity()).toBundle());

        }
    };

    private OnLongClickListener onLongClickArchitecture = new OnLongClickListener() {
        public boolean onLongClick(View root) {
            boolArchitecture = !boolArchitecture;
            SelectedItems();
            return true;
        }
    };


    //BUTTON FILM CULTURE
    private OnClickListener onClickFilmCulture = new OnClickListener() {
        public void onClick(View root) {
            //Fragment to Activity
            Intent activity = new Intent(CategoriesFragment.this.getContext(), ActivityFilmCulture.class);
            startActivity(activity);
        }
    };

    private OnLongClickListener onLongClickFilmCulture = new OnLongClickListener() {
        public boolean onLongClick(View root) {
            boolFilmCulture = !boolFilmCulture;
            SelectedItems();
            return true;
        }
    };

    //BUTTON SPORTS
    private OnClickListener onClickSports = new OnClickListener() {
        public void onClick(View root) {
            //Fragment to Activity
            Intent activity = new Intent(CategoriesFragment.this.getContext(), ActivitySports.class);
            startActivity(activity);
        }
    };

    private OnLongClickListener onLongClickSports = new OnLongClickListener() {
        public boolean onLongClick(View root) {
            boolSports = !boolSports;
            SelectedItems();
            return true;
        }
    };


    //BUTTON HISTORICAL MONUMENTS
    private OnClickListener onClickHistoricalMonuments = new OnClickListener() {
        public void onClick(View root) {
            //Fragment to Activity
            Intent activity = new Intent(CategoriesFragment.this.getContext(), ActivityHistoricalMonuments.class);
            startActivity(activity);
        }
    };

    private OnLongClickListener onLongClickHistoricalMonuments = new OnLongClickListener() {
        public boolean onLongClick(View root) {
            boolHistoricalMonuments = !boolHistoricalMonuments;
            SelectedItems();
            return true;
        }
    };

    //BOTON MUSIC
    private OnClickListener onClickMusic = new OnClickListener() {
        public void onClick(View root) {
            //Fragment to Activity
            Intent activity = new Intent(CategoriesFragment.this.getContext(), ActivityMusic.class);
            startActivity(activity);
        }
    };

    private OnLongClickListener onLongClickMusic = new OnLongClickListener() {
        public boolean onLongClick(View root) {
            boolMusic = !boolMusic;
            SelectedItems();
            return true;
        }
    };

    //BUTTON LEISURE
    private OnClickListener onClickLeisure = new OnClickListener() {
        public void onClick(View root) {
            //Fragment to Activity
            Intent activity = new Intent(CategoriesFragment.this.getContext(), ActivityLeisure.class);
            startActivity(activity);
        }
    };

    private OnLongClickListener onLongClickLeisure = new OnLongClickListener() {
        public boolean onLongClick(View root) {
            boolLeisure = !boolLeisure;
            SelectedItems();
            return true;
        }
    };

    //BUTTON URBAN ART
    private OnClickListener onClickUrbanArt = new OnClickListener() {
        public void onClick(View root) {
            //Fragment to Activity
            Intent activity = new Intent(CategoriesFragment.this.getContext(), ActivityUrbanArt.class);
            startActivity(activity);
        }
    };

    private OnLongClickListener onLongClickUrbanArt = new OnLongClickListener() {
        public boolean onLongClick(View root) {
            boolUrbanArt = !boolUrbanArt;
            SelectedItems();
            return true;
        }
    };

    //BUTTON GASTRONOMY
    private OnClickListener onClickGastronomy = new OnClickListener() {
        public void onClick(View root) {
            //Fragment to Activity
            Intent activity = new Intent(CategoriesFragment.this.getContext(), ActivityGastronomy.class);
            startActivity(activity);
        }
    };

    private OnLongClickListener onLongClickGastronomy = new OnLongClickListener() {
        public boolean onLongClick(View root) {
            boolGastronomy = !boolGastronomy;
            SelectedItems();
            return true;
        }
    };

    //BUTTON PAINTING
    private OnClickListener onClickPainting = new OnClickListener() {
        public void onClick(View root) {
            //Fragment to Activity
            Intent activity = new Intent(CategoriesFragment.this.getContext(), ActivityPainting.class);
            startActivity(activity);
        }
    };

    private OnLongClickListener onLongClickPainting = new OnLongClickListener() {
        public boolean onLongClick(View root) {
            boolPainting = !boolPainting;
            SelectedItems();
            return true;
        }
    };

    //BUTTON WHERE BE POSER
    private OnClickListener onClickPoser = new OnClickListener() {
        public void onClick(View root) {
            //Fragment to Activity
            Intent activity = new Intent(CategoriesFragment.this.getContext(), ActivityPoser.class);
            startActivity(activity);
        }
    };

    private OnLongClickListener onLongClickPoser = new OnLongClickListener() {
        public boolean onLongClick(View root) {
            boolPoser = !boolPoser;
            SelectedItems();
            return true;
        }
    };

    //BUTTON GENERATE SELECTED ROUTE
    private OnClickListener generarRutaListener = new OnClickListener() {
        public void onClick(View root) {
            if (boolFilmCulture || boolMusic || boolArchitecture || boolGastronomy || boolHistoricalMonuments || boolPainting || boolUrbanArt || boolSports || boolLeisure) {
                //mostrar mensaje de acceso a subcategoria
                Toast.makeText(getContext(), "Charging route...", Toast.LENGTH_SHORT).show();

                //Fragment to Activity
                // Create Fragment
                Fragment fragment = new FragmentHome();
                FragmentHome.fillSelectedItems(LogicCategory.generateSelectList(boolFilmCulture, boolMusic, boolArchitecture, boolGastronomy, boolHistoricalMonuments, boolPainting, boolUrbanArt, boolSports, boolLeisure));
                // Obtain fragment admin in activity
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                // Define transaction
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                // Replace content with fragment
                fragmentTransaction.replace(R.id.new_categorias_layout, fragment);
                fragmentTransaction.addToBackStack(null);
                // Change to
                fragmentTransaction.commit();

            } else {
                //mostrar mensaje categorias null
                Toast.makeText(getContext(), "SELECT 1 OR MORE ITEMS", Toast.LENGTH_SHORT).show();
            }
        }
    };


    /*
        ---------------------------------------SELECCION DE CATEGORIAS------------------------------------------------
        */
    private void SelectedItems() {

        //FILM CULTURE
        imageViewFilmCulture.setImageResource(R.drawable.icofilmcolor1);
        textViewFilmCulture.setText(R.string.filmCulture);
        if (boolFilmCulture) {//=true set selected
            imageViewFilmCultureCheck.setImageResource(R.drawable.checkgreen);

        } else {//false set unselected
            imageViewFilmCultureCheck.setImageResource(R.drawable.check0);
        }

        //MUSIC
        imageViewMusic.setImageResource(R.drawable.icomusiccolor1);
        textViewMusic.setText(R.string.music);
        if (boolMusic) {//=true set selected
            imageViewMusicCheck.setImageResource(R.drawable.checkgreen);
        } else {//false set unselected
            imageViewMusicCheck.setImageResource(R.drawable.check0);
        }

        //ARCHITECTURE
        imageViewArchitecture.setImageResource(R.drawable.icoarchitecturecolor1);
        textViewArchitecture.setText(R.string.architecture);
        if (boolArchitecture) {//=true set selected
            imageViewArchitectureCheck.setImageResource(R.drawable.checkgreen);
        } else {//false set unselected
            imageViewArchitectureCheck.setImageResource(R.drawable.check0);
        }

        //GASTRONOMY
        imageViewGastronomy.setImageResource(R.drawable.icogastronomycolor1);
        textViewGastronomy.setText(R.string.gastronomy);
        if (boolGastronomy) {//=true set selected
            imageViewGastronomyCheck.setImageResource(R.drawable.checkgreen);
        } else {//false set unselected
            imageViewGastronomyCheck.setImageResource(R.drawable.check0);
        }

        //HISTORICAL MONUMENTS
        imageViewHistoricalMonuments.setImageResource(R.drawable.icomonumentscolor1);
        textViewHistoricalMonuments.setText(R.string.monuments);
        if (boolHistoricalMonuments) {//=true set selected
            imageViewHistoricalMonumentsCheck.setImageResource(R.drawable.checkgreen);
        } else {//false set unselected
            imageViewHistoricalMonumentsCheck.setImageResource(R.drawable.check0);
        }

        //PAINTING
        imageViewPainting.setImageResource(R.drawable.icopaintingcolor1);
        textViewPainting.setText(R.string.painting);
        if (boolPainting) {//=true set selected
            imageViewPaintingCheck.setImageResource(R.drawable.checkgreen);
        } else {//false set unselected
            imageViewPaintingCheck.setImageResource(R.drawable.check0);
        }

        //URBAN ART
        imageViewUrbanArt.setImageResource(R.drawable.icourbanartcolor1);
        textViewUrbanArt.setText(R.string.urbanArt);
        if (boolUrbanArt) {//=true set selected
            imageViewUrbanArtCheck.setImageResource(R.drawable.checkgreen);
        } else {//false set unselected
            imageViewUrbanArtCheck.setImageResource(R.drawable.check0);
        }

        //SPORTS
        imageViewSports.setImageResource(R.drawable.icosportscolor1);
        textViewSports.setText(R.string.sports);
        if (boolSports) {//=true set selected
            imageViewSportsCheck.setImageResource(R.drawable.checkgreen);
        } else {//false set unselected
            imageViewSportsCheck.setImageResource(R.drawable.check0);
        }

        //LEISURE
        imageViewLeisure.setImageResource(R.drawable.icoleisurecolor1);
        textViewLeisure.setText(R.string.leisure);
        if (boolLeisure) {//=true set selected
            imageViewLeisureCheck.setImageResource(R.drawable.checkgreen);
        } else {//false set unselected
            imageViewLeisureCheck.setImageResource(R.drawable.check0);
        }

        //WHERE BE POSER
        imageViewPoser.setImageResource(R.drawable.icoposercolor1);
        textViewPoser.setText(R.string.poser);
        if (boolPoser) {//=true set selected
            imageViewPoserCheck.setImageResource(R.drawable.checkgreen);
        } else {//false set unselected
            imageViewPoserCheck.setImageResource(R.drawable.check0);
        }
    }

}
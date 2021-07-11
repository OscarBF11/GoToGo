package com.example.proyecto.ui.emergency.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdapterProfilePhotos extends RecyclerView.Adapter<AdapterProfilePhotos.FurnitureView>
        implements Filterable, View.OnClickListener {


    private List<HashMap<String, Object>> furnitureList;
    private List<HashMap<String, Object>> filteredList;
    private CustomFilter customFilter;
    private View.OnLongClickListener listener;


    public AdapterProfilePhotos(List<HashMap<String, Object>> fList) {
        this.furnitureList = fList;
        filteredList = furnitureList;
        customFilter = new CustomFilter();
    }

    @NonNull
    @Override
    public FurnitureView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_profilephotos, viewGroup, false);

        //set Listener to view
        view.setOnClickListener(this);

        return new FurnitureView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FurnitureView furnitureView, int position) {

        HashMap<String, Object> map = filteredList.get(position);

        try {
            furnitureView.imageFurniture.setImageResource((Integer) map.get("Image")); //Obtain image from map
        } catch (NullPointerException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    @Override
    public Filter getFilter() {
        return customFilter;
    }

    //method in charge of listening and setting variable listener
    public void setOnClickListener(View.OnLongClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onLongClick(v);
        }
    }


    static class FurnitureView extends RecyclerView.ViewHolder {
        ImageView imageFurniture;

        FurnitureView(@NonNull View itemView) {
            super(itemView);

            imageFurniture = itemView.findViewById(R.id.profile_image);


        }
    }

    public class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults filterResults = new FilterResults();

            List<HashMap<String, Object>> newList = furnitureList;
            List<HashMap<String, Object>> resultList = new ArrayList<>();

            String searchValue = constraint.toString().toLowerCase();

            for (int i = 0; i < newList.size(); i++) {

                HashMap<String, Object> map = newList.get(i);
                String title = String.valueOf(map.get("Title"));

                if (title.toLowerCase().contains(searchValue)) {
                    resultList.add(map);
                }

            }


            filterResults.count = resultList.size();
            filterResults.values = resultList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredList = (List<HashMap<String, Object>>) results.values;
            notifyDataSetChanged();

        }
    }

}

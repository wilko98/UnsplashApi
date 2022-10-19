package com.example.unsplashapi.ui.SearchFragment;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.unsplashapi.R;
import com.example.unsplashapi.data.model.Image;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchHolder> {

    @NonNull
    private List<Bitmap> listOfImages;

    public SearchAdapter() {
        listOfImages = new ArrayList<>();
    }

    @NonNull
    @Override
    public SearchHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SearchHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.li_photo,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchHolder searchHolder, int i) {
        searchHolder.bind(listOfImages.get(i));
    }

//    public void add(String url){
//        listOfImages.add(url);
//        notifyDataSetChanged();
//    }

    public void add(Bitmap image){
        listOfImages.add(image);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listOfImages.size();
    }
}

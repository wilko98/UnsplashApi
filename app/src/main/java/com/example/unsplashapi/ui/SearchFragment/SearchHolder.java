package com.example.unsplashapi.ui.SearchFragment;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.unsplashapi.ApplicationSingleton;
import com.example.unsplashapi.R;
import com.example.unsplashapi.data.model.Image;
import com.squareup.picasso.Picasso;

import java.net.URISyntaxException;
import java.net.URL;

public class SearchHolder extends RecyclerView.ViewHolder {
    private ImageView mImage;


    SearchHolder(@NonNull View itemView) {
        super(itemView);
        mImage = itemView.findViewById(R.id.li_image);
    }

    void bind(Bitmap image) {
        mImage.setImageBitmap(image);
    }

}

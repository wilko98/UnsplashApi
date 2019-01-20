package com.example.unsplashapi.UI;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.unsplashapi.R;
import com.example.unsplashapi.common.BasePresenter;
import com.example.unsplashapi.common.PresenterFragment;
import com.example.unsplashapi.utils.ApiUtils;
import com.squareup.picasso.Picasso;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class RandomPhotoFragment extends PresenterFragment {

    ImageView imgageView;
    Button b;

    public static Fragment newInstance() {
        return new RandomPhotoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fr_random_photo,container,false);
        imgageView = v.findViewById(R.id.randomPhoto);
        b = v.findViewById(R.id.randomBtn);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        imgageView.setImageResource(R.mipmap.ic_launcher_round);
        Picasso.with(getActivity()).load("https://images.unsplash.com/photo-1547822050-0fdeeb81c946?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60").into(imgageView);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRandomPhoto();
            }
        });
    }

    private void getRandomPhoto() {
        Toast.makeText(getActivity(), "Finding photo", Toast.LENGTH_SHORT).show();
        ApiUtils.getApiService().getRandomPhoto()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(throwable -> {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                    return null;
                })
                .subscribe(
                        randomPhotoResponse ->{
                            Picasso.with(getActivity()).load(randomPhotoResponse.getStandartImageUrl()).into(imgageView);

                        }
                );
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }
}

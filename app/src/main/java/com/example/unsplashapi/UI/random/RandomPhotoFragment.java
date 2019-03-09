package com.example.unsplashapi.UI.random;

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

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.unsplashapi.R;
import com.example.unsplashapi.common.PresenterFragment;
import com.squareup.picasso.Picasso;

public class RandomPhotoFragment extends PresenterFragment implements RandomPhotoView{

    @InjectPresenter
    RandomPhotoPresenter presenter;

    @ProvidePresenter
    RandomPhotoPresenter providePresenter(){return new RandomPhotoPresenter();}

    protected RandomPhotoPresenter getPresenter() {
        return presenter;
    }


    ImageView imgageView;

    Button mRandomizeBtm;
    Button mDownloadBtn;

    public static Fragment newInstance() {
        return new RandomPhotoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fr_random_photo,container,false);
        imgageView = v.findViewById(R.id.randomPhoto);
        mRandomizeBtm = v.findViewById(R.id.randomBtn);
        mDownloadBtn = v.findViewById(R.id.downloadBtn);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        imgageView.setImageResource(R.mipmap.ic_launcher_round);
        Picasso.with(getActivity()).load("https://images.unsplash.com/photo-1547822050-0fdeeb81c946?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60").into(imgageView);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRandomizeBtm.setOnClickListener(v -> presenter.getRandomPhoto());
        mDownloadBtn.setOnClickListener(v -> {

        });
//        mDownloadBtn.setOnClickListener(v -> presenter.d());
    }


    @Override
    public void showStart(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRandomPhoto(String url) {
        Picasso.with(getActivity()).load(url).into(imgageView);
    }

    @Override
    public void showRefresh() {

    }

    @Override
    public void hideRefresh() {

    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
    }
}

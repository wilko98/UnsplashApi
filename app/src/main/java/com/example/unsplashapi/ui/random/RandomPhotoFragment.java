package com.example.unsplashapi.ui.random;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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
import com.example.unsplashapi.utils.ApiUtils;
import com.squareup.picasso.Picasso;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class RandomPhotoFragment extends Fragment {

    private ImageView imgageView;
    private Button mRandomizeBtm;
    private Button mDownloadBtn;
    private String currentImageURL;
    private DownloadManager downloadManager;
    private long refid;

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
        currentImageURL = "https://images.unsplash.com/photo-1547822050-0fdeeb81c946?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60";
        Picasso.with(getActivity()).load(currentImageURL).into(imgageView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        downloadManager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
        mRandomizeBtm.setOnClickListener(v -> getRandomPhoto());
        mDownloadBtn.setOnClickListener(v -> {
            downloadFile(downloadManager,currentImageURL);


        });
    }


    @SuppressLint("CheckResult")
    public void getRandomPhoto() {
        ApiUtils.getApiService().getRandomPhoto()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(throwable -> {
                    showError();
                    return null;
                })
                .subscribe(
                        randomPhotoResponse ->{
                            showRandomPhoto(randomPhotoResponse.getStandartImageUrl());
                        },
                        throwable -> showError()
                );
    }
    public void downloadFile(DownloadManager downloadManager, String URL){
        Uri Download_Uri = Uri.parse(URL);
        DownloadManager.Request request = new DownloadManager.Request(Download_Uri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setAllowedOverRoaming(false);
        request.setTitle("GadgetSaint Downloading " + "Sample" + ".png");
        request.setDescription("Downloading " + "Sample" + ".png");
        request.setVisibleInDownloadsUi(true);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "/GadgetSaint/"  + "/" + "Sample" + ".png");
        refid = downloadManager.enqueue(request);
    }

    public void showStart(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    public void showRandomPhoto(String url) {
        currentImageURL = url;
        Picasso.with(getActivity()).load(url).into(imgageView);
    }

    public void showError() {

    }


}

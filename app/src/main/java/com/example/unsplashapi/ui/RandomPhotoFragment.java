package com.example.unsplashapi.ui;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.unsplashapi.R;
import com.example.unsplashapi.data.model.UrlsBean;
import com.example.unsplashapi.utils.ApiUtils;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class RandomPhotoFragment extends Fragment {

    private String firstImageUrl;
    private ImageView imgageView;
    private Button mRandomizeBtm;
    private Button mDownloadBtn;
    private UrlsBean currentImageURLS;
    public String mDownloadUrl;
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
        firstImageUrl = "https://images.unsplash.com/photo-1547822050-0fdeeb81c946?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60";
        Picasso.with(getActivity()).load(firstImageUrl).into(imgageView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        downloadManager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
        mRandomizeBtm.setOnClickListener(v -> getRandomPhoto());
        mDownloadBtn.setOnClickListener(v -> {
            downloadFile(downloadManager,currentImageURLS);
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
                            showRandomPhoto(randomPhotoResponse.getUrls());
                        },
                        throwable -> showError()
                );
    }
    public void downloadFile(DownloadManager downloadManager, UrlsBean urls){
        String downloadUrl;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose resolution");
        builder.setItems(R.array.resolutions, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        mDownloadUrl = currentImageURLS.getRegular();
                        break;
                    case 1:
                        mDownloadUrl = currentImageURLS.getFull();
                        break;
                    case 2:
                        mDownloadUrl = currentImageURLS.getRaw();
                        break;
                    case 3:
                        mDownloadUrl = currentImageURLS.getSmall();
                        break;

                }
            }
        }).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Uri Download_Uri = Uri.parse(mDownloadUrl);
                DownloadManager.Request request = new DownloadManager.Request(Download_Uri);
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                request.setAllowedOverRoaming(false);
                request.setTitle("UnsplashApi Downloading " + "photo" + ".png");
                request.setDescription("Downloading " + "photo" + ".png");
                request.setVisibleInDownloadsUi(true);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "/Unsplash/"  + "/" + "photo" + ".png");
                refid = downloadManager.enqueue(request);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }).create().show();
    }

    public void showStart(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    public void showRandomPhoto(UrlsBean urls) {
        currentImageURLS = urls;
        String url= urls.getRegular();
        Picasso.with(getActivity()).load(url).into(imgageView);
    }

    public void showError() {

    }


}

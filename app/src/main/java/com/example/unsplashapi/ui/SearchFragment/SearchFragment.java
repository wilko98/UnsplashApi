package com.example.unsplashapi.ui.SearchFragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.unsplashapi.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SearchFragment extends Fragment {

    private RecyclerView mRecycler;
    private SwipeRefreshLayout mRefresher;
    private SearchAdapter mAdapter;
    private HttpURLConnection connection;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fr_search, container, false);
        mRecycler = v.findViewById(R.id.recycler);
        mRefresher = v.findViewById(R.id.refresher);

//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mAdapter = new SearchAdapter();
        mRecycler.setAdapter(mAdapter);
        String src = "https://images.unsplash.com/photo-1547822050-0fdeeb81c946?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60";
        mRecycler.post(new Runnable() {
            @Override
            public void run() {
                addBitmapFromURL(src);
            }
        });
    }

    public void addBitmapFromURL(String src) {
        Thread b = new Thread(() -> {
            try {
                connection = (HttpURLConnection) new URL(src).openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
//                mAdapter.add(myBitmap);
                mRecycler.post(() -> mAdapter.add(myBitmap));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Thread a = new Thread(() -> {
            mRecycler.post(() -> {
//                try {
//
                    b.start();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            });

        });
        a.start();

    }
}

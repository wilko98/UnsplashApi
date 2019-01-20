package com.example.unsplashapi.common;

import com.arellomobile.mvp.MvpAppCompatFragment;

/**
 * Created by Tamerlan Kunakbayev.
 */

public abstract class PresenterFragment extends MvpAppCompatFragment {

    protected abstract BasePresenter getPresenter();

    @Override
    public void onDetach() {
        if (getPresenter() != null) {
            getPresenter().disposeAll();
        }
        super.onDetach();
    }
}

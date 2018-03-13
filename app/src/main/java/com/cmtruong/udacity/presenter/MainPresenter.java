package com.cmtruong.udacity.presenter;

/**
 * Created by davidetruong on 13/03/2018.
 */

public interface MainPresenter {
    void onResume();

    void onItemClicked(int position);

    void onDestroy();
}

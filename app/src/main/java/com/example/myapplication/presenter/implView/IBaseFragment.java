package com.example.myapplication.presenter.implView;

/**
 * Created by 李思言 on 2016/11/13.
 */

public interface IBaseFragment {

    void showProgressDialog();

    void hideProgressDialog();

    void showError(String error);
}

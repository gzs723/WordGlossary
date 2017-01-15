package com.example.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.activity.BaseActivity;
import com.example.myapplication.presenter.implView.INewsFragment;

import butterknife.ButterKnife;

/**
 * Created by 李思言 on 2016/12/7.
 */

public class NewsFragment extends BaseFragment implements INewsFragment{


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.activity_login,null,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void showNewsPicture() {

    }

    @Override
    public void showNewsTitle() {

    }
}

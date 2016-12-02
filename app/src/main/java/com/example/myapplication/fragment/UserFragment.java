package com.example.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.activity.UserMessageActivity;
import com.example.myapplication.presenter.implPresenter.UserPresenterlmpl;
import com.example.myapplication.presenter.implView.IUserFragment;
import com.example.myapplication.share_auth.UserinfoActivity;
import com.example.myapplication.util.SharePreferenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 李思言 on 2016/11/19.
 */

public class UserFragment extends BaseFragment implements IUserFragment {


    private UserPresenterlmpl mUserPresenterlmpl;


    @BindView(R.id.user_head)
    ImageView mHeadView;
    @BindView(R.id.text_login)
    TextView textlogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.d("User","onCreate");

       View view=inflater.inflate(R.layout.activity_user,container,false);

        ButterKnife.bind(this,view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUserPresenterlmpl=new UserPresenterlmpl(this,getContext());
        Log.d("User","onViewCreated");

    }

    @Override
    public void onResume() {

        super.onResume();
        initview();
        loaddate();
        Log.d("User","onResume");
    }




    private void initview(){


    }

    private void loaddate(){


        mUserPresenterlmpl.getUserMessage();

    }



    @OnClick(R.id.login)

    public void login(){

        mUserPresenterlmpl.obtainUserMessage();

    }


    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void updateUserMessage(String name, String imgurl) {

        textlogin.setText(name);
        Glide.with(this)
             .load(imgurl)
             .error(R.mipmap.ic_launcher)
             .into(mHeadView);

    }

    @Override
    public void onDestroy() {

        super.onDestroy();

        Log.d("User","onDestroy");

    }
}

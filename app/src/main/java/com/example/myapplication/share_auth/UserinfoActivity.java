package com.example.myapplication.share_auth;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.activity.BaseActivity;
import com.example.myapplication.fragment.UserFragment;
import com.example.myapplication.presenter.implPresenter.UserPresenterlmpl;
import com.example.myapplication.util.SharePreferenceUtil;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.Log;


import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wangfei on 15/10/10.
 */
public class UserinfoActivity extends BaseActivity {

    private UMShareAPI mShareAPI = null;

    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_user);
        ButterKnife.bind(this);
        mShareAPI = UMShareAPI.get(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @OnClick(R.id.logqq)

    public void logqq(){

        SHARE_MEDIA platform = null;

        platform = SHARE_MEDIA.QQ;

        mShareAPI.getPlatformInfo(UserinfoActivity.this, platform, umAuthListener);
    }



    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            for (String key : data.keySet()) {
                Log.e("xxxxxx key = "+key+"    value= "+data.get(key));
            }
            if (data!=null){


                SharePreferenceUtil.putUser(getApplicationContext(),data.get("screen_name"),data.get("profile_image_url"));

                finish();

            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText( getApplicationContext(), "get fail"+t.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText( getApplicationContext(), "get cancel", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mShareAPI.onActivityResult(requestCode, resultCode, data);
    }
}

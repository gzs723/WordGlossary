package com.example.myapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.fragment.UserFragment;
import com.example.myapplication.util.SharePreferenceUtil;

/**
 * Created by 李思言 on 2016/11/23.
 */

public class UserMessageActivity extends BaseActivity {

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_message);

       textView= (TextView) findViewById(R.id.logout);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharePreferenceUtil.clearUser(getApplicationContext());
                finish();
            }
        });
    }


}

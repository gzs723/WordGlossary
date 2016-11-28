package com.example.myapplication.presenter.implPresenter;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.myapplication.R;
import com.example.myapplication.activity.UserMessageActivity;
import com.example.myapplication.presenter.IUserPresenter;
import com.example.myapplication.presenter.implView.IUserFragment;
import com.example.myapplication.share_auth.UserinfoActivity;
import com.example.myapplication.util.SharePreferenceUtil;

/**
 * Created by 李思言 on 2016/11/19.
 */

public class UserPresenterlmpl extends BasePresenterImpl implements IUserPresenter{

    private IUserFragment mIUserFragment;
    private Context context;

    public UserPresenterlmpl(IUserFragment iUserFragment, Context context){

        mIUserFragment= iUserFragment;
        this.context=context;

    }



    @Override
    public void getUserMessage() {

        if (SharePreferenceUtil.ishasUser(context)){

            mIUserFragment.updateUserMessage(SharePreferenceUtil.getUser(context).getString("uesr_name",null)
                        ,SharePreferenceUtil.getUser(context).getString("user_img",null));

        }else{

            mIUserFragment.updateUserMessage(context.getResources().getString(R.string.login)
                    ,null);

        }

    }

    @Override
    public void obtainUserMessage() {

        if (SharePreferenceUtil.ishasUser(context)){

            Intent intent=new Intent(context, UserMessageActivity.class);
            context.startActivity(intent);

        }else {

            Intent intent=new Intent(context, UserinfoActivity.class);
            context.startActivity(intent);
        }

    }


}

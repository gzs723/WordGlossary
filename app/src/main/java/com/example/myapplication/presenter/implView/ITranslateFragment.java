package com.example.myapplication.presenter.implView;

import com.example.myapplication.api.YoudaoApi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李思言 on 2016/11/13.
 */

public interface ITranslateFragment extends IBaseFragment {

    //更新视图
    void updateGlossary(List<String> ydArrayList);

}

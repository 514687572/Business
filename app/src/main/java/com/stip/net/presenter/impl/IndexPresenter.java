package com.stip.net.presenter.impl;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;
import com.stip.net.bean.TaskResponse;
import com.stip.net.presenter.IIndexPresenter;
import com.stip.net.utils.ConstantUtils;
import com.stip.net.utils.HttpUtils;
import com.stip.net.utils.StipRequest;
import com.stip.net.utils.ToastUtils;
import com.stip.net.utils.VolleyErrorHelper;
import com.stip.net.view.IIndexView;
import com.stip.net.view.impl.IndexActivity;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by C on 2018/07/03.
 */
public class IndexPresenter implements IIndexPresenter {
    private IIndexView indexView;

    @Inject
    public IndexPresenter() {
    }

    @Override
    public void load(final IndexActivity indexActivity, HashMap<String, String> params) {
        StipRequest.ResponeListener<TaskResponse> responseListener=new StipRequest.ResponeListener<TaskResponse>(){
            @Override
            public void onErrorResponse(VolleyError error) {
                String errorInfo= VolleyErrorHelper.getMessage(error,indexActivity);
                ToastUtils.showLong(indexActivity,errorInfo);
            }

            @Override
            public void onResponse(TaskResponse response) {
                indexView=indexActivity;
                indexView.onResponse(response);
            }
        };
        Type type = new TypeToken<TaskResponse>() {}.getType();
        StipRequest<TaskResponse> yr = new StipRequest<TaskResponse>(ConstantUtils.POST,ConstantUtils.SERVER_ADDRESS+ConstantUtils.GET_TASKS_URL, type, params,responseListener);
        HttpUtils.addRequest(yr);
    }
}

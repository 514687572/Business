package com.stip.net.presenter.impl;

import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;
import com.stip.net.base.StipResponse;
import com.stip.net.presenter.ITaskDetailPresenter;
import com.stip.net.utils.ConstantUtils;
import com.stip.net.utils.HttpUtils;
import com.stip.net.utils.StipRequest;
import com.stip.net.utils.ToastUtils;
import com.stip.net.utils.VolleyErrorHelper;
import com.stip.net.view.ITaskDetailView;
import com.stip.net.view.impl.TaskDetailActivity;

import java.lang.reflect.Type;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by C on 2016/8/22.
 */
public class TakDetailPresenter implements ITaskDetailPresenter {
    private ITaskDetailView taskDetailView;

    @Inject
    public TakDetailPresenter() {
    }

    @Override
    public void delete(final TaskDetailActivity taskDetailActivity, Map<String, String> params) {
        StipRequest.ResponeListener<StipResponse> responseListener=new StipRequest.ResponeListener<StipResponse>(){
            @Override
            public void onErrorResponse(VolleyError error) {
                String errorInfo= VolleyErrorHelper.getMessage(error,taskDetailActivity);
                ToastUtils.showLong(taskDetailActivity,errorInfo);
            }

            @Override
            public void onResponse(StipResponse response) {
                taskDetailView=taskDetailActivity;
                taskDetailView.onResponse(response);
            }
        };
        Type type = new TypeToken<StipResponse>() {}.getType();
        StipRequest<StipResponse> yr = new StipRequest<StipResponse>(ConstantUtils.POST,ConstantUtils.SERVER_ADDRESS+ConstantUtils.DELETE_TASK_URL, type, params,responseListener);
        HttpUtils.addRequest(yr);
    }
}

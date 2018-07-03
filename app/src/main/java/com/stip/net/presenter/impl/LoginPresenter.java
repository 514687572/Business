package com.stip.net.presenter.impl;

import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;
import com.stip.net.bean.StipResponse;
import com.stip.net.presenter.ILoginPresenter;
import com.stip.net.utils.ConstantUtils;
import com.stip.net.utils.HttpUtils;
import com.stip.net.utils.StipRequest;
import com.stip.net.utils.ToastUtils;
import com.stip.net.utils.VolleyErrorHelper;
import com.stip.net.view.ILoginView;
import com.stip.net.view.impl.LoginActivity;

import java.lang.reflect.Type;
import java.util.HashMap;

import javax.inject.Inject;

/**
 * Created by C on 2016/8/22.
 */
public class LoginPresenter implements ILoginPresenter {
    private ILoginView loginView;

    @Inject
    public LoginPresenter() {
    }

    @Override
    public void login(final LoginActivity loginActivity,HashMap<String, String> params) {
        StipRequest.ResponeListener<StipResponse> responseListener=new StipRequest.ResponeListener<StipResponse>(){
            @Override
            public void onErrorResponse(VolleyError error) {
                String errorInfo= VolleyErrorHelper.getMessage(error,loginActivity);
                ToastUtils.showLong(loginActivity,errorInfo);
            }

            @Override
            public void onResponse(StipResponse response) {
                loginView=loginActivity;
                loginView.onResponse(response);
            }
        };
        Type type = new TypeToken<StipResponse>() {}.getType();
        StipRequest<StipResponse> yr = new StipRequest<StipResponse>(ConstantUtils.POST,ConstantUtils.SERVER_ADDRESS+ConstantUtils.MCH_LOGIN_URL, type, params,responseListener);
        HttpUtils.addRequest(yr);
    }
}

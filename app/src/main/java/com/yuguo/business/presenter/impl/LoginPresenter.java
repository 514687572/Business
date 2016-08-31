package com.yuguo.business.presenter.impl;

import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;
import com.yuguo.business.bean.YuguoResponse;
import com.yuguo.business.presenter.ILoginPresenter;
import com.yuguo.business.utils.ConstantUtils;
import com.yuguo.business.utils.HttpUtils;
import com.yuguo.business.utils.ToastUtils;
import com.yuguo.business.utils.VolleyErrorHelper;
import com.yuguo.business.utils.YuguoRequest;
import com.yuguo.business.view.ILoginView;
import com.yuguo.business.view.impl.LoginActivity;

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
        YuguoRequest.ResponeListener<YuguoResponse> responseListener=new YuguoRequest.ResponeListener<YuguoResponse>(){
            @Override
            public void onErrorResponse(VolleyError error) {
                String errorInfo= VolleyErrorHelper.getMessage(error,loginActivity);
                ToastUtils.showLong(loginActivity,errorInfo);
            }

            @Override
            public void onResponse(YuguoResponse response) {
                loginView=loginActivity;
                loginView.onResponse(response);
            }
        };
        Type type = new TypeToken<YuguoResponse>() {}.getType();
        YuguoRequest<YuguoResponse> yr = new YuguoRequest<YuguoResponse>(ConstantUtils.POST,ConstantUtils.SERVER_ADDRESS+ConstantUtils.MCH_LOGIN_URL, type, params,responseListener);
        HttpUtils.addRequest(yr);
    }
}

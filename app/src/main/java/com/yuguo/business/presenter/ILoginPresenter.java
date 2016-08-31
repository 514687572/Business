package com.yuguo.business.presenter;

import com.yuguo.business.view.impl.LoginActivity;

import java.util.HashMap;

/**
 * Created by C on 2016/8/22.
 */
public interface ILoginPresenter {
    void login(LoginActivity loginActivity, HashMap<String, String> params);
}

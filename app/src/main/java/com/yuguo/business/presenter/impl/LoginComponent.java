package com.yuguo.business.presenter.impl;

import com.yuguo.business.view.impl.LoginActivity;

import dagger.Component;

/**
 * Created by C on 2016/8/24.
 */
@Component
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
}

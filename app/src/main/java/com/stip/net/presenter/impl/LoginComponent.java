package com.stip.net.presenter.impl;

import com.stip.net.view.impl.LoginActivity;

import dagger.Component;

/**
 * Created by C on 2016/8/24.
 */
@Component
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
}

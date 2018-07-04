package com.stip.net.presenter.impl;

import com.stip.net.view.impl.IndexActivity;

import dagger.Component;

/**
 * Created by C on 2018/07/03.
 */
@Component
public interface IndexComponent {
    void inject(IndexActivity indexActivity);
}

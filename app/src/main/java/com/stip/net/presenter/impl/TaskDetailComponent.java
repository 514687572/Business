package com.stip.net.presenter.impl;

import com.stip.net.view.impl.LoginActivity;
import com.stip.net.view.impl.TaskDetailActivity;

import dagger.Component;

/**
 * Created by C on 2016/8/24.
 */
@Component
public interface TaskDetailComponent {
    void inject(TaskDetailActivity taskDetailActivity);
}

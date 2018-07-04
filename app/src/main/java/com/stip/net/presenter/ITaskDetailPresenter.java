package com.stip.net.presenter;

import com.stip.net.view.impl.LoginActivity;
import com.stip.net.view.impl.TaskDetailActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by C on 2016/8/22.
 */
public interface ITaskDetailPresenter {
    void delete(TaskDetailActivity taskDetailActivity, Map<String, String> params);
}

package com.stip.net.view;

import com.android.volley.Response;
import com.stip.net.bean.TaskResponse;

import java.util.Map;

/**
 * Created by C on 2016/8/22.
 */
public interface IIndexView {
    void onResponse(TaskResponse response);
}

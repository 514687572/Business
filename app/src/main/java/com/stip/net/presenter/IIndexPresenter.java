package com.stip.net.presenter;

import com.stip.net.view.impl.IndexActivity;
import com.stip.net.view.impl.LoginActivity;

import java.util.HashMap;

/**
 * Created by C on 2018/7/03.
 */
public interface IIndexPresenter {
    void load(IndexActivity indexActivity, HashMap<String, String> params);
}

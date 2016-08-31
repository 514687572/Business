package com.yuguo.business.view.impl;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.yuguo.business.presenter.impl.DaggerLoginComponent;
import com.yuguo.business.presenter.impl.LoginPresenter;
import com.yuguo.business.utils.ConstantUtils;
import com.yuguo.business.utils.StringUtils;
import com.yuguo.business.utils.ToastUtils;
import com.yuguo.business.bean.YuguoResponse;
import com.yuguo.business.view.ILoginView;
import com.yuguo.business.view.OrderCenterActivity;
import com.yuguo.business.R;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 登录界面交互
 * @author C
 *
 */

public class LoginActivity extends Activity implements ILoginView {
	@InjectView(R.id.buttonLogin)
	Button loginButton;
	@InjectView(R.id.mch_password)
	EditText mch_password;
	@InjectView(R.id.mch_Account)
	EditText mch_Account;
	@Inject
	LoginPresenter loginPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		ButterKnife.inject(this);

		DaggerLoginComponent.builder().build().inject(this);
	}
	
	/**
	 * 登录点击事件
	 */
	@OnClick(R.id.buttonLogin)
	public void onLogin(){
		String userName=mch_Account.getText().toString();
		String password=mch_password.getText().toString();
		
		if(StringUtils.isBlank(userName)||StringUtils.isBlank(password)){
			ToastUtils.showLong(LoginActivity.this, "用户名密码不能为空");
			return;
		}
		
		HashMap<String, String> params=new HashMap<String, String>();
		params.put("userName",userName);
		params.put("password",password);

		loginPresenter.login(this,params);
	}

	/**
	 * 返回登录网络请求结果
	 * @param response
     */
	public void onResponse(YuguoResponse response){
		if(ConstantUtils.SUCCESS_STATUS==response.isSuccess()){
			Intent intent = new Intent();
			intent.setClass(LoginActivity.this,OrderCenterActivity.class);
			startActivity(intent);
		}else if(ConstantUtils.FAILURE_STATUS==response.isSuccess()){
			ToastUtils.showLong(LoginActivity.this, "用户名或密码有误");
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.reset(this);
	}
}

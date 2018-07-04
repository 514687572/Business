package com.stip.net.view.impl;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.stip.net.base.StipResponse;
import com.stip.net.utils.ConstantUtils;
import com.stip.net.utils.StringUtils;
import com.stip.net.utils.ToastUtils;
import com.stip.net.view.ILoginView;
import com.stip.net.R;
import com.stip.net.presenter.impl.DaggerLoginComponent;
import com.stip.net.presenter.impl.LoginPresenter;

import java.util.HashMap;

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

		SharedPreferences sharedPreferences=getSharedPreferences("config",0);
		//取出数据，如果取出的数据时空时，只需把getString("","")第二个参数设置成空字符串就行了，不用在判断
		String userName=sharedPreferences.getString("name","");
		String password=sharedPreferences.getString("password","");

		if(!userName.isEmpty()&&!password.isEmpty()){
			HashMap<String, String> params=new HashMap<String, String>();
			params.put("userName",userName);
			params.put("password",password);

			loginPresenter.login(this,params);
		}
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
	public void onResponse(StipResponse response){
		if(ConstantUtils.SUCCESS_STATUS==response.isSuccess()){
			ToastUtils.showLong(LoginActivity.this, "登录成功");
			Intent intent = new Intent();
			intent.setClass(LoginActivity.this,IndexActivity.class);
			startActivity(intent);

			//getSharedPreferences(name,model);,name 会生成一个xml文件，model ：模式，可读可写等模式
			SharedPreferences sp=getSharedPreferences("config",0);
			SharedPreferences.Editor editor=sp.edit();
			//把数据进行保存

			editor.putString("name",mch_Account.getText().toString());
			editor.putString("password",mch_password.getText().toString());
			//提交数据
			editor.commit();

			this.finish();
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

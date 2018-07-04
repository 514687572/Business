package com.stip.net.view.impl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.stip.net.R;
import com.stip.net.base.StipResponse;
import com.stip.net.presenter.impl.DaggerLoginComponent;
import com.stip.net.presenter.impl.DaggerTaskDetailComponent;
import com.stip.net.presenter.impl.LoginPresenter;
import com.stip.net.presenter.impl.TakDetailPresenter;
import com.stip.net.utils.ConstantUtils;
import com.stip.net.utils.StringUtils;
import com.stip.net.utils.ToastUtils;
import com.stip.net.view.ILoginView;
import com.stip.net.view.ITaskDetailView;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 登录界面交互
 * @author C
 *
 */

public class TaskDetailActivity extends Activity implements ITaskDetailView {
	@InjectView(R.id.tex_taskUrl)
	TextView tex_taskUrl;
	@InjectView(R.id.tex_taskCount)
	TextView tex_taskCount;
	@InjectView(R.id.tex_taskStatus)
	TextView tex_taskStatus;
	@Inject
	TakDetailPresenter takDetailPresenter;

	String taskId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.task_detail);
		ButterKnife.inject(this);

		DaggerTaskDetailComponent.builder().build().inject(this);

		Intent intent = getIntent();

		taskId=intent.getStringExtra("taskId");
		tex_taskUrl.setText(intent.getStringExtra("taskUrl"));
		tex_taskCount.setText(intent.getStringExtra("taskCount"));
		tex_taskStatus.setText(intent.getStringExtra("taskStatus"));
	}

	/**
	 * 删除点击事件
	 */
	@OnClick(R.id.deleteButton)
	public void onDelete(){
		Map<String, String> params=new HashMap<String, String>();
		params.put("taskId",taskId);

		takDetailPresenter.delete(this,params);
	}

	/**
	 * 返回登录网络请求结果
	 * @param response
     */
	public void onResponse(StipResponse response){
		if(ConstantUtils.SUCCESS_STATUS==response.isSuccess()){
			ToastUtils.showLong(TaskDetailActivity.this, "删除成功");
			Intent intent = new Intent();
			intent.setClass(TaskDetailActivity.this,IndexActivity.class);
			startActivity(intent);
		}else if(ConstantUtils.FAILURE_STATUS==response.isSuccess()){
			ToastUtils.showLong(TaskDetailActivity.this, "删除失败请重试");
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.reset(this);
	}
}

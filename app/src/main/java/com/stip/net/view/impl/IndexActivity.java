package com.stip.net.view.impl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.stip.net.R;
import com.stip.net.bean.SysTask;
import com.stip.net.bean.TaskResponse;
import com.stip.net.presenter.impl.DaggerIndexComponent;
import com.stip.net.presenter.impl.IndexPresenter;
import com.stip.net.utils.ConstantUtils;
import com.stip.net.utils.ToastUtils;
import com.stip.net.view.IIndexView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 主页交互信息
 * @author C
 *
 */

public class IndexActivity extends Activity implements IIndexView {
	@InjectView(R.id.contexListView)
	ListView list;
	@Inject
	IndexPresenter indexPresenter;
	//记录用户首次点击返回键的时间
	private long firstTime = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_index);
		ButterKnife.inject(this);

		DaggerIndexComponent.builder().build().inject(this);
		onLoad();
	}

	public void onLoad(){
		indexPresenter.load(this,null);
	}

	/**
	 * 返回登录网络请求结果
	 * @param response
     */
	public void onResponse(TaskResponse response){
		if(ConstantUtils.SUCCESS_STATUS==response.isSuccess()){
			Map<String, Object> resData=null;
			List<Map<String, Object>> tasks=new ArrayList<Map<String, Object>>();
			for(SysTask task:response.getTaskList()){
				resData=new HashMap<String,Object>();

				String[] title=new String[] {"taskUrl", "taskCount","taskStatus","taskId"};
				int[] disInt=new int[] {R.id.taskName,R.id.taskText,R.id.taskStatus,R.id.taskId};

				resData.put("taskUrl",task.getTaskUrl());
				resData.put("taskCount",task.getTaskCount());
				resData.put("taskStatus",task.getTaskStatus());
				resData.put("taskId",task.getTaskId());

				tasks.add(resData);

				//配置适配器
				SimpleAdapter adapter = new SimpleAdapter(this,tasks,R.layout.task_list,title,disInt); //布局里的控件id

				//添加并且显示
				list.setAdapter(adapter);
				list.setOnItemClickListener(new ListViewItemClickListener());
			}
		}else if(ConstantUtils.FAILURE_STATUS==response.isSuccess()){
			ToastUtils.showLong(IndexActivity.this, "用户名或密码有误");
		}
	}

	class ListViewItemClickListener implements AdapterView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
			Map<String, Object> mMap = (Map<String, Object>) list.getItemAtPosition(position);
			Intent intent = new Intent();
			intent.putExtra("taskUrl",mMap.get("taskUrl").toString());
			intent.putExtra("taskStatus",mMap.get("taskStatus").toString());
			intent.putExtra("taskCount",mMap.get("taskCount").toString());
			intent.putExtra("taskId",mMap.get("taskId").toString());
			intent.setClass(IndexActivity.this,TaskDetailActivity.class);
			startActivity(intent);
		}

	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.reset(this);
	}

	/**
	 * 第三种方法
	 */
	@Override
	public void onBackPressed() {
		long secondTime = System.currentTimeMillis();
		if (secondTime - firstTime > 2000) {
			ToastUtils.showLong(IndexActivity.this, "再按一次退出程序");
			firstTime = secondTime;
		} else {
			this.finish();
		}
	}

}

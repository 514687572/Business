package com.yuguo.business.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.yuguo.business.R;

public class OrderCenterActivity extends Activity  {
	private SlidingMenu mMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.setContentView(R.layout.activity_order_center);
		mMenu = (SlidingMenu) findViewById(R.id.id_menu);
	}

	public void toggleMenu(View view) {
		mMenu.toggle();
	}

}

package com.stip.net.base;

import java.io.Serializable;

/**
 * 请求返回父类
 * @author C
 *
 */
public class StipResponse implements Serializable {
	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}

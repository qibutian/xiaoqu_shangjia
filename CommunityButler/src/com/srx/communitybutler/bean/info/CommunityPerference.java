package com.srx.communitybutler.bean.info;

import net.duohuo.dhroid.util.Perference;

public class CommunityPerference extends Perference {
	// 第一次登陆
	public int isFirst = 0;

	public String pswd;

	public String phone;

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getIsFirst() {
		return isFirst;
	}

	public void setIsFirst(int isFirst) {
		this.isFirst = isFirst;
	}

}

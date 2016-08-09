package com.srx.communitybutler.activity.my;

import net.duohuo.dhroid.ioc.IocContainer;
import net.duohuo.dhroid.net.DhNet;
import net.duohuo.dhroid.net.NetTask;
import net.duohuo.dhroid.net.Response;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.srx.communitybutler.R;
import com.srx.communitybutler.base.CommunityBaseActivity;
import com.srx.communitybutler.bean.info.CommunityPerference;
import com.srx.communitybutler.bean.info.User;

/**
 * 
 * 登陆
 * @author Administrator
 *
 */
public class LoginActivity extends CommunityBaseActivity implements
		OnClickListener {
	private EditText passwordEt, telEt;
	private TextView forgetPwdT;
	private Button loginBtn;

	CommunityPerference per;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setTitle("登录");
//		setRightAction("注册", -1, new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent it = new Intent(self, RegisterActivity.class);
//				startActivity(it);
//			}
//		});
		passwordEt = (EditText) findViewById(R.id.password);
		telEt = (EditText) findViewById(R.id.tel);
		forgetPwdT = (TextView) findViewById(R.id.forgetpwd);
		loginBtn = (Button) findViewById(R.id.login);

		forgetPwdT.setOnClickListener(this);
		loginBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// 登陆
		case R.id.login:
			login();
			break;
		// 忘记密码
		case R.id.forgetpwd:
			Intent intent = new Intent(self, ForgetPasswordActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}
	
	private void login() {
		// final String tel = telEt.getText().toString();
		// final String password = passwordEt.getText().toString();

		final String tel = "13770791947";
		final String password = "123456a";
		if (TextUtils.isEmpty(tel)) {
			showToast("请输入手机号");
			return;
		}
		if (tel.length() != 11) {
			showToast("手机号格式不正确");
			return;
		}
		if (TextUtils.isEmpty(password)) {
			showToast("请输入密码");
			return;
		}
		if (password.length() < 6 || password.length() > 15) {
			showToast("密码为6-15位字母或数字的组合");
			return;
		}
		DhNet smsNet = new DhNet("");
		smsNet.addParam("pswd", password);
		smsNet.addParam("phone", tel);

		smsNet.doPostInDialog(new NetTask(self) {

			@Override
			public void doInUI(Response response, Integer transfer) {
				// TODO Auto-generated method stub
				if (response.isSuccess()) {
					JSONObject jo = response.jSONFromData();
					User user = User.getInstance();
					user.setLogin(true);
//					user.setUserid(JSONUtil.getString(jo, "id"));
//					user.setShareCode(JSONUtil.getString(jo, "code"));
					per = IocContainer.getShare().get(CommunityPerference.class);
					per.load();
					per.setPhone(tel);
					per.setPswd(password);
					per.commit();
					finish();
				}
			}
		});
	}
	
	
}

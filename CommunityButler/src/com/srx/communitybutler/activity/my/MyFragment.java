package com.srx.communitybutler.activity.my;

import java.io.File;

import net.duohuo.dhroid.ioc.IocContainer;
import net.duohuo.dhroid.net.DhNet;
import net.duohuo.dhroid.net.JSONUtil;
import net.duohuo.dhroid.net.NetTask;
import net.duohuo.dhroid.net.Response;
import net.duohuo.dhroid.util.ViewUtil;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.srx.communitybutler.R;
import com.srx.communitybutler.api.API;
import com.srx.communitybutler.bean.info.CommunityPerference;
import com.srx.communitybutler.bean.info.User;

import de.greenrobot.event.EventBus;

/**
 * 我的
 * 
 * @author Administrator
 * 
 */
public class MyFragment extends Fragment implements OnClickListener {
	static MyFragment instance;

	View mainV;

	LayoutInflater mLayoutInflater;

	private ImageView headI;

	CommunityPerference per;


	public static MyFragment getInstance() {
		if (instance == null) {
			instance = new MyFragment();
		}

		return instance;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mainV = inflater.inflate(R.layout.fragment_my, null);
		mLayoutInflater = inflater;
		per = IocContainer.getShare().get(CommunityPerference.class);
		per.load();
		initView();
		return mainV;
	}

	private void initView() {
		EventBus.getDefault().register(this);
		headI = (ImageView) mainV.findViewById(R.id.head);

		headI.setOnClickListener(this);
		getUserInfo();
	}

	private void getUserInfo() {
	}

	@Override
	public void onClick(View v) {
		Intent it;
		switch (v.getId()) {
		// 头像
		case R.id.head:
			// it = new Intent(getActivity(), ChangePasswordActivity.class);
			// it = new Intent(getActivity(), RegisterActivity.class);
			// it = new Intent(getActivity(), LoginActivity.class);
			// // it = new Intent(getActivity(), PaymentActivity.class);
			// // it = new Intent(getActivity(), CampusSelectActivity.class);
			// getActivity().startActivity(it);
			break;
		default:
			break;
		}
	}

//	public void onEventMainThread(ReChargeEB reChargeEB) {
//		getUserInfo();
//	}
//	
//	public void onEventMainThread(CreditEB creditEB) {
//		getUserInfo();
//	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}
}

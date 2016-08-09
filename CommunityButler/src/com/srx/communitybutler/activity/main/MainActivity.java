package com.srx.communitybutler.activity.main;

import net.duohuo.dhroid.ioc.IocContainer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.srx.communitybutler.R;
import com.srx.communitybutler.activity.goods.GoodsFragment;
import com.srx.communitybutler.activity.my.MyFragment;
import com.srx.communitybutler.activity.order.OrderFragment;
import com.srx.communitybutler.base.CommunityBaseFragmentActivity;
import com.srx.communitybutler.bean.info.CommunityPerference;
import com.srx.communitybutler.bean.info.User;
import com.srx.communitybutler.manage.UserInfoManage;
import com.srx.communitybutler.manage.UserInfoManage.LoginCallBack;

import de.greenrobot.event.EventBus;

public class MainActivity extends CommunityBaseFragmentActivity {
	private FragmentManager fm;
	private Fragment currentFragment;

	private LinearLayout tabV;
	CommunityPerference per;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        EventBus.getDefault().register(this);
		per = IocContainer.getShare().get(CommunityPerference.class);
		per.load();
		initView();
		initTab();
		setTab(0);
    }

    
	public void initView() {
		// TODO Auto-generated method stub
		fm = getSupportFragmentManager();
		tabV = (LinearLayout) findViewById(R.id.tab);
	}
	
	private void initTab() {
		for (int i = 0; i < tabV.getChildCount(); i++) {
			final int index = i;
			LinearLayout childV = (LinearLayout) tabV.getChildAt(i);
			childV.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					setTab(index);
				}
			});
		}
	}
	
	public void setTab(final int index) {
		for (int i = 0; i < tabV.getChildCount(); i++) {
			LinearLayout childV = (LinearLayout) tabV.getChildAt(i);
			RelativeLayout imgV = (RelativeLayout) childV.getChildAt(0);
			final ImageView imgI = (ImageView) imgV.getChildAt(0);
			final TextView textT = (TextView) childV.getChildAt(1);

			if (index == 2 || index == 1) {
				if (!User.getInstance().isLogin()) {
					UserInfoManage.getInstance().checkLogin(MainActivity.this,
							new LoginCallBack() {

								@Override
								public void onisLogin() {

								}

								@Override
								public void onLoginFail() {
									// TODO Auto-generated method stub

								}
							});
					return;
				}
			}
			if (i == index) {
				switch (i) {
				case 0: // 订单
					switchContent(OrderFragment.getInstance());
					imgI.setImageResource(R.drawable.icon_home_s);
					textT.setTextColor(getResources().getColor(
							R.color.tab_index_bg));
					break;

				case 1: // 商品
					switchContent(GoodsFragment.getInstance());
					imgI.setImageResource(R.drawable.icon_order_s);
					textT.setTextColor(getResources().getColor(
							R.color.tab_index_bg));
					break;

				case 2: // 我的
					switchContent(MyFragment.getInstance());
					imgI.setImageResource(R.drawable.icon_my_s);
					textT.setTextColor(getResources().getColor(
							R.color.tab_index_bg));

					break;

				default:
					break;
				}
			} else {
				childV.setBackgroundColor(getResources().getColor(
						R.color.nothing));
				switch (i) {
				case 0:
					imgI.setImageResource(R.drawable.icon_home_n);
					textT.setTextColor(getResources().getColor(
							R.color.text_43_black));
					break;

				case 1:
					imgI.setImageResource(R.drawable.icon_order_n);
					textT.setTextColor(getResources().getColor(
							R.color.text_43_black));
					break;

				case 2:
					imgI.setImageResource(R.drawable.icon_my_n);
					textT.setTextColor(getResources().getColor(
							R.color.text_43_black));
					break;

				default:
					break;
				}
			}
		}
	}

	public void switchContent(Fragment fragment) {
		try {
			FragmentTransaction t = fm.beginTransaction();
			if (currentFragment != null) {
				t.hide(currentFragment);
			}
			if (!fragment.isAdded()) {
				t.add(R.id.main_content, fragment);
			}
			t.show(fragment);
			t.commitAllowingStateLoss();
			currentFragment = fragment;
		} catch (Exception e) {
		}
	}

//	public void onEventMainThread(LogoutEB out) {
//
//		setTab(0);
//	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}
}

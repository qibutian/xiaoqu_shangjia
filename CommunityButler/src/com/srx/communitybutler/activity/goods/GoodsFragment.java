package com.srx.communitybutler.activity.goods;

import net.duohuo.dhroid.adapter.NetJSONAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.srx.communitybutler.R;
import com.srx.communitybutler.activity.order.AccomplishFragment;
import com.srx.communitybutler.views.RefreshListViewAndMore;

/**
 * 
 * 商品
 * @author Administrator
 *
 */
public class GoodsFragment extends Fragment{
	static GoodsFragment instance;

	View mainV;

	LayoutInflater mLayoutInflater;
	
	
	public static GoodsFragment getInstance() {
		if (instance == null) {
			instance = new GoodsFragment();
		}

		return instance;

	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mainV = inflater.inflate(R.layout.fragment_goods, null);
		mLayoutInflater = inflater;
		initView();
		// TODO Auto-generated method stub
		return mainV;
	}

	private void initView() {
		
	}

}

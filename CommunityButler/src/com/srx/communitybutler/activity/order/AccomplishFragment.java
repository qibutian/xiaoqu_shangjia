package com.srx.communitybutler.activity.order;

import net.duohuo.dhroid.adapter.NetJSONAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.srx.communitybutler.R;
import com.srx.communitybutler.views.RefreshListViewAndMore;

/**
 * 
 * 完成发货
 * @author Administrator
 *
 */
public class AccomplishFragment extends Fragment{
	static AccomplishFragment instance;

	View mainV;

	LayoutInflater mLayoutInflater;
	
	RefreshListViewAndMore listV;
	NetJSONAdapter adapter;
	ListView contentListV;
	
	public static AccomplishFragment getInstance() {
		if (instance == null) {
			instance = new AccomplishFragment();
		}

		return instance;

	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mainV = inflater.inflate(R.layout.fragment_accomplish, null);
		mLayoutInflater = inflater;
		initView();
		// TODO Auto-generated method stub
		return mainV;
	}

	private void initView() {
		listV = (RefreshListViewAndMore) mainV.findViewById(R.id.my_listview);
		getData();
	}

	private void getData() {

	}
}

package com.srx.communitybutler.activity.order;

import java.util.Date;

import net.duohuo.dhroid.adapter.FieldMap;
import net.duohuo.dhroid.adapter.NetJSONAdapter;
import net.duohuo.dhroid.net.JSONUtil;

import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.srx.communitybutler.R;
import com.srx.communitybutler.api.API;
import com.srx.communitybutler.views.RefreshListViewAndMore;

/**
 * 等待发货
 * @author Administrator
 *
 */
public class AwaitShipmentFragment extends Fragment{
	static AwaitShipmentFragment instance;

	View mainV;

	LayoutInflater mLayoutInflater;
	
	RefreshListViewAndMore listV;
	NetJSONAdapter adapter;
	ListView contentListV;
	
	public static AwaitShipmentFragment getInstance() {
		if (instance == null) {
			instance = new AwaitShipmentFragment();
		}

		return instance;

	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mainV = inflater.inflate(R.layout.fragment_await_shipment, null);
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

package com.xxjwd.sjbg;

import java.util.ArrayList;
import java.util.HashMap;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.xxjwd.classes.TeQing;
import com.xxjwd.transfer.Transfer;
import com.zcj.lib.AnimFragmentActivity;
import com.zcj.lib.MyListView;
import com.zcj.util.ActivityUtil;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class TeQingChaXunDetailActivity extends AnimFragmentActivity {

	
	protected static boolean isOk = false;
	PullToRefreshScrollView mPullRefreshScrollView;
	ScrollView mScrollView;
	private int tid;
	String title;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tq_chaxun_details);
		mPullRefreshScrollView = (PullToRefreshScrollView) findViewById(R.id.listScrollView);
		mScrollView = mPullRefreshScrollView.getRefreshableView();
		mPullRefreshScrollView.setOnRefreshListener(new OnRefreshListener<ScrollView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
				getZbData();
			}
		});
		getActionBar().setTitle("回复明细");
		tid = getIntent().getIntExtra("tid", 0);
		title = getIntent().getStringExtra("bt");
	    getZbData();
	}
	
	
	public void setZbView(TeQing[] tqs) {
		// TODO Auto-generated method stub
		TextView txtLdps = (TextView) findViewById(R.id.txt_tq_chaxun_detail_title);
		txtLdps.setText(title);
		if (tqs.length > 0)
		{
			MyListView lvInstr = (MyListView) findViewById(R.id.lv_tq_chaxun_detail_replys);
			ArrayList<HashMap<String, String >> listItems1 = new ArrayList<HashMap< String,String>>();
			for(int i=0;i<tqs.length;i++)
			{
				HashMap<String, String> map1 = new HashMap<String, String>();
				map1.put("name", tqs[i].getReceiverName());
				map1.put("dept", tqs[i].getReceiverDept());
				map1.put("content",("回复内容：") + (tqs[i].getReplyContent() != null?"N/A":tqs[i].getReplyContent()));
				map1.put("time", "回复时间：" + tqs[i].getReplyTime());
				map1.put("order","序号：" +  String.valueOf((i+1)));
			
				listItems1.add(map1);
			}
			SimpleAdapter sad1 =  new SimpleAdapter(this,listItems1,
		              R.layout.tq_chaxun_details_listitem,
		              new String[] { "name","dept","content","time","order"}, 
		                 
		              //ListItem的XML文件里面的两个TextView ID 
		              new int[] {R.id.txt_tq_chaxun_details_listitem_receiver_name,R.id.txt_tq_chaxun_details_listitem_receiver_dept,R.id.txt_tq_chaxun_details_listitem_reply_content,R.id.txt_tq_chaxun_details_listitem_reply_time,R.id.txt_tq_chaxun_details_listitem_xuhao}); 
			lvInstr.setAdapter(sad1);

		}
		txtLdps.setFocusable(true);
		txtLdps.setFocusableInTouchMode(true);
		txtLdps.requestFocus(); 
	}
	
	void getZbData()
	{

		ActivityUtil.getInstance().noticeSaying(this);
		new GetDataTask().execute();
	}
	



	private class GetDataTask extends AsyncTask<Void, Void, TeQing[]> {


		@Override
		protected TeQing[] doInBackground(Void... params) {
		
	
		
			
			return Transfer.tqyjCheckReplyDetails(tid, 1, 1000);
		}

		@Override
		protected void onPostExecute(TeQing[] result) {
			// Do some stuff here

			
			setZbView(result);
			ActivityUtil.getInstance().dismiss();
			mPullRefreshScrollView.onRefreshComplete();
		}
	}




	
	
}

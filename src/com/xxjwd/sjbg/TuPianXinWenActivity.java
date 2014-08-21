package com.xxjwd.sjbg;

import java.util.ArrayList;
import java.util.List;

import org.kobjects.base64.Base64;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.xxjwd.classes.XinWen;
import com.xxjwd.transfer.Transfer;


import com.zcj.adapter.ListViewAdapter_TuPianXinWen;
import com.zcj.lib.AnimFragmentActivity;
import com.zcj.lib.MyApp;
import com.zcj.util.ActivityUtil;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class TuPianXinWenActivity extends AnimFragmentActivity {

	private  PullToRefreshListView listView;  
	private ListViewAdapter_TuPianXinWen adapter;
	View actionbarLayout;
	MyApp app;
	private int xwlx;
	private String xwlb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tupianxinwen);
		xwlb = "图片新闻";
		xwlx = 27;
	    getActionBar().setTitle(xwlb);
	    app = (MyApp)getApplication();
		listView =(PullToRefreshListView)findViewById(R.id.tpxw_list_listview); 

            //获取id是list的ListView  
  //自动为id是list的ListView设置适配器   
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
		
				XinWen xw = (XinWen) adapter.getItem(position);
				app.set_tpxw( xw);
				Intent intent = new Intent();
				intent.setClass(TuPianXinWenActivity.this, TuPianXinWenDetailActivity.class);
				startActivity(intent);
		}
	});

		listView.setOnRefreshListener(new OnRefreshListener<ListView>() {
 
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
					DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

				// Update the LastUpdatedLabel
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);

				// Do work to refresh the list here.
			
		}
		});

		// set on bottom listener
		listView.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

			@Override
			public void onLastItemVisible() {
				doGetNewsList(false);
			}
		});
		
		
		doGetNewsList(true);
	}
	
	
	 void doGetNewsList (boolean isUp)
	 {
		 ActivityUtil.getInstance().noticeSaying(this);
		 new GetDataTask().execute(isUp);
	 }


	
	 private class GetDataTask extends AsyncTask<Boolean, Void, List<XinWen>> {

	        private boolean isUp;
	        List<Bitmap> bitmap;
	        String filePath,fileName;

	        @Override
	        protected List<XinWen> doInBackground(Boolean... params) {
	        	isUp = params[0];
	        	int ksxh;
	        	if (isUp)
	        	{
	        		ksxh = 1;
	        		
	        	}
	        	else
	        	{
	        		ksxh = adapter.getCount() + 1;

	        	}
	        	XinWen[] xws = Transfer.getXinWen(xwlx,ksxh , 2);
	        	if (xws == null)
	        	{
	        		bitmap = null;
	        		return null;
	        	}
	        	else
	        	{
	        		
	        		List<XinWen> items = new ArrayList<XinWen>();
	        		bitmap = new ArrayList<Bitmap>();
	        		for (int i = 0; i < xws.length; i++) {  
	      
	            		items.add(xws[i]); 
	            		filePath = xws[i].getPath();
	            		fileName = xws[i].getAttachFiles();
	            		int dotPos = fileName.indexOf(';');
	            		if ( dotPos >= 0) fileName = fileName.substring(0,dotPos);
	        			String fileFullPath = filePath + "/" + fileName;
	        			String file = Transfer.getRemoteFile(fileFullPath);
	        			byte[] buffer = Base64.decode(file);
	        			bitmap.add( BitmapFactory.decodeByteArray(buffer, 0, buffer.length));
	        		}
	        		return items;
	        	}
	        }

	        @Override
	        protected void onPostExecute(List<XinWen> xws) {
	        	
	        	if (isUp)
	        	{
	        		adapter = new ListViewAdapter_TuPianXinWen(TuPianXinWenActivity.this,xws,bitmap);
	        		listView.setAdapter(adapter); 
	        	}
	        	else
	        	{
	        		if (xws != null && xws.size()>0 )
	        		{
	        			for(int i=0;i<xws.size();i++)
	        			adapter.addItem(xws.get(i));  
	        		}
	        	}


	            adapter.notifyDataSetChanged();
	            listView.onRefreshComplete();
	            ActivityUtil.getInstance().dismiss();
	            
	        }
	    }




}

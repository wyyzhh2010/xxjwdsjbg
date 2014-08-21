package com.xxjwd.sjbg;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.kobjects.base64.Base64;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.xxjwd.classes.XinWen;
import com.xxjwd.transfer.Transfer;
import com.zcj.adapter.ListViewAdapter_XinWen;
import com.zcj.lib.AnimFragmentActivity;
import com.zcj.lib.MyApp;
import com.zcj.util.ActivityUtil;
import com.zcj.util.IntentUtil;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class XinWenActivity extends AnimFragmentActivity {

	private  PullToRefreshListView listView;  
	private ListViewAdapter_XinWen adapter;
	View actionbarLayout;
	MyApp app;
	private int xwlx;
	private String xwlb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xinwen);
		Intent intent = getIntent();
		xwlb = intent.getStringExtra("xwlb");
		xwlx = intent.getIntExtra("xwlx", 0);
	    getActionBar().setTitle(xwlb);
		
		listView =(PullToRefreshListView)findViewById(R.id.xw_list_listview); 

            //获取id是list的ListView  
  //自动为id是list的ListView设置适配器   
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			
			ListView listView = (ListView)parent;
			XinWen item_xw = (XinWen) listView.getItemAtPosition(position);
			
			String filePath = item_xw.getPath();
			String fileName = item_xw.getAttachFiles();
			if (fileName.indexOf(';') > 0) return;
			String[] str = new String[2];
			str[0] = filePath;
			str[1] = fileName;
			ActivityUtil.getInstance().noticeSaying(XinWenActivity.this);
			new getFileTask().execute(str);
        
			
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


	 private class getFileTask extends AsyncTask  <String, Void, String>
	  {
		 String filePath,fileName;
		  @Override
			protected String doInBackground(String... params) {
				// TODO Auto-generated method stub
			  filePath = params[0];
			  fileName = params[1];
			  String fileFullPath = filePath + "/" + fileName;
				
				return Transfer.getRemoteFile(fileFullPath);
			}
			
			@Override
		    protected void onPostExecute(String file) {
				String SdPath = Environment.getExternalStorageDirectory().toString();
					
				int dotPos = fileName.lastIndexOf('.');
				String fileExt = fileName.substring(dotPos+1 ,fileName.length());
					
		        byte[] buffer = Base64.decode(file);
		    	FileOutputStream outStream;
				try {
					outStream = new FileOutputStream(SdPath + "/a." + fileExt);
					outStream.flush();
					outStream.write(buffer); 
					outStream.close();
					} 
				catch (FileNotFoundException e) {
					e.printStackTrace();
					} 
				catch (IOException e) {
					e.printStackTrace();
					} 
			     Intent intent= IntentUtil.getFileIntent(SdPath + "/a." + fileExt, fileExt);
			     ActivityUtil.getInstance().dismiss();
		         startActivity(intent);
			    
			}
	  }

	 private class GetDataTask extends AsyncTask<Boolean, Void, List<XinWen>> {

	        private boolean isUp;

	       

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
	        	XinWen[] xws = Transfer.getXinWen(xwlx,ksxh , 20);
	        	if (xws == null)
	        	{
	        		return null;
	        	}
	        	else
	        	{
	        		List<XinWen> items = new ArrayList<XinWen>();
	        		for (int i = 0; i < xws.length; i++) {  
	      
	            		items.add(xws[i]); 
	        		}
	        		return items;
	        	}
	        }

	        @Override
	        protected void onPostExecute(List<XinWen> xws) {
	        	
	        	if (isUp)
	        	{
	        		adapter = new ListViewAdapter_XinWen(XinWenActivity.this,xws);
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

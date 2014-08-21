package com.xxjwd.sjbg;


import java.util.ArrayList;  
import java.util.List;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;  
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;  
import android.widget.AbsListView;  
import android.widget.AbsListView.OnScrollListener;  
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;  
import android.widget.Spinner;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.xxjwd.classes.TeQing;
import com.xxjwd.sjbg.R;
import com.xxjwd.transfer.Transfer;
import com.zcj.adapter.ListViewAdapter_TeQing;
import com.zcj.lib.AnimFragmentActivity;
import com.zcj.lib.MyApp;
import com.zcj.util.ActivityUtil;


public class TeQingListActivity extends AnimFragmentActivity implements OnScrollListener {  
	
	private  PullToRefreshListView listView;  
	private ListViewAdapter_TeQing adapter;
	private Spinner mActionbarSpinner;  
	private ActionBar actionBar;
	View actionbarLayout;
	MyApp app;
	final int COUNT_OF_FEACH=20;
	@Override  
	public void onCreate(Bundle savedInstanceState) {  
		super.onCreate(savedInstanceState);  
		setContentView(R.layout.pulltorefresh_listview);  
        app = (MyApp)getApplication();  
        actionBar=getActionBar();
		//使自定义的普通View能在title栏显示, actionBar.setCustomView能起作用.  
        
		actionBar.setTitle("");
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowCustomEnabled(true);  
		//下拉列表  
		actionbarLayout = LayoutInflater.from(this).inflate(R.layout.gw_actionbar_list, null);  
        mActionbarSpinner = (Spinner) actionbarLayout.findViewById(R.id.action_bar_spinner);
        initSpinner();  
		int titleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android"); 
		TextView title = (TextView)findViewById(titleId); 
		title.setTextColor(this.getResources().getColor(R.color.actionbar_title_color));
        
        listView =(PullToRefreshListView)findViewById(R.id.gw_list_listview); 

                   //获取id是list的ListView  
         //自动为id是list的ListView设置适配器   
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				int pos = mActionbarSpinner.getSelectedItemPosition();
				int type;
				switch(pos)
			    {
			    	case 0://level 1,2查询;level 3接收
			    		if (app.get_tqLevel() == 3)
			    		{
			    			type = 1;
			    		}
			    		else
			    		{
			    			type = 2;
			    		}
			    		break;
			        case 1://level1接收，level2,3无此种情况
			        	type = 1;
			        	break;
			        default:
			        	type=-1;
			        	break;
			    }
				ListView listView = (ListView)parent;
				TeQing item_tq = (TeQing) listView.getItemAtPosition(position);
				if (item_tq.getId() == 0) return;
				Intent intent=new Intent();
				intent.putExtra("bt", item_tq.getTitle());
				intent.putExtra("nr", item_tq.getContent());
				intent.putExtra("tid", item_tq.getId());
				if (type == -1 ) return;
				else if (type == 1)//接收
				{
					
					intent.setClass(TeQingListActivity.this, TeQingJieShouActivity.class);
					startActivity(intent);
				}
				else
				{
					intent.setClass(TeQingListActivity.this, TeQingChaXunDetailActivity.class);
				}
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
				doGetData(true);
			}
		});

        // set on bottom listener
        listView.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

			@Override
			public void onLastItemVisible() {
				doGetData(false);
			}
		});
     
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
		doGetData(true);
	}
	
	void doGetData(boolean isUp)
	{
		ActivityUtil.getInstance().noticeSaying(this);
		Bundle b = new Bundle();
		b.putBoolean("isUp", isUp);
		if (mActionbarSpinner == null)
		b.putInt("pos", 0);
		else
		b.putInt("pos",  mActionbarSpinner.getSelectedItemPosition());
		new GetDataTask().execute(b);
	}
	
    private void initSpinner() {
		// TODO Auto-generated method stub
    	
        
        String[] mItems;
         
        
        //在Bar上显示定制view  
        switch (app.get_tqLevel())
        {
        	case 1://领导查询
        		
        		mItems= new String[]{"特情查询","特情接收"};
        		break;
        	case 2://信息发布人
        		mItems= new String[]{"特情查询"};
        		break;
        	case 3://信息接收人
        		mItems= new String[]{"特情接收"};
        		break;
        	default:
        		mItems= new String[]{"特情查询"};
        		break;
        }
        ArrayAdapter<String> spAdapter = new ArrayAdapter<String>  
        (this,R.layout.gw_actionbar_listitem,mItems);  
       mActionbarSpinner.setAdapter(spAdapter);  
       //事件监听  
       mActionbarSpinner.setOnItemSelectedListener(new SpinnerItemSelectedListener());  
         
        actionBar.setCustomView(actionbarLayout);
	}

    private class SpinnerItemSelectedListener implements OnItemSelectedListener {  
        
        @Override  
        public void onItemSelected(AdapterView<?> arg0, View view, int position,long arg3) {  
        	doGetData(true);
        }  
          
        @Override  
        public void onNothingSelected(AdapterView<?> arg0) {}  
    }  

	
	

	 @Override 
	    public boolean onOptionsItemSelected(MenuItem item) {  
	        switch(item.getItemId()){ 

	        case R.id.gw_list_search:
	            //openSearch();
	            
	            return true; 
	        case R.id.gw_list_refresh:
	        	doGetData(true);
	        	return true;
	        default: 
	            break; 
	        } 
	        return super.onOptionsItemSelected(item); 
	    } 
	 
	 @Override
	 public boolean onCreateOptionsMenu(Menu menu) {
	     // Inflate the menu items for use in the action bar
	     MenuInflater inflater = getMenuInflater();
	     inflater.inflate(R.menu.gw_list_menu, menu);
	     return super.onCreateOptionsMenu(menu);
	 }
	 

	
    private class GetDataTask extends AsyncTask<Bundle, Void, List<TeQing>> {

    	boolean isUp;
    	int type;
        @Override
        protected List<TeQing> doInBackground(Bundle... params) {
        	//do nothing
        	int pos = params[0].getInt("pos");
        	boolean isUp = params[0].getBoolean("isUp");
        	this.isUp = isUp;
        	int ksxh,uid;
        	uid = app.getUser().getUid();
        	if (isUp) ksxh = 1;
        	else ksxh = adapter.getCount() + 1;
        	TeQing[] tqs;
    		switch(pos)
        	{
        	case 0://level 1,2查询;level 3接收
        		if (app.get_tqLevel() == 3)
        		{
        			tqs = Transfer.getTeQingByWorkno(uid, ksxh, COUNT_OF_FEACH);
        			type = 1;
        		}
        		else
        		{
        			tqs = Transfer.tqyjCheckReply(uid, ksxh, COUNT_OF_FEACH);
        			type = 2;
        		}
        		break;
        	case 1://level1接收，level2,3无此种情况
        		tqs = Transfer.getTeQingByWorkno(uid, ksxh, COUNT_OF_FEACH);
        		type = 1;
        		break;
    
        	default:
        		tqs=null;
        		break;
        	}
        	
        	
        	List<TeQing> tqList = new ArrayList<TeQing>() ;
        	if (tqs == null)
        	{

        	}
        	else
        	{
        		for(int i=0;i<tqs.length;i++)
        		{
        			TeQing tq = tqs[i];
        			tqList.add(tq);
        		}
        		
        	}
        	return tqList;
        }

        @Override
        protected void onPostExecute(List<TeQing> tqs) {
        	
            if (isUp)
            {
            	if (tqs != null)
            	{
            		adapter = new ListViewAdapter_TeQing( TeQingListActivity.this,tqs,type);
            		listView.setAdapter(adapter);
            	}
            }
            else
            {
            	for(int i=0;i<tqs.size();i++)
            	{
            		adapter.addItem(tqs.get(i));
            	}
            }
            adapter.notifyDataSetChanged();
            listView.onRefreshComplete();
            ActivityUtil.getInstance().dismiss();
        }
    }

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		
	}


}  


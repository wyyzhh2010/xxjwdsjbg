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
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Spinner;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.xxjwd.classes.GongWen;
import com.xxjwd.sjbg.R;
import com.xxjwd.transfer.Transfer;
import com.zcj.adapter.ListViewAdapter_GongWen;
import com.zcj.lib.AnimFragmentActivity;
import com.zcj.lib.MyApp;
import com.zcj.util.ActivityUtil;


public class GongWenListActivity extends AnimFragmentActivity implements OnScrollListener {  
	
	private  PullToRefreshListView listView;  
	private ListViewAdapter_GongWen adapter;
	private Spinner mActionbarSpinner;  
	private ActionBar actionBar;
	View actionbarLayout;
	MyApp app;
	String filter="";
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
				
    			ListView listView = (ListView)parent;
    			GongWen item_gw = (GongWen) listView.getItemAtPosition(position);
    			if (item_gw.getNumber().equals("00-00-00")) return;
    	     	Intent intent=new Intent();
    	    	intent.putExtra("wh", item_gw.getNumber());
                intent.setClass(GongWenListActivity.this, GongWenDetailActivity.class);
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
    	
        
        String[] mItems = new String[]{"未签文件","所有局文","所有段文"}; 
        ArrayAdapter<String> spAdapter = new ArrayAdapter<String>  
         (this,R.layout.gw_actionbar_listitem,mItems);  
        mActionbarSpinner.setAdapter(spAdapter);  
        //事件监听  
        mActionbarSpinner.setOnItemSelectedListener(new SpinnerItemSelectedListener());  
          
        //在Bar上显示定制view  
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
	     MenuItem searchItem = menu.findItem(R.id.gw_list_search);
			SearchView sv = (SearchView) searchItem.getActionView();
			sv.setQueryHint("可按文号或标题查询");
			sv.setIconifiedByDefault(true);
			sv.setSubmitButtonEnabled(true);
			sv.setOnQueryTextListener(oQueryTextListener);
	     return super.onCreateOptionsMenu(menu);
	 }
	 
	 OnQueryTextListener oQueryTextListener = new OnQueryTextListener() {
			
			@Override
			public boolean onQueryTextSubmit(String query) {
				//Toast.makeText(GongWenListActivity.this, query, Toast.LENGTH_LONG).show();
				filter = query;
				doGetData(true);
				return true;
			}
			
			@Override
			public boolean onQueryTextChange(String newText) {
				return false;
			}
		};
	
    private class GetDataTask extends AsyncTask<Bundle, Void, List<GongWen>> {

    	boolean isUp;
        @Override
        protected List<GongWen> doInBackground(Bundle... params) {
        	//do nothing
        	int pos = params[0].getInt("pos");
        	boolean isUp = params[0].getBoolean("isUp");
        	this.isUp = isUp;
        	int lblx,gwlx,dwlx,ksxh,uid;
        	uid = app.getUser().getUid();
    		switch(pos)
        	{
        	case 0://未签文件
        		lblx = 0;
        		gwlx = 2;
        		dwlx = 2;
        		break;
        	case 1://所有局文
        		lblx = 1;
        		gwlx = 2;
        		dwlx = 1;
        		break;
        	case 2:
        		lblx = 1;
        		gwlx = 2;
        		dwlx = 0;
        		break;
        	default:
        			lblx = 0;
            		gwlx = 2;
            		dwlx = 2;
        			break;
        	}
        	
        	if (isUp) ksxh = 1;
        	else ksxh = adapter.getCount() + 1;
        	
        	GongWen[] gws = Transfer.getGwlb(uid, lblx, gwlx, dwlx,filter, ksxh, COUNT_OF_FEACH);
        	filter = "";
        	List<GongWen> gwList = new ArrayList<GongWen>() ;
        	if (gws == null)
        	{

        	}
        	else
        	{
        		for(int i=0;i<gws.length;i++)
        		{
        			GongWen gw = gws[i];
        			gwList.add(gw);
        		}
        		
        	}
        	return gwList;
        }

        @Override
        protected void onPostExecute(List<GongWen> gws) {
        	
            if (isUp)
            {
            	if (gws != null)
            	{
            		adapter = new ListViewAdapter_GongWen( GongWenListActivity.this,gws);
            		listView.setAdapter(adapter);
            	}
            }
            else
            {
            	for(int i=0;i<gws.size();i++)
            	{
            		adapter.addItem(gws.get(i));
            	}
            }
            ActivityUtil.getInstance().dismiss();
            adapter.notifyDataSetChanged();
            listView.onRefreshComplete();
            
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


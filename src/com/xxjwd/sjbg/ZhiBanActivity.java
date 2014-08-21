package com.xxjwd.sjbg;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.xxjwd.classes.ZbPerson;
import com.xxjwd.transfer.Transfer;
import com.zcj.lib.AnimFragmentActivity;
import com.zcj.lib.MyListView;
import com.zcj.util.ActivityUtil;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ZhiBanActivity extends AnimFragmentActivity {

	
	protected static boolean isOk = false;
	PullToRefreshScrollView mPullRefreshScrollView;
	ScrollView mScrollView;
	private static int year;
	private int monthOfYear;
	private int dayOfMonth;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zhiban);
		mPullRefreshScrollView = (PullToRefreshScrollView) findViewById(R.id.listScrollView);
		mScrollView = mPullRefreshScrollView.getRefreshableView();
		mPullRefreshScrollView.setOnRefreshListener(new OnRefreshListener<ScrollView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
				getZbData();
			}
		});
		
		Calendar c = Calendar.getInstance();

	    int hourOfDate = c.get(Calendar.HOUR_OF_DAY);
		//SimpleDateFormat   sDateFormat   =   new   SimpleDateFormat("yyyy-MM-dd");
		if (hourOfDate >= 0 && hourOfDate < 8) 
		c.add(Calendar.DATE, -1);
	    year = c.get(Calendar.YEAR);
	    monthOfYear = c.get(Calendar.MONTH) + 1;
	    dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
	      getZbData(year,monthOfYear,dayOfMonth);
	}
	
	
	public void setZbView(String ldps, ZbPerson[] persons) {
		// TODO Auto-generated method stub
		TextView txtLdps = (TextView) findViewById(R.id.txtLdps);
		txtLdps.setText(ldps);
		if (persons.length > 0)
		{
			MyListView lvInstr = (MyListView) findViewById(R.id.lvZbPerson);
			ArrayList<HashMap<String, String >> listItems1 = new ArrayList<HashMap< String,String>>();
			for(int i=0;i<persons.length;i++)
			{
				HashMap<String, String> map1 = new HashMap<String, String>();
				map1.put("workno", persons[i].getWorkno());
				map1.put("name", persons[i].getName());
				map1.put("dept", persons[i].getDept());
				map1.put("daynight", persons[i].getDayNight());
				map1.put("mobile", persons[i].getMobile());
				map1.put("phone", persons[i].getPhone());
				map1.put("signtime","签到时间：" + persons[i].getSignTime());
				listItems1.add(map1);
			}
			SimpleAdapter sad1 =  new SimpleAdapter(this,listItems1,
		              R.layout.zhiban_listitem,
		              new String[] {"workno", "name","dept","daynight","mobile","phone","signtime"}, 
		                 
		              //ListItem的XML文件里面的两个TextView ID 
		              new int[] {R.id.txtWorkno,R.id.txtName,R.id.txtDept,R.id.txtDayNight,R.id.txtMobile,R.id.txtPhone,R.id.txtSign}); 
			lvInstr.setAdapter(sad1);

		}
		txtLdps.setFocusable(true);
		txtLdps.setFocusableInTouchMode(true);
		txtLdps.requestFocus(); 
	}
	
	void getZbData(int year,int month,int day)
	{
		String zbrq = year + "-" + month + "-" + day;
		getActionBar().setTitle("值班查询：" + year + "年" + month + "月" + day + "日");
		ActivityUtil.getInstance().noticeSaying(this);
		new GetDataTask().execute(zbrq);
	}
	
	void getZbData()
	{
		getZbData(year,monthOfYear,dayOfMonth);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.zhi_ban_menu, menu);
		return true;
	}

	@Override	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_show_date_dialog) {
			

			DatePickerDialog picker = new DatePickerDialog(this, R.style.dialog, new DatePickerDialog.OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker v, int year, int month, int day) {
					if (isOk)
					{
						isOk = false;
						getZbData(year,month+1,day);
						ZhiBanActivity.year = year;
						monthOfYear = month + 1;
						dayOfMonth = day;
					}
				}},year, monthOfYear-1, dayOfMonth);
				picker.setCancelable(true);
				picker.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
			        new DialogInterface.OnClickListener() {
			            @Override
			            public void onClick(DialogInterface dialog, int which) {
			             isOk = true; 
			            }
			        });
			    picker.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", 
			        new DialogInterface.OnClickListener() {
			            @Override
			            public void onClick(DialogInterface dialog, int which) {
			            	isOk = false;
			            }
			        });
				
			picker.show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	


	private class GetDataTask extends AsyncTask<String, Void, Void> {

		String ldps;
		ZbPerson[] persons;
		@Override
		protected Void doInBackground(String... params) {
		
			String date = params[0];
			ldps = Transfer.getZhiBanPiShi(date);
			persons = Transfer.getZbPerson(date, 2);//0是白天,1是晚上,2是全天
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// Do some stuff here

			ActivityUtil.getInstance().dismiss();
			setZbView(ldps,persons);
			mPullRefreshScrollView.onRefreshComplete();
		}
	}




	
	
}

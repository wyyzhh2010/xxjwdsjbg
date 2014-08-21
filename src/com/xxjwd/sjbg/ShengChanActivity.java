package com.xxjwd.sjbg;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import com.xxjwd.classes.JianBao;
import com.xxjwd.transfer.Transfer;
import com.zcj.lib.AnimFragment;
import com.zcj.lib.AnimFragmentActivity;
import com.zcj.lib.PagerSlidingTabStrip;
import com.zcj.util.ActivityUtil;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;


public class ShengChanActivity extends AnimFragmentActivity  {

	private final Handler handler = new Handler();
	protected static boolean isOk = false;
	private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	private SectionsPagerAdapter adapter;
	
	private Drawable oldBackground = null;
	private int currentColor = 0xFF3F9FE0;
	private int year;
	private int monthOfYear;
	private int dayOfMonth;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sheng_chan);
		

		Calendar c = Calendar.getInstance();

	    int hourOfDate = c.get(Calendar.HOUR_OF_DAY);

		if (hourOfDate >= 0 && hourOfDate < 8) 
		c.add(Calendar.DATE, -1);
	    year = c.get(Calendar.YEAR);
	    monthOfYear = c.get(Calendar.MONTH) + 1;
	    dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
	     getJianBao( year, monthOfYear ,dayOfMonth - 1);

		
		
	}
	
	
	public void getJianBao(int year,int month,int day)
	{
		String date = year + "-" + month + "-" + day;
		getActionBar().setTitle("生产简报：" + year + "年" + month + "月" + day + "日");
		ActivityUtil.getInstance().noticeSaying(this);
		new getAllData().execute(date);
	}
	
	private void changeColor(int newColor) {

		tabs.setIndicatorColor(newColor);

		// change ActionBar color just if an ActionBar is available
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

			Drawable colorDrawable = new ColorDrawable(newColor);
			Drawable bottomDrawable = getResources().getDrawable(R.drawable.actionbar_bottom);
			LayerDrawable ld = new LayerDrawable(new Drawable[] { colorDrawable, bottomDrawable });
			
			if (oldBackground == null) {

				if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
					ld.setCallback(drawableCallback);
				} else {
					getActionBar().setBackgroundDrawable(ld);
				}

			} else {

				TransitionDrawable td = new TransitionDrawable(new Drawable[] { oldBackground, ld });

				// workaround for broken ActionBarContainer drawable handling on
				// pre-API 17 builds
				// https://github.com/android/platform_frameworks_base/commit/a7cc06d82e45918c37429a59b14545c6a57db4e4
				if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
					td.setCallback(drawableCallback);
				} else {
					getActionBar().setBackgroundDrawable(td);
				}

				td.startTransition(200);

			}

			oldBackground = ld;

			// http://stackoverflow.com/questions/11002691/actionbar-setbackgrounddrawable-nulling-background-from-thread-handler
			getActionBar().setDisplayShowTitleEnabled(false);
			getActionBar().setDisplayShowTitleEnabled(true);

		}

		currentColor = newColor;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sheng_chan_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_show_date_dialog) {
			

			DatePickerDialog picker = new DatePickerDialog(this, R.style.dialog, new DatePickerDialog.OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker v, int year, int month, int day) {
					if (isOk)
					{
						isOk = false;
						
						getJianBao(year,month+1,day);
					
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

	private Drawable.Callback drawableCallback = new Drawable.Callback() {
		@Override
		public void invalidateDrawable(Drawable who) {
			getActionBar().setBackgroundDrawable(who);
		}

		@Override
		public void scheduleDrawable(Drawable who, Runnable what, long when) {
			handler.postAtTime(what, when);
		}

		@Override
		public void unscheduleDrawable(Drawable who, Runnable what) {
			handler.removeCallbacks(what);
		}
	};


	public static List<List<JianBao>> jbs_list_list;
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		String[] _titles;

		public SectionsPagerAdapter(
				FragmentManager fm,
				String[] bms) {
			// TODO Auto-generated constructor stub
			super(fm);
			this._titles = bms;

		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class
			// below).
		
			return PlaceholderFragment.newInstance(position);
			}
		

		@Override
		public int getCount() {
			
			return _titles.length;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			if (position < _titles.length)
			return _titles[position];
			else return null;
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends AnimFragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";
		
		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int pos) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, pos);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			int  pos= getArguments().getInt(ARG_SECTION_NUMBER);
			View rootView = inflater.inflate(
					R.layout.fragment_activity_sheng_chan, container, false);
			ListView lv = (ListView) rootView
					.findViewById(R.id.scjb_list_view);
			
			ArrayList<HashMap<String, String >> listItems1 = new ArrayList<HashMap< String,String>>();
			List<JianBao> jbs = jbs_list_list.get(pos);
			if (jbs != null && jbs.size() > 0)
			{
			
			for(int i=0;i<jbs.size();i++)
			{
				HashMap<String, String> map1 = new HashMap<String, String>();
				map1.put("xm", jbs.get(i).getXmmc());
				map1.put("nr", jbs.get(i).getXmnr());
				listItems1.add(map1);
			}
			
			}
			else
			{
				HashMap<String, String> map1 = new HashMap<String, String>();
				map1.put("xm", "未找到该部门信息");
				map1.put("nr", "N/A");
				listItems1.add(map1);
			}
			SimpleAdapter sad1 =  new SimpleAdapter(this.getActivity(), listItems1,
		              R.layout.jianbao_listitem,
		              new String[] {"xm", "nr",}, 
		              new int[] {R.id.txt_jb_xm,R.id.txt_jb_nr}); 
			lv.setAdapter(sad1);
			return rootView;
		}
	}

	
	class getAllData extends AsyncTask <String ,Void ,Void>
	{
		String date;
		String[] bms;
		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			date = params[0];
			bms=Transfer.getJianBaoBuMen();
			JianBao[] jbs = Transfer.getAllJianBao(date);
			jbs_list_list = new ArrayList<List<JianBao>>();
	
			String preBm="";
			int preBmPos=1;
			if (jbs == null || jbs.length == 0)
			{
				for(int i=0;i<bms.length;i++)
				{
					jbs_list_list.add(null);
				}
			}
			else
			{
			List<JianBao> jb_list = new ArrayList<JianBao>();
			for(int i=0;i<jbs.length;i++)
			{
				
				String bm = jbs[i].getBm();
				int cBmPos = jbs[i].getBmOrder();

				if (i==0)//i=0初始化
				{
					if (cBmPos > preBmPos)
					{
						for(int j=preBmPos;j<cBmPos;j++)
						{
							jbs_list_list.add(null);
							
						}
					}
					jb_list.add(jbs[i]);
					preBm = bm;
				}
				else if (bm.equals(preBm)) //bm=preBm,未换部门
				{
					jb_list.add(jbs[i]);
					preBm = bm;
					preBmPos = cBmPos;
				}
				else //换部门了
				{
					jbs_list_list.add(jb_list);
					if (cBmPos > preBmPos+1)
					{
						for(int j=preBmPos+1;j<cBmPos;j++)
						{
							jbs_list_list.add(null);
							
						}
					}
					jb_list = new ArrayList<JianBao>();
					jb_list .add(jbs[i]);
					preBmPos = cBmPos;
					preBm = bm;
				}
				if(i==jbs.length-1)//最后一个了
				{
					jbs_list_list.add(jb_list);
					preBmPos = cBmPos;
				}
			}
			while (bms.length > preBmPos) //判断后面是否还有没填的部门
			{
				jbs_list_list.add(null);
				preBmPos++;
			}
			}
			return null;
		}
		@Override
		protected void onPostExecute(Void v)
		{
			
			tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs_scjb);
			pager = (ViewPager) findViewById(R.id.pager_scjb);
			adapter = new SectionsPagerAdapter(getSupportFragmentManager(),bms);

			pager.setAdapter(adapter);

			final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
					.getDisplayMetrics());
			pager.setPageMargin(pageMargin);

			tabs.setViewPager(pager);

			changeColor(currentColor);
			ActivityUtil.getInstance().dismiss();
		}
		
	}
}

package com.zcj.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zcj.fragment.Menu9Fragment;

public class MyPagerAdapter extends FragmentPagerAdapter {

	String[] _titles;
	boolean checkRecent ()
	{
		return false;
	}
	public MyPagerAdapter(FragmentManager fm) {
		super(fm);
		if (checkRecent() == true)
		{
			_titles =  new String[] { "最近访问", "移动办公", "机车运用", "机车检修", "安全管理" ,"综合服务","个人信息"};
		}
		else
			
		{
			_titles =  new String[] { "移动办公", "机车运用", "机车检修", "安全管理", "综合服务","个人信息"};
		}
	}
	public MyPagerAdapter(FragmentManager fm ,String[] titles)
	{
		super(fm);
		this._titles = titles;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return _titles[position];
	}

	@Override
	public int getCount() {
		return _titles.length;
	}

	@Override
	public Fragment getItem(int position) {
		return Menu9Fragment.newInstance(position,checkRecent());
	}

}
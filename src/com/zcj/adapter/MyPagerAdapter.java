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
			_titles =  new String[] { "�������", "�ƶ��칫", "��������", "��������", "��ȫ����" ,"�ۺϷ���","������Ϣ"};
		}
		else
			
		{
			_titles =  new String[] { "�ƶ��칫", "��������", "��������", "��ȫ����", "�ۺϷ���","������Ϣ"};
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
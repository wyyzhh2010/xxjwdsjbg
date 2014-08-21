package com.zcj.lib;

import com.xxjwd.classes.User;
import com.xxjwd.classes.XinWen;

import android.app.Application;

public class MyApp extends Application {
	
	private User _user;
	private int _tqLevel;
	private int _gwLevel;
	private XinWen _tpxw;
	public XinWen get_tpxw() {
		return _tpxw;
	}

	public void set_tpxw(XinWen _tpxw) {
		this._tpxw = _tpxw;
	}

	public int get_tqLevel() {
		return _tqLevel;
	}

	public void set_tqLevel(int _tqLevel) {
		this._tqLevel = _tqLevel;
	}

	public void setUser(User value)
	{
		this._user = value;
	}

	public User getUser()
	{
		return this._user;
	}

	public int get_gwLevel() {
		return _gwLevel;
	}

	public void set_gwLevel(int _gwLevel) {
		this._gwLevel = _gwLevel;
	}
}

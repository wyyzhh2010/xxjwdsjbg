package com.xxjwd.sjbg;



import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.xxjwd.sjbg.R;
import com.xxjwd.transfer.Transfer;
import com.zcj.util.AutoUpdateUtil;
import com.zcj.util.NetworkUtil;


public class AppStartActivity extends Activity {


	
	public void getp()
	{
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria(); 
		// 获得最好的定位效果 
		criteria.setAccuracy(Criteria.ACCURACY_FINE); 
		criteria.setAltitudeRequired(false); 
		criteria.setBearingRequired(false); 
		criteria.setCostAllowed(false); 
		// 使用省电模式 
		criteria.setPowerRequirement(Criteria.POWER_HIGH); 
		
		// 获得当前的位置提供者 
		//String provider = LocationManager.GPS_PROVIDER; //locationManager.getBestProvider(criteria, true); 
		// 获得当前的位置 
		//Location location = locationManager.getLastKnownLocation(provider); 
		// 获得当前位置的纬度 
		//Double latitude = location.getLatitude() * 1E6; 
		// 获得当前位置的经度 
		//Double longitude = location.getLongitude() * 1E6; 
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_app_start);
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				if(NetworkUtil.getNetworkState(AppStartActivity.this) != NetworkUtil.NONE) 
				{
					//PostionInfo.getPosition(AppStartActivity.this);
					//getp();
					new AutoUpdateUtil(AppStartActivity.this).doCheckUpdate();
					//Toast.makeText(AppStartActivity.this,  Transfer.isIn(),Toast.LENGTH_SHORT).show();
					
				} 
				else
				{
					Toast.makeText(AppStartActivity.this, "没有网络",
						     Toast.LENGTH_SHORT).show();
				
				AppStartActivity.this.finish();
				}
			}
			}, 2000);
		}

	
	
	
}

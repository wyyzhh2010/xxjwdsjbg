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
		// �����õĶ�λЧ�� 
		criteria.setAccuracy(Criteria.ACCURACY_FINE); 
		criteria.setAltitudeRequired(false); 
		criteria.setBearingRequired(false); 
		criteria.setCostAllowed(false); 
		// ʹ��ʡ��ģʽ 
		criteria.setPowerRequirement(Criteria.POWER_HIGH); 
		
		// ��õ�ǰ��λ���ṩ�� 
		//String provider = LocationManager.GPS_PROVIDER; //locationManager.getBestProvider(criteria, true); 
		// ��õ�ǰ��λ�� 
		//Location location = locationManager.getLastKnownLocation(provider); 
		// ��õ�ǰλ�õ�γ�� 
		//Double latitude = location.getLatitude() * 1E6; 
		// ��õ�ǰλ�õľ��� 
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
					Toast.makeText(AppStartActivity.this, "û������",
						     Toast.LENGTH_SHORT).show();
				
				AppStartActivity.this.finish();
				}
			}
			}, 2000);
		}

	
	
	
}

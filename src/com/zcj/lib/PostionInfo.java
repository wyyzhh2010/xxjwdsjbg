package com.zcj.lib;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class PostionInfo {
	private static double latitude=0.0;
	private static double longitude =0.0;
	
	

	public static void getPosition(Context context)
	{
	LocationManager locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
			if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
				Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				if(location != null){
					latitude = location.getLatitude();
					longitude = location.getLongitude();
					}
			}else{
				LocationListener locationListener = new LocationListener() {
					

					// Provider��enableʱ�����˺���������GPS����
					@Override
					public void onProviderEnabled(String provider) {
						
					}
					
					// Provider��disableʱ�����˺���������GPS���ر� 
					@Override
					public void onProviderDisabled(String provider) {
						
					}
					
					//������ı�ʱ�����˺��������Provider������ͬ�����꣬���Ͳ��ᱻ���� 
					@Override
					public void onLocationChanged(Location location) {
						if (location != null) {   
							Log.e("Map", "Location changed : Lat: "  
							+ location.getLatitude() + " Lng: "  
							+ location.getLongitude());   
						}
					}

					@Override
					public void onStatusChanged(String provider, int status,
							Bundle extras) {
						// TODO Auto-generated method stub
						
					}
				};
				locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000, 0,locationListener);   
				Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);   
				if(location != null){   
					latitude = location.getLatitude(); //����   
					longitude = location.getLongitude(); //γ��
				}
				else 
				{
					latitude= 0;
					longitude = 0;
				}
			}
			//return new Point (latitude,longitude);
	}
}

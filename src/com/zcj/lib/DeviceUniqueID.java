package com.zcj.lib;

import com.zcj.util.SecurityUtil;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

public class DeviceUniqueID {
	
	public static String getUniqueID(Context context)
	{
		String id;
		id = getIMEI(context);
		id += getBuildID();
		id += getDeviceID(context);
		id += getWlanMac(context);
		id += getBlueToothMac(context);
		id = SecurityUtil.MD5(id);
		return id;
	}
	

	static private String getBuildID()
	{
		String m_szDevIDShort = "35" + //we make this look like a valid IMEI 

		Build.BOARD.length()%10 + 
		Build.BRAND.length()%10 + 
		Build.CPU_ABI.length()%10 + 
		Build.DEVICE.length()%10 + 
		Build.DISPLAY.length()%10 + 
		Build.HOST.length()%10 + 
		Build.ID.length()%10 + 
		Build.MANUFACTURER.length()%10 + 
		Build.MODEL.length()%10 + 
		Build.PRODUCT.length()%10 + 
		Build.TAGS.length()%10 + 
		Build.TYPE.length()%10 + 
		Build.USER.length()%10 ; //13 digits
		return m_szDevIDShort;
	}

	static private String getIMEI(Context context)
	{
		TelephonyManager TelephonyMgr = (TelephonyManager)context.getSystemService(android.content.Context.TELEPHONY_SERVICE); 
		String szImei = TelephonyMgr.getDeviceId();
		return szImei;
	}
	
	static private String getDeviceID(Context context)
	{
		String m_szAndroidID = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
		return m_szAndroidID;
	}
	
	static private String getWlanMac(Context context)
	{
		WifiManager wm = (WifiManager)context.getSystemService(Context.WIFI_SERVICE); 
		String m_szWLANMAC = wm.getConnectionInfo().getMacAddress();
		return m_szWLANMAC;
	}
	
	static private String getBlueToothMac(Context context)
	{
		BluetoothAdapter m_BluetoothAdapter = null; // Local Bluetooth adapter      
		m_BluetoothAdapter = BluetoothAdapter.getDefaultAdapter();      
		String m_szBTMAC = m_BluetoothAdapter.getAddress();
		return m_szBTMAC;
	}
}

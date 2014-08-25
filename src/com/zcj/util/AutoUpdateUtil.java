package com.zcj.util;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.xxjwd.classes.ApkInfo;
import com.xxjwd.sjbg.LoginActivity;
import com.xxjwd.sjbg.R;
import com.xxjwd.transfer.Transfer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
public class AutoUpdateUtil {
	private final static int DOWN_ERROR = 1;
	private final static int GET_UNDATAINFO_ERROR = 2;
	private final static int INSTALL_ERROR = 3;
	static AutoUpdateUtil instance;
	private ProgressDialog progressDialog; // 进度条对话框
	private File file;
	private Context mContext;
	private static final String TAG = "com.xxjwd.sjbg.autoupdate";
	public AutoUpdateUtil(Context context)
	{
		this.mContext = context;
		
	}
	
	public void doCheckUpdate()
	{
		new checkUpdateTask().execute(getVerCode(mContext));
		//showNextActivity();
	}
	
	public static AutoUpdateUtil newInstance(Context context)
	{
		if(instance == null){
			instance = new AutoUpdateUtil(context);
		}
		return instance;//instance;
	}

	public static int getVerCode(Context context) {  
        int verCode = -1;  
        try {  
            verCode = context.getPackageManager().getPackageInfo(  
                    "com.xxjwd.sjbg", 0).versionCode;  
        } catch (NameNotFoundException e) {  
            Log.e(TAG, e.getMessage());  
        }  
        return verCode;  
    }  
     
    public static String getVerName(Context context) {  
        String verName = "";  
        try {  
            verName = context.getPackageManager().getPackageInfo(  
                    "com.xxjwd.sjbg", 0).versionName;  
        } catch (NameNotFoundException e) {  
            Log.e(TAG, e.getMessage());  
        }  
        return verName;        
    }  
 
  //服务器端获取APK信息 
    public File getFileFromServer(String path, ProgressDialog pd) {
    // 如果相等的话表示当前的sdcard挂载在手机上并且是可用的
    try {
    if (Environment.getExternalStorageState().equals(
    Environment.MEDIA_MOUNTED)) {
    URL url = new URL(path);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    // 自带获取到文件的大小
    int Max = conn.getContentLength();
    pd.setMax(Max);
    InputStream is = conn.getInputStream();
    // 新建一个文件路径以及保存的软件名称
    File file = new File(Environment.getExternalStorageDirectory(),"OnlineDesignForPAD.apk");
    FileOutputStream fos = new FileOutputStream(file);
    BufferedInputStream bis = new BufferedInputStream(is);
    byte[] buffer = new byte[2048];
    int len;
    int total = 0;
    while ((len = bis.read(buffer)) != -1) {
    fos.write(buffer, 0, len);
    total += len;
    // 获取当前下载量
    pd.setProgress(total);
    }
    fos.close();
    bis.close();
    is.close();
    conn.disconnect();
    return file;
    } else {
    return null;
    }
    } catch (Exception e) {
    Message msg = new Message();
    msg.what = GET_UNDATAINFO_ERROR;
    handler.sendMessage(msg);
    return null;
    }
    }
    
    
    
    
    protected void showUpdataDialog(final ApkInfo apk) {
    AlertDialog.Builder builer = new Builder(mContext,R.style.dialog);
    builer.setTitle("自动更新");
    builer.setMessage("最新版本："+apk.getVerName() + "，请点击确定按钮更新。");
    builer.setCancelable(false);
    // 当点确定按钮时从服务器上下载 新的apk 然后安装
    builer.setPositiveButton("确定", new OnClickListener() {
    public void onClick(DialogInterface dialog, int which) {
    // APK下载
    new DownloadAPK().execute(apk.getURL() + apk.getFileName());
    }
    });
    // 当点取消按钮时进行登录
    AlertDialog dialog = builer.create();
    dialog.show();
    }
   
    void showNextActivity()
    {
    	Activity act = (Activity)mContext;
    	Intent intent = new Intent(mContext, LoginActivity.class);
		act.startActivity(intent);
    	act.finish();
    }
    
    Handler handler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
    // TODO Auto-generated method stub
    super.handleMessage(msg);
    Activity act = (Activity)mContext;
    switch (msg.what) {
    case GET_UNDATAINFO_ERROR:
    // 服务器超时
    Toast.makeText(mContext, "获取服务器更新信息失败", Toast.LENGTH_LONG).show();
    act.finish();
    break;
    case DOWN_ERROR:
    // 下载apk失败
    Toast.makeText(mContext, "下载新版本失败", Toast.LENGTH_LONG).show();
    act.finish();
    break;
    case INSTALL_ERROR:
    Toast.makeText(mContext, "安装新版本失败", Toast.LENGTH_LONG).show();
    act.finish();
    break;
    }
    }
    };
    //获取当前APK的版本号
   
    private class checkUpdateTask extends AsyncTask<Integer, Void, ApkInfo> {

		 int localVerCode = 0;

		@Override
		protected ApkInfo doInBackground(Integer... params) {
			// TODO Auto-generated method stub
			this.localVerCode = params[0];
			return Transfer.getApkInfo();
		}
		
		@Override
	    protected void onPostExecute(ApkInfo apk) {
		    
			int serverVerCode = apk.getVerCode();
			if (serverVerCode > localVerCode)
			{
				showUpdataDialog(apk);
			}
			else
			{
				showNextActivity();
			}
		}
	 }     // 到服务器后台下载最新APK
   
    
    class DownloadAPK extends AsyncTask<String, Integer, File> {
    @Override
    protected void onPreExecute() {
    progressDialog = new ProgressDialog(mContext,R.style.dialog);
    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    progressDialog.setMessage("下载进度");
    progressDialog.setTitle("自动更新");
    progressDialog.setIndeterminate(false);
    progressDialog.setCancelable(false);
    progressDialog.show();
    super.onPreExecute();
    }

    @Override
    protected File doInBackground(String... params) {
    try {
    file = getFileFromServer(params[0],progressDialog);
    } catch (Exception e) {
    Message msg = new Message();
    msg.what = DOWN_ERROR;
    handler.sendMessage(msg);
    e.printStackTrace();
    }
    return file;
    }

    @Override
    protected void onPostExecute(File result) {
    // doInBackground返回时触发，换句话说，就是doInBackground执行完后触发
    // 这里的result就是上面doInBackground执行后的返回值
    installApk(result);
    progressDialog.dismiss(); // 结束掉进度条对话框
    super.onPostExecute(result);
    }
    }
    //调用安装apk
    protected void installApk(File file) {
    if (file.toString().endsWith(".apk")) {
    Intent intent = new Intent();
    intent.setAction(Intent.ACTION_VIEW);
    intent.setDataAndType(Uri.fromFile(file),
    "application/vnd.android.package-archive");
    mContext.startActivity(intent);
    Activity act = (Activity)mContext;
    act.finish();
    } else {
    Message msg = new Message();
    msg.what = INSTALL_ERROR;
    handler.sendMessage(msg);
    }
    }
    //设置向后台发送的JSON 获取版本信息
}
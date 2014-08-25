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
	private ProgressDialog progressDialog; // �������Ի���
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
 
  //�������˻�ȡAPK��Ϣ 
    public File getFileFromServer(String path, ProgressDialog pd) {
    // �����ȵĻ���ʾ��ǰ��sdcard�������ֻ��ϲ����ǿ��õ�
    try {
    if (Environment.getExternalStorageState().equals(
    Environment.MEDIA_MOUNTED)) {
    URL url = new URL(path);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    // �Դ���ȡ���ļ��Ĵ�С
    int Max = conn.getContentLength();
    pd.setMax(Max);
    InputStream is = conn.getInputStream();
    // �½�һ���ļ�·���Լ�������������
    File file = new File(Environment.getExternalStorageDirectory(),"OnlineDesignForPAD.apk");
    FileOutputStream fos = new FileOutputStream(file);
    BufferedInputStream bis = new BufferedInputStream(is);
    byte[] buffer = new byte[2048];
    int len;
    int total = 0;
    while ((len = bis.read(buffer)) != -1) {
    fos.write(buffer, 0, len);
    total += len;
    // ��ȡ��ǰ������
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
    builer.setTitle("�Զ�����");
    builer.setMessage("���°汾��"+apk.getVerName() + "������ȷ����ť���¡�");
    builer.setCancelable(false);
    // ����ȷ����ťʱ�ӷ����������� �µ�apk Ȼ��װ
    builer.setPositiveButton("ȷ��", new OnClickListener() {
    public void onClick(DialogInterface dialog, int which) {
    // APK����
    new DownloadAPK().execute(apk.getURL() + apk.getFileName());
    }
    });
    // ����ȡ����ťʱ���е�¼
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
    // ��������ʱ
    Toast.makeText(mContext, "��ȡ������������Ϣʧ��", Toast.LENGTH_LONG).show();
    act.finish();
    break;
    case DOWN_ERROR:
    // ����apkʧ��
    Toast.makeText(mContext, "�����°汾ʧ��", Toast.LENGTH_LONG).show();
    act.finish();
    break;
    case INSTALL_ERROR:
    Toast.makeText(mContext, "��װ�°汾ʧ��", Toast.LENGTH_LONG).show();
    act.finish();
    break;
    }
    }
    };
    //��ȡ��ǰAPK�İ汾��
   
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
	 }     // ����������̨��������APK
   
    
    class DownloadAPK extends AsyncTask<String, Integer, File> {
    @Override
    protected void onPreExecute() {
    progressDialog = new ProgressDialog(mContext,R.style.dialog);
    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    progressDialog.setMessage("���ؽ���");
    progressDialog.setTitle("�Զ�����");
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
    // doInBackground����ʱ���������仰˵������doInBackgroundִ����󴥷�
    // �����result��������doInBackgroundִ�к�ķ���ֵ
    installApk(result);
    progressDialog.dismiss(); // �������������Ի���
    super.onPostExecute(result);
    }
    }
    //���ð�װapk
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
    //�������̨���͵�JSON ��ȡ�汾��Ϣ
}
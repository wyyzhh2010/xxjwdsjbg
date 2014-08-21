package com.xxjwd.sjbg;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;  
import java.util.HashMap;  

import org.kobjects.base64.Base64;

import com.xxjwd.classes.BOOLEAN;
import com.xxjwd.classes.GongWen;
import com.xxjwd.classes.Instruction;
import com.xxjwd.sjbg.R;
import com.xxjwd.transfer.Transfer;

import android.widget.AdapterView;  
import android.widget.SimpleAdapter;
import android.widget.TextView;  

import com.zcj.lib.AnimFragmentActivity;
import com.zcj.lib.MyApp;
import com.zcj.lib.MyListView;
import com.zcj.util.ActivityUtil;
import com.zcj.util.IntentUtil;


public class GongWenDetailActivity extends AnimFragmentActivity {
	private GongWen gw;
	MyApp app;
	MenuItem mi;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);  
         setContentView(R.layout.gw_details);
         Intent intent = getIntent();
         String wh = intent.getStringExtra("wh");
      //锁定竖屏
         setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
       //标题栏隐藏
	      ActionBar actionBar = getActionBar();
	      actionBar.setTitle("公文内容");
			actionBar.setDisplayHomeAsUpEnabled(true);
      
			app = (MyApp) getApplication();
			doGetData(wh);
			
	
    }
	
	@Override
	public void onResume()
	{
		super.onResume();
		Intent intent = getIntent();
        String wh = intent.getStringExtra("wh");
        doGetData(wh);
	}
    
	void doGetData(String wh)
	{
		ActivityUtil.getInstance().noticeSaying(this);
		new GetDataTask().execute(wh);
	}
	void doGetFile(String fjPath)
	{
		ActivityUtil.getInstance().noticeSaying(this);
		new getFileTask().execute(fjPath);
	}
	
	public void showDetails(GongWen gw ,Instruction[] instructions,boolean isSigned )
	{
		//初始化各控件内容
	      
		this.gw = gw;

		mi.setVisible(!isSigned);
	    TextView txtRedTitle = (TextView) findViewById(R.id.txtRedTitle); 
	    txtRedTitle.setText(gw.getRedTitle().replace("<br>","\n").replace("&nbsp;", " ")); 
	    TextView txtNumber = (TextView) findViewById(R.id.txtNumber); 
	    txtNumber.setText(gw.getNumber());
	    TextView txtTitle = (TextView)findViewById(R.id.txtTitle);
	    txtTitle.setText(gw.getTitle());
	    TextView txtContent = (TextView)findViewById(R.id.txtContent);
	    txtContent.setText(gw.getContent().replace("<br>","\n").replace("&nbsp;", " "));
	    TextView txtFileType = (TextView)findViewById(R.id.txtFileType);
	    txtFileType.setText("呈送人：" + gw.getFileType());
	    TextView txtSuggestion = (TextView)findViewById(R.id.txtSuggestion);
	    txtSuggestion.setText("呈送意见：" + gw.getSuggestion());
	    TextView txtSendDept = (TextView)findViewById(R.id.txtSendDept);
	    txtSendDept.setText(gw.getSendDept());
	    TextView txtSendDate = (TextView)findViewById(R.id.txtSendDate);
	    txtSendDate.setText(gw.getSendDate());
	    MyListView lvAttachFiles = (MyListView) findViewById(R.id.lvAttachFiles);
	    ArrayList<HashMap<String, Object>> listItems = new ArrayList<HashMap<String, Object>>();
	    HashMap<String, Object> map = new HashMap<String, Object>();  
	    String fjFile = gw.getAttachPath01();
	    getIntent().putExtra("a1", fjFile);
	    map.put("ItemTitle", fjFile.substring(fjFile.lastIndexOf("/") +1,fjFile.length()));     //文字  
	    map.put("ItemImage", R.drawable.gmail);//图片     
	    listItems.add(map); 
	    SimpleAdapter sad = new SimpleAdapter(this,listItems,
	            R.layout.gw_details_attach,
	            new String[] {"ItemTitle", "ItemImage"}, 
	                 
	              //ListItem的XML文件里面的两个TextView ID 
	            new int[] {R.id.ItemTitle,R.id.ItemImage}); 
	    lvAttachFiles.setAdapter(sad);
		lvAttachFiles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
	        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

	        	
	        	
	            doGetFile(getIntent().getStringExtra("a" + (position+1)));
	            
	        }

		});

		MyListView lvInstr = (MyListView) findViewById(R.id.lvInstructions);
		ArrayList<HashMap<String, String >> listItems1 = new ArrayList<HashMap< String,String>>();
		if (instructions.length > 0)
		{
			 
			for(int i=0;i<instructions.length;i++)
			{
				HashMap<String, String> map1 = new HashMap<String, String>();
				map1.put("instruContent", instructions[i].getContent().replace("<br>", " ").replace("&nbsp", " "));
				map1.put("instruName", instructions[i].getName());
				map1.put("instruDate", instructions[i].getDate());
				listItems1.add(map1);
			}
			SimpleAdapter sad1 =  new SimpleAdapter(this,listItems1,
		              R.layout.gw_details_instruction,
		              new String[] {"instruContent", "instruName","instruDate"}, 
		                 
		              //ListItem的XML文件里面的两个TextView ID 
		              new int[] {R.id.txtInstrContent,R.id.txtInstrName,R.id.txtInstrDate}); 
			lvInstr.setAdapter(sad1);
		}
		
		txtRedTitle.setFocusable(true);
		txtRedTitle.setFocusableInTouchMode(true);
		txtRedTitle.requestFocus();   
	}

    @Override 
    public boolean onOptionsItemSelected(MenuItem item) {  
        switch(item.getItemId()){ 
        case R.id.gw_details_sign:
        	
        if (app.get_gwLevel() == 6) //领导
        {
        	Intent intent = new Intent();
        	intent.putExtra("wh", gw.getNumber());
        	intent.putExtra("bt", gw.getTitle());
        	intent.setClass(GongWenDetailActivity.this, GongWenSignActivity.class);
        	startActivity(intent);
        }
        else
        {
        	ActivityUtil.getInstance().noticeSaying(this);
        	new signGwTask().execute(gw.getNumber());
        }
        	return true;
        default: 
            break; 
        } 
        return super.onOptionsItemSelected(item); 
    } 
 
 @Override
 public boolean onCreateOptionsMenu(Menu menu) {
     // Inflate the menu items for use in the action bar
     MenuInflater inflater = getMenuInflater();
     inflater.inflate(R.menu.gw_details_menu, menu);
     mi =(MenuItem)menu.findItem(R.id.gw_details_sign);
     return super.onCreateOptionsMenu(menu);
 }

  private class getFileTask extends AsyncTask  <String, Void, String>
  {
	  @Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String fjPath = params[0];
			
			return Transfer.getRemoteFile(fjPath);
		}
		
		@Override
	    protected void onPostExecute(String file) {
			String SdPath = Environment.getExternalStorageDirectory().toString();
        	String base64Code =  file;
            byte[] buffer = Base64.decode(base64Code);
    		FileOutputStream outStream;
			try {
				outStream = new FileOutputStream(SdPath + "/a.doc");
				outStream.flush();
				outStream.write(buffer); 
        		outStream.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			ActivityUtil.getInstance().dismiss();
			Intent i = IntentUtil.getFileIntent(SdPath + "/a.doc","doc");	
    		startActivity(i);
		    
		}
  }
 private class GetDataTask extends AsyncTask<String, Void, Void> {

	 GongWen gw = null;
	 Instruction[] ins = null;
	 boolean isSigned = true;
	@Override
	protected Void doInBackground(String... params) {
		// TODO Auto-generated method stub
		String wh = params[0];
		gw =Transfer.getGongWenByWh(wh);
		ins = Transfer.getInstruction(wh);
		BOOLEAN boo =Transfer.isGwSigned(wh, app.getUser().getUid());
		if (boo == null) isSigned = false;
		else isSigned = boo.isTure() ;
		return null;
	}
	
	@Override
    protected void onPostExecute(Void v) {
	      if (gw == null)
	      {
	    	  Toast.makeText(getApplicationContext(), "未找到该公文",Toast.LENGTH_LONG).show();;
	      }
	      else
	      {
	    	  showDetails(gw,ins,isSigned);
	      }
	      ActivityUtil.getInstance().dismiss();
	}
 }    
   
    
 class signGwTask extends AsyncTask <String ,Void ,Boolean>
	{
	

		@Override
		protected Boolean doInBackground(String... params) {
			// TODO Auto-generated method stub
			String wh = params[0];
			int uid = app.getUser().getUid();
			BOOLEAN boo = Transfer.signGwMiddle(wh, uid);
			if (boo == null) return false;
			return boo.isTure();
		}
		
		@Override
		protected void onPostExecute(final Boolean succesed)
		{
			String message;
			ActivityUtil.getInstance().dismiss();
			if (succesed)
			{
				message = "公文签收成功！";
				
			}
			else
			{
				message = "公文签收失败，请检查网络是否正确连接。";
			}
			AlertDialog dlg = new AlertDialog.Builder(GongWenDetailActivity.this,R.style.dialog).setTitle(R.string.title_activity_gong_wen_qs)
			.setMessage(message)
			.setPositiveButton("确定", new DialogInterface.OnClickListener() {   

                @Override   
                public void onClick(DialogInterface dialog, int which) {   
                    // TODO Auto-generated method stub
                	if (succesed) finish();
                }   
            })
			.create();
			dlg.setCancelable(false);
			dlg.show();
			
		}
	}
   
}


  


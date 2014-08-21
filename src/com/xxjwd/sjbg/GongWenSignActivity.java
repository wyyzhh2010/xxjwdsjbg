package com.xxjwd.sjbg;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.xxjwd.classes.BOOLEAN;
import com.xxjwd.classes.BuMenGw;
import com.xxjwd.classes.BuMenLeiBie;
import com.xxjwd.classes.UserGw;
import com.xxjwd.transfer.Transfer;
import com.zcj.lib.AnimFragmentActivity;
import com.zcj.lib.MyApp;
import com.zcj.util.ActivityUtil;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GongWenSignActivity extends AnimFragmentActivity implements OnClickListener,OnLongClickListener {



	private EditText et;
	private String nextUsers;
	private String wh;
	private String DunHao = "、";
	private int uid;
	private boolean bzcy=false;
	private MyApp app;
	final List<String> strs = new ArrayList<String>();
	final List<Integer> users = new ArrayList<Integer>();
	private String[] values;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gw_sign_leader);
		app = (MyApp)getApplication();
		nextUsers = "";
		Intent intent = getIntent();
		TextView tv = (TextView) findViewById(R.id.txtSignLeaderTitle);
		tv.setText(intent.getStringExtra("bt"));
		wh = intent.getStringExtra("wh");
		uid = app.getUser().getUid();
		
		et = (EditText)findViewById(R.id.txtSignLdps);
		
	      ActionBar actionBar = getActionBar();
	      actionBar.setTitle("公文签收");
			actionBar.setDisplayHomeAsUpEnabled(true);
			InitButtons();
	}
	
	void InitButtons()
	{
		ActivityUtil.getInstance().noticeSaying(this);
		new getBmlbTask().execute();
	}
	
	void setBanZiChengYuan()
	{
		ActivityUtil.getInstance().noticeSaying(this);
		new getBzcyTask().execute("showBanZiChengYuan");
		
	}
	
	
	void setMultiChoiseDialog(int id)
	{
		ActivityUtil.getInstance().noticeSaying(this);
		if (id == 0)
		{

			new getBzcyTask().execute("showDldDialog");
		}
		else
		{
			new getBmsTask().execute(id);
    		
		}
	}
	
	public void showDldDialog(final UserGw[] gws)
	{

		values = new String[gws.length];
		for(int i=0;i<values.length;i++)
		{
			values[i] = gws[i].getYhsm();
			
			
		}
		final boolean[] isChkeds = new boolean[values.length];
		new AlertDialog.Builder(this,R.style.dialog).setTitle("请选择")
		
        .setMultiChoiceItems(values, new boolean[values.length] ,  
                new OnMultiChoiceClickListener() {  
                    public void onClick(DialogInterface dialog,  
                            int which, boolean isChecked) {  
                        // 来回重复选择取消，得相应去改变item对应的bool值，点击确定时，根据这个bool[],得到选择的内容  
                    	isChkeds[which] = isChecked;  
                    }  
                }) // 设置对话框[肯定]按钮  
        .setPositiveButton("确定", new DialogInterface.OnClickListener() {  
            public void onClick(DialogInterface dialog, int which) {
            	strs.clear();
            	users.clear();
            	if (! bzcy) nextUsers= "";
            	for(int i=0;i<isChkeds.length;i++){
					if(isChkeds[i]){
						strs.add(gws[i].getYhnc());
						users.add(gws[i].getYhbh());
					}
            	
					
				
            }  
            	for(int j=0;j < strs.size();j++)
				{
					if (j == strs.size()-1) 
						{
						et.getText().append(strs.get(j));
						if (!bzcy) nextUsers = nextUsers + users.get(j);
						}
					else 
						{
						et.getText().append(strs.get(j) + DunHao);
						if (!bzcy) nextUsers = nextUsers + users.get(j) + getResources().getString(R.string.FenGe);
						}
				}
            	
            }
        }).setNegativeButton("取消", null)// 设置对话框[否定]按钮  
        .show();  
	}
	
	public void showBmDialog(final BuMenGw[] bms)
	{
		values = new String[bms.length];
		for(int i=0;i<values.length;i++)
		{
			values[i] = bms[i].getBmmc();
			
			
		}
		final boolean[] isChkeds = new boolean[values.length];
		
		new AlertDialog.Builder(this,R.style.dialog).setTitle("请选择")
        .setMultiChoiceItems(values, new boolean[values.length] ,  
                new OnMultiChoiceClickListener() {  
                    public void onClick(DialogInterface dialog,  
                            int which, boolean isChecked) {  
                        // 来回重复选择取消，得相应去改变item对应的bool值，点击确定时，根据这个bool[],得到选择的内容  
                    	isChkeds[which] = isChecked;  
                    }  
                }) // 设置对话框[肯定]按钮  
        .setPositiveButton("确定", new DialogInterface.OnClickListener() {  
            public void onClick(DialogInterface dialog, int which) {  
            	strs.clear();
            	for(int i=0;i<isChkeds.length;i++){
					if(isChkeds[i]){
						strs.add(bms[i].getBmmc());
					}
				}
            	for(int j=0;j < strs.size();j++)
				{
					if (j == strs.size()-1) 
						{
						et.getText().append(strs.get(j));
						
						}
					else 
						{
						et.getText().append(strs.get(j) + DunHao);
						
						}
				}
            }  
        }).setNegativeButton("取消", null)// 设置对话框[否定]按钮  
        .show();  
		
	}
	
	public void showBanZiChengYuan(UserGw[] gws)
	{
		et.getText().append("班子成员");
		bzcy = true;
		nextUsers = "";
		for(int i=0;i<gws.length;i++)
		{
			if (i == gws.length - 1)
			{
				nextUsers = nextUsers+ gws[i].getYhbh(); 
			}
			else
			{
				nextUsers = nextUsers+ gws[i].getYhbh() + getResources().getString(R.string.FenGe);
			}
			
		}
	}
	public void showButtons(BuMenLeiBie[] bms)
	{
		
		LinearLayout ll = (LinearLayout)findViewById(R.id.layout_gw_sign_bumen);
		Button btn = new Button(this);
		btn.setId(0);
		btn.setText("段 领 导");
		btn.setOnClickListener(this);
		btn.setOnLongClickListener(this);
		LinearLayout.LayoutParams lp =  new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT,1);
		
		ll.addView(btn,lp);
		for(int i=0;i<bms.length;i++)
		{
			btn = new Button(this);
			btn.setId(bms[i].getID());
			btn.setText(bms[i].getLbmc());
			btn.setOnClickListener(this);
			btn.setOnLongClickListener(this);
			lp =  new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT,1);
			ll.addView(btn,lp);
		}
		Button btnQing = (Button) findViewById(R.id.btnQing);
		btnQing.setOnClickListener(this);
		Button btnYue = (Button) findViewById(R.id.btnYue);
		btnYue.setOnClickListener(this);
		Button btnYueBan = (Button) findViewById(R.id.btnYueBan);
		btnYueBan.setOnClickListener(this);
		Button btnYueShi = (Button) findViewById(R.id.btnYueShi);
		btnYueShi.setOnClickListener(this);
		Button btnYueChu = (Button) findViewById(R.id.btnYueChu);
		btnYueChu.setOnClickListener(this);
		Button btnChuanYue = (Button) findViewById(R.id.btnChuanYue);
		btnChuanYue.setOnClickListener(this);
		Button btnDunHao = (Button) findViewById(R.id.btnDunHao);
		btnDunHao.setOnClickListener(this);
		Button btnDouHao = (Button) findViewById(R.id.btnDouHao);
		btnDouHao.setOnClickListener(this);
		Button btnJuHao = (Button) findViewById(R.id. btnJuHao);
		btnJuHao.setOnClickListener(this);
	}
	

	@Override  
    public boolean onLongClick(View view){
		if (view.getId()==0) 
			{
				
				setBanZiChengYuan();
			}
		else et.getText().append("各" + ((Button)view).getText());
		return true;  
	}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			Button btn = (Button) v;
			if (v.getId() <=6)
			{
				setMultiChoiseDialog(v.getId());
			}
			
			else
			{
				et.getText().append(btn.getText());
			}
		}
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gw_sign_leader_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){ 
        	case R.id.gw_sign_leader_accept:
        		String ins = et.getText().toString();
        		if (ins.length() == 0)
        		{
        			Toast.makeText(this, "请先签署意见", Toast.LENGTH_LONG).show();
        		}
        		else 
        		{

        			doSignGw();
        		}
        	return true;
        	case R.id.gw_sign_leader_undo:
        		et.setText("");
        		nextUsers = "";
        		bzcy = false;
        	return true;
     default: 
         break; 
     } 
		return super.onOptionsItemSelected(item);
	}

	private void doSignGw() {
		// TODO Auto-generated method stub
		
		ActivityUtil.getInstance().noticeSaying(getApplicationContext());
		Bundle b = new Bundle();
		b.putString("wh", wh);
		b.putInt("uid", uid);
		b.putString("ins", et.getText().toString());
		b.putString("nextUsers", nextUsers);
		new signGwTask().execute(b);
	}

	class getBmlbTask extends AsyncTask <Void ,Void ,BuMenLeiBie[]>
	{

		@Override
		protected BuMenLeiBie[] doInBackground(Void... params) {
			// TODO Auto-generated method stub
			return Transfer.getBmlbGw();
		}
		
		@Override
		protected void onPostExecute(BuMenLeiBie[] bms)
		{
			GongWenSignActivity.this.showButtons(bms);
			ActivityUtil.getInstance().dismiss();
		}
	}
	
	class getBzcyTask extends AsyncTask <String ,Void ,UserGw[]>
	{
		String method = null;

		@Override
		protected UserGw[] doInBackground(String... params) {
			// TODO Auto-generated method stub
			this.method = params[0];
			return Transfer.getDldList();
		}
		
		@Override
		protected void onPostExecute(UserGw[] users)
		{
			try {
				@SuppressWarnings("rawtypes")
				Class[] para = new Class[1];
				para[0] = UserGw[].class;
				Object[] arg = new Object[1];
				arg[0] = users;
				Method m = GongWenSignActivity.this.getClass().getMethod(method, para);
				m.invoke(GongWenSignActivity.this, arg);
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ActivityUtil.getInstance().dismiss();
		}
	}

	class getBmsTask extends AsyncTask <Integer ,Void ,BuMenGw[]>
	{
	

		@Override
		protected BuMenGw[] doInBackground(Integer... params) {
			// TODO Auto-generated method stub
			int id = params[0];
			return Transfer.getBuMenList(id);
		}
		
		@Override
		protected void onPostExecute(BuMenGw[] bms)
		{
			showBmDialog(bms);

			ActivityUtil.getInstance().dismiss();
		}
	}
	
	
	class signGwTask extends AsyncTask <Bundle ,Void ,Boolean>
	{
	

		@Override
		protected Boolean doInBackground(Bundle... params) {
			// TODO Auto-generated method stub
			String wh = params[0].getString("wh");
			int uid = params[0].getInt("uid");
			String ins = params[0].getString("ins");
			String nextUsers = params[0].getString("nextUsers");
			BOOLEAN boo = Transfer.signGw(wh, uid, ins, nextUsers);
			if (boo == null) return false;
			else return boo.isTure();
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
			AlertDialog dlg = new AlertDialog.Builder(GongWenSignActivity.this,R.style.dialog).setTitle(R.string.title_activity_gong_wen_qs)
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

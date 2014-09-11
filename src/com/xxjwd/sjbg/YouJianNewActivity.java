package com.xxjwd.sjbg;


import com.xxjwd.classes.INT;
import com.xxjwd.classes.YouJian;
import com.xxjwd.classes.YouJianDiZhi;
import com.xxjwd.classes.YouJianSend;
import com.xxjwd.transfer.Transfer;
import com.zcj.lib.AnimFragmentActivity;
import com.zcj.lib.MyApp;
import com.zcj.util.StringUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;


public class YouJianNewActivity extends AnimFragmentActivity implements OnClickListener {
	private EditText mail_to;
	private EditText mail_from;
	private EditText mail_topic;
	private EditText mail_content;
	
	private Button send;
	private ImageButton add_lianxiren;
	private ImageButton attachment;
	private GridView gridView;
	
	private int mailid=-1;
	
	 private static final int SUCCESS = 1;
	 private static final int FAILED = -1;
	 private boolean isCaogaoxiang=true;
	 MyApp app;
	   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.youjian_send_new);
		//init();
		app = (MyApp)getApplication();
	}

	
	
	private void init(){
		mail_to=(EditText) findViewById(R.id.mail_to);
		mail_from=(EditText) findViewById(R.id.mail_from);
		mail_topic=(EditText) findViewById(R.id.mail_topic);
		mail_content=(EditText) findViewById(R.id.content);
		attachment=(ImageButton) findViewById(R.id.add_att);
		add_lianxiren=(ImageButton) findViewById(R.id.btn_email_open_constact);
		
		send.setOnClickListener(this);
		attachment.setOnClickListener(this);
		add_lianxiren.setOnClickListener(this);
		

	

		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.add_att:
			
			break;
		case R.id.btn_email_open_constact:
			Intent intent=new Intent(YouJianNewActivity.this,YouJianNewActivity.class);
			startActivityForResult(intent, 2);
			break;
		}
		
	};
	
	
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_youjian_new_send:
			INT i;
			i = sendMail();
			Toast.makeText(this, ""+i.getValue(), Toast.LENGTH_LONG).show();
			return true;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private INT sendMail() {
		// TODO Auto-generated method stub
		int uid = 3975;
		
		int importance = 3;
		String subject = "测试一下的主题";
		String body = "发一封邮件进行车市，发不出去怎么办？；";
		String from = StringUtil.strFuHaoKaiShi + app.getUser().getUserNo()+"@xxjwd.com" + StringUtil.strFuHaoFenGe + app.getUser().getUserName();
		String to = StringUtil.strFuHaoKaiShi + "3974@xxjwd.com" + StringUtil.strFuHaoFenGe ;
		String cc = "";
		String bcc = "";
		String attachment = "";
		return Transfer.sendMail(uid ,importance,subject,body,from,to,cc,bcc,attachment);
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.youjian_new_menu, menu);
		
		return super.onCreateOptionsMenu(menu);
	}

	
}

package com.xxjwd.sjbg;


import com.xxjwd.classes.BOOLEAN;
import com.xxjwd.transfer.Transfer;
import com.zcj.lib.AnimFragmentActivity;
import com.zcj.lib.MyApp;
import com.zcj.util.ActivityUtil;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TeQingJieShouActivity extends AnimFragmentActivity implements OnClickListener {


	private MyApp app;
	private int tid;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tq_jieshou);
		app = (MyApp)getApplication();
		Intent intent = getIntent();
		tid = intent.getIntExtra("tid", 0);
		TextView tv = (TextView) findViewById(R.id.txt_tq_jieshou_detail_title);
		tv.setText(intent.getStringExtra("bt"));
		TextView tv1 = (TextView) findViewById(R.id.txt_tq_jieshou_detail_content);
		tv1.setText(intent.getStringExtra("nr"));
		Button btn = (Button) findViewById(R.id.btn_tq_jieshou_detail_ok);
		btn.setOnClickListener(this);
		tv.setFocusable(true);
		tv.setFocusableInTouchMode(true);
		tv.requestFocus();   
	}
	

	




		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			doReply();
			
		}
		




	private void doReply() {
		// TODO Auto-generated method stub
		
		ActivityUtil.getInstance().noticeSaying(getApplicationContext());
		EditText et = (EditText)findViewById(R.id.edt_tq_jieshou_detail_reply);
		String replyContent = et.getText().toString();
		if (replyContent.length() == 0)
		{
			Toast.makeText(this, "请先输入回复内容。", Toast.LENGTH_LONG).show();
		}
		else 
		{
			ActivityUtil.getInstance().noticeSaying(this);
			new replyTeQingTask().execute(replyContent);
			
		}
		
	}

	
	
	class replyTeQingTask extends AsyncTask <String ,Void ,Boolean>
	{
	

		@Override
		protected Boolean doInBackground(String... params) {
			// TODO Auto-generated method stub
			String replyContent = params[0];
			int uid = app.getUser().getUid();
			BOOLEAN boo = Transfer.tqyjReply(uid, tid, replyContent);
			if (boo == null) return false;
			return boo.isTure();
		}
		
		@Override
		protected void onPostExecute(Boolean succesed)
		{
			String message;
			ActivityUtil.getInstance().dismiss();
			if (succesed)
			{
				message = "特情回复成功！";
			}
			else
			{
				message = "特情回复失败，请检查网络是否正确连接。";
			}
			new AlertDialog.Builder(TeQingJieShouActivity.this,R.style.dialog).setTitle(R.string.title_activity_gong_wen_qs)
			.setMessage(message).show();
			finish();
		}
	}

}

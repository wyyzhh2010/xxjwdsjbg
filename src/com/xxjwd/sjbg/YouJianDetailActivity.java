package com.xxjwd.sjbg;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.webkit.WebView;
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
import com.xxjwd.classes.YouJian;
import com.xxjwd.classes.YouJianFuJian;
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
import com.zcj.util.StringUtil;

public class YouJianDetailActivity extends AnimFragmentActivity {
	private YouJian yj;
	MyApp app;
	MenuItem mi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.youjian_detail);
		Intent intent = getIntent();
		String muid = intent.getStringExtra("muid");
		String mailboxName = intent.getStringExtra("mailboxName");
		// 锁定竖屏
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// 标题栏隐藏
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("邮件内容");
		actionBar.setDisplayHomeAsUpEnabled(true);

		app = (MyApp) getApplication();
		doGetData(muid,mailboxName);

	}

	@Override
	public void onResume() {
		super.onResume();
		Intent intent = getIntent();
		String uid = intent.getStringExtra("muid");
		String mailboxName = intent.getStringExtra("mailboxName");
		doGetData(uid,mailboxName);
	}

	void doGetData(String uid,String mailboxName) {
		ActivityUtil.getInstance().noticeSaying(this);
		new GetDataTask().execute(uid,mailboxName);
	}

	void doGetFile(String fjPath) {
		ActivityUtil.getInstance().noticeSaying(this);
		new getFileTask().execute(fjPath);
	}

	public void showDetails(YouJian yj) {
		// 初始化各控件内容

		this.yj = yj;

		TextView txtSubject = (TextView) findViewById(R.id.txt_youjian_detail_subject);
		txtSubject.setText("主    题：" + yj.getSubject());
		TextView txtFrom = (TextView) findViewById(R.id.txt_youjian_detail_from);
		txtFrom.setText("发件人：" + yj.getFrom().ShowFullString());
		TextView txtTo = (TextView) findViewById(R.id.txt_youjian_detail_to);
		txtTo.setText("收件人：" +  StringUtil.ShowFullString(yj.getTo()));
		TextView txtReplyTo = (TextView) findViewById(R.id.txt_youjian_detail_replyto);
		//txtReplyTo.setText(yj.getReplyTo()[0].getAddress());
		txtReplyTo.setVisibility(View.GONE);
		TextView txtCc = (TextView) findViewById(R.id.txt_youjian_detail_cc);
		txtCc.setText("抄    送：" + StringUtil.ShowFullString( yj.getCc()));
		
		if (StringUtil.ShowFullString( yj.getCc()).equals("")) txtCc.setVisibility(View.GONE);
		TextView txtDate = (TextView) findViewById(R.id.txt_youjian_detail_date);
		txtDate.setText("时    间：" + yj.getDate());
		WebView wvBody = (WebView)findViewById(R.id.wv_mailcontent);
		wvBody.setVisibility(View.VISIBLE);
		wvBody.loadDataWithBaseURL(null, yj.getBody(), "text/html", "utf-8", null);
		MyListView lvAttachFiles = (MyListView) findViewById(R.id.lv_youjian_detail_AttachFiles);
		lvAttachFiles.setVisibility(View.GONE);
		ArrayList<HashMap<String, Object>> listItems = new ArrayList<HashMap<String, Object>>();
		
		YouJianFuJian[] atts = yj.getAttachments();
		if (atts != null && atts.length > 0) {
			lvAttachFiles.setVisibility(View.VISIBLE);
			for (int i = 0; i < atts.length; i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("ItemTitle", atts[i].getFilename()); // 文字
				map.put("ItemImage", R.drawable.ic_email_attachment_small);// 图片
				listItems.add(map);
			}
			SimpleAdapter sad = new SimpleAdapter(this, listItems,
					R.layout.gw_details_attach, new String[] { "ItemTitle",
							"ItemImage" },

					// ListItem的XML文件里面的两个TextView ID
					new int[] { R.id.ItemTitle, R.id.ItemImage });
			lvAttachFiles.setAdapter(sad);
			lvAttachFiles
					.setOnItemClickListener(new AdapterView.OnItemClickListener() {

						public void onItemClick(AdapterView<?> arg0, View arg1,
								int position, long arg3) {

							doGetFile(getIntent().getStringExtra(
									"a" + (position + 1)));

						}

					});

		}

		txtSubject.setFocusable(true);
		txtSubject.setFocusableInTouchMode(true);
		txtSubject.requestFocus();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.gw_details_sign:

		
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
		mi = (MenuItem) menu.findItem(R.id.gw_details_sign);
		return super.onCreateOptionsMenu(menu);
	}

	private class getFileTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String fjPath = params[0];

			return Transfer.getRemoteFile(fjPath);
		}

		@Override
		protected void onPostExecute(String file) {
			String SdPath = Environment.getExternalStorageDirectory()
					.toString();
			String base64Code = file;
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
			Intent i = IntentUtil.getFileIntent(SdPath + "/a.doc", "doc");
			startActivity(i);

		}
	}

	private class GetDataTask extends AsyncTask<String, Void, Void> {

		YouJian yj = null;
		


		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			String muid = params[0];
			int iuid = Integer.parseInt(muid);
			String mailboxName = params[1];
			yj = Transfer.getMailMessage(3975, iuid, mailboxName);
			return null;
		}

		@Override
		protected void onPostExecute(Void v) {
			if (yj == null) {
				Toast.makeText(getApplicationContext(), "未找到该邮件",
						Toast.LENGTH_LONG).show();
				;
			} else {
				showDetails(yj);
			}
			ActivityUtil.getInstance().dismiss();
		}
	}

	class signGwTask extends AsyncTask<String, Void, Boolean> {

		@Override
		protected Boolean doInBackground(String... params) {
			// TODO Auto-generated method stub
			String wh = params[0];
			int uid = app.getUser().getUid();
			BOOLEAN boo = Transfer.signGwMiddle(wh, uid);
			if (boo == null)
				return false;
			return boo.isTure();
		}

		@Override
		protected void onPostExecute(final Boolean succesed) {
			String message;
			ActivityUtil.getInstance().dismiss();
			if (succesed) {
				message = "公文签收成功！";

			} else {
				message = "公文签收失败，请检查网络是否正确连接。";
			}
			AlertDialog dlg = new AlertDialog.Builder(
					YouJianDetailActivity.this, R.style.dialog)
					.setTitle(R.string.title_activity_gong_wen_qs)
					.setMessage(message)
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									if (succesed)
										finish();
								}
							}).create();
			dlg.setCancelable(false);
			dlg.show();

		}
	}

}

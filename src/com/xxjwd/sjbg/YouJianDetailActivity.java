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

public class YouJianDetailActivity extends AnimFragmentActivity {
	private YouJian yj;
	MyApp app;
	MenuItem mi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.youjian_details);
		Intent intent = getIntent();
		String wh = intent.getStringExtra("muid");
		// 锁定竖屏
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// 标题栏隐藏
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("邮件内容");
		actionBar.setDisplayHomeAsUpEnabled(true);

		app = (MyApp) getApplication();
		doGetData(wh);

	}

	@Override
	public void onResume() {
		super.onResume();
		Intent intent = getIntent();
		String uid = intent.getStringExtra("muid");
		doGetData(uid);
	}

	void doGetData(String uid) {
		ActivityUtil.getInstance().noticeSaying(this);
		new GetDataTask().execute(uid);
	}

	void doGetFile(String fjPath) {
		ActivityUtil.getInstance().noticeSaying(this);
		new getFileTask().execute(fjPath);
	}

	public void showDetails(YouJian yj) {
		// 初始化各控件内容

		this.yj = yj;

		TextView txtSubject = (TextView) findViewById(R.id.txt_yj_detail_Subject);
		txtSubject.setText(yj.getSubject());
		TextView txtFrom = (TextView) findViewById(R.id.txt_yj_detail_From);
		txtFrom.setText(yj.getFrom().getAddress());
		TextView txtBody = (TextView) findViewById(R.id.txt_yj_detail_Body);
		txtBody.setText(yj.getBody());
		TextView txtTo = (TextView) findViewById(R.id.txt_yj_detail_To);
		txtTo.setText(yj.getTo()[0].getAddress());
		TextView txtReplyTo = (TextView) findViewById(R.id.txt_yj_detail_ReplyTo);
		//txtReplyTo.setText(yj.getReplyTo()[0].getAddress());
		TextView txtCc = (TextView) findViewById(R.id.txt_yj_detail_Cc);
		//txtCc.setText(yj.getCc()[0].getAddress());
		TextView txtDate = (TextView) findViewById(R.id.txt_yj_detail_Date);
		txtDate.setText(yj.getDate());
		MyListView lvAttachFiles = (MyListView) findViewById(R.id.lv_yj_detail_AttachFiles);
		ArrayList<HashMap<String, Object>> listItems = new ArrayList<HashMap<String, Object>>();
		
		YouJianFuJian[] atts = yj.getAttachments();
		if (atts.length > 0) {
			for (int i = 0; i < atts.length; i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("ItemTitle", atts[i].getFilename()); // 文字
				map.put("ItemImage", R.drawable.gmail);// 图片
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
			String wh = params[0];
			yj = Transfer.getMailMessage(3975, 1, "imp");
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

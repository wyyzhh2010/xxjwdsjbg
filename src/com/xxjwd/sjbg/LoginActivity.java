package com.xxjwd.sjbg;

import com.xxjwd.classes.INT;
import com.xxjwd.classes.User;
import com.xxjwd.classes.WenJianJia;
import com.xxjwd.sjbg.R;
import com.xxjwd.transfer.Transfer;
import com.zcj.lib.AnimFragmentActivity;
import com.zcj.lib.DeviceUniqueID;
import com.zcj.lib.MyApp;
import com.zcj.util.ActivityUtil;
import com.zcj.util.AutoUpdateUtil;
import com.zcj.util.SecurityUtil;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AnimFragmentActivity implements OnClickListener {
	
	Button btnLogin,btnNext;
	EditText edtNum;
	EditText edtPass ;
	TextView tv1,tv2;
	CheckBox chbRemeber;
	boolean isRemeber;
	int lastVerCode,verCode;
	SharedPreferences sp;
	int step;
	int gh;
	String mm,sjh,uCode,rCode;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        getActionBar().hide();
        step = 1;
        uCode = DeviceUniqueID.getUniqueID(this);
        edtPass = (EditText)findViewById(R.id.login_edit_pwd);
        edtNum = (EditText)findViewById(R.id.login_edit_account);
        btnLogin = (Button)findViewById(R.id.login_btn_login);
        btnNext = (Button)findViewById(R.id.login_btn_register);
        chbRemeber = (CheckBox)findViewById(R.id.login_cb_savepwd);
        tv1 = (TextView)findViewById(R.id.login_textview1);
        tv2 = (TextView)findViewById(R.id.login_textview2);
        sp = this.getSharedPreferences("u", Context.MODE_PRIVATE);
       
        isRemeber = sp.getBoolean("isRemeberPass", false);
        lastVerCode = sp.getInt("lastVercode", 0);
        verCode=AutoUpdateUtil.getVerCode(this);
        if ( AutoUpdateUtil.getVerCode(this) > lastVerCode)
        {
        	showUpdateContentDialog();
        }
        chbRemeber.setChecked(isRemeber);
        btnLogin.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        if (isRemeber)
        {
        	
        	edtPass.setText(SecurityUtil.Decrypt(sp.getString("p", "")));
        	edtNum.setText(sp.getString("u", ""));
        }

        
        
    }
    
	private void showUpdateContentDialog() {
		// TODO Auto-generated method stub
		  AlertDialog.Builder builer = new Builder(this,R.style.dialog);
		    builer.setTitle("系统通信");
		    builer.setMessage("恭喜您更新到最新版本：："+AutoUpdateUtil.getVerName(this) + "本次更新内容如下：\n"); //+ Transfer.getApkInfo().getUpdateContent());
		    builer.setCancelable(false);
		    // 当点确定按钮时从服务器上下载 新的apk 然后安装
		    builer.setPositiveButton("确定", new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int which) {
		    // APK下载
		    
		    }
		    });
		    // 当点取消按钮时进行登录
		    AlertDialog dialog = builer.create();
		    dialog.show();
		    sp.edit().putInt("lastVercode", verCode).commit();
	}



		public void onClick(View v) {
			Button btn = (Button)v;
			String name = edtNum.getText().toString();
			String password = edtPass.getText().toString();
			//WenJianJia wjj;
			//wjj = Transfer.selectMailBox(3974, "inbox");
			//edtNum.setText(wjj.getUidNext()+"next");
			switch (btn.getId())
			{
			case  R.id.login_btn_login:

				
				if (step == 1)
				{
				//if (name.equals(""))
				//{
				//	name = "0001";
				//	password="169169";
				//}
				
				String[] param = new String[3];
				param[0] = name;
				param[1] = password;
				param[2] = uCode;
				ActivityUtil.getInstance().noticeSaying(this);

				//ActivityUtil.getInstance().dismiss();
				new LoginTask().execute(param);
				}
				else if (step == 2) //输入注册码以后，下一步输入安全问题和答案
				{
					if (sjh.equals(""))
					{
						Toast.makeText(getApplicationContext(),"请先输入手机号获得验证码.", Toast.LENGTH_SHORT).show();
						return;
					}
					if (password.equals(""))
					{
						Toast.makeText(getApplicationContext(),"请输入验证码，验证码已经已短信方式发送到您的手机上。", Toast.LENGTH_SHORT).show();
						return;
					}
					rCode = password;
					setStep(3);
				}
				else
				{
					if (name.equals("") || password.equals(""))
					{
						Toast.makeText(getApplicationContext(),"请输入安全问题和答案。", Toast.LENGTH_SHORT).show();
						return;
					}
					String[] param1 = new String[2];
					param1[0] = name;
					param1[1] = password;
					ActivityUtil.getInstance().noticeSaying(this);
					new RegisterTask().execute(param1);
				}
				break;
			case R.id.login_btn_register :
				if (step == 1)//验证工号和密码是否正确
				{
					if (name.equals("") || password.equals( ""))
					{
						Toast.makeText(getApplicationContext(),"请输入工号和密码", Toast.LENGTH_SHORT).show();
						return;
					}
					String[] param1 = new String[2];
					param1[0] = name;
					param1[1] = password;
					ActivityUtil.getInstance().noticeSaying(this);
					new CheckUserTask().execute(param1);
				}
				else if (step == 2)//填写手机号，获取验证码
				{
					if (name.equals(""))
					{
						Toast.makeText(getApplicationContext(),"请输入手机号", Toast.LENGTH_SHORT).show();
						return;
					}
					String[] param2 = new String[1];
					param2[0] = name;
					ActivityUtil.getInstance().noticeSaying(this);
					new GetRCodeTask().execute(param2);
				}
				else//取消注册，回到初始界面
				{
					doInit();

				}
				break;
			default:
				break;
			}
		}
    	
		void setStep(int step)
		{
			edtNum.setText("");
			edtPass.setText("");
			switch (step)
			{
				case 1:
					tv1.setText("工号");
					tv2.setText("密码");
					edtNum.setText("");
					edtPass.setText("");
					edtPass.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
					btnNext.setText("自助注册");
					btnLogin.setText("  登  录  ");
					edtNum.requestFocus();
					this.step = 1;
					break;
				case 2:
					tv1.setText("手机号");
					tv2.setText("注册码");
					edtNum.setText("");
					edtPass.setText("");
					edtPass.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
					btnNext.setText("获取注册码");
					btnLogin.setText("下一步");
					edtNum.requestFocus();
					this.step = 2;
					break;
				case 3:
					tv1.setText("安全问题");
					tv2.setText("问题答案");
					edtNum.setText("");
					edtPass.setText("");
					edtPass.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
					btnNext.setText("  取  消  ");
					btnLogin.setText("  确  定  ");
					edtNum.requestFocus();
					this.step = 3;
					break;
				default:
					break;
			}
		}

    class LoginTask extends AsyncTask <String ,Void ,User>
    {

    	String name,password,ucode;
    	int loginValue;
    	int tqLevel,gwLevel;
		@Override
		protected User doInBackground(String... params) {
			// TODO Auto-generated method stub
			name = params[0];
			password = params[1];
			ucode = params[2];
			User user = null;
			INT login = Transfer.login(name, password, ucode);
			loginValue = login.getValue();
			switch (loginValue)
			{
				case 1://正常登陆
					user = Transfer.getUserById(name);
					tqLevel = Transfer.getTqyjLevel(user.getUid()).getValue();
					gwLevel = Transfer.getGwclLevel(user.getUid()).getValue();
					break;
				case -1://设备未注册
				case -2://用户不存在
				case -3://密码错误
				case -100://数据库链接错误
				default://其他错误
					
					break;
			}
			return user;
		}

    	
		@Override 
		protected void onPostExecute(User user)
		{
			if (user != null)
			{
			
			Toast.makeText(getApplicationContext(), user.getUserName() + "登录成功，欢迎您使用新乡机务段手机办公系统",
				     Toast.LENGTH_SHORT).show();

			sp.edit().putBoolean("isRemeberPass", chbRemeber.isChecked()).commit();
			if (chbRemeber.isChecked())
			{
				sp.edit().putString("u", name).commit();
				sp.edit().putString("p",SecurityUtil.Encrypt(password)).commit();
			}
			MyApp app = (MyApp)getApplication();
			app.setUser(user);
			app.set_tqLevel(tqLevel);
			app.set_gwLevel(gwLevel);
			Intent intent = new Intent(getApplicationContext(), MainActivity.class);
			
			startActivity(intent);
			
			LoginActivity.this.finish();
			}
			else
				
			{
				Toast.makeText(getApplicationContext(),"登录失败，错误代码：" + loginValue,
					     Toast.LENGTH_SHORT).show();
			}
			ActivityUtil.getInstance().dismiss();
		}
		 @Override
	        protected void onCancelled() {
	            super.onCancelled();
	            ActivityUtil.getInstance().dismiss();
	        }
	
    }



    class CheckUserTask extends AsyncTask <String ,Void ,Void>
    {

    	String name,password;
    	int result = -1;
		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			name = params[0];
			password = params[1];
			INT login = Transfer.login(name, password, "");
			result = login.getValue();
			return null;
		}

    	
		@Override 
		protected void onPostExecute(Void v)
		{
			
			if (result == -1)//设备未注册
			{
				gh = Integer.parseInt(name);
				mm = password;
				setStep(2);
			}
			else 
			{
				String txt="";
				if ( result == 1) txt = "您已经完成注册，可直接登录系统。";
				else if (result == -2) txt = "工号不存在，请检查工号是否正确。";
				else if (result == -3) txt = "密码错误，请重新输入。";
				else if (result == -100) txt = "未知错误。";
				Toast.makeText(getApplicationContext(), txt,
					     Toast.LENGTH_SHORT).show();
			}
			

			
			ActivityUtil.getInstance().dismiss();
		}
		 @Override
	        protected void onCancelled() {
	            super.onCancelled();
	            ActivityUtil.getInstance().dismiss();
	        }
	
    }

    class GetRCodeTask extends AsyncTask <String ,Void ,Void>
    {

    	String mobile;
    	int result = -1;
		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			mobile = params[0];
			result = Transfer.requestRegisterCode(gh, mobile, uCode).getValue();
			return null;
		}

    	
		@Override 
		protected void onPostExecute(Void v)
		{
			
			if (result == 1)//发送成功
			{
				sjh = mobile;
				Toast.makeText(getApplicationContext(), "获取注册码成功，请在2分钟内输入验证。",
					     Toast.LENGTH_SHORT).show();
			}
			else 
			{
				
				Toast.makeText(getApplicationContext(), "获取注册码失败：错误代码：" + result,
					     Toast.LENGTH_SHORT).show();
			}
			

			
			ActivityUtil.getInstance().dismiss();
		}
		 @Override
	        protected void onCancelled() {
	            super.onCancelled();
	            ActivityUtil.getInstance().dismiss();
	        }
	
    }

    class RegisterTask extends AsyncTask <String ,Void ,Void>
    {

    	String wenti,daan;
    	int result = -1;
		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			wenti = params[0];
			daan = params[1];
			result = Transfer.registerDevice(gh, sjh, uCode, rCode, wenti, daan, "").getValue();
			return null;
		}

    	
		@Override 
		protected void onPostExecute(Void v)
		{
			
			if (result == 1)//发送成功
			{
				doInit();
				Toast.makeText(getApplicationContext(), "注册码成功，您现在可以开始登录。",
					     Toast.LENGTH_SHORT).show();
			}
			else 
			{
				
				Toast.makeText(getApplicationContext(), "注册失败：错误代码：" + result,
					     Toast.LENGTH_SHORT).show();
			}
			

			
			ActivityUtil.getInstance().dismiss();
		}
		 @Override
	        protected void onCancelled() {
	            super.onCancelled();
	            ActivityUtil.getInstance().dismiss();
	        }
	
    }

	private void doInit() {
		// TODO Auto-generated method stub
		gh = -1;
		mm = "";
		sjh = "";
		rCode = "";
		setStep(1);
	}

}
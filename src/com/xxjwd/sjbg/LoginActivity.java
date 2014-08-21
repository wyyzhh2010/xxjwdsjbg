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
		    builer.setTitle("ϵͳͨ��");
		    builer.setMessage("��ϲ�����µ����°汾����"+AutoUpdateUtil.getVerName(this) + "���θ����������£�\n"); //+ Transfer.getApkInfo().getUpdateContent());
		    builer.setCancelable(false);
		    // ����ȷ����ťʱ�ӷ����������� �µ�apk Ȼ��װ
		    builer.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int which) {
		    // APK����
		    
		    }
		    });
		    // ����ȡ����ťʱ���е�¼
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
				else if (step == 2) //����ע�����Ժ���һ�����밲ȫ����ʹ�
				{
					if (sjh.equals(""))
					{
						Toast.makeText(getApplicationContext(),"���������ֻ��Ż����֤��.", Toast.LENGTH_SHORT).show();
						return;
					}
					if (password.equals(""))
					{
						Toast.makeText(getApplicationContext(),"��������֤�룬��֤���Ѿ��Ѷ��ŷ�ʽ���͵������ֻ��ϡ�", Toast.LENGTH_SHORT).show();
						return;
					}
					rCode = password;
					setStep(3);
				}
				else
				{
					if (name.equals("") || password.equals(""))
					{
						Toast.makeText(getApplicationContext(),"�����밲ȫ����ʹ𰸡�", Toast.LENGTH_SHORT).show();
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
				if (step == 1)//��֤���ź������Ƿ���ȷ
				{
					if (name.equals("") || password.equals( ""))
					{
						Toast.makeText(getApplicationContext(),"�����빤�ź�����", Toast.LENGTH_SHORT).show();
						return;
					}
					String[] param1 = new String[2];
					param1[0] = name;
					param1[1] = password;
					ActivityUtil.getInstance().noticeSaying(this);
					new CheckUserTask().execute(param1);
				}
				else if (step == 2)//��д�ֻ��ţ���ȡ��֤��
				{
					if (name.equals(""))
					{
						Toast.makeText(getApplicationContext(),"�������ֻ���", Toast.LENGTH_SHORT).show();
						return;
					}
					String[] param2 = new String[1];
					param2[0] = name;
					ActivityUtil.getInstance().noticeSaying(this);
					new GetRCodeTask().execute(param2);
				}
				else//ȡ��ע�ᣬ�ص���ʼ����
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
					tv1.setText("����");
					tv2.setText("����");
					edtNum.setText("");
					edtPass.setText("");
					edtPass.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
					btnNext.setText("����ע��");
					btnLogin.setText("  ��  ¼  ");
					edtNum.requestFocus();
					this.step = 1;
					break;
				case 2:
					tv1.setText("�ֻ���");
					tv2.setText("ע����");
					edtNum.setText("");
					edtPass.setText("");
					edtPass.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
					btnNext.setText("��ȡע����");
					btnLogin.setText("��һ��");
					edtNum.requestFocus();
					this.step = 2;
					break;
				case 3:
					tv1.setText("��ȫ����");
					tv2.setText("�����");
					edtNum.setText("");
					edtPass.setText("");
					edtPass.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
					btnNext.setText("  ȡ  ��  ");
					btnLogin.setText("  ȷ  ��  ");
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
				case 1://������½
					user = Transfer.getUserById(name);
					tqLevel = Transfer.getTqyjLevel(user.getUid()).getValue();
					gwLevel = Transfer.getGwclLevel(user.getUid()).getValue();
					break;
				case -1://�豸δע��
				case -2://�û�������
				case -3://�������
				case -100://���ݿ����Ӵ���
				default://��������
					
					break;
			}
			return user;
		}

    	
		@Override 
		protected void onPostExecute(User user)
		{
			if (user != null)
			{
			
			Toast.makeText(getApplicationContext(), user.getUserName() + "��¼�ɹ�����ӭ��ʹ�����������ֻ��칫ϵͳ",
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
				Toast.makeText(getApplicationContext(),"��¼ʧ�ܣ�������룺" + loginValue,
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
			
			if (result == -1)//�豸δע��
			{
				gh = Integer.parseInt(name);
				mm = password;
				setStep(2);
			}
			else 
			{
				String txt="";
				if ( result == 1) txt = "���Ѿ����ע�ᣬ��ֱ�ӵ�¼ϵͳ��";
				else if (result == -2) txt = "���Ų����ڣ����鹤���Ƿ���ȷ��";
				else if (result == -3) txt = "����������������롣";
				else if (result == -100) txt = "δ֪����";
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
			
			if (result == 1)//���ͳɹ�
			{
				sjh = mobile;
				Toast.makeText(getApplicationContext(), "��ȡע����ɹ�������2������������֤��",
					     Toast.LENGTH_SHORT).show();
			}
			else 
			{
				
				Toast.makeText(getApplicationContext(), "��ȡע����ʧ�ܣ�������룺" + result,
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
			
			if (result == 1)//���ͳɹ�
			{
				doInit();
				Toast.makeText(getApplicationContext(), "ע����ɹ��������ڿ��Կ�ʼ��¼��",
					     Toast.LENGTH_SHORT).show();
			}
			else 
			{
				
				Toast.makeText(getApplicationContext(), "ע��ʧ�ܣ�������룺" + result,
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
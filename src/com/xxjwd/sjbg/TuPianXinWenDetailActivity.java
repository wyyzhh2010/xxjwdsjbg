package com.xxjwd.sjbg;


import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MenuItem;
import java.util.ArrayList;  
import java.util.List;

import org.kobjects.base64.Base64;

import com.xxjwd.classes.XinWen;
import com.xxjwd.sjbg.R;
import com.xxjwd.transfer.Transfer;

import android.widget.TextView;  

import com.zcj.adapter.ImagePagerAdapter;
import com.zcj.lib.AnimFragmentActivity;
import com.zcj.lib.AutoScrollViewPager;
import com.zcj.lib.MyApp;
import com.zcj.util.ActivityUtil;


public class TuPianXinWenDetailActivity extends AnimFragmentActivity {
	MyApp app;
	MenuItem mi;
	XinWen tpxw;
	private AutoScrollViewPager viewPager;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);  
         setContentView(R.layout.tpxw_details);
         viewPager = (AutoScrollViewPager) findViewById(R.id.view_pager_tpxw_details_images);
         app = (MyApp) getApplication();
         tpxw = app.get_tpxw();
      //锁定竖屏
         setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	      getActionBar().setTitle("公文内容");
		
			
			doGetFile();
			
	
    }
	
	@Override
	public void onResume()
	{
		super.onResume();
		 viewPager.startAutoScroll();
	}
    @Override
    protected void onPause() {
        super.onPause();
        // stop auto scroll when onPause
        viewPager.stopAutoScroll();
    }
  
	void doGetFile()
	{
		ActivityUtil.getInstance().noticeSaying(this);
		new getFileTask().execute();
	}
	
	public void showDetails(List<Bitmap> bitmap)
	{
		//初始化各控件内容
	      
	
	    TextView txtRedTitle = (TextView) findViewById(R.id.txt_tpxw_details_title); 
	    txtRedTitle.setText(tpxw.getTitle()); 
	    TextView txtNumber = (TextView) findViewById(R.id.txt_tpxw_details_content); 
	    txtNumber.setText(tpxw.getContent());
	    TextView txtTitle = (TextView)findViewById(R.id.txt_tpxw_details_dept);
	    txtTitle.setText("发文部门：" + tpxw.getPublisher() + "   更新日期：" + tpxw.getDate());
	    
        viewPager.setAdapter(new ImagePagerAdapter(this, bitmap));
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());

        viewPager.setInterval(2000);
        viewPager.startAutoScroll();
		
		txtRedTitle.setFocusable(true);
		txtRedTitle.setFocusableInTouchMode(true);
		txtRedTitle.requestFocus();   
	}

	 public class MyOnPageChangeListener implements OnPageChangeListener {

	        @Override
	        public void onPageSelected(int position) {
	            
	        }

	        @Override
	        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
	        }

	        @Override
	        public void onPageScrollStateChanged(int arg0) {
	        }
	    }

  private class getFileTask extends AsyncTask  <Void, Void, List<Bitmap>>
  {
	  
	  String filePath,fileName;
	  String [] fileNames;
	  @Override
		protected List<Bitmap> doInBackground(Void... params) {
			// TODO Auto-generated method stub
		  	filePath = tpxw.getPath();
		  	fileName = tpxw.getAttachFiles();
			fileNames = fileName.split(";");
			List<Bitmap>bitmap = new ArrayList<Bitmap>();
			for (int i=0;i<fileNames.length;i++)
			{
				String file = Transfer.getRemoteFile(filePath + "/" + fileNames[i]);
				byte[] buffer = Base64.decode(file);
				bitmap.add( BitmapFactory.decodeByteArray(buffer, 0, buffer.length));
			}
			return bitmap;
		}
		
		@Override
	    protected void onPostExecute(List<Bitmap> bitmap) {
			
			ActivityUtil.getInstance().dismiss();
			showDetails(bitmap);
		}
  }
  
   
}


  


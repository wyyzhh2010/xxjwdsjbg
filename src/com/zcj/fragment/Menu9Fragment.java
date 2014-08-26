/*
 * Copyright (C) 2013 Andreas Stuetz <andreas.stuetz@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zcj.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.xxjwd.sjbg.ShengChanActivity;
import com.xxjwd.sjbg.TeQingListActivity;
import com.xxjwd.sjbg.TuPianXinWenActivity;
import com.xxjwd.sjbg.XinWenActivity;
import com.xxjwd.sjbg.GongWenListActivity;
import com.xxjwd.sjbg.YouJianListActivity;
import com.xxjwd.sjbg.ZhiBanActivity;
import com.xxjwd.sjbg.R;
import com.zcj.lib.AnimFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Menu9Fragment extends AnimFragment {

	private static final String ARG_POSITION = "position";

	private int position;
	private boolean recent;
	public static Menu9Fragment newInstance(int position,boolean recent) {
		Menu9Fragment f = new Menu9Fragment();
		Bundle b = new Bundle();
		b.putInt(ARG_POSITION, position);
		b.putBoolean("recent", recent);
		f.setArguments(b);
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		position = getArguments().getInt(ARG_POSITION);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		position = getArguments().getInt(ARG_POSITION);
		recent = getArguments().getBoolean("recent");
		int realPostion = position;
		if (recent == false) realPostion = realPostion + 1;
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

		FrameLayout fl = new FrameLayout(getActivity());
		fl.setLayoutParams(params);

		@SuppressWarnings("unused")
		final int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources()
				.getDisplayMetrics());

		 
		
		View view =  inflater.inflate(R.layout.gridview, container, false);
		GridView mGridView = (GridView) view.findViewById(R.id.GridView);
		mGridView.setId(position);
		int[] imageRes;
		         //定义标题数组
		         String[] itemName;
		    
		         switch (realPostion)
		         {
		         	case 0:
		         		imageRes = new int[] {R.drawable.p193};
		         		itemName = new String[]{"最后访问"};
		         		break;
		         	case 1://移动办公
		         		imageRes = new int[] {R.drawable.p201,R.drawable.p193,R.drawable.p201,R.drawable.p201,R.drawable.p193,R.drawable.p193,R.drawable.p201,R.drawable.p201};
		         		itemName = new String[]{"公文处理","工作写实","生产简报","电子邮件","电话号码","呼叫中心","值班查询","特情预警"};
		         		break;
		         	case 2://机车运用
		         		imageRes = new int[] {R.drawable.p201,R.drawable.p193,R.drawable.p193,R.drawable.p193,R.drawable.p193};
		         		itemName = new String[]{"运用信息","电子铭牌","机车计划","人员计划","待班计划"};
		         		break;
		         	case 3://机车检修
		         		imageRes = new int[] {R.drawable.p201,R.drawable.p193,R.drawable.p193};
		         		itemName = new String[]{"检修信息","机车状态","检修计划"};
		         		break;
		         	case 4://安全管理
		         		imageRes = new int[] {R.drawable.p201,R.drawable.p193,R.drawable.p193};
		         		itemName = new String[]{"安全信息","超劳预警","测酒记录"};
		         		break;

		         	case 5://综合服务
		         		imageRes = new int[] {R.drawable.p201,R.drawable.p201,R.drawable.p201,R.drawable.p201,R.drawable.p201,R.drawable.p201,R.drawable.p201,R.drawable.p201,R.drawable.p193,R.drawable.p193};
		         		itemName = new String[]{"行政信息","党群信息","路局通报","新机速报","督查督办","时事新闻","生活百科","图片新闻","职工心声","天气预报"};
		         		break;
		         	case 6://个人信息
		         		imageRes = new int[] {R.drawable.p193};
		         		itemName = new String[]{"修改密码"};
		         		break;
		         	default:
		         		imageRes = new int[] {R.drawable.p193};
		         		itemName = new String[]{"出错啦!"};
		         		break;
		         }
		                
		                  List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		                  int length = itemName.length;
		                  for (int i = 0; i < length; i++) {
		                          HashMap<String, Object> map = new HashMap<String, Object>();
		                          map.put("ItemImageView", imageRes[i]);
		                          map.put("ItemTextView", itemName[i]);
		                          data.add(map);
		                  }
		                  //为itme.xml添加适配器
		                  SimpleAdapter simpleAdapter = new SimpleAdapter(view.getContext(),
		                  data, R.layout.menu_item, new String[] { "ItemImageView","ItemTextView" }, new int[] { R.id.ItemImageView,R.id.ItemTextView });
		                  mGridView.setAdapter(simpleAdapter);
		                  
		                  //为mGridView添加点击事件监听器
		                  mGridView.setOnItemClickListener(new GridViewItemOnClick(recent));
		        
		       

		return view;
	}
//定义点击事件监听器
public class GridViewItemOnClick implements OnItemClickListener {
	boolean recent;
	public GridViewItemOnClick(boolean recent)
	{
		this.recent = recent;
	}
public void onItemClick(AdapterView<?> arg0, View view, int position,long arg3) {
		
       
       
       int vid = arg0.findFocus().getId();
       if (recent == false) vid= vid +1;
       if ( vid== 1)//移动办公
       {
    	   if (position == 0)
    	   {
    		   Intent intent = new Intent();
    	       intent.setClass(view.getContext(), GongWenListActivity.class);
    		   startActivity(intent);
    
    	   }
    	   else if (position == 2)
    	   {
    		   Intent intent = new Intent();

    	       intent.setClass(view.getContext(), ShengChanActivity.class);
    	       startActivity(intent);
    	   }
    	   else if (position == 3)
    	   {
    		   Intent intent = new Intent();

    	       intent.setClass(view.getContext(), YouJianListActivity.class);
    	       startActivity(intent);
    	   }
    	   else if (position == 6)
    	   {
    		   Intent intent = new Intent();

    	       intent.setClass(view.getContext(), ZhiBanActivity.class);
    	       startActivity(intent);
    		  
    	   }
    	   else if (position == 7)
    	   {
    		   Intent intent = new Intent();

    	       intent.setClass(view.getContext(), TeQingListActivity.class);
    	       startActivity(intent);
    		  
    	   }
    	   else
    	   {
    		   Toast.makeText(view.getContext(), "本功能正在开发中，敬请期待！",
    			       Toast.LENGTH_SHORT).show();
    	   }
       }
       else if (vid == 2)//机车运用
       {
    	   
    	   if (position == 0)//运用信息
    	   {
    		   Intent intent = new Intent();
    		   intent.putExtra("xwlx", 17);
    		   intent.putExtra("xwlb", "运用信息");
    		   intent.setClass(view.getContext(), XinWenActivity.class);
    		   startActivity(intent);
    	   }
    	   else if (position == 1)//电子铭牌
    	   {
    		   
    	   }
    		   
       }
       else if (vid == 3)//机车检修
       {
    	   if (position == 0)//检修信息
    	   {
    		   Intent intent = new Intent();
    		   intent.putExtra("xwlx", 18);
    		   intent.putExtra("xwlb", "检修信息");
    		   intent.setClass(view.getContext(), XinWenActivity.class);
    		   startActivity(intent);
    	   }
       }
       else if (vid == 4)//安全管理
       {
    	   if (position == 0)//检修信息
    	   {
    		   Intent intent = new Intent();
    		   intent.putExtra("xwlx", 1);
    		   intent.putExtra("xwlb", "安全信息");
    		   intent.setClass(view.getContext(), XinWenActivity.class);
    		   startActivity(intent);
    	   }
       }
	   else if (vid == 5) //综合服务
	   {
		   Intent intent = new Intent();
		   switch (position)
		   {
		   case 0:
    		   intent.putExtra("xwlx", 2);
    		   intent.putExtra("xwlb", "行政信息");
    		   break;
		   case 1:
    		   intent.putExtra("xwlx", 3);
    		   intent.putExtra("xwlb", "党群信息");
			   break;
		   case 2:
    		   intent.putExtra("xwlx", 9);
    		   intent.putExtra("xwlb", "路局通报");
			   break;
		   case 3:
    		   intent.putExtra("xwlx", 10);
    		   intent.putExtra("xwlb", "新机速报");
			   break;
		   case 4:
    		   intent.putExtra("xwlx", 23);
    		   intent.putExtra("xwlb", "督查督办");
			   break;
		   case 5:
    		   intent.putExtra("xwlx", 19);
    		   intent.putExtra("xwlb", "时事新闻");
			   break;
		   case 6:
    		   intent.putExtra("xwlx", 20);
    		   intent.putExtra("xwlb", "生活百科");
			   break;
		   case 7:
			   intent.setClass(view.getContext(), TuPianXinWenActivity.class);
			   startActivity(intent);
			   return;
			   default:
				   Toast.makeText(view.getContext(), "本功能正在开发中，敬请期待！",
		    		       Toast.LENGTH_SHORT).show();
			   return;
    	       
		   }
		   intent.setClass(view.getContext(), XinWenActivity.class);
		   startActivity(intent);
	   }
       else
       {
    	   Toast.makeText(view.getContext(), "本功能正在开发中，敬请期待！",
    		       Toast.LENGTH_SHORT).show();
       }
}
}
}
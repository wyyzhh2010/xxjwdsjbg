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
		         //�����������
		         String[] itemName;
		    
		         switch (realPostion)
		         {
		         	case 0:
		         		imageRes = new int[] {R.drawable.p193};
		         		itemName = new String[]{"������"};
		         		break;
		         	case 1://�ƶ��칫
		         		imageRes = new int[] {R.drawable.p201,R.drawable.p193,R.drawable.p201,R.drawable.p201,R.drawable.p193,R.drawable.p193,R.drawable.p201,R.drawable.p201};
		         		itemName = new String[]{"���Ĵ���","����дʵ","������","�����ʼ�","�绰����","��������","ֵ���ѯ","����Ԥ��"};
		         		break;
		         	case 2://��������
		         		imageRes = new int[] {R.drawable.p201,R.drawable.p193,R.drawable.p193,R.drawable.p193,R.drawable.p193};
		         		itemName = new String[]{"������Ϣ","��������","�����ƻ�","��Ա�ƻ�","����ƻ�"};
		         		break;
		         	case 3://��������
		         		imageRes = new int[] {R.drawable.p201,R.drawable.p193,R.drawable.p193};
		         		itemName = new String[]{"������Ϣ","����״̬","���޼ƻ�"};
		         		break;
		         	case 4://��ȫ����
		         		imageRes = new int[] {R.drawable.p201,R.drawable.p193,R.drawable.p193};
		         		itemName = new String[]{"��ȫ��Ϣ","����Ԥ��","��Ƽ�¼"};
		         		break;

		         	case 5://�ۺϷ���
		         		imageRes = new int[] {R.drawable.p201,R.drawable.p201,R.drawable.p201,R.drawable.p201,R.drawable.p201,R.drawable.p201,R.drawable.p201,R.drawable.p201,R.drawable.p193,R.drawable.p193};
		         		itemName = new String[]{"������Ϣ","��Ⱥ��Ϣ","·��ͨ��","�»��ٱ�","���鶽��","ʱ������","����ٿ�","ͼƬ����","ְ������","����Ԥ��"};
		         		break;
		         	case 6://������Ϣ
		         		imageRes = new int[] {R.drawable.p193};
		         		itemName = new String[]{"�޸�����"};
		         		break;
		         	default:
		         		imageRes = new int[] {R.drawable.p193};
		         		itemName = new String[]{"������!"};
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
		                  //Ϊitme.xml���������
		                  SimpleAdapter simpleAdapter = new SimpleAdapter(view.getContext(),
		                  data, R.layout.menu_item, new String[] { "ItemImageView","ItemTextView" }, new int[] { R.id.ItemImageView,R.id.ItemTextView });
		                  mGridView.setAdapter(simpleAdapter);
		                  
		                  //ΪmGridView��ӵ���¼�������
		                  mGridView.setOnItemClickListener(new GridViewItemOnClick(recent));
		        
		       

		return view;
	}
//�������¼�������
public class GridViewItemOnClick implements OnItemClickListener {
	boolean recent;
	public GridViewItemOnClick(boolean recent)
	{
		this.recent = recent;
	}
public void onItemClick(AdapterView<?> arg0, View view, int position,long arg3) {
		
       
       
       int vid = arg0.findFocus().getId();
       if (recent == false) vid= vid +1;
       if ( vid== 1)//�ƶ��칫
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
    		   Toast.makeText(view.getContext(), "���������ڿ����У������ڴ���",
    			       Toast.LENGTH_SHORT).show();
    	   }
       }
       else if (vid == 2)//��������
       {
    	   
    	   if (position == 0)//������Ϣ
    	   {
    		   Intent intent = new Intent();
    		   intent.putExtra("xwlx", 17);
    		   intent.putExtra("xwlb", "������Ϣ");
    		   intent.setClass(view.getContext(), XinWenActivity.class);
    		   startActivity(intent);
    	   }
    	   else if (position == 1)//��������
    	   {
    		   
    	   }
    		   
       }
       else if (vid == 3)//��������
       {
    	   if (position == 0)//������Ϣ
    	   {
    		   Intent intent = new Intent();
    		   intent.putExtra("xwlx", 18);
    		   intent.putExtra("xwlb", "������Ϣ");
    		   intent.setClass(view.getContext(), XinWenActivity.class);
    		   startActivity(intent);
    	   }
       }
       else if (vid == 4)//��ȫ����
       {
    	   if (position == 0)//������Ϣ
    	   {
    		   Intent intent = new Intent();
    		   intent.putExtra("xwlx", 1);
    		   intent.putExtra("xwlb", "��ȫ��Ϣ");
    		   intent.setClass(view.getContext(), XinWenActivity.class);
    		   startActivity(intent);
    	   }
       }
	   else if (vid == 5) //�ۺϷ���
	   {
		   Intent intent = new Intent();
		   switch (position)
		   {
		   case 0:
    		   intent.putExtra("xwlx", 2);
    		   intent.putExtra("xwlb", "������Ϣ");
    		   break;
		   case 1:
    		   intent.putExtra("xwlx", 3);
    		   intent.putExtra("xwlb", "��Ⱥ��Ϣ");
			   break;
		   case 2:
    		   intent.putExtra("xwlx", 9);
    		   intent.putExtra("xwlb", "·��ͨ��");
			   break;
		   case 3:
    		   intent.putExtra("xwlx", 10);
    		   intent.putExtra("xwlb", "�»��ٱ�");
			   break;
		   case 4:
    		   intent.putExtra("xwlx", 23);
    		   intent.putExtra("xwlb", "���鶽��");
			   break;
		   case 5:
    		   intent.putExtra("xwlx", 19);
    		   intent.putExtra("xwlb", "ʱ������");
			   break;
		   case 6:
    		   intent.putExtra("xwlx", 20);
    		   intent.putExtra("xwlb", "����ٿ�");
			   break;
		   case 7:
			   intent.setClass(view.getContext(), TuPianXinWenActivity.class);
			   startActivity(intent);
			   return;
			   default:
				   Toast.makeText(view.getContext(), "���������ڿ����У������ڴ���",
		    		       Toast.LENGTH_SHORT).show();
			   return;
    	       
		   }
		   intent.setClass(view.getContext(), XinWenActivity.class);
		   startActivity(intent);
	   }
       else
       {
    	   Toast.makeText(view.getContext(), "���������ڿ����У������ڴ���",
    		       Toast.LENGTH_SHORT).show();
       }
}
}
}
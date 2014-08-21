package com.zcj.adapter;

import java.util.List;  

import android.content.Context;  
import android.view.LayoutInflater;  
import android.view.View;  
import android.view.ViewGroup;  
import android.widget.BaseAdapter;  
import android.widget.TextView;  
import com.xxjwd.classes.TeQing;
import com.xxjwd.sjbg.R;

public class ListViewAdapter_TeQing extends BaseAdapter {  
	private List<TeQing> items;  
    private LayoutInflater inflater;  
    private int[] colors=new int[]{0x30FF0000,0x300000FF};
    private int type;
    public ListViewAdapter_TeQing(Context context,List<TeQing> items,int type) {  
    	this.items = items;  
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.type = type;
    }  

    @Override  
    public int getCount() {  
    	return items.size();  
    }  

    @Override  
    public Object getItem(int position) {  
        return items.get(position);  
    }  

    @Override  
	public long getItemId(int position) {  
        return position;  
	}  


    @Override  
    public View getView(int position, View view, ViewGroup parent) {  
    	ViewHolder holder = null; 
    	if (type==1)//����
    	{
    	if (view == null) 
    	{ 
    		view = inflater.inflate(R.layout.tq_jieshou_listitem, null); 
    		holder = new ViewHolder(); 
    		holder.text1 = (TextView) view.findViewById(R.id.txt_tq_jieshou_title);  
    		holder.text2 = (TextView) view.findViewById(R.id.txt_tq_jieshou_sender_dept);
    		holder.text3 = (TextView) view.findViewById(R.id.txt_tq_jieshou_sender_name); 
    		holder.text4 = (TextView) view.findViewById(R.id.txt_tq_jieshou_sendtime); 
    		holder.text5 = (TextView) view.findViewById(R.id.txt_tq_jieshou_receivetime);
    		view.setTag(holder);//��ViewHolder����   
    	}
    	else
    	{
    		holder = (ViewHolder)view.getTag();//ȡ��ViewHolder����
    	}   
    	
    		holder.text1.setText(items.get(position).getTitle().replace("<br>", "").replace("&nbsp;", " ")); 
    		holder.text2.setText("���Ͳ��ţ�" + items.get(position).getSenderDept()); 
    		holder.text3.setText("�����ˣ�" + items.get(position).getSenderName()); 
    		holder.text4.setText("����ʱ�䣺" + items.get(position).getSendTime());
    		String hfsj= items.get(position).getReplyTime();
    		if (hfsj.equals("") || hfsj == null)
    		{
    			holder.text5.setTextColor(R.color.red);
    			holder.text5.setText("δ�ظ�");
    		}
    		else
    		{
    			holder.text5.setTextColor(R.color.green);
    			holder.text5.setText("�ظ�ʱ�䣺" + hfsj);
    		}
    	}
    	else //��ѯ
    	{
    		if (view == null) 
        	{ 
        		view = inflater.inflate(R.layout.tq_chaxun_listitem, null); 
        		holder = new ViewHolder(); 
        		holder.text1 = (TextView) view.findViewById(R.id.txt_tq_chaxun_title);  
        		holder.text2 = (TextView) view.findViewById(R.id.txt_tq_chaxun_sender_dept);
        		holder.text3 = (TextView) view.findViewById(R.id.txt_tq_chaxun_sender_name); 
        		holder.text4 = (TextView) view.findViewById(R.id.txt_tq_chaxun_sendtime); 
        		holder.text5 = (TextView) view.findViewById(R.id.txt_tq_chaxun_send_count);
        		holder.text6 = (TextView) view.findViewById(R.id.txt_tq_chaxun_reply_count);
        		holder.text7 = (TextView) view.findViewById(R.id.txt_tq_chaxun_xuhao);
        		view.setTag(holder);//��ViewHolder����   
        	}
        	else
        	{
        		holder = (ViewHolder)view.getTag();//ȡ��ViewHolder����
        	}   
        	
        		holder.text1.setText(items.get(position).getTitle().replace("<br>", "").replace("&nbsp;", " ")); 
        		holder.text2.setText("���Ͳ��ţ�" + items.get(position).getSenderDept()); 
        		holder.text3.setText("�����ˣ�" + items.get(position).getSenderName()); 
        		holder.text4.setText("����ʱ�䣺" + items.get(position).getSendTime());
        		holder.text5.setText("����������" + items.get(position).getSendCount());
        		holder.text6.setText("����������" + items.get(position).getReplyCount());
        		holder.text7.setText("��ţ�" + (position + 1));

    	}
    	@SuppressWarnings("unused")
		int colorPos=position%colors.length;

        //view.setBackgroundColor(colors[colorPos]);
    	return view;  
    }  
    
    public class ViewHolder {  
		public TextView text1;  
        public TextView text2; 
        public TextView text3;
        public TextView text4;
        public TextView text5;
        public TextView text6;
        public TextView text7;
	}  
    
    

    /** 
     * ����б��� 
     * @param teQing 
     */  
    public void addItem(TeQing teQing) {  
    	items.add(teQing); 
    } 
}  



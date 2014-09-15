package com.zcj.adapter;

import java.util.List;  

import android.content.Context;  
import android.view.LayoutInflater;  
import android.view.View;  
import android.view.ViewGroup;  
import android.widget.BaseAdapter;  
import android.widget.ImageView;
import android.widget.TextView;  

import com.xxjwd.classes.YouJianSimple;
import com.xxjwd.sjbg.R;
import com.zcj.util.StringUtil;

public class ListViewAdapter_YouJian extends BaseAdapter {  
	private List<YouJianSimple> items;  
    private LayoutInflater inflater;  
    private int[] colors=new int[]{0x30FF0000,0x300000FF};
    public ListViewAdapter_YouJian(Context context,List<YouJianSimple> items) {  
    	this.items = items;  
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);        
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
    	if (view == null) { 
    		view = inflater.inflate(R.layout.youjian_ml_list_item, null); 
    		holder = new ViewHolder(); 
    		holder.text1 = (TextView) view.findViewById(R.id.txt_youjian_listitem_subject);  
    		holder.text2 = (TextView) view.findViewById(R.id.txt_youjian_listitem_order);
    		holder.text3 = (TextView) view.findViewById(R.id.txt_youjian_listitem_sender); 
    		holder.text4 = (TextView) view.findViewById(R.id.txt_youjian_listitem_date); 
    		holder.text5 = (TextView) view.findViewById(R.id.txt_youjian_listitem_size); 
    		holder.img = (ImageView)view.findViewById(R.id.img_youjian_attach_image);
    		
    		view.setTag(holder);//绑定ViewHolder对象   
    		}else{
    			holder = (ViewHolder)view.getTag();//取出ViewHolder对象
    			}   
    	
    	holder.text1.setText("主题：" + items.get(position).getSubject()); 
    	holder.text2.setText("序号：" + (position +1 )); 
    	holder.text3.setText("发件人：" + items.get(position).getFrom()); 
    	holder.text4.setText("日期：" + items.get(position).getDate());   
    	holder.text5.setText("邮件大小：" + StringUtil.ShowFullSize( items.get(position).getSize()));
    	if (items.get(position).isHasAttachements()) holder.img.setVisibility(View.VISIBLE);
    	else holder.img.setVisibility(View.GONE);
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
        public ImageView img;
	}  
    
    

    /** 
     * 添加列表项 
     * @param item 
     */  
    public void addItem(YouJianSimple item) {  
    	items.add(item); 
    } 
}  



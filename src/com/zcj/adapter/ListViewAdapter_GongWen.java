package com.zcj.adapter;

import java.util.List;  

import android.content.Context;  
import android.view.LayoutInflater;  
import android.view.View;  
import android.view.ViewGroup;  
import android.widget.BaseAdapter;  
import android.widget.TextView;  





import com.xxjwd.classes.GongWen;
import com.xxjwd.sjbg.R;

public class ListViewAdapter_GongWen extends BaseAdapter {  
	private List<GongWen> items;  
    private LayoutInflater inflater;  
    private int[] colors=new int[]{0x30FF0000,0x300000FF};
    public ListViewAdapter_GongWen(Context context,List<GongWen> items) {  
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
    		view = inflater.inflate(R.layout.gong_wen_ml_list_item, null); 
    		holder = new ViewHolder(); 
    		holder.text1 = (TextView) view.findViewById(R.id.textView11);  
    		holder.text2 = (TextView) view.findViewById(R.id.textView12);
    		holder.text3 = (TextView) view.findViewById(R.id.textView13); 
    		holder.text4 = (TextView) view.findViewById(R.id.textView14); 
    		view.setTag(holder);//绑定ViewHolder对象   
    		}else{
    			holder = (ViewHolder)view.getTag();//取出ViewHolder对象
    			}   
    	
    	holder.text1.setText(items.get(position).getTitle().replace("<br>", "").replace("&nbsp;", " ")); 
    	holder.text2.setText("序号：" + (position +1 )); 
    	holder.text3.setText("发文日期：" + items.get(position).getSendDate()); 
    	holder.text4.setText("发文单位：" + items.get(position).getSendDept());    	
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
	}  
    
    

    /** 
     * 添加列表项 
     * @param item 
     */  
    public void addItem(GongWen item) {  
    	items.add(item); 
    } 
}  



package com.zcj.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xxjwd.classes.XinWen;
import com.xxjwd.sjbg.R;

public class ListViewAdapter_TuPianXinWen extends BaseAdapter {
	private List<XinWen> items;  
	List<Bitmap> bitmap;
    private LayoutInflater inflater;  
    private int[] colors=new int[]{0x30FF0000,0x300000FF};
    public ListViewAdapter_TuPianXinWen(Context context,List<XinWen> items,List<Bitmap> bitmap) {  
    	this.items = items;  
    	this.bitmap = bitmap;
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
    		view = inflater.inflate(R.layout.tpxw_listitem, null); 
    		holder = new ViewHolder(); 
    		holder.text1 = (ImageView) view.findViewById(R.id.img_tpxw_listitem);  
    		holder.text2 = (TextView) view.findViewById(R.id.txt_tpxw_listitem_title);
    		holder.text3 = (TextView) view.findViewById(R.id.txt_tpxw_listitem_time); 
    		holder.text4 = (TextView) view.findViewById(R.id.txt_tpxw_listitem_dept); 
    		view.setTag(holder);//绑定ViewHolder对象   
    		}else{
    			holder = (ViewHolder)view.getTag();//取出ViewHolder对象
    			}   

    	holder.text1.setImageBitmap(bitmap.get(position));
    	holder.text2.setText(items.get(position).getTitle().replace("<br>", "").replace("&nbsp;", " ").replace(".htm", "").replace(".html", "")); 
    	holder.text3.setText("发文时间：" +items.get(position).getDate()); 
    	holder.text4.setText("发布部门：" + items.get(position).getPublisher());    	
    	@SuppressWarnings("unused")
		int colorPos=position%colors.length;

        //view.setBackgroundColor(colors[colorPos]);
    	return view;  
    }  
    
    public class ViewHolder {  
		public ImageView text1;  
        public TextView text2; 
        public TextView text3;
        public TextView text4;
	}  
    
    

    /** 
     * 添加列表项 
     * @param item 
     */  
    public void addItem(XinWen item) {  
    	items.add(item); 
    } 
}

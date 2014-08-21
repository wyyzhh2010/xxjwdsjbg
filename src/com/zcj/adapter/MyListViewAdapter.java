package com.zcj.adapter;

import java.util.List;

import java.util.Map;

import android.content.Context;

import android.view.View;

import android.view.ViewGroup;

import android.widget.SimpleAdapter;

public class MyListViewAdapter extends SimpleAdapter {

     private int[] colors=new int[]{0x30FF0000,0x300000FF};//����û�����ý�ȥʹ��,ֻ�Ǽ�������������

    public MyListViewAdapter(Context context, List<? extends Map<String, ?>> data,

             int resource, String[] from, int[] to) {

         super(context, data, resource, from, to);
    }
     @Override

     public View getView(int position ,View convertView,ViewGroup parent){

         View view=super.getView(position, convertView, parent);

         int colorPos=position%colors.length;

         view.setBackgroundColor(colors[colorPos]); //��ɫ����


         return view; }
     }
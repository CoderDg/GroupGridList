
package com.nimo.show.view;

import com.nimo.show.view.RowItem.ShowStyle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public abstract class GroupGridViewAdapter extends BaseAdapter {

    private static final String TAG = "GroupGridViewAdapter";

    private ArrayList<RowItem> mRowList;
    private ArrayList<IGroupItem> groupItems;

    public GroupGridViewAdapter(Context context, ArrayList<IGroupItem> groups) {
        groupItems = groups;
        mRowList = splitToRowItems(groups);
    }

    
    public void setShowItems(ArrayList<IGroupItem> mShowItems) {
        groupItems = mShowItems;
        mRowList = splitToRowItems(mShowItems);
        notifyDataSetChanged();
    }
    
    public void addShowItem(IGroupItem groupItem){
        if(groupItem == null){
            return;
        }
        
        ArrayList<IGroupItem> items=  new ArrayList<IGroupItem>();
        items.add(groupItem);
        ArrayList<RowItem> rowItems = mRowList = splitToRowItems(items);
        if(rowItems != null && items.size()>0){
            mRowList.addAll(rowItems);
        }
        //需要自己去刷新，因为不清楚会添加几项
    }
    
    @Override
    public int getCount() {
        if (mRowList == null)
            return 0;

        return mRowList.size();
    }
    
    @Override
    public Object getItem(int position) {
        return mRowList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 如果类型比较多,需要复写;
     */
    @Override
    public int getItemViewType(int position) {
        if (mRowList == null) {
            return -1;
        }
        RowItem item = mRowList.get(position);
        return item.getShowStyle();
    }

    @Override
    public int getViewTypeCount() {
        return 6;// 5+1
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (mRowList == null) {
            return null;
        }

        RowItem item = mRowList.get(position);
        if (item.getShowStyle() == ShowStyle.STYLE_GROUP) {
            return getGroupView(convertView,item);
        } else {
            return getChildView(convertView,item);
        }
    }
    //获取分组的头部，
    public abstract View getGroupView( View convertView, RowItem item); 
    
    public abstract View getChildView(View convertView,RowItem item) ;
    
    public abstract ArrayList<RowItem> splitToRowItems(ArrayList<IGroupItem> mShowItems);
}

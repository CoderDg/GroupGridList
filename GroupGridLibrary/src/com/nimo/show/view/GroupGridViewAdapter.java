
package com.nimo.show.view;

import com.nimo.show.view.RowItem.ShowStyle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class GroupGridViewAdapter extends BaseAdapter {

    private static final String TAG = "GroupGridViewAdapter";

    private ArrayList<RowItem> mRowList;
    private ArrayList<IGroupItem> groupItems;
    /**
     * 记录group与分组间的映射，只需要在每组的第一个RowItem中记录该组拆分的row数
     */
    protected HashMap<String, RowItem> groupToRowMap;

    public GroupGridViewAdapter(Context context, ArrayList<IGroupItem> groups) {
        groupToRowMap = new HashMap<String, RowItem>();
        mRowList = new ArrayList<RowItem>();
        groupItems = groups;
        splitToRowItems(groups);
    }

    /**
     * 更新数据
     * @param mShowItems
     */
    public void setShowItems(ArrayList<IGroupItem> groups) {
        groupItems = groups;
         splitToRowItems(groups);
        notifyDataSetChanged();
    }
    
    /**
     * 添加一组数据
     * @param groupItem
     */
    public synchronized void addGroupItem(IGroupItem groupItem){
        if(groupItem == null){
            return;
        }
        ArrayList<RowItem> rowItems = new ArrayList<RowItem>();
        RowItem item = splitToRowItems(groupItem, rowItems);
        item.setGroupRowCounts(rowItems.size());
        groupToRowMap.put(groupItem.getGroupInfo(), item);
        mRowList.addAll(rowItems);
        groupItems.add(groupItem);
    }
    
    /**
     * 添加一组数据到指定位置
     * @param groupItem
     * @param groupIndex
     */
    public synchronized void addGroupItem(IGroupItem groupItem, int groupIndex){
        if(groupItem == null){
            return;
        }
        ArrayList<RowItem> rowItems = new ArrayList<RowItem>();
        RowItem item = splitToRowItems(groupItem, rowItems);
        item.setGroupRowCounts(rowItems.size());
        groupToRowMap.put(groupItem.getGroupInfo(), item);
        int rowIndex = mRowList.size();
        if(groupIndex == 0){
            rowIndex = 0;
        }else if(groupIndex < groupItems.size()){
            RowItem nextItem = groupToRowMap.get(groupItems.get(groupIndex).getGroupInfo());
            rowIndex = mRowList.indexOf(nextItem)-1;
        }
        mRowList.addAll(rowIndex, rowItems);
    }
    
    /**
     * 删除一组数据
     * @param groupItem
     */
    public synchronized void removeGroupItem(IGroupItem groupItem){
        if(groupItem == null){
            return;
        }
        groupItems.remove(groupItem);
        RowItem item = groupToRowMap.get(groupItem.getGroupInfo());
        if(item == null || !mRowList.contains(item)){
            return;
        }
        groupToRowMap.remove(groupItem.getGroupInfo());
        int index = mRowList.indexOf(item);
        for (int pos = index+item.getGroupRowCounts()-1; pos >= index; pos--) {
            mRowList.remove(pos);
        }
    }
    
    @Override
    public synchronized int getCount() {
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
        return ShowStyle.STYLE_MAX_VALUE;// 
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
    
    protected synchronized void splitToRowItems(ArrayList<IGroupItem> groups){
        if(groups == null){
            return;
        }
        
        for (IGroupItem groupItem : groups) {
            addGroupItem(groupItem);
        }        
    }
    
    //获取分组的头部，
    public abstract View getGroupView( View convertView, RowItem item); 
    
    public abstract View getChildView(View convertView,RowItem item) ;
    /**
     * 拆分Group为多个行RowItem
     * @param groupItem
     * @param rowItems
     * @return 第一项RowItem，里面记录了该组被拆分的行数
     */
    public abstract RowItem splitToRowItems(IGroupItem groupItem, ArrayList<RowItem> rowItems);
}

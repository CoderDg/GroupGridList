
package com.nimo.show.view;

import android.R.integer;

import java.util.ArrayList;

public class RowItem {
    // 图片组合样式
    private ShowStyle showStyle;
    
    //子成员列表
    private ArrayList<IChildItem> columnItems;

    private String rowInfo;
    //该行在分组中的位置 0:start 1：mid 2：end
    private int groupPos;
    
    //记录group被拆分的row数,目前每个组只有第一个RowItem纪录
    private int groupRowCounts;

    public ArrayList<IChildItem> getColumnItems() {
        return columnItems;
    }

    public void setColumnItems(ArrayList<IChildItem> columnItem) {
        this.columnItems = columnItem;
    }

    public ShowStyle getShowStyle() {
        return showStyle;
    }

    public void setShowStyle(ShowStyle showStyle) {
        this.showStyle = showStyle;
    }

    public int getGroupPos() {
        return groupPos;
    }

    public void setGroupPos(int groupPos) {
        this.groupPos = groupPos;
    }

    public int getGroupRowCounts() {
        return groupRowCounts;
    }

    public void setGroupRowCounts(int groupRowCounts) {
        this.groupRowCounts = groupRowCounts;
    }

    public String getRowInfo() {
        return rowInfo;
    }

    public void setRowInfo(String rowInfo) {
        this.rowInfo = rowInfo;
    }

}

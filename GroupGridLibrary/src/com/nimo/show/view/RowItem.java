
package com.nimo.show.view;

import java.util.ArrayList;

public class RowItem {
    // 图片组合样式
    private int showStyle;
    
    //子成员列表
    private ArrayList<IChildItem> columnItems;
    
    //它所在的组
    private IGroupItem group;

    private String name;
    //该行在分组中的索引
    private int groupIndex;//0:start  1:mid 2:end    
    
    public IGroupItem getGroupItem() {
        return group;
    }

    public void setGroupItem(IGroupItem group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<IChildItem> getColumnItems() {
        return columnItems;
    }

    public void setColumnItems(ArrayList<IChildItem> columnItem) {
        this.columnItems = columnItem;
    }

    public int getShowStyle() {
        return showStyle;
    }

    public void setShowStyle(int showStyle) {
        this.showStyle = showStyle;
    }

    public int getGroupIndex() {
        return groupIndex;
    }

    public void setGroupIndex(int groupIndex) {
        this.groupIndex = groupIndex;
    }

    public static interface ShowStyle {
        //样式最大值，要大于所有的样式值
        int STYLE_MAX_VALUE = 2;
        
        // 0:group
        int STYLE_GROUP = 0;
        // 1:2个个长图
        int STYLE_TWO_SQUARE_IMG = 1;

    }
}

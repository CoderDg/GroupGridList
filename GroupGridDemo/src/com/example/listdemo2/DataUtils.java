
package com.example.listdemo2;

import com.nimo.show.view.IChildItem;
import com.nimo.show.view.IGroupItem;
import com.nimo.show.view.RowItem;
import com.nimo.show.view.RowItem.ShowStyle;

import java.util.ArrayList;

class DataUtils {

    private static ArrayList<IChildItem> columnItems;

    public static ArrayList<RowItem> formatData(ArrayList<IGroupItem> groups) {
        ArrayList<RowItem> rowList = new ArrayList<RowItem>();
        for (int i = 0; i < groups.size(); i++) {
            IGroupItem group = groups.get(i);
            // 创建group title
            RowItem groupItem = createRowItem(ShowStyle.STYLE_GROUP);
            groupItem.setName(groups.get(i).getName());
            groupItem.setGroupIndex(0);
            groupItem.setColumnItems(null);
            groupItem.setGroupItem(group);
            rowList.add(groupItem);

            columnItems = new ArrayList<IChildItem>();

            ArrayList<IChildItem> childList = groups.get(i).getChildList();
            int childCount = childList.size();
            for (int j = 0; j < childCount; j++) {
                IChildItem child = childList.get(j);
                columnItems.add(child);
                if( (j+1) %2 == 0){//每两个1组
                    rowList.add(createRowItem(ShowStyle.STYLE_TWO_SQUARE_IMG));
                }                      
            }
        }
        return rowList;
    }

    private static RowItem createRowItem(int showStyle) {
        RowItem tempItem = new RowItem();
        tempItem.setShowStyle(showStyle);
        tempItem.setColumnItems(columnItems);
        columnItems = new ArrayList<IChildItem>();
        return tempItem;
    }

}


package com.example.listdemo2;

import com.nimo.show.view.IChildItem;
import com.nimo.show.view.IGroupItem;
import com.nimo.show.view.RowItem;
import com.nimo.show.view.RowItem.ShowStyle;

import android.R.integer;

import java.util.ArrayList;

class DataUtils {

    private static ArrayList<IChildItem> columnItems;

    public static RowItem formatData(IGroupItem group,  ArrayList<RowItem> rowList) {
            // 创建group title
            RowItem groupItem = createRowItem(ShowStyle.STYLE_GROUP, 0);
            groupItem.setRowInfo(group.getGroupInfo());
            groupItem.setGroupPos(0);
            groupItem.setColumnItems(null);
            rowList.add(groupItem);
       
            columnItems = new ArrayList<IChildItem>();
            ArrayList<IChildItem> childList = group.getChildList();
            int childCount = childList.size();
            for (int j = 0; j < childCount; j++) {
                IChildItem child = childList.get(j);
                columnItems.add(child);
                if( (j+1) %2 == 0){//每两个1组
                    if(j+2>childCount){
                        rowList.add(createRowItem(ShowStyle.STYLE_TWO_SQUARE_IMG, 2));
                    }else{                        
                        rowList.add(createRowItem(ShowStyle.STYLE_TWO_SQUARE_IMG, 1));
                    }
                }                      
            }
            
            return groupItem;
    }

    private static RowItem createRowItem(int showStyle, int groupIndex) {
        RowItem tempItem = new RowItem();
        tempItem.setShowStyle(showStyle);
        tempItem.setColumnItems(columnItems);
        tempItem.setGroupPos(groupIndex);
        columnItems = new ArrayList<IChildItem>();
        return tempItem;
    }

}


package com.nimo.show.view;

import java.util.ArrayList;

public abstract class IGroupItem{

    private ArrayList<IChildItem> childList;

    private String groupInfo;
    
    public ArrayList<IChildItem> getChildList() {
        return childList;
    }

    public void setChildList(ArrayList<IChildItem> childList) {
        this.childList = childList;
    }

    public String getGroupInfo() {
        return groupInfo;
    }

    public void setGroupInfo(String groupInfo) {
        this.groupInfo = groupInfo;
    }
}


package com.nimo.show.view;

import java.util.ArrayList;

public abstract class IGroupItem implements Cloneable{

    private ArrayList<IChildItem> childList;

    private String name;
    
    public ArrayList<IChildItem> getChildList() {
        return childList;
    }

    public void setChildList(ArrayList<IChildItem> childList) {
        this.childList = childList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }

}

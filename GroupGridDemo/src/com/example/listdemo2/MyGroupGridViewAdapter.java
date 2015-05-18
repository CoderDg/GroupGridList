
package com.example.listdemo2;

import com.nimo.show.view.GroupGridViewAdapter;
import com.nimo.show.view.IChildItem;
import com.nimo.show.view.IGroupItem;
import com.nimo.show.view.RowItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import android.R.integer;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyGroupGridViewAdapter extends GroupGridViewAdapter {

    private DisplayImageOptions ops;

    private Context context;

    private Toast toast;

    private String defaultUrl = "drawable://" + R.drawable.square_bg;
    private LayoutInflater mInflater;

    public MyGroupGridViewAdapter(Context context, ArrayList<IGroupItem> groups) {
        super(context, groups);
        this.context = context;
        mInflater = LayoutInflater.from(context);
        ops = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.square_bg)
                .showImageForEmptyUri(R.drawable.square_bg).showImageOnFail(R.drawable.square_bg)
                .cacheInMemory(true).cacheOnDisk(true).considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565).imageScaleType(ImageScaleType.EXACTLY).build();
    }


    @Override
    public View getGroupView(int pos, View convertView, RowItem item) {
        GroupViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.group_title_layout, null);
            holder = new GroupViewHolder();
            holder.titleView = (TextView) convertView.findViewById(R.id.group_title_view);            
            convertView.setTag(holder);
        } else {
            holder = (GroupViewHolder) convertView.getTag();
        }
        holder.titleView.setText(item.getRowInfo());

        return convertView;
    }
    

    @Override
    public View getChildView(int pos, View convertView, RowItem item) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.child_pic_layout, null);
            holder = new ViewHolder();
            holder.firstImgView = (ImageView) convertView.findViewById(R.id.firstImgView);
            holder.secondImgView = (ImageView) convertView.findViewById(R.id.secondImgView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        ArrayList<IChildItem> childItems = item.getColumnItems();
        ImageLoader.getInstance().displayImage(childItems.get(0).getChildInfo(), holder.firstImgView,ops);
        ImageLoader.getInstance().displayImage(childItems.get(1).getChildInfo(), holder.secondImgView,ops);        
        return convertView;
    }

    static class GroupViewHolder {
        TextView titleView;
    }

    static class ViewHolder {
        ImageView firstImgView;
        ImageView secondImgView;
    }

    @Override
    public RowItem splitToRowItems(IGroupItem groupItem, ArrayList<RowItem> rowItems) {
        // TODO Auto-generated method stub
        return DataUtils.formatData(groupItem, rowItems);
    }
}

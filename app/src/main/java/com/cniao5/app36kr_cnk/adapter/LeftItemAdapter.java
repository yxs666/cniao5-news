package com.cniao5.app36kr_cnk.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cniao5.app36kr_cnk.R;
import com.cniao5.app36kr_cnk.application.CNKApplication;
import com.cniao5.app36kr_cnk.entity.LeftItemMenu;
import com.cniao5.app36kr_cnk.utils.MenuDataUtils;

import java.util.List;

/**
 * 当前类注释:
 * ProjectName：App36Kr_CNK
 * Author:<a href="http://www.cniao5.com">菜鸟窝</a>
 * Description：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，对课程与服务”吹毛求疵”般的要求，
 * 打造极致课程，是菜鸟窝不变的承诺
 */
public class LeftItemAdapter extends BaseAdapter {
    public LayoutInflater mInflater;
    private List<LeftItemMenu> itemMenus;
    public LeftItemAdapter(){
        mInflater=LayoutInflater.from(CNKApplication.getInstance());
          itemMenus= MenuDataUtils.getItemMenus();
    }
    @Override
    public int getCount() {
        return itemMenus!=null?itemMenus.size():0;
    }
    @Override
    public Object getItem(int position) {
        return itemMenus.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder _Holder=null;
        if(convertView==null){
            _Holder=new Holder();
            convertView=mInflater.inflate(R.layout.item_left_menu_layout,null);
            _Holder.item_left_view_img=(ImageView)convertView.findViewById(R.id.item_left_view_img);
            _Holder.item_left_view_title=(TextView)convertView.findViewById(R.id.item_left_view_title);
            convertView.setTag(_Holder);
        }else{
            _Holder=(Holder)convertView.getTag();
        }
        _Holder.item_left_view_img.setImageResource(itemMenus.get(position).getLeftIcon());
        _Holder.item_left_view_title.setText(itemMenus.get(position).getTitle());
        return convertView;
    }
    private static class Holder{
        ImageView item_left_view_img;
        TextView item_left_view_title;
    }
}

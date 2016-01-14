package com.cniao5.app36kr_cnk.ui;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cniao5.app36kr_cnk.R;
import com.cniao5.app36kr_cnk.adapter.LeftItemAdapter;
import com.cniao5.app36kr_cnk.biz.HeadDataManager;
import com.cniao5.app36kr_cnk.common.DefineView;
import com.cniao5.app36kr_cnk.entity.AdHeadBean;
import com.cniao5.app36kr_cnk.ui.base.BaseActivity;
import com.cniao5.app36kr_cnk.widget.DragLayout;
import com.nineoldandroids.view.ViewHelper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

/**
 * 当前类注释:主Activity类
 * ProjectName：App36Kr
 * Author:<a href="http://www.cniao5.com">菜鸟窝</a>
 * Description：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，对课程与服务”吹毛求疵”般的要求，
 * 打造极致课程，是菜鸟窝不变的承诺
 */
public class MainActivity extends BaseActivity implements DefineView{
    public DragLayout getDrag_layout() {
        return drag_layout;
    }

    private DragLayout drag_layout;
    private ImageView top_bar_icon;
    private ListView lv_left_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setStatusBar();
        initView();
        initValidata();
        initListener();
        bindData();
    }
    public void initView() {
        drag_layout = (DragLayout) findViewById(R.id.drag_layout);
        top_bar_icon = (ImageView) findViewById(R.id.top_bar_icon);
        lv_left_main=(ListView)findViewById(R.id.lv_left_main);
    }
    @Override
    public void initValidata() {
        lv_left_main.setAdapter(new LeftItemAdapter());
    }
    @Override
    public void initListener() {
        drag_layout.setDragListener(new CustomDragListener());
        top_bar_icon.setOnClickListener(new CustomOnClickListener());

    }
    @Override
    public void bindData() {

    }
    class CustomDragListener implements DragLayout.DragListener{

        /**
         * 界面打开
         */
        @Override
        public void onOpen() {

        }

        /**
         * 界面关闭
         */
        @Override
        public void onClose() {

        }

        /**
         * 界面进行滑动
         * @param percent
         */
        @Override
        public void onDrag(float percent) {
              ViewHelper.setAlpha(top_bar_icon,1-percent);
        }
    }
    class CustomOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View arg0) {
            drag_layout.open();
        }
    }

}

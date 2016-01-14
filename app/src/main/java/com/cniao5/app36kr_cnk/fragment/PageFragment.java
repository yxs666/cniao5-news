package com.cniao5.app36kr_cnk.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cniao5.app36kr_cnk.R;
import com.cniao5.app36kr_cnk.biz.CategoryDataManager;
import com.cniao5.app36kr_cnk.common.Config;
import com.cniao5.app36kr_cnk.common.DefineView;
import com.cniao5.app36kr_cnk.entity.CategoriesBean;
import com.cniao5.app36kr_cnk.fragment.base.BaseFragment;
import com.cniao5.app36kr_cnk.utils.OkhttpManager;
import com.squareup.okhttp.Request;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

/**
 * 当前类注释:页面Fragment
 * ProjectName：App36Kr_CNK
 * Author:<a href="http://www.cniao5.com">菜鸟窝</a>
 * Description：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，对课程与服务”吹毛求疵”般的要求，
 * 打造极致课程，是菜鸟窝不变的承诺
 */
public class PageFragment extends BaseFragment implements DefineView{
    private View mView;
    private static final String KEY="EXTRA";
    private CategoriesBean categoriesBean;
    private TextView tv_page;
    public static PageFragment newInstance(CategoriesBean extra){
        Bundle bundle=new Bundle();
        bundle.putSerializable(KEY,extra);
        PageFragment fragment=new PageFragment();
        fragment.setArguments(bundle);
        return  fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();
        if(bundle!=null) {
            categoriesBean=(CategoriesBean)bundle.getSerializable(KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mView==null){
            mView=inflater.inflate(R.layout.page_fragment_layout,container,false);
            initView();
            initValidata();
            initListener();
            bindData();
        }
        return mView;
    }
    @Override
    public void initView() {
        tv_page=(TextView)mView.findViewById(R.id.tv_page);
        tv_page.setText(categoriesBean.getTitle());
    }
    @Override
    public void initValidata() {
    }

    @Override
    public void initListener() {

    }

    @Override
    public void bindData() {

    }
}

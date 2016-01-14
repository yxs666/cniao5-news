package com.cniao5.app36kr_cnk.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cniao5.adapter.helper.BaseAdapterHelper;
import com.cniao5.adapter.helper.QuickAdapter;
import com.cniao5.app36kr_cnk.R;
import com.cniao5.app36kr_cnk.biz.HeadDataManager;
import com.cniao5.app36kr_cnk.biz.HomeNewsDataManager;
import com.cniao5.app36kr_cnk.common.Config;
import com.cniao5.app36kr_cnk.common.DefineView;
import com.cniao5.app36kr_cnk.entity.AdHeadBean;
import com.cniao5.app36kr_cnk.entity.CategoriesBean;
import com.cniao5.app36kr_cnk.entity.HomeNewsBean;
import com.cniao5.app36kr_cnk.fragment.base.BaseFragment;
import com.cniao5.app36kr_cnk.utils.OkhttpManager;
import com.cniao5.cwidgetutils.AutoGallery;
import com.cniao5.cwidgetutils.FlowIndicator;
import com.cniao5.cwidgetutils.PullToRefreshListView;
import com.squareup.okhttp.Request;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

/**
 * 当前类注释:
 * ProjectName：App36Kr_CNK
 * Author:<a href="http://www.cniao5.com">菜鸟窝</a>
 * Description：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，对课程与服务”吹毛求疵”般的要求，
 * 打造极致课程，是菜鸟窝不变的承诺
 */
public class HomeFragment extends BaseFragment implements DefineView {
    private View mView;
    private static final String KEY="EXTRA";
    private CategoriesBean categoriesBean;
    private PullToRefreshListView home_listview;
    private List<HomeNewsBean> homeNewsBeans;
    private List<AdHeadBean> adHeadBeans;
    private QuickAdapter<HomeNewsBean> quickAdapter;
    private String[] masks;
    private int[] mask_colors;
    private FrameLayout home_framelayout;
    private LinearLayout loading,empty,error;
    private View headView;
    private LayoutInflater mInflater;
    private AutoGallery headline_image_gallery;
    private FlowIndicator headline_circle_indicator;
    private int gallerySelectedPositon=0;//Gallery索引
    private int circleSelectedPosition = 0; // 默认指示器的圆圈的位置为第一

    public static HomeFragment newInstance(CategoriesBean extra){
        Bundle bundle=new Bundle();
        bundle.putSerializable(KEY,extra);
        HomeFragment fragment=new HomeFragment();
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
            mView=inflater.inflate(R.layout.home_fragment_layout,container,false);
            mInflater=LayoutInflater.from(getActivity());
            headView=mInflater.inflate(R.layout.gallery_indicator_layout,null);
            initView();
            initValidata();
            initListener();
        }
        return mView;
    }

    @Override
    public void initView() {
        home_listview=(PullToRefreshListView)mView.findViewById(R.id.home_listview);
        home_listview.addHeaderView(headView);
        home_framelayout=(FrameLayout)mView.findViewById(R.id.home_framelayout);
        loading=(LinearLayout)mView.findViewById(R.id.loading);
        empty=(LinearLayout)mView.findViewById(R.id.empty);
        error=(LinearLayout)mView.findViewById(R.id.error);

        //获取AutoGallery和FlowIndicator控件
        headline_image_gallery=(AutoGallery)headView.findViewById(R.id.headline_image_gallery);
        headline_circle_indicator=(FlowIndicator)headView.findViewById(R.id.headline_circle_indicator);
    }
    @Override
    public void initValidata() {
        masks=new String[]{"氪TV","O2O","新硬件","Fun!!","企业服务","Fit&Health","在线教育","互联网金融","大公司","专栏"};
        mask_colors=new int[]{R.color.mask_tags_1,R.color.mask_tags_2,
                R.color.mask_tags_3,R.color.mask_tags_4,R.color.mask_tags_5,
                R.color.mask_tags_6,R.color.mask_tags_7,R.color.mask_tags_8,
                R.color.mask_tags_9,R.color.mask_tags_10,R.color.mask_tags_11,R.color.mask_tags_12};

        home_listview.setVisibility(View.GONE);
        home_framelayout.setVisibility(View.VISIBLE);
        loading.setVisibility(View.VISIBLE);
        empty.setVisibility(View.GONE);
        error.setVisibility(View.GONE);

        OkhttpManager.getAsync(categoriesBean.getHref(), new OkhttpManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, Exception e) {
                Log.d("zttjianggqq","首页新闻数据加载失败...");
            }
            @Override
            public void requestSuccess(String result) {
                Document document=Jsoup.parse(result, Config.CRAWLER_URL);
                adHeadBeans=new HeadDataManager().getHeadBeans(document);
                homeNewsBeans=new HomeNewsDataManager().getHomeNewsBeans(document);
                if(adHeadBeans!=null&&homeNewsBeans!=null){
                    home_listview.setVisibility(View.VISIBLE);
                    home_framelayout.setVisibility(View.GONE);
                    loading.setVisibility(View.GONE);
                    empty.setVisibility(View.GONE);
                    error.setVisibility(View.GONE);
                    bindData();
                }else{
                    home_listview.setVisibility(View.GONE);
                    home_framelayout.setVisibility(View.VISIBLE);
                    loading.setVisibility(View.GONE);
                    empty.setVisibility(View.VISIBLE);
                    error.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public void initListener() {

    }

    @Override
    public void bindData() {
        int topSize=adHeadBeans.size();
        //设置指示器
        headline_circle_indicator.setCount(topSize);
        headline_circle_indicator.setSeletion(circleSelectedPosition);
        //设置画廊Gallery
        headline_image_gallery.setLength(topSize);
        gallerySelectedPositon=topSize*50+circleSelectedPosition;
        headline_image_gallery.setSelection(gallerySelectedPositon);
        headline_image_gallery.setDelayMillis(4000);
        headline_image_gallery.start();
        headline_image_gallery.setAdapter(new GalleryAdapter());
        headline_image_gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                circleSelectedPosition=position;
                gallerySelectedPositon=circleSelectedPosition%adHeadBeans.size();
                headline_circle_indicator.setSeletion(gallerySelectedPositon);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        quickAdapter=new QuickAdapter<HomeNewsBean>(getActivity(),R.layout.item_home_news_layout,homeNewsBeans) {
            @Override
            protected void convert(BaseAdapterHelper helper, HomeNewsBean item) {
                String mask=item.getMask();
                helper.setText(R.id.item_news_tv_name,item.getAuthorBean().getName())
                      .setText(R.id.item_news_tv_time,item.getDatetext())
                      .setText(R.id.item_news_tv_type,mask)
                      .setText(R.id.item_news_tv_title,item.getTitle())
                        .setImageUrl(R.id.item_news_tv_img,item.getImgurl())
                .setImageUrl(R.id.item_news_img_icon,item.getAuthorBean().getAvatar());
                int index=0;
                for(int i=0;i<masks.length;i++){
                    if(masks[i].equals(mask)){
                        index=i;
                        break;
                    }
                }
                TextView tv_type=(TextView)helper.getView(R.id.item_news_tv_type);
                tv_type.setTextColor(getActivity().getResources().getColor(mask_colors[index]));
                helper.getView(R.id.item_news_tv_arrow).setBackgroundColor(getActivity().getResources().getColor(mask_colors[index]));
            }
        };
        home_listview.setAdapter(quickAdapter);
    }

    /**
     * AutoGallery的自定义Adapter
     */
   class GalleryAdapter extends BaseAdapter{

       @Override
       public int getCount() {
           return Integer.MAX_VALUE;
       }

       @Override
       public Object getItem(int position) {
           return adHeadBeans.get(position);
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
               convertView=mInflater.inflate(R.layout.item_gallery_layout,null);
               _Holder.item_head_gallery_img=(ImageView)convertView.findViewById(R.id.item_head_gallery_img);
               convertView.setTag(_Holder);
           }else {
               _Holder=(Holder)convertView.getTag();
           }
           //显示数据
           Picasso.with(mView.getContext()).load(adHeadBeans.get(position%adHeadBeans.size()).getImgurl()).into(_Holder.item_head_gallery_img);
           return convertView;
       }
   }

   private static class  Holder{
        ImageView item_head_gallery_img;
    }
}

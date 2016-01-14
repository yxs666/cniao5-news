package com.cniao5.app36kr_cnk.utils;

import com.cniao5.app36kr_cnk.entity.CategoriesBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 当前类注释:
 * ProjectName：App36Kr_CNK
 * Author:<a href="http://www.cniao5.com">菜鸟窝</a>
 * Description：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，对课程与服务”吹毛求疵”般的要求，
 * 打造极致课程，是菜鸟窝不变的承诺
 */
public class CategoryDataUtils {
    public static List<CategoriesBean>  getCategoryBeans(){
        List<CategoriesBean>  beans=new ArrayList<>();
        beans.add(new CategoriesBean("全部","http://www.36kr.com/","全部"));
        beans.add(new CategoriesBean("氪TV","http://www.36kr.com/columns/tv","tv"));
        beans.add(new CategoriesBean("O2O","http://www.36kr.com/columns/o2o","o2o"));
        beans.add(new CategoriesBean("新硬件","http://www.36kr.com/columns/hardware","hardware"));
        beans.add(new CategoriesBean("Fun!!","http://www.36kr.com/columns/fun","fun"));
        beans.add(new CategoriesBean("企业服务","http://www.36kr.com/columns/enterprise","enterprise"));
        beans.add(new CategoriesBean("Fit&Health","http://www.36kr.com/columns/sports","sports"));
        beans.add(new CategoriesBean("在线教育","http://www.36kr.com/columns/edu","edu"));
        beans.add(new CategoriesBean("互联网金融","http://www.36kr.com/columns/finance","finance"));
        beans.add(new CategoriesBean("大公司","http://www.36kr.com/columns/company","company"));
        beans.add(new CategoriesBean("专栏","http://www.36kr.com/columns/column","column"));
        return beans;
    }
}

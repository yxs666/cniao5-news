package com.cniao5.app36kr_cnk.biz;

import com.cniao5.app36kr_cnk.entity.CategoriesBean;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 分类Tab数据抓取服务
 * @author jiangqq
 *
 */
public class CategoryDataManager {
	public CategoryDataManager(){
    }
	
	public List<CategoriesBean> getCategoriesBeans(Document document){
		List<CategoriesBean> categoriesBeans=new ArrayList<CategoriesBean>();
		Elements elements=document.select("div.categories").first().select("a");
		for (Element element : elements) {
			String title=element.text();
			String data_type=element.attr("data-type");
			String href=element.attr("abs:href");
			CategoriesBean bean=new CategoriesBean();
			bean.setTitle(title);
			bean.setHref(href);
			bean.setData_type(data_type);
			categoriesBeans.add(bean);
		}
		return categoriesBeans;
	}
	
	/**
	 * 抓取文章分类数据
	 * @param document
	 * @return
	 */
	public List<CategoriesBean> getCategoriesBeans_CNK(Document document){
		List<CategoriesBean> categoriesBeans=new ArrayList<CategoriesBean>();
		Elements cateElement=document.select("div.categories").first().select("a");
		//System.out.println(cateElement);
		for (Element element : cateElement) {
			String title=element.text();
			String href=element.attr("abs:href");
			String data_type=element.attr("data-type");
			categoriesBeans.add(new CategoriesBean(title, href, data_type));
		}
		return categoriesBeans;
	}
}

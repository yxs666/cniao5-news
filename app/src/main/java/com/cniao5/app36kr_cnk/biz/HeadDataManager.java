package com.cniao5.app36kr_cnk.biz;

import com.cniao5.app36kr_cnk.entity.AdHeadBean;
import com.cniao5.app36kr_cnk.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * 首页顶部广告数据抓取工具类
 * @author jiangqq
 *
 */
public class HeadDataManager {
	public HeadDataManager(){
   
     }
	/**
	 * 进行根据地址抓取顶部广告数据
	 * @param pUrl
	 * @return
	 */
	public List<AdHeadBean> getHeadBeans(Document document){
	   List<AdHeadBean> adHeadBeans=new ArrayList<AdHeadBean>();;
	   Elements elements=document.select("div.head-images");
	   Elements links = elements.first().select("a[data-lazyload]"); //带有data-lazyload属性的a元素
	   for (Element element : links) {
		  String title=element.text();
		  String href=element.attr("href");
		  String imgurl= ImageUtils.getCutImageUrl(element.attr("data-lazyload"));
		  String mask= element.select("span").first().text();
		  AdHeadBean bean=new AdHeadBean();
		  bean.setTitle(title);
		  bean.setImgurl(imgurl);
		  bean.setHref(href);
		  bean.setMask(mask);
		  adHeadBeans.add(bean);
	   }
       return adHeadBeans;
	}
	
	
	/**
	 * 进行抓取广告轮播信息
	 * @param document
	 * @return
	 */
	public List<AdHeadBean> getHeadBeans_CNK(Document document){
		List<AdHeadBean> adHeadBeans=new ArrayList<AdHeadBean>();
		Elements headElements=document.select("div.head-images").first().select("a[data-lazyload]");
		//System.out.println(headElements);
		for (Element element : headElements) {
			String imgurl=ImageUtils.getCutImageUrl(element.attr("data-lazyload"));
			String href=element.attr("href");
			String mask=element.select("span").first().text();
			String title=element.text();
			adHeadBeans.add(new AdHeadBean(title, imgurl, href, mask));
		}
		return adHeadBeans;
	}
	
}

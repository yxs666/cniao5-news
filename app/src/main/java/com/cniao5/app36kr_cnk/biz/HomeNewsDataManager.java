package com.cniao5.app36kr_cnk.biz;

import com.cniao5.app36kr_cnk.common.Config;
import com.cniao5.app36kr_cnk.entity.AuthorBean;
import com.cniao5.app36kr_cnk.entity.HomeNewsBean;
import com.cniao5.app36kr_cnk.utils.ImageUtils;
import com.cniao5.app36kr_cnk.utils.CTextUtils;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * 首页新闻数据抓取管理器
 * @author jiangqq
 *
 */
public class HomeNewsDataManager {
  public HomeNewsDataManager(){ 
  }
  /**
   * 进行抓取首页信息数据
   * @param document
   * @return
   */
  public List<HomeNewsBean> getHomeNewsBeans(Document document){
	  List<HomeNewsBean> homeNewsBeans=new ArrayList<HomeNewsBean>();
	  Elements elements= document.select("div.articles").first().select("article");
	  for (Element element : elements) {
		  //图标以及文章类型
		  Element a_pic_element=element.select("a.pic").first();
		  String imgurl="";
		  String mask="";
		  if(a_pic_element!=null){
			   imgurl= ImageUtils.getCutImageUrl(a_pic_element.attr("data-lazyload"));
			   mask=a_pic_element.text();
		  }
		  //desc信息 连接地址和标题
		  Element desc_element=element.select("div.desc").first();
		  String href=desc_element.select("a.title").first().attr("href");
		  //进行href过滤 因为网站又文章列表无法点击 ，所以这边直接滤过了
		  if(href.equals("javascript:void(0)")){
			  continue;
		  }
		  String tId=CTextUtils.getTitleId(href);
		  href=Config.CRAWLER_URL+href;
		  String title=desc_element.select("a.title").first().text();
		  //作者信息
		  Element author_element=desc_element.select("div.author").first();
		  //查找只存在data-lazyload属性的a标签
		  Element link = author_element.select("a").first();
		  String author_href= Config.CRAWLER_URL+link.attr("href");
		  String avatar=ImageUtils.getCutImageUrl(link.select("span.avatar").first().attr("data-lazyload"));
		  String name=link.text();
		  //时间
		  Element time_element=author_element.select("time.timeago").first();
		  String datetime="";
		  String datetext="";
		  if(time_element!=null){
			   datetime=author_element.select("time.timeago").first().attr("title"); 
			   datetext=author_element.select("time.timeago").first().text();
		  }else {
			   datetime=author_element.select("abbr.timeago").first().attr("title"); 
			   datetext=author_element.select("abbr.timeago").first().text();
		}
		  //文章简介
		  String brief=desc_element.select("div.brief").first().text();
		  AuthorBean authorBean=new AuthorBean();
		  authorBean.setName(name);
		  authorBean.setAvatar(avatar);
		  authorBean.setHref(author_href);
		  HomeNewsBean bean=new HomeNewsBean();
		  bean.settId(tId);
		  bean.setImgurl(imgurl);
		  bean.setMask(mask);
		  bean.setHref(href);
		  bean.setTitle(title);
		  bean.setAuthorBean(authorBean);
		  bean.setDatetime(datetime);
		  bean.setBrief(brief);
		  bean.setDatetext(datetext);
		  homeNewsBeans.add(bean);
	  }
	  return homeNewsBeans;
  }
  
  /**
   * 抓取文章类别数据 根据分类
   * @param document
   * @return
   */
  public List<HomeNewsBean> getHomeNewsBeans_CNK(Document document){
	  List<HomeNewsBean> homeNewsBeans=new ArrayList<HomeNewsBean>();
	  Elements articleElement=document.select("div.articles").first().select("article");
	  for (Element element : articleElement) {
		Element pic_element=element.select("a.pic").first();
		String imgurl=ImageUtils.getCutImageUrl(pic_element.attr("data-lazyload"));
		String href=pic_element.attr("abs:href");
		String mask=pic_element.text();
		String aId=CTextUtils.getArticleId(href);	
		
		Element descElement=element.select("div.desc").first();
		String title=descElement.select("a.title").first().text();
		String brief=descElement.select("div.brief").first().text();
		
		Element authorElement=descElement.select("div.author").first();
		String datetime=authorElement.select("time.timeago").first().attr("datetime");
		String datetext=authorElement.select("time.timeago").first().text();
		
		Element a_Element=authorElement.select("a").first();
		String au_href=a_Element.attr("abs:href");
	    Element avatarElement=a_Element.select("span.avatar").first();
	    String avatar=avatarElement.attr("data-lazyload");
	    String name=a_Element.select("span.name").first().text();
		AuthorBean authorBean=new AuthorBean();
		authorBean.setHref(au_href);
		authorBean.setAvatar(avatar);
		authorBean.setName(name);
		HomeNewsBean bean=new HomeNewsBean();
		bean.settId(aId);
		bean.setImgurl(imgurl);
		bean.setHref(href);
		bean.setMask(mask);
		bean.setTitle(title);
		bean.setBrief(brief);
		bean.setDatetime(datetime);
		bean.setDatetext(datetext);
		bean.setAuthorBean(authorBean);
	    homeNewsBeans.add(bean);
	  }
	  
	  return homeNewsBeans;
  }
}

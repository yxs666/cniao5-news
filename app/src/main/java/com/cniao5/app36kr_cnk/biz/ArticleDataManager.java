package com.cniao5.app36kr_cnk.biz;

import com.cniao5.app36kr_cnk.entity.ArticleBean;
import com.cniao5.app36kr_cnk.entity.AuthorBean;
import com.cniao5.app36kr_cnk.entity.TagBean;
import com.cniao5.app36kr_cnk.utils.CTextUtils;
import com.cniao5.app36kr_cnk.utils.HttpRequest;
import com.cniao5.app36kr_cnk.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * 文章详情数据 以及作者，搜索标签相关信息数据抓取服务器类
 * 当前类注释:
 * ProjectName：36KrDataCrawler
 * Author:<a href="http://www.cniao5.com">菜鸟窝</a>
 * Description：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，对课程与服务”吹毛求疵”般的要求，
 * 打造极致课程，是菜鸟窝不变的承诺
 */
public class ArticleDataManager {
	private String articleId;
    public ArticleDataManager(String articleId){
    	this.articleId=articleId;
    }
    /**
     * 进行根据HTML Document对象抓取文章相关信息
     * @param document
     * @return
     */
    public ArticleBean getArticleBean(Document document){
    	ArticleBean articleBean=new ArticleBean();
    	//首先获取局部的文章相关数据
    	Element singleElement=document.select("article.single-post").first();
    	//获取标题
    	//single-post__title
    	String title=singleElement.select("h1.single-post__title").first().text();
    	//获取时间
    	String datetime=singleElement.select("time.timeago").first().attr("datetime");
    	String datetext=singleElement.select("time.timeago").first().text();
    	//获取头图片
    	String headImage= ImageUtils.getCutImageUrl(singleElement.select("div.single-post-header__headline").first().select("img[src]").first().attr("src"));
    	//获取文章内容
    	String context=Jsoup.parseBodyFragment(singleElement.select("section.article").first().toString()).toString();
    	articleBean.setTitle(title);
    	articleBean.setDatetime(datetime);
    	articleBean.setDatetext(datetext);
    	articleBean.setHeadImage(headImage);
    	articleBean.setContext(CTextUtils.replaceSSymbol(context));
    	//抓取搜索标签
        Elements tagElements=singleElement.select("span.tag-item");
        if(tagElements!=null&&tagElements.size()>0){
        	List<TagBean> tagBeans=new ArrayList<TagBean>();
        	for (Element element : tagElements) {
        	   Element a_Element=element.select("a").first();
			   String href=a_Element.attr("abs:href");
			   String tagname=a_Element.text();
			   tagBeans.add(new TagBean(href, tagname));
        	}
        	articleBean.setTagBeans(tagBeans);
        }
        //开始抓取用户信息
	    String author_Str=HttpRequest.sendPost("http://36kr.com/asynces/posts/author_info", "url_code="+articleId);
	    AuthorBean bean=null;
	    try {
			JSONObject authorObject=new JSONObject(author_Str);
			String name=authorObject.getString("name");
			String description=CTextUtils.replaceEmail(authorObject.getString("tagline"));
			String avatar=ImageUtils.getCutImageUrl(authorObject.getString("avatar"));
			String badge=authorObject.getString("role");
			String article_total=authorObject.getString("posts_count");
			String read_number=authorObject.getString("views_count");
			String href="http:"+authorObject.getString("more_articles");
			bean=new AuthorBean();
			bean.setName(name);
			bean.setDescription(description);
			bean.setAvatar(avatar);
			bean.setBadge(badge);
			bean.setArticle_total(article_total);
			bean.setRead_number(read_number);
			bean.setHref(href);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	    articleBean.setAuthorBean(bean);
    	return articleBean;
    }
    
    /**
     * 进行抓取文章详情
     * @param document
     * @return
     */
    public ArticleBean getArticleBean_CNK(Document document){
    	ArticleBean bean=new ArticleBean();
    	Element singleElement=document.select("article.single-post").first();
    	String title=singleElement.select("h1.single-post__title").first().text();
    	String datetime=singleElement.select("time.timeago").first().attr("datetime");
    	String datetext=singleElement.select("time.timeago").first().text();
    	Element head=singleElement.select("div.single-post-header__headline").first();
    	String headImage= ImageUtils.getCutImageUrl(head.select("img").first().attr("src"));
    	String context=singleElement.select("section.article").first().toString();
    	bean.setTitle(title);
    	bean.setDatetime(datetime);
    	bean.setDatetext(datetext);
    	bean.setHeadImage(headImage);
    	bean.setContext(context);
    	
    	//开始抓取标签Tag数据
        Elements tagsElements= singleElement.select("section.single-post-tags").first().select("span.tag-item");
        List<TagBean> tagBeans=new ArrayList<TagBean>();
    	for (Element element : tagsElements) {
    		String href=element.select("a").first().attr("abs:href");
    		String tagname=element.text();
			TagBean tagBean=new TagBean();
			tagBean.setHref(href);
			tagBean.setTagname(tagname);
			tagBeans.add(tagBean);
		}
    	bean.setTagBeans(tagBeans);
    	//开始抓取作者信息
    	AuthorBean authorBean=new AuthorBean();
    	String result= HttpRequest.sendPost("http://36kr.com/asynces/posts/author_info", "url_code=" + articleId);
    	try {
			JSONObject authorObject=new JSONObject(result);
			String name=authorObject.getString("name");
			String avatar=authorObject.getString("avatar");
			String badge=authorObject.getString("role");
			String description=authorObject.getString("tagline");
			String href="http:"+authorObject.getString("more_articles");
			String article_total=authorObject.getString("posts_count");
			String read_number=authorObject.getString("views_count");
			authorBean.setName(name);
			authorBean.setAvatar(avatar);
			authorBean.setBadge(badge);
			authorBean.setDescription(description);
			authorBean.setHref(href);
			authorBean.setArticle_total(article_total);
			authorBean.setRead_number(read_number);
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	bean.setAuthorBean(authorBean);
    	return bean;
    }
}

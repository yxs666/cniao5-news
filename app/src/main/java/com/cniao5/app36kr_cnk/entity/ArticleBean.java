package com.cniao5.app36kr_cnk.entity;

import java.util.List;

/**
 * 
 * 当前类注释:文章详情数据bean ProjectName：36KrDataCrawler Author:<a
 * href="http://www.cniao5.com">菜鸟窝</a> Description：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，对课程与服务”吹毛求疵”般的要求， 打造极致课程，是菜鸟窝不变的承诺
 */
public class ArticleBean {
	private String title; // 文章标题
	private String datetime; // 文章发表时间
	private String datetext; // 文章发表时间时间 已计算
	private String context; // 文章内容
	private String headImage;  //文章头图片

	private List<TagBean> tagBeans; // 文章搜索标签集合
	private AuthorBean authorBean; // 文章作者信息

	public ArticleBean() {
		super();
	}

	public ArticleBean(String title, String datetime, String datetext,
			String context, String headImage, List<TagBean> tagBeans,
			AuthorBean authorBean) {
		super();
		this.title = title;
		this.datetime = datetime;
		this.datetext = datetext;
		this.context = context;
		this.headImage = headImage;
		this.tagBeans = tagBeans;
		this.authorBean = authorBean;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getDatetext() {
		return datetext;
	}

	public void setDatetext(String datetext) {
		this.datetext = datetext;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public List<TagBean> getTagBeans() {
		return tagBeans;
	}

	public void setTagBeans(List<TagBean> tagBeans) {
		this.tagBeans = tagBeans;
	}

	public AuthorBean getAuthorBean() {
		return authorBean;
	}

	public void setAuthorBean(AuthorBean authorBean) {
		this.authorBean = authorBean;
	}

	@Override
	public String toString() {
		return "ArticleBean [title=" + title + ", datetime=" + datetime
				+ ", datetext=" + datetext + ", context=" + context
				+ ", headImage=" + headImage + ", tagBeans=" + tagBeans
				+ ", authorBean=" + authorBean + "]";
	}

	
}

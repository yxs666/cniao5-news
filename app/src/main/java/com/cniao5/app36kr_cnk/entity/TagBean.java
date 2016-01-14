package com.cniao5.app36kr_cnk.entity;

/**
 * 
 * 当前类注释:文章标签信息实体类 ProjectName：36KrDataCrawler Author:<a
 * href="http://www.cniao5.com">菜鸟窝</a> Description：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，对课程与服务”吹毛求疵”般的要求， 打造极致课程，是菜鸟窝不变的承诺
 */
public class TagBean {
	private String href;
	private String tagname;

	public TagBean() {
		super();
	}

	public TagBean(String href, String tagname) {
		super();
		this.href = href;
		this.tagname = tagname;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getTagname() {
		return tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}

	@Override
	public String toString() {
		return "TagBean [href=" + href + ", tagname=" + tagname + "]";
	}

}

package com.cniao5.app36kr_cnk.entity;

/**
 * 首页列表数据信息实体类
 * 
 * @author jiangqq
 *
 */
public class HomeNewsBean {
	private String tId;  //文章ID
	private String imgurl; // 文章列表缩略图片
	private String mask; // 文章类型
	private String href; // 文章地址
	AuthorBean authorBean; // 用户信息
	private String title; // 文章标题
	private String brief; // 文章简述
	private String datetime; // 文章发表时间
	private String datetext;  //文章发表时间时间 已计算

	public HomeNewsBean() {
		super();
	}

	public HomeNewsBean(String tId, String imgurl, String mask, String href,
			AuthorBean authorBean, String title, String brief, String datetime,
			String datetext) {
		super();
		this.tId = tId;
		this.imgurl = imgurl;
		this.mask = mask;
		this.href = href;
		this.authorBean = authorBean;
		this.title = title;
		this.brief = brief;
		this.datetime = datetime;
		this.datetext = datetext;
	}

	public String gettId() {
		return tId;
	}

	public void settId(String tId) {
		this.tId = tId;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getMask() {
		return mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public AuthorBean getAuthorBean() {
		return authorBean;
	}

	public void setAuthorBean(AuthorBean authorBean) {
		this.authorBean = authorBean;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
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

	@Override
	public String toString() {
		return "HomeNewsBean [tId=" + tId + ", imgurl=" + imgurl + ", mask="
				+ mask + ", href=" + href + ", authorBean=" + authorBean
				+ ", title=" + title + ", brief=" + brief + ", datetime="
				+ datetime + ", datetext=" + datetext + "]";
	}
}

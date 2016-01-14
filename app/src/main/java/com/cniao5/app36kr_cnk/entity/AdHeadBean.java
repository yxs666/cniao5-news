package com.cniao5.app36kr_cnk.entity;

/**
 * 首页顶部广告条信息实体类
 * 
 * @author jiangqq
 *
 */
public class AdHeadBean {
	private String title; // 标题
	private String imgurl; // 图片地址
	private String href; // 文章详情地址
	private String mask; // 文章类型
	public AdHeadBean() {
		super();
	}
	public AdHeadBean(String title, String imgurl, String href, String mask) {
		super();
		this.title = title;
		this.imgurl = imgurl;
		this.href = href;
		this.mask = mask;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getMask() {
		return mask;
	}
	public void setMask(String mask) {
		this.mask = mask;
	}
	@Override
	public String toString() {
		return "AdHeadBean [title=" + title + ", imgurl=" + imgurl + ", href="
				+ href + ", mask=" + mask + "]";
	}

	
}

package com.cniao5.app36kr_cnk.entity;

/**
 * 作者用户信息实体类
 * 
 * @author jiangqq
 *
 */
public class AuthorBean {
	private String name; // 作者名字
	private String avatar; // 作者头像
	private String href; // 作者主页地址
	private String badge; // 作者等级 (例如:新人作者)
	private String description; // 作者个人说明
	private String article_total; // 发表文章的总量
	private String read_number; // 文章的总阅读量

	public AuthorBean() {
		super();
	}

	public AuthorBean(String name, String avatar, String href, String badge,
			String description, String article_total, String read_number) {
		super();
		this.name = name;
		this.avatar = avatar;
		this.href = href;
		this.badge = badge;
		this.description = description;
		this.article_total = article_total;
		this.read_number = read_number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getBadge() {
		return badge;
	}

	public void setBadge(String badge) {
		this.badge = badge;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getArticle_total() {
		return article_total;
	}

	public void setArticle_total(String article_total) {
		this.article_total = article_total;
	}

	public String getRead_number() {
		return read_number;
	}

	public void setRead_number(String read_number) {
		this.read_number = read_number;
	}

	@Override
	public String toString() {
		return "AuthorBean [name=" + name + ", avatar=" + avatar + ", href="
				+ href + ", badge=" + badge + ", description=" + description
				+ ", article_total=" + article_total + ", read_number="
				+ read_number + "]";
	}
}

package com.stackroute.newsapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This POJO Class to Maintain NEWS
 * 
 * @author Ashok
 *
 */
@Entity
@Table(name = "news")
public class News {

	@Override
	public String toString() {
		return "News [newsId=" + newsId + ", author=" + author + ", title=" + title + ", description=" + description
				+ ", url=" + url + ", urlToImage=" + urlToImage + ", publishedAt=" + publishedAt + ", content="
				+ content + ", username=" + username + "]";
	}

	@Id
	@Column(name = "news_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int newsId;

	@Column(name = "author", length = 5000)
	private String author;

	@Column(name = "title", length = 5000)
	private String title;

	@Column(name = "description", length = 5000)
	private String description;

	@Column(name = "url", length = 5000)
	private String url;

	@Column(name = "url_to_image", length = 5000)
	private String urlToImage;

	@Column(name = "published_at", length = 5000)
	private String publishedAt;

	@Column(name = "content", length = 5000)
	private String content;

	@Column(name = "username")
	private String username;

	public News() {
		super();
		// TODO Auto-generated constructor stub
	}

	public News(int newsId, String author, String title, String description, String url, String urlToImage,
			String publishedAt, String content, String username) {
		super();
		this.newsId = newsId;
		this.author = author;
		this.title = title;
		this.description = description;
		this.url = url;
		this.urlToImage = urlToImage;
		this.publishedAt = publishedAt;
		this.content = content;
		this.username = username;
	}

	public int getNewsId() {
		return newsId;
	}

	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlToImage() {
		return urlToImage;
	}

	public void setUrlToImage(String urlToImage) {
		this.urlToImage = urlToImage;
	}

	public String getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(String publishedAt) {
		this.publishedAt = publishedAt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}

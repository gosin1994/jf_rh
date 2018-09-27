package com.jt.entity;

public class Keyword {
	private Integer id;
	private String keyword;
	private String url;
	
	
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getKeyword() {
		return keyword;
	}



	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	@Override
	public String toString() {
		return "Keyword [id=" + id + ", keyword=" + keyword + ", url=" + url + "]";
	}
	
	

}

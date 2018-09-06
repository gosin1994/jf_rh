package com.jt.entity;

import java.util.ArrayList;
import java.util.List;

public class Node {
	
	private Integer id;
	
	private String text;
	
	private String href;
	
	private List<Node> nodes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	
	public void addNode(Node node) {
		
		if(this.nodes==null){
			nodes = new ArrayList<Node>();
		}
		nodes.add(node);
	}
	
}

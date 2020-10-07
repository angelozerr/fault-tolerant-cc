package com.redhat.beaverama.parser;

import java.util.List;

public abstract class Node implements NodeAPI {

	public TokenKind kind;
	public Node parent;
	public List<NodeAPI> children;
	
	public Node() {
		// TODO Auto-generated constructor stub
	}
	
	public Node(TokenKind kind, Node parent, List<NodeAPI> children) {
		super();
		this.kind = kind;
		this.parent = parent;
		this.children = children;
	}
	
	
}

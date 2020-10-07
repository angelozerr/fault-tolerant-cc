package com.redhat.beaverama.parser.ast;

import java.util.List;

import com.redhat.beaverama.parser.Node;
import com.redhat.beaverama.parser.NodeAPI;
import com.redhat.beaverama.parser.Token;
import com.redhat.beaverama.parser.TokenKind;

public class IfStatement extends Node {

	public Token ifKeyword;
	public Token openParen;
	public Token closeParen;
	
	@Override
	public int getStart() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getEndPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

}

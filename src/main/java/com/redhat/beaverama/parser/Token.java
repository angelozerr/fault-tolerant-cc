package com.redhat.beaverama.parser;

public class Token implements NodeAPI {

	public final TokenKind kind;
	public final int fullStart;
	public final int start;
	public final int length;

	public Token(TokenKind kind, int fullStart, int start, int length) {
		this.kind = kind;
		this.fullStart = fullStart;
		this.start = start;
		this.length = length;
	}

	public TokenKind getKind() {
		return kind;
	}

	public int getFullStart() {
		return fullStart;
	}

	@Override
	public int getStart() {
		return start;
	}

	public int getLength() {
		return length;
	}

	@Override
	public int getEndPosition() {
		return fullStart + length;
	}

}

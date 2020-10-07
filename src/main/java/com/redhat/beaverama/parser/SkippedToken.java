package com.redhat.beaverama.parser;

public class SkippedToken extends Token {

	public SkippedToken(TokenKind kind, int fullStart, int start, int length) {
		super(kind, fullStart, start, length);
	}

}

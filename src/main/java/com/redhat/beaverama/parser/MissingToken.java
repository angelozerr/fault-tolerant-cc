package com.redhat.beaverama.parser;

public class MissingToken extends Token {

	public MissingToken(TokenKind kind, int fullStart) {
		super(kind, fullStart, fullStart, 0);
	}

}

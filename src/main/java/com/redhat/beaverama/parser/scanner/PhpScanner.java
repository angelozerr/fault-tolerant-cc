package com.redhat.beaverama.parser.scanner;

import com.redhat.beaverama.parser.TokenKind;

public class PhpScanner implements Scanner {

	private TokenKind tokenType;
	private int tokenOffset;
	private String tokenError;
	private ScannerState state;
	private MultiLineStream stream;

	public PhpScanner(String input) {

	}

	public PhpScanner(String input, int initialOffset, ScannerState initialState) {
		stream = new MultiLineStream(input, initialOffset);
		state = initialState;
		tokenOffset = 0;
		tokenType = TokenKind.Unknown;
	}

	public TokenKind scan() {
		int offset = stream.pos();
		ScannerState oldState = state;
		TokenKind token = internalScan();
		if (token != TokenKind.EOS && offset == stream.pos()) {
			log("Scanner.scan has not advanced at offset " + offset + ", state before: " + oldState + " after: "
					+ state);
			stream.advance(1);
			return finishToken(offset, TokenKind.Unknown);
		}
		return token;
	}

	private TokenKind internalScan() {
		int offset = stream.pos();
		if (stream.eos()) {
			return finishToken(offset, TokenKind.EOS);
		}
		String errorMessage = null;

		switch (state) {
		
		case WithinContent: {
			if (stream.advanceIfChars(Constants.IF)) { // if
				state = ScannerState.AfterIf;
				return finishToken(offset, TokenKind.IfKeyword);
			}
			break;
		}
		
		
		case AfterIf: {
			if (stream.skipWhitespace()) {
				return finishToken(offset, TokenKind.Whitespace);
			}
			if (stream.advanceIfChars(Constants._ORB)) { // (
				state = ScannerState.AfterOpenParenthese;
				return finishToken(offset, TokenKind.OpenParenthese);
			}
			break;
		}
		
		case AfterOpenParenthese: {
			if (stream.skipWhitespace()) {
				return finishToken(offset, TokenKind.Whitespace);
			}
			if (stream.advanceIfChars(Constants._CRB)) { // )
				state = ScannerState.AfterCloseParenthese;
				return finishToken(offset, TokenKind.CloseParenthese);
			}
			break;
		}
		
		}
		stream.advance(1);
		state = ScannerState.WithinContent;
		return finishToken(offset, TokenKind.Unknown, errorMessage);
	}

	TokenKind finishToken(int offset, TokenKind type) {
		return finishToken(offset, type, null);
	}

	TokenKind finishToken(int offset, TokenKind type, String errorMessage) {
		tokenType = type;
		tokenOffset = offset;
		tokenError = errorMessage;
		return type;
	}

	private void log(String message) {
		System.err.println(message);
	}

	@Override
	public TokenKind getTokenType() {
		return tokenType;
	}

	@Override
	public int getTokenOffset() {
		return tokenOffset;
	}

	@Override
	public int getTokenLength() {
		return stream.pos() - tokenOffset;
	}

	@Override
	public int getTokenEnd() {
		return stream.pos();
	}

	@Override
	public String getTokenText() {
		return stream.getSource().substring(tokenOffset, stream.pos());
	}

	@Override
	public String getTokenError() {
		return tokenError;
	}

	@Override
	public ScannerState getScannerState() {
		return state;
	}

	public static Scanner createScanner(String input) {
		return createScanner(input, 0);
	}

	public static Scanner createScanner(String input, int initialOffset) {
		return createScanner(input, initialOffset, ScannerState.WithinContent);
	}

	public static Scanner createScanner(String input, int initialOffset, ScannerState initialState) {
		return new PhpScanner(input, initialOffset, initialState);
	}

}

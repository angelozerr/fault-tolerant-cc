package com.redhat.beaverama.parser.scanner;

import java.util.function.Predicate;

public class MultiLineStream {

	private final String source;

	private int position;

	public MultiLineStream(String source, int position) {
		this.source = source;
		this.position = position;
	}

	public String getSource() {
		return source;
	}

	public int pos() {
		return this.position;
	}

	public boolean eos() {
		return this.source.length() <= this.position;
	}

	public void advance(int n) {
		this.position += n;
	}

	public boolean advanceIfChars(int... ch) {
		int i;
		if (this.position + ch.length > this.source.length()) {
			return false;
		}
		for (i = 0; i < ch.length; i++) {
			if (peekChar(i) != ch[i]) {
				return false;
			}
		}
		this.advance(i);
		return true;
	}

	public int peekChar() {
		return peekChar(0);
	}

	public int peekChar(int n) {
		int pos = this.position + n;
		if (pos >= this.source.length()) {
			return -1;
		}
		return this.source.codePointAt(pos);
	}

	/**
	 * Advances until it reaches a whitespace character
	 */
	public boolean skipWhitespace() {
		int n = this.advanceWhileChar(Constants.WHITESPACE_PREDICATE);
		return n > 0;
	}

	public int advanceWhileChar(Predicate<Integer> condition) {
		int posNow = this.position;
		while (this.position < this.source.length() && condition.test(peekChar())) {
			this.position++;
		}
		return this.position - posNow;
	}
}

package com.redhat.beaverama.parser;

public class LineCharacterPosition {

	private final int line;

	private final int character;

	public LineCharacterPosition(int line, int character) {
		this.line = line;
		this.character = character;
	}

	public int getLine() {
		return line;
	}

	public int getCharacter() {
		return character;
	}

}

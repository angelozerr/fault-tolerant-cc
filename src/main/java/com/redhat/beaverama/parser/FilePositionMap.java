package com.redhat.beaverama.parser;

public interface FilePositionMap {

	/**
	 * @param NodeAPI node the node to get the start line for. TODO deprecate and merge
	 *             this and getTokenStartLine into getStartLine if
	 *             https://github.com/Microsoft/tolerant-php-parser/issues/166 is
	 *             fixed, (i.e. if there is a consistent way to get the start
	 *             offset)
	 */
	default int getNodeStartLine(NodeAPI node) {
		return this.getLineNumberForOffset(node.getStart());
	}

	/**
	 * @param Token token the token to get the start line for.
	 */
	/*
	 * public int getTokenStartLine(Token token) { return
	 * this.getLineNumberForOffset(token.getStart()); }
	 */

	/**
	 * @param Node|Token node
	 */
	default int getStartLine(NodeAPI node) {
		int offset = node.getStart();
		return this.getLineNumberForOffset(offset);
	}

	/**
	 * @param Node|Token node Similar to getStartLine but includes the column
	 */
	default LineCharacterPosition getStartLineCharacterPositionForOffset(NodeAPI node) {
		int offset = node.getStart();
		return this.getLineCharacterPositionForOffset(offset);
	}

	/** @param Node|Token node */
	default int getEndLine(NodeAPI node) {
		return this.getLineNumberForOffset(node.getEndPosition());
	}

	/**
	 * @param Node|Token node Similar to getStartLine but includes the column
	 */
	default LineCharacterPosition getEndLineCharacterPosition(NodeAPI node) {
		return this.getLineCharacterPositionForOffset(node.getEndPosition());
	}

	/**
	 * @param int offset Similar to getStartLine but includes both the line and the
	 *            column
	 */
	default LineCharacterPosition getLineCharacterPositionForOffset(int offset) {
		int line = this.getLineNumberForOffset(offset);
		int character = this.getColumnForOffset(offset);
		return new LineCharacterPosition(line, character);
	}

	/**
	 * @param int offset - A 0-based byte offset
	 * @return int - gets the 1-based line number for offset
	 */
	int getLineNumberForOffset(int offset);

	/**
	 * @param int offset - A 0-based byte offset
	 * @return int - gets the 1-based column number for offset
	 */
	int getColumnForOffset(int offset);

}

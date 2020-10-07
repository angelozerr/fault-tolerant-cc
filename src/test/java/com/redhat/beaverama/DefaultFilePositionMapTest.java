package com.redhat.beaverama;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.redhat.beaverama.parser.DefaultFilePositionMap;
import com.redhat.beaverama.parser.FilePositionMap;
import com.redhat.beaverama.parser.LineCharacterPosition;

public class DefaultFilePositionMapTest {

	/**
	 * The map keeps the current offset and line - Move the requests forward,
	 * backwards, and to the same position as the previous request to verify that
	 * this is done properly.
	 */
	@Test
	public void testLineCharacterPosition() {
		FilePositionMap map = new DefaultFilePositionMap("foo\n\nbar\n");
		expectLineCharacterPositionEquals(1, 1, map.getLineCharacterPositionForOffset(0));
		expectLineCharacterPositionEquals(1, 1, map.getLineCharacterPositionForOffset(-1));
		expectLineCharacterPositionEquals(1, 3, map.getLineCharacterPositionForOffset(2));
		expectLineCharacterPositionEquals(1, 4, map.getLineCharacterPositionForOffset(3));
		expectLineCharacterPositionEquals(2, 1, map.getLineCharacterPositionForOffset(4));
		expectLineCharacterPositionEquals(1, 4, map.getLineCharacterPositionForOffset(3));
		expectLineCharacterPositionEquals(3, 1, map.getLineCharacterPositionForOffset(5));
		expectLineCharacterPositionEquals(3, 4, map.getLineCharacterPositionForOffset(8));
		expectLineCharacterPositionEquals(3, 4, map.getLineCharacterPositionForOffset(8));
		expectLineCharacterPositionEquals(4, 1, map.getLineCharacterPositionForOffset(9));
		expectLineCharacterPositionEquals(4, 1, map.getLineCharacterPositionForOffset(12));
		expectLineCharacterPositionEquals(1, 4, map.getLineCharacterPositionForOffset(3));
	}

	/**
	 * The map keeps the current offset and line - Move the requests forward,
	 * backwards, and to the same position as the previous request to verify that
	 * this is done properly.
	 */
	@Test
	public void testLineNumber() {
		FilePositionMap map = new DefaultFilePositionMap("foo\n\nbar\n");
		assertEquals(1, map.getLineNumberForOffset(0));
		assertEquals(1, map.getLineNumberForOffset(-1));
		assertEquals(1, map.getLineNumberForOffset(2));
		assertEquals(1, map.getLineNumberForOffset(3));
		assertEquals(2, map.getLineNumberForOffset(4));
		assertEquals(3, map.getLineNumberForOffset(5));
		assertEquals(3, map.getLineNumberForOffset(8));
		assertEquals(3, map.getLineNumberForOffset(8));
		assertEquals(4, map.getLineNumberForOffset(9));
		assertEquals(4, map.getLineNumberForOffset(12));
		assertEquals(1, map.getLineNumberForOffset(3));
		assertEquals(2, map.getLineNumberForOffset(4));
	}

	private static void expectLineCharacterPositionEquals(int line, int character, LineCharacterPosition position) {
		assertEquals(line, position.getLine(), "Expected same line");
		assertEquals(character, position.getCharacter(), "Expected same character");
	}

}

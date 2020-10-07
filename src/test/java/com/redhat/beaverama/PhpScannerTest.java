package com.redhat.beaverama;

import com.redhat.beaverama.parser.TokenKind;
import com.redhat.beaverama.parser.scanner.PhpScanner;
import com.redhat.beaverama.parser.scanner.Scanner;

public class PhpScannerTest {

	public static void main(String[] args) {
		Scanner scanner = PhpScanner.createScanner("if ()");
		TokenKind token = scanner.scan();
		display(scanner);
		while (token != TokenKind.EOS) {
			token = scanner.scan();
			display(scanner);
		}
	}

	private static void display(Scanner scanner) {
		System.err.println("kind: " + scanner.getTokenType() +  ", from: " + scanner.getTokenOffset() + ", to: " + scanner.getTokenEnd());
		
	}
}

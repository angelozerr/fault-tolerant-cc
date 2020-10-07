package com.redhat.beaverama.parser;

import com.redhat.beaverama.parser.ast.SourceFileNode;

public class Test {

	public static void main(String[] args) {
		Parser parser = new Parser();
		SourceFileNode sourceFile = parser.parseSourceFile("if ()", "file.php");
		System.err.println(sourceFile);
	}
}

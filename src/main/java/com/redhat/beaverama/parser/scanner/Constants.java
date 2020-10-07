package com.redhat.beaverama.parser.scanner;

import java.util.function.Predicate;

public class Constants {

	public final static int _ORB = "(".codePointAt(0);
	public final static int _CRB = ")".codePointAt(0);

	public static final int[] IF = { "i".codePointAt(0), "f".codePointAt(0) };

	public static final int _NWL = "\n".codePointAt(0);
	public static final int _CAR = "\r".codePointAt(0);
	public static final int _LFD = "\f".codePointAt(0);
	public static final int _WSP = " ".codePointAt(0);
	public static final int _TAB = "\t".codePointAt(0);

	public static final Predicate<Integer> WHITESPACE_PREDICATE = ch -> {
		return ch == _WSP || ch == _TAB || ch == _NWL || ch == _LFD || ch == _CAR;
	};

}

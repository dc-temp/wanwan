package com.example.wanwan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

public class MyChinaUtil {
	public static int judge(String input) {
		Pattern p = Pattern.compile("[\u4E00-\u9FA5]");
		Matcher m = p.matcher(input);
		int i = 0;
		while (m.find()) {
			i++;
		}
		return i;
	}

	public static int changdu(String ss) {
		byte[] b = ss.getBytes();
		return b.length;
	}

	public static boolean rightString(String s) {
		if (s.replaceAll("^[A-Za-z0-9\u4e00-\u9fa5]+$", "").length() == 0) {
			return true;
		} else {
			System.out.println("input incorrect");
			return false;
		}
	}

	public static boolean passwordz(String input1) {
		String patternStr = "^((?![a-zA-Z0-9]+$)(?![^a-zA-Z/D]+$)).{6,20}$|((?![^0-9/D]+$)(?![^a-zA-Z/D]+$)).{6,20}$|((?![a-zA-Z0-9]+$)(?![^0-9/D]+$)).{6,20}$";
		Pattern pattern = Pattern.compile(patternStr);
		return input1.matches(patternStr);
	}

	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
	public static boolean isEmail(String mobiles) {
		Pattern p = Pattern
				.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

}

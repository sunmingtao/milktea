package com.milktea.common.util;

public class StringUtils {
	public static boolean isEmpty(String s){
		return s == null || s.trim().length()==0;
	}
}

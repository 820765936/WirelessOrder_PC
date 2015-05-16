package com.yeyunlin.util;

public class LimitManager {
	private static boolean isLanded = false;

	public static boolean isLanded() {
		return isLanded;
	}

	public static void setLanded(boolean isLanded) {
		LimitManager.isLanded = isLanded;
	}
}

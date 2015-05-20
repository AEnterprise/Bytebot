package com.bytebot.irc.utils;

import java.util.Arrays;

/**
 * Created by AEnterprise
 */
public class Utils {
	public static final String[] ADMINS = new String[]{"AEnterprise", "ace5852"};

	public static boolean isAdmin(String user) {
		return Arrays.asList(ADMINS).contains(user);
	}
}

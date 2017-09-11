package com.omji.utils;

public class GeneralUtil {

	// Does this string have a value. true or false. Checks for null or empty
	// string

	public static boolean hasValue(String s) {

		return (s != null) && (s.trim().length() > 0);
	}

	// Get the File Extension name from a filename

	public static String getFileExtension(String filename) {
		if (!GeneralUtil.hasValue(filename))
			return null;
		int index = filename.lastIndexOf('.');
		if (index == -1)
			return null;
		return filename.substring(index + 1, filename.length());
	}

}

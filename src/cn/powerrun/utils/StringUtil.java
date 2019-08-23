package cn.powerrun.utils;

public class StringUtil {
	/**
	 * 判空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {// 去掉两端空格
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判非空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		if (str != null && !"".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String getSuffix(String str) {
		if(str.indexOf(".") == -1) {
			return "";
		}else {
			return str.substring(str.indexOf("."), str.length());
		}
		
	}
}

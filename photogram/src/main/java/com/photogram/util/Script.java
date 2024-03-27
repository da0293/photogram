package com.photogram.util;

public class Script {
	/*
	 * script 코드를 만듬
	 * 경고창 띄우고 뒤로가기 
	 */
	public static String back(String msg) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		sb.append("alert('"+msg+"');");
		sb.append("history.back();");
		sb.append("</script>"); 
		return sb.toString();
		
	}
}

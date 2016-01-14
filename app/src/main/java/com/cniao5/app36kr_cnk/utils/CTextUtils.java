package com.cniao5.app36kr_cnk.utils;

/**
 * 文本字符串相关处理工具类
 * @author jiangqq
 *
 */
public class CTextUtils {
	/**
	 * 提取文章编号  例如:/p/5040196.html 转成5040196
	 * @param pStr
	 * @return
	 */
    public static String getTitleId(String pStr){
    	if(pStr.contains(".")){
    		String[] temp=pStr.split("\\.");  //  /p/5040196
        	int index =temp[0].lastIndexOf("/");
        	return temp[0].substring(index+1);
    	}
    	return pStr;
    }
    /**
     * http://36kr.com/p/5040401.html-->5040401
     * @param pStr
     * @return
     */
    public static String getArticleId(String pStr){
    	String tempStr=pStr.substring(pStr.lastIndexOf("/")+1);
    	if(tempStr.contains(".")){
    		String[] temp=tempStr.split("\\.");  //  5040196
        	return temp[0];
    	}
    	return pStr;
    }
    
    public static String replaceSSymbol(String pStr){
    	return pStr.replace("\\n", "");
    }
    
    /**
     * 进行邮箱的#号转换成@符号
     * @param pStr
     * @return
     */
    public static String replaceEmail(String pStr){
    	if(pStr.contains("#")){
    		return pStr.replace("#", "@");
    	}
       return pStr;	
    }
}

package cn.liguohao.ikaros.util;

import java.util.regex.Pattern;

/**字符串工具类
 * @author: liguohao_cn
 * @date: 2020年9月2日 下午2:59:59
 */
public class StringUtils extends org.springframework.util.StringUtils {

	/**
	 * @Title: isNumeric
	 * @Description: 判断是否为正整数
	 * @param string
	 * @return: boolean
	 */
	public static boolean isNumeric(String string){
	    Pattern pattern = Pattern.compile("[0-9]*");
	    return pattern.matcher(string).matches();   
	}
	
	
}

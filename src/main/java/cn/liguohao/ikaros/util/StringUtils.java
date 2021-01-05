package cn.liguohao.ikaros.util;

import java.util.regex.Pattern;

/**字符串工具类
 * @author liguohao_cn
 * @since 2020年9月2日 下午2:59:59
 */
public class StringUtils extends org.springframework.util.StringUtils {

	/**判断是否为正整数
	 * @param string 待判断的字符串
	 * @return: 是否为正整数
	 */
	public static boolean isNumeric(String string){
	    Pattern pattern = Pattern.compile("[0-9]*");
	    return pattern.matcher(string).matches();   
	}

	/**
	 * 判断是否为MD5加密后的字符串
	 * @param str 待判断的字符串
	 * @return 是否经过了MD5加密
	 */
	public static boolean isMD5Str(String str){
		int cnt = 0;
		for (int i=0; i<str.length(); ++i) {
			switch (str.charAt(i)) {
				case '0': case '1': case '2': case '3': case '4':
				case '5': case '6': case '7': case '8': case '9':
				case 'a': case 'b': case 'c': case 'd': case 'e': case 'f':
				case 'A': case 'B': case 'C': case 'D': case 'E': case 'F':
					++ cnt;
					if (32 <= cnt) return true;
					break;
				case '/':
					if ((i + 10) < str.length()) {// "/storage/"
						char ch1 = str.charAt(i+1);
						char ch2 = str.charAt(i+8);
						if ('/' == ch2 && ('s' == ch1 || 'S' == ch1)) return true;
					}
				default:
					cnt = 0;
					break;
			}
		}
		return false;
	}


	
}

package cn.liguohao.ikaros.util;

import java.util.UUID;

/**UUID工具类
 * @author: liguohao
 * @token工具类 2020-7-18 1:10:12
 */
public class UUIDUtils {
	/**
	 * 随机生成id
	 * 
	 * @return
	 */
	public static String getId() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

	public static String getUUID64() {
		return getId() + getId();
	}

	/**
	 * 生成随机码
	 * 
	 * @return
	 */
	public static String getCode() {
		return getId();
	}

	public static void main(String[] args) {
		 System.out.println(getId());
		
		//for (int i = 0; i <= 100; i++) {
		//String str = UUID.randomUUID().toString();
		//			System.out.println(str);
		//}

	}
}

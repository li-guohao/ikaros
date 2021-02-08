package cn.liguohao.ikaros.util;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

/**bean操作通用方法封装
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2020-7-18 1:09:27
 */
public class BeanUtil {
	
	
	/**
	 *  判断属性是否存在
	 *  @author:李国豪
	 *  @date:2020年1月21日
	 */
	public static boolean isExist(Object property){
		if(property!=null && !"".equals(property)){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 	动态更新字段
	 *  @author:李国豪
	 *  @date:2020年1月21日
	 */
	public static Object copyFieldByIsExist(Object sourceObj,Object targetObj){
		Field[] declaredFields = sourceObj.getClass().getDeclaredFields();
		for (Field field : declaredFields) {
			try {
				//获取原来的访问权限
				boolean accessible = field.canAccess(field);
				field.setAccessible(true);
				//String name = 
						field.getName();
				Object value = field.get(sourceObj);
				if(isExist(value)){	//字段存在
					field.set(targetObj, value);
				}
				//恢复原来发访问权限
				field.setAccessible(accessible);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return targetObj;
	}
	
	/**
	 *  遍历一个类
	 *  @author:李国豪
	 *  @date:2020年1月21日
	 */
	public static void bianLi(Object obj){
		 Field[] fields = obj.getClass().getDeclaredFields();
		 for(int i = 0 , len = fields.length; i < len; i++) {
		 // 对于每个属性，获取属性名
		 String varName = fields[i].getName();
		 try {
		 // 获取原来的访问控制权限
		 boolean accessFlag = fields[i].isAccessible();
		 // 修改访问控制权限
		 fields[i].setAccessible(true);
		 // 获取在对象f中属性fields[i]对应的对象中的变量
		 Object o;
		try {
			o = fields[i].get(obj);
			System.err.println("传入的对象中包含一个如下的变量：" + varName + " = " + o);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		 // 恢复访问控制权限
		 fields[i].setAccessible(accessFlag);
		 } catch (IllegalArgumentException ex) {
		 ex.printStackTrace();
		 } 
	}
	}
    /*static class User {
		private Date birthday;
		private  String realName;
		private Long uid;
		public User() {
		}
		public Date getBirthday() {
			return birthday;
		}

		public void setBirthday(Date birthday) {
			this.birthday = birthday;
		}

		public String getRealName() {
			return realName;
		}

		public void setRealName(String realName) {
			this.realName = realName;
		}

		public Long getUid() {
			return uid;
		}

		public void setUid(Long uid) {
			this.uid = uid;
		}
	}
	public static void main(String[] args) {
		User user = new User();
		user.setBirthday(new Date(System.currentTimeMillis()));
		user.setRealName("2321");
		user.setUid(231L);
		User oldUser = new User();
		oldUser.setUid(1L);
		System.out.println(oldUser.getUid()+"-->"+oldUser.getRealName());
		//bianLi(user);
		User newUser = (User) copyFieldByIsExist(user, new User());
		System.out.println(newUser.getUid()+"-->"+newUser.getRealName());
	}*/
	/**
	 *  将字符数组转化成Long类型数组
	 *  前提是字符数组里的字符串为数字
	 *  @author:李国豪
	 *  @date:2020年1月22日
	 */
	public static Long[] stringArrayToLongArray(String[] stringArr){
		//创建一个空的Long类型数组 大小与String数组一致
		Long[] longArr = new Long[stringArr.length];
		//循环遍历，转化添加
		for (int i=0;i<stringArr.length;i++) {
			longArr[i] = Long.valueOf(stringArr[i]);
		}
		return longArr;
	}
	/**
	 *  将字符串如[1,2,3,4,5]转化成Long类型数组
	 *  @author:李国豪
	 *  @date:2020年1月22日
	 */
	public static Long[] stringToLongArray(String ids) {
		String[] stringArr = ids.toString().split(",");
		return stringArrayToLongArray(stringArr);
	}
}

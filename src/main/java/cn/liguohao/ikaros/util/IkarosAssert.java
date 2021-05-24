package cn.liguohao.ikaros.util;

import cn.liguohao.ikaros.exception.IllegalArgumentIkarosException;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

/**伊卡洛斯断言
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/1/2
 */
public class IkarosAssert extends Assert {


    /**
     * 字符串不能为空
     * @param str 待校验的值-String类型
     * @param message 异常情况的消息
     * @see StringUtils#isEmpty(Object)
     */
    public static void isNotEmpty(String str, String message) {
        if(StringUtils.isEmpty(str)) {throw new IllegalArgumentIkarosException(message);}
    }

    /**
     * 值不能为空
     * @param value 待校验的值-Long类型
     * @param message 异常情况的消息
     * @see #isNotEmpty(String, String)
     */
    public static void isNotEmpty(Long value,String message) {
        IkarosAssert.isNotEmpty(String.valueOf(value),message);
    }

    /**
     * 对象应该不为空
     * @param obj 待校验的值-Object类型
     * @param message 对象为空时抛出的异常情况的消息
     * @see ObjectUtils#isEmpty(Object)
     */
    public static void isNotEmpty(Object obj,String message) {
        if(ObjectUtils.isEmpty(obj)) {throw new IllegalArgumentIkarosException(message);}
    }



    /**
     * 对应的条件应该为false
     * @param expression 表达式
     * @param message 条件为true时抛出的异常消息
     */
    public static void isFalse(boolean expression, String message) {
        if (expression) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 对象不为null
     * @param object 待校验的值-Object类型
     * @param message 对象为null时抛出的异常情况的消息
     */
    public static void isNotNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }



}

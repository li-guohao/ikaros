package cn.liguohao.ikaros.util;

import cn.liguohao.ikaros.exception.IllegalArgumentIkarosException;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

/**伊卡洛斯断言
 * @author liguohao_cn
 * @date 2021/1/2
 */
public class IkarosAssert extends Assert {


    /**
     * 字符串不能为空
     * @param str 待校验的值-String类型
     * @param message 异常情况的消息
     * @see StringUtils#isEmpty(Object)
     */
    public static void isEmpty(@Nullable String str, String message) {
        if(StringUtils.isEmpty(str)) throw new IllegalArgumentIkarosException(message);
    }

    /**
     * 不能为空
     * @param value 待校验的值-Long类型
     * @param message 异常情况的消息
     * @see #isEmpty(String, String)
     */
    public static void isEmpty(Long value,  String message) {
        isEmpty(String.valueOf(value),message);
    }

    /**
     * 对象不能为空
     * @param obj 待校验的值-Object类型
     * @param message 异常情况的消息
     * @see ObjectUtils#isEmpty(Object)
     */
    public static void isEmpty(@Nullable Object obj, String message) {
        if(ObjectUtils.isEmpty(obj)) throw new IllegalArgumentIkarosException(message);
    }



}

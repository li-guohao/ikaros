package cn.liguohao.ikaros.vo;

import org.junit.Test;

/**
 * @author liguohao_cn
 * @version 2021/1/2
 */
public class ResultTest {

    /**
     * 测试链式设置的对象引用
     * 结论：同一对象
     */
    @Test
    public void testObj(){
        Result res1 = Result.build();
        Result res2 = res1.setData("date");
        Result res3 = res2.setStatus(Status.success);
        Result res4 = res3.setMessage("测试");
        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
        System.out.println(res4);

        System.out.println("------------------------------");

        System.out.println(System.identityHashCode(res1));
        System.out.println(System.identityHashCode(res2));
        System.out.println(System.identityHashCode(res3));
        System.out.println(System.identityHashCode(res4));

        System.out.println("同一对象");
    }

    /**
     * 测试链式编程更改属性值是否会影响之前对象
     * 结论：猜想正确 会影响
     */
    @Test
    public void testObjPro(){
        Result res1 = Result.build();
        Result res2 = res1.setData("date");
        Result res3 = res2.setStatus(Status.success);
        Result res4 = res3.setMessage("测试");

        System.out.println(res1.getMessage()); //测试
    }
}

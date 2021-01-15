package cn.liguohao.ikaros.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**文件操作工具类
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2020/12/8
 */
public class FileUtils {

    /**
     * 获取文件的字节数组
     * @param file 源文件
     * @return
     */
    public static byte[] toByteArray(File file) throws IOException {
        // 文件存在判断
        if(!file.exists())  throw new FileNotFoundException(file.getPath());

        // 根据文件大小创建字节数组
        byte[] bytes = new byte[(int) file.length()];

        // 创建流读取到字节数组

        FileInputStream fileInputStream = new FileInputStream(file);
        fileInputStream.read(bytes);

        // 返回字节数组
        return bytes;
    }

}

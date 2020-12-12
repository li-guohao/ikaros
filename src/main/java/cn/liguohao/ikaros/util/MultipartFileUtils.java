package cn.liguohao.ikaros.util;

import cn.liguohao.ikaros.store.File;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Map;

/**
 * @Description TODO MultipartFile 工具类
 * @Auther: liguohao
 * @Date: 2020/11/28 09:47
 */
public class MultipartFileUtils {

    /**
     * 根据 org.springframework.web.multipart.MultipartFile 生成数据库文件对象cn.liguohao.ikaros.store.File
     *  返回结果已经设置的属性如下：originalName, suffix, diskName, size, realtivePath(/...), description
     * @param multipartFile multipart格式文件对象
     * @return 据库文件对象cn.liguohao.ikaros.store.File
     */
    public static File generateDatabaseFile(MultipartFile multipartFile){
        // 构建dbFile
        File file = new File();
        // 获取原来的文件名称
        String originalName = multipartFile.getOriginalFilename();
        file.setOriginalName(originalName);

        // 获取文件的后缀格式
        String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
        file.setSuffix(suffix);

        //生成文件名 UUID
        String diksFileName = UUIDUtils.getId() + "." + suffix;
        file.setDiskName(diksFileName);

        // 文件大小
        long size = multipartFile.getSize();
        file.setSize(size);

        //生成文件多层路径,日期 /2020/11/14
        Calendar now = Calendar.getInstance();
        String middle = "";
        middle += "/" + now.get(Calendar.YEAR); // /2020
        middle += "/" + (now.get(Calendar.MONTH) + 1);// /11
        middle += "/" + now.get(Calendar.DAY_OF_MONTH); //14
        middle += "/" + now.get(Calendar.HOUR_OF_DAY); //15

        // 构建文件相对于upload目录的路径 /2020/11/14/simple.jpg
        String realtivePath = middle + "/" + diksFileName;
        file.setRelativePath(realtivePath);
        if(StringUtils.isEmpty(file.getDescription())) file.setDescription("这是一段默认的对于该文件的描述QAQ");

        // 返回构建结果
        return file;
    }

    /**
     * MultipartFile 转 File
     * @param multipartFile MultipartFile格式文件
     * @return java.io.File
     * @throws Exception
     */
    public static java.io.File multipartFileToFile(MultipartFile multipartFile) throws IOException {

        java.io.File toFile = null;
        if (multipartFile.equals("") || multipartFile.getSize() <= 0) {
            multipartFile = null;
        } else {
            InputStream ins = null;
            ins = multipartFile.getInputStream();
            toFile = new java.io.File(System.getProperty("user.home")+"/.ikaros/cache/file/"+multipartFile.getOriginalFilename());
            if(!toFile.getParentFile().exists()){
                toFile.getParentFile().mkdirs();
            }
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }

    /**
     * 获取流文件
     * @param ins 输入流
     * @param file java.io.File
     */
    private static void inputStreamToFile(InputStream ins, java.io.File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}

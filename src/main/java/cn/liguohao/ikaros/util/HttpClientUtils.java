package cn.liguohao.ikaros.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**HttpClient 工具类
 * @Auther: liguohao
 * @since 2020/11/29 13:30
 */
public class HttpClientUtils {

    /**
     * 下载文件
     * @param url 文件URL地址 如 https://file.liguohao.cn/api/v3/file/get/12/error.zip
     * @param destFilePath 文件储存地 绝对路径 包含完整的文件名称 如 /root/.test/error.zip
     */
    public static void downloadFile(String url,String destFilePath) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        HttpEntity httpResponseEntity = httpResponse.getEntity();
        InputStream httpResponseEntityContent = httpResponseEntity.getContent();


        File zipFile = new File(destFilePath);
        if(!zipFile.getParentFile().exists()){
            zipFile.getParentFile().mkdirs();
        }

        FileOutputStream fileout = new FileOutputStream(zipFile);

        // 根据实际运行效果 设置缓冲区大小
        byte[] buffer = new byte[1024*5];
        int ch = 0;
        while ((ch = httpResponseEntityContent.read(buffer)) != -1) {
            fileout.write(buffer, 0, ch);
        }

        fileout.flush();
        fileout.close();
        httpResponseEntityContent.close();
    }

    /**
     * 解压ZIP文件到指定目录
     * @param zipFile ZIP文件
     * @param destFileDir 解压缩的目录绝对路径 末尾不带/
     * @throws IOException
     */
    public static void unzip(File zipFile, String destFileDir) throws IOException{

        ZipFile zip = new ZipFile(zipFile, Charset.forName("GBK"));//解决中文文件夹乱码
        String name = zip.getName().substring(zip.getName().lastIndexOf('\\')+1, zip.getName().lastIndexOf('.'));

        File pathFile = new File(destFileDir+"/" +name);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }

        for (Enumeration<? extends ZipEntry> entries = zip.entries(); entries.hasMoreElements();) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String zipEntryName = entry.getName();
            InputStream in = zip.getInputStream(entry);
            String outPath = "";
            if(name.equals(zipEntryName.substring(0,zipEntryName.indexOf("/")<0?zipEntryName.length():zipEntryName.indexOf("/")))){
                outPath = (destFileDir +"/" + zipEntryName ).replaceAll("\\*", "/");
            }else {
                outPath = (destFileDir+"/" + name +"/"+ zipEntryName).replaceAll("\\*", "/");
            }
//            String outPath = (destFileDir + name +"/"+ zipEntryName).replaceAll("\\*", "/");

            // 判断路径是否存在,不存在则创建文件路径
            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
            if (!file.exists()) {
                file.mkdirs();
            }
            // 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
            if (new File(outPath).isDirectory()) {
                continue;
            }
            // 输出文件路径信息
//			System.out.println(outPath);

            FileOutputStream out = new FileOutputStream(outPath);
            byte[] buf1 = new byte[1024];
            int len;
            while ((len = in.read(buf1)) > 0) {
                out.write(buf1, 0, len);
            }
            in.close();
            out.close();
        }
    }

}

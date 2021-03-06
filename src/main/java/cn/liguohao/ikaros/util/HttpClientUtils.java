package cn.liguohao.ikaros.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**HttpClient 工具类
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2020/11/29 13:30
 */
public class HttpClientUtils {

    /**
     * 下载文件
     * @param url 文件URL地址 如 https://file.liguohao.cn/api/v3/file/get/12/error.zip
     * @param destFilePath 文件储存地 绝对路径 包含完整的文件名称 如 /root/.test/error.zip
     */
    public static void downloadFile(String url,String destFilePath) throws IOException{

        CloseableHttpClient httpClient = null;
        HttpGet httpGet = null;
        CloseableHttpResponse httpResponse = null;
        HttpEntity httpResponseEntity = null;
        FileOutputStream fileOut = null;
        InputStream httpResponseEntityContent = null;

        try {
            httpClient = HttpClients.createDefault();
            httpGet = new HttpGet(url);
            httpResponse = httpClient.execute(httpGet);
            httpResponseEntity = httpResponse.getEntity();
            httpResponseEntityContent = httpResponseEntity.getContent();

            File zipFile = new File(destFilePath);
            if(!zipFile.getParentFile().exists()){
                zipFile.getParentFile().mkdirs();
            }

            fileOut = new FileOutputStream(zipFile);

            httpResponseEntityContent.transferTo(fileOut);
            fileOut.flush();

        } catch (IOException exception) {
            throw exception;
        } finally {
            httpResponseEntityContent.close();
            fileOut.close();
            httpResponseEntity.getContent();
            httpResponse.close();
            httpClient.close();
        }
    }

    /**
     * 解压ZIP文件到指定目录
     * @param zipFile ZIP文件
     * @param destFileDir 解压缩的目录绝对路径 末尾不带/
     * @throws IOException
     */
    public static void unzip(File zipFile, String destFileDir) throws IOException {

        ZipFile zip = new ZipFile(zipFile, Charset.forName("GBK"));//解决中文文件夹乱码
        String name = zip.getName().substring(zip.getName().lastIndexOf('\\')+1, zip.getName().lastIndexOf('.'));

        File pathFile = new File(destFileDir+"/" +name);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }

        try {
            for (Enumeration<? extends ZipEntry> entries = zip.entries(); entries.hasMoreElements();) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String zipEntryName = entry.getName();

                String outPath = "";
                if(name.equals(zipEntryName.substring(0,zipEntryName.indexOf("/")<0?zipEntryName.length():zipEntryName.indexOf("/")))){
                    outPath = (destFileDir +"/" + zipEntryName ).replaceAll("\\*", "/");
                }else {
                    outPath = (destFileDir+"/" + name +"/"+ zipEntryName).replaceAll("\\*", "/");
                }

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
                //System.out.println(outPath);

                InputStream in = null;
                FileOutputStream out = null;
                try {
                    in = zip.getInputStream(entry);
                    out = new FileOutputStream(outPath);
                    in.transferTo(out);
                    out.flush();
                } catch (IOException exception) {
                    throw exception;
                } finally {
                    in.close();
                    out.close();
                }

            }
        } catch (IOException exception) {
            throw exception;
        } finally {
            zip.close();
        }
    }

}

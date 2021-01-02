package cn.liguohao.ikaros.service;

import cn.liguohao.ikaros.constant.DiskFileConfig;
import cn.liguohao.ikaros.store.database.DBFile;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**数据库服务层测试
 * @author liguohao_cn
 * @date 2021/1/2
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DBFileServiceTest {

    @Autowired
    private DBFileService dbFileService;

    @Test
    public void save(){
        dbFileService.save(
                DBFile.build()
                .setOriginalName("test.png")
                .setDescription("测试文件数据记录")
                .setPlace(DiskFileConfig.LOCAL.getName())
                .setSuffix("png")
                .setUploadTime(LocalDateTime.now())
        );
    }

    @Test
    public void findOne(){
        DBFile dbFile = dbFileService.findOne(Example.of(
                DBFile.build()
                        .setSuffix("png")
                        .setFileId(2L)
        ));
        System.out.println(JSON.toJSON(dbFile));
    }

    @Test
    public void findList(){
        List<DBFile> dbFileList = dbFileService.findList(Example.of(
                DBFile.build()
                        .setSuffix("png")));
        System.out.println(JSON.toJSON(dbFileList));
    }

}

package cn.welldone.collect.service.task;

import cn.welldone.collect.mapperservice.task.TaskMapper;
import cn.welldone.collect.service.file.FileService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileServiceTest {
    @Resource
    FileService fileService;

    @Resource
    TaskMapper taskMapper;

    @Test
    public void testDownLoadFile(){
        //Assert.assertEquals(fileService.downloadFile(1,1),"sdwd");
    }
}
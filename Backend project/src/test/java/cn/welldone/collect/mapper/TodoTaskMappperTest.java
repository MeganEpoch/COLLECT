package cn.welldone.collect.mapper;

import cn.welldone.collect.mapperservice.task.TodoTaskMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoTaskMappperTest {
    @Resource
    TodoTaskMapper todoTaskMapper;

    @Test
    public void testCountByDevice(){
        System.out.println(todoTaskMapper.countByDevice(1,"linux"));
    }
    @Test
    public void testCountByDifficulty(){
        System.out.println(todoTaskMapper.countByDifficulty(1,2));
    }

    @Test
    public void testCountByType(){
        System.out.println(todoTaskMapper.countByType(2,"性能测试"));
    }
}

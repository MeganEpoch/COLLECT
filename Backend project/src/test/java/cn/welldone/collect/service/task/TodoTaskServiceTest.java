package cn.welldone.collect.service.task;

import cn.welldone.collect.mapperservice.task.TodoTaskMapper;
import cn.welldone.collect.po.task.TodoTask;
import cn.welldone.collect.util.Constant;
import cn.welldone.collect.vo.ResultVO;
import cn.welldone.collect.vo.task.TodoTaskVO;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoTaskServiceTest {
    @Resource
    TodoTaskService todoTaskService;
    @Resource
    TodoTaskMapper todoTaskMapper;

    static TodoTaskVO[] todoTasks;

    @Test
    public void testSelectTodoTask() {
        ResultVO<TodoTaskVO> result = todoTaskService.selectTodoTask(1,2);
        //System.out.println(result.getData().getId());

    }

    @Test
    public void testGetFinishedTask(){
        System.out.println(todoTaskService.getFinishedTasks(2));
    }


    @Test
    public void testQueryMostRecentTask() {
        TodoTask todoTask = todoTaskService.queryMostRecentTask(2,1);
        System.out.println(todoTask.getId());

    }

    @Test
    public void testGetTodoTasks(){
        System.out.println(todoTaskService.getTodoTasks(2));
    }

    @Test
    public void testGetTOdoTaskById(){
        System.out.println(todoTaskService.getTodoTaskById(1));
    }

    @Test
    @Transactional
    public void testFinishTask(){
        Assert.assertEquals(todoTaskService.finishTask(1,2).getCode(), Constant.REQUEST_SUCCESS);
    }

    @Test
    public void testGetTodoTask(){
        System.out.println(todoTaskService.getTodoTasks(2));
    }

}

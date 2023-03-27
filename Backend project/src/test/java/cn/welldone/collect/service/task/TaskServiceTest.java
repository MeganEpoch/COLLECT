package cn.welldone.collect.service.task;

import cn.welldone.collect.mapperservice.task.TaskMapper;
import cn.welldone.collect.po.task.Task;
import cn.welldone.collect.util.Constant;
import cn.welldone.collect.util.PageInfoUtil;
import cn.welldone.collect.vo.ResultVO;
import cn.welldone.collect.vo.task.TaskVO;
import com.github.pagehelper.PageInfo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskServiceTest {
    @Resource
    TaskService taskService;
    @Resource
    TaskMapper taskMapper;

    static TaskVO[] tasks;

    static {
//        try {
//            tasks = new cn.welldone.collect.vo.task.TaskVO[]{
//        //            new ReportVO(null, 1, 1, null, null, "1", "1"),
//        //            new ReportVO(null, 2, 2, null, null, "2", "2"),
//        //            new ReportVO(null, 3, 3, null, null, "3", "3"),
//        //            new ReportVO(null, 1, 4, null, null, "4", "4"),
////                    new cn.welldone.collect.vo.task.TaskVO(null,"1","功能测试","null",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-12-12 12:12:12"),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2023-12-12 12:12:12"),3,1,"njq",4,true,true,"cbgdhcbe","dhejwdnewm"，0),
////                    new cn.welldone.collect.vo.task.TaskVO(null,"2","性能测试","null",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-12-12 12:12:12"),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2023-12-12 12:12:12"),3,2,"lym",4,true,true,"dbhewjbd","jwkdnkjnw"，0)
//
//            };
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }

//    @Before
//    public void addTasks() {
//        for (TaskVO taskVO : tasks) {
//            ResultVO<TaskVO> result = taskService.createTask(taskVO);
//            Assert.assertEquals(Constant.REQUEST_SUCCESS, result.getCode());
//        }
//    }

//


//    @Test
//    public void testGetTask() {
//        for (TaskVO taskVO : tasks) {
//            TaskVO vo = taskService.getOpenTasks(taskVO.getId()).getData();
//            Assert.assertNotNull(vo);
//            Assert.assertEquals(taskVO, vo);
//        }
//    }
@Test
@Transactional
public void testCreateTask() throws ParseException {
        ResultVO<TaskVO> result = taskService.createTask(new TaskVO(null,"test1","功能测试","null",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-12-12 12:12:12"),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2023-12-12 12:12:12"),3,1,"njq",4,true,true,"cbgdhcbe","dhejwdnewm" ,"ios",5));
        Assert.assertEquals(Constant.REQUEST_SUCCESS, result.getCode());
    }

    @Test
    public void testGetTasks(){
        System.out.println(taskService.getTasks(1,4,7,Constant.TASK_STATUS_VALID).getList().get(0));

    }

    @Test
    public void testGetManageableTasks(){
        System.out.println(taskService.getManageableTasks(1));
    }

    @Test
    public void testGetTask(){
        System.out.println(taskService.getTask(1,2));

    }

    @Test
    public void testGetByPrimaryKey(){
        System.out.println(taskService.getByPrimaryKey(1));

    }

    @Test
    public void testGetAllTasks(){
        System.out.println(taskService.getAllTasks());

    }

//
    @Test
    public void testGetRecommendTasks(){
        System.out.println(taskService.getRecommendTasks(2));
    }

    @Test
    public void testGetAvailableTasks(){
        System.out.println(taskService.getAvailableTasks());
    }
}

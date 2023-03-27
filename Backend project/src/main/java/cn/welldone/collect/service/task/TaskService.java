package cn.welldone.collect.service.task;

import cn.welldone.collect.po.task.Task;
import cn.welldone.collect.vo.task.TaskVO;
import cn.welldone.collect.vo.ResultVO;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface TaskService {
    // 查询所有正在招募/已结束的任务
    PageInfo<TaskVO> getTasks(Integer currPage, Integer pageSize, Integer uid, Integer status);
//    // 根据分类，分页查询课程
//    PageInfo<TaskVO> getCoursesByType(Integer currPage, Integer pageSize, Integer uid, String type);

    // 查询用户所有已接受的任务
//    List<TaskVO> getAcceptedTasks(Integer uid);
    // 查询发包方所有可管理的任务
    List<TaskVO> getManageableTasks(Integer uid);
    // 查询单个任务 改
    TaskVO getTask(Integer taskId, Integer uid);
    // 创建任务
    ResultVO<TaskVO> createTask(TaskVO taskVO);

//    ResultVO<TaskVO> beforeCreateTask(TaskVO taskVO);


    Task getByPrimaryKey(Integer TaskId);



//查询所有任务
List<TaskVO> getAllTasks();

    List<TaskVO> getRecommendTasks(Integer eid);

    List<Task> getAvailableTasks();


    int getTaskStatus(Integer taskId);
}
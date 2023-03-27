package cn.welldone.collect.mapperservice.task;

import cn.welldone.collect.po.task.Task;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface TaskMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Task record);

    Task selectByPrimaryKey(Integer id);

    List<Task> selectAll();
//正在招募的任务or过期的任务
//    List<Task> selectTasks(Integer status);
    List<Task> selectValidTasks(Date today);

    List<Task> selectInvalidTasks(Date today);

    int updateByPrimaryKey(Task task);

//    List<Task> selectByType(String type);

    List<Task> queryAll(String key);

    List<Task> selectByEmployerId(Integer userId);

    List<Task> selectByEmployeeId(Integer userId);

    // 查询任务工人浏览任务广场的任务
    List<Task> selectByStatus(Integer eid);

    void clearTable();

    int updateStatusByPrimaryKey(@Param("taskId")Integer taskId, @Param("status")Integer status);

}
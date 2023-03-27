package cn.welldone.collect.mapperservice.task;

import cn.welldone.collect.po.task.TodoTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface TodoTaskMapper {
    TodoTask queryMostRecentTask(Integer userId, Integer taskId);

    TodoTask selectByPrimaryKey(Integer todoTaskId);
    //在任务广场选取任务加入到todo_task表中
    int insert(TodoTask todoTask);

    

    List<TodoTask> selectByEmployeeId(Integer userId);

    List<TodoTask> selectByStatus(Integer userId,Integer status);

    int countByDevice(@Param("userId")Integer userId, @Param("device")String device);

    int countByType(@Param("userId")Integer userId,@Param("type")String type);

    int countByDifficulty(@Param("userId")Integer userId,@Param("difficulty")int difficulty);

   int  updateStatus(@Param("id")Integer id,@Param("status")Integer status);


//    int
}

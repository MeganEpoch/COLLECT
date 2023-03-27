package cn.welldone.collect.service.task;

import cn.welldone.collect.po.task.TodoTask;
import cn.welldone.collect.vo.ResultVO;
import cn.welldone.collect.vo.task.TodoTaskVO;

import java.util.List;

public interface TodoTaskService {
    TodoTask queryMostRecentTask(Integer id, Integer taskId);

    // 更新任务
//    ResultVO<TodoTaskVO> updateTodoTask(Integer todoTaskId, Integer todoTaskStatus);
    // 根据用户id获取该用户的所有待做任务
    List<TodoTaskVO> getTodoTasks(Integer uid);


    ResultVO<TodoTaskVO> getTodoTaskById(Integer todoTaskId);

    // 根据用户id获取该用户的所有已完成任务
    List<TodoTaskVO> getFinishedTasks(Integer uid);

    //任务选取：众包工人选择正在招募众包工人的众测任务并接受任务
     ResultVO<TodoTaskVO> selectTodoTask(Integer taskId, Integer employeeId);

    ResultVO<String> finishTask(Integer todoTaskId, Integer employeeId);
}

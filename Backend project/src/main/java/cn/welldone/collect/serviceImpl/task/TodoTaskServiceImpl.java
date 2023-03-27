package cn.welldone.collect.serviceImpl.task;

import cn.welldone.collect.mapperservice.report.ReportMapper;
import cn.welldone.collect.mapperservice.task.TodoTaskMapper;
import cn.welldone.collect.po.report.Report;
import cn.welldone.collect.po.task.Task;
import cn.welldone.collect.po.task.TodoTask;
import cn.welldone.collect.service.task.TaskService;
import cn.welldone.collect.service.task.TodoTaskService;
import cn.welldone.collect.util.Constant;
import cn.welldone.collect.vo.ResultVO;
import cn.welldone.collect.vo.task.TaskVO;
import cn.welldone.collect.vo.task.TodoTaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoTaskServiceImpl implements TodoTaskService {
    @Resource
    TodoTaskMapper todoTaskMapper;

    TodoTaskService todoTaskService;
    @Autowired
    TaskService taskService;

    @Resource
    ReportMapper reportMapper;

    @Override
    //搜索众包工人接受id=taskId任务的记录
    public TodoTask queryMostRecentTask(Integer uid, Integer taskId) {
        return todoTaskMapper.queryMostRecentTask(uid, taskId);
    }

//    @Override
//    public ResultVO<TodoTaskVO> updateTodoTask(Integer todoTaskId, Integer todoTaskStatus) {
//        return null;
//    }

    @Override
    public List<TodoTaskVO> getTodoTasks(Integer uid) {
        List<TodoTaskVO> ret = new ArrayList<>();
        List<TodoTask> todoTaskList = todoTaskMapper.selectByStatus(uid, Constant.TODO_STATUS_DOING);
        for (TodoTask todoTask : todoTaskList) {
            ret.add(new TodoTaskVO(todoTask, Constant.TODO_STATUS_DOING));
        }
        return ret;
    }

    @Override
    public ResultVO<TodoTaskVO> getTodoTaskById(Integer todoTaskId) {
        TodoTask todoTask = todoTaskMapper.selectByPrimaryKey(todoTaskId);
        TodoTaskVO todoTaskVO = new TodoTaskVO(todoTask);
        //自动给相同字段赋值
        // BeanUtils.copyProperties(todoTask,todoTaskVO);
        return new ResultVO<>(Constant.REQUEST_SUCCESS, "获取待办任务信息成功", todoTaskVO);
    }

    @Override
    public List<TodoTaskVO> getFinishedTasks(Integer uid) {
        List<TodoTaskVO> ret = new ArrayList<>();
        List<TodoTask> todoTaskList = todoTaskMapper.selectByStatus(uid, Constant.TODO_STATUS_FINISHED);
        for (TodoTask todoTask : todoTaskList) {
            ret.add(new TodoTaskVO(todoTask, Constant.TODO_STATUS_FINISHED));
        }
        return ret;
    }

//    @Override
//    public ResultVO<TodoTaskVO> createTodoTask(Integer taskId, Integer userId) {
//        return null;
//    }

    /**
     * 任务选取：众包工人选择正在招募众包工人的众测任务并接受任务
     *
     * @param taskId
     * @param employeeId
     * @return
     * @author njq
     */
    @Override
    public ResultVO<TodoTaskVO> selectTodoTask(Integer taskId, Integer employeeId) {
        List<TodoTask> todoTaskList = todoTaskMapper.selectByEmployeeId(employeeId);
        for (TodoTask todoTask : todoTaskList) {
            if (todoTask.getTaskId().equals(taskId)) {
                if (todoTask.getStatus() == Constant.TODO_STATUS_DOING) {
                    return new ResultVO<>(Constant.REQUEST_FAIL, "已选取该任务");
                }
            }
        }
        TodoTask todoTask = new TodoTask();

        todoTask.setUserId(employeeId);
        todoTask.setTaskId(taskId);
        Task task = taskService.getByPrimaryKey(taskId);
        TaskVO taskVO = new TaskVO(task);
        todoTask.setTaskName(taskVO.getName());
//        todoTask.setStatus(Constant.TODO_STATUS_SUCCESS);
        todoTask.setStatus(Constant.TODO_STATUS_DOING);
        todoTask.setCreateTime(taskVO.getCreateTime());

        try {
            if (todoTaskMapper.insert(todoTask) != 1) {
                return new ResultVO<>(Constant.REQUEST_FAIL, "选取任务失败", null);
            }
            TodoTaskVO todoTaskVO = new TodoTaskVO(todoTask);

            return new ResultVO<>(Constant.REQUEST_SUCCESS, "选取任务成功", todoTaskVO);

        } catch (Exception e) {
            return new ResultVO<>(Constant.REQUEST_FAIL, "检查传入参数");
        }

    }

    @Override
    public ResultVO<String> finishTask(Integer todoTaskId, Integer employeeId) {
        TodoTask todoTask = todoTaskMapper.selectByPrimaryKey(todoTaskId);
        Report report = reportMapper.selectByTaskIdAndEmployeeId(todoTask.getTaskId(), employeeId);
        if (report != null) {
            todoTaskMapper.updateStatus(todoTaskId, Constant.TODO_STATUS_FINISHED);
            return new ResultVO<>(Constant.REQUEST_SUCCESS, "恭喜您完成任务");
        }


        return new ResultVO<>(Constant.REQUEST_FAIL, "您还未提交报告");
    }
}

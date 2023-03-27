package cn.welldone.collect.controller.task;

import cn.welldone.collect.mapperservice.task.TaskMapper;
import cn.welldone.collect.mapperservice.task.TodoTaskMapper;
import cn.welldone.collect.po.task.Task;
import cn.welldone.collect.po.task.TodoTask;
import cn.welldone.collect.service.task.TodoTaskService;
import cn.welldone.collect.vo.ResultVO;
import cn.welldone.collect.vo.task.TaskVO;
import cn.welldone.collect.vo.task.TodoTaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@RestController
//@Controller
@RequestMapping("/todo_task")
public class TodoTaskController {
    @Resource
    private TodoTaskService todoTaskService;

//    @PostMapping("/create")
//    public ResultVO<TodoTaskVO> createTodoTask(@RequestBody TodoTaskVO orderVO){
//        return orderService.insertTodoTask(orderVO);
//    }

    /**
     * 根据uid查询工人所有的待做任务
     */
    @GetMapping("/todo/{uid}") //checked by postman
    public List<TodoTaskVO> getTodoTasks(@PathVariable Integer uid) {
        return todoTaskService.getTodoTasks(uid);
    }

    @GetMapping("/todo_taskId/{todo_taskId}") //checked by postman
    public ResultVO<TodoTaskVO> getTodoTaskById(@PathVariable Integer todo_taskId) {
        return todoTaskService.getTodoTaskById(todo_taskId);
    }

        /**
         * 根据uid查询工人历史完成的任务
         */
        @GetMapping("/finished/{uid}")  //checked by postman
        public List<TodoTaskVO> getFinishedTasks(@PathVariable Integer uid){
            return todoTaskService.getFinishedTasks(uid);
        }


    /**
     * 任务选取：众包工人选择正在招募众包工人的众测任务并接受任务
     */

    @RequestMapping("/select")
    public ResultVO<TodoTaskVO> selectTodoTask(@RequestParam Integer taskId, @RequestParam Integer employeeId) {
        return todoTaskService.selectTodoTask(taskId, employeeId);
    }

    /**
     * 完成任务
     */
    @PostMapping("/finish")
    public ResultVO<String> finishTask(@RequestParam Integer uid,@RequestParam Integer todoTaskId) {
        return todoTaskService.finishTask(todoTaskId,uid);
    }
}




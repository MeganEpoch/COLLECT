package cn.welldone.collect.controller.task;

import cn.welldone.collect.service.task.TaskService;
import cn.welldone.collect.util.Constant;
import cn.welldone.collect.vo.task.TaskVO;
import cn.welldone.collect.vo.ResultVO;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Resource
    private TaskService taskService;

    /**
     * 查询单一任务
     * @param uid 用户id
     * @param taskId 任务id
     */
    @GetMapping("/{taskId}") //checked by postman
    public TaskVO getTaskById(@RequestParam(required = false, defaultValue = "") Integer uid, @PathVariable Integer taskId) {
        return taskService.getTask(taskId, uid);
    }


    /**
     * 根据正在招募众包工人的任务（任务广场）
     * 如果带参数uid，则需要判断该用户是否接受过任务
     * @param uid 用户id
     */
    @GetMapping("/all/{page}") //checked by postman
    public PageInfo<TaskVO> getValidTasks(@RequestParam(required = false, defaultValue = "") Integer uid,
                                          @PathVariable Integer page) {
        return taskService.getTasks(page, Constant.TASK_PAGE_SIZE, uid,Constant.TASK_STATUS_VALID);
    }


//获取当前任务状态（是不是所有报告都提交了）
@GetMapping("/status/{taskId}")
public int getTaskStatus(@PathVariable Integer taskId){
        return taskService.getTaskStatus(taskId);
    }
    /**
     * 获取发包方可管理的任务列表
     * @param rid 发包方id
     */
    @GetMapping("/rid/{rid}")//checked by postman
    public List<TaskVO> getManageableTasks(@PathVariable Integer rid) {
        return taskService.getManageableTasks(rid);
    }


    /**
     * 发包方创建任务
     * @param task 任务VO
     */
    @PostMapping("/create")//checked by junit
    public ResultVO<TaskVO> createTask(@RequestBody TaskVO task) {
        return taskService.createTask(task);
    }



    /**
     * 管理员浏览所有任务
     */
    @GetMapping("/admin")//checked by postman
    public List<TaskVO> getAllTasks() {
        return taskService.getAllTasks();
    }

    /**
     * 获取推荐任务
     *  @param eid 工人id
     */
    @GetMapping("/recommend/{eid}")
    public List<TaskVO> getRecommendTasks(@PathVariable Integer eid){
        return taskService.getRecommendTasks(eid) ;}


}


package cn.welldone.collect.serviceImpl.task;

import cn.welldone.collect.mapperservice.recommend.RecommendRuleMapper;
import cn.welldone.collect.mapperservice.task.TaskMapper;
import cn.welldone.collect.po.task.Task;
import cn.welldone.collect.po.task.TodoTask;
import cn.welldone.collect.service.recommend.RecommendService;
import cn.welldone.collect.service.task.TaskService;
import cn.welldone.collect.service.task.TodoTaskService;
import cn.welldone.collect.util.Constant;
import cn.welldone.collect.util.PageInfoUtil;
import cn.welldone.collect.vo.task.TaskVO;
import cn.welldone.collect.vo.ResultVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Resource
    private TaskMapper taskMapper;

    private TodoTaskService todoTaskService;
    @Resource
    private RecommendRuleMapper recommendRuleMapper;

    @Resource
    @Qualifier("ItemSimilarityServiceImpl")
    private RecommendService itemSimilarityService;

    @Resource
    @Qualifier("userAttributeRecommendServiceImpl")
    private RecommendService userAttributeRecommendService;

    @Resource
    @Qualifier("taskBasedServiceImpl")
    private RecommendService taskBasedService;



    @Autowired
    public void setTodoTaskService(TodoTaskService todoTaskService) {
        this.todoTaskService = todoTaskService;
    }


///获取符合要求的任务（status）正在招募或过期
    @Override
    public PageInfo<TaskVO> getTasks(Integer currPage, Integer pageSize, Integer uid,Integer status) {
        if(currPage==null || currPage<1) currPage=1;
        PageHelper.startPage(currPage, pageSize);
        //一页的任务
        Date today = new Date();
        if(status==Constant.TASK_STATUS_VALID){
        PageInfo<Task> po = new PageInfo<>(taskMapper.selectValidTasks(today));
            return getTaskVOPageInfo(uid, po);
        }
        else {
            PageInfo<Task> po = new PageInfo<>(taskMapper.selectInvalidTasks(today));
            return getTaskVOPageInfo(uid, po);}
//        PageInfo<Task> po = new PageInfo<>(taskMapper.selectTasks(status));
    }



    //发包方浏览自己发布的众包任务（个人信息）
    @Override
    public List<TaskVO> getManageableTasks(Integer uid) {
        List<TaskVO> ret = new ArrayList<>();
        List<Task> taskList = taskMapper.selectByEmployerId(uid);
        for (Task task : taskList) {
            ret.add(new TaskVO(task, false, true));
        }
        return ret;
    }

    @Override
    public TaskVO getTask(Integer taskId, Integer uid) {
        Task task = taskMapper.selectByPrimaryKey(taskId);
        boolean accepted = false;
        boolean manageable = false;
        if (uid != null && uid > 0) {
            TodoTask todoTask = todoTaskService.queryMostRecentTask(uid, taskId);
            if (todoTask != null)
                accepted = todoTask.getStatus().equals(Constant.TODO_STATUS_SUCCESS);
            manageable = uid.equals(task.getEmployerId());
        }


        return new TaskVO(task, accepted, manageable);
    }

//  发包方创建任务，创建的所有任务在task表中,此时参数taskVO已经包含两个文件名
    @Override
    public ResultVO<TaskVO> createTask(TaskVO taskVO) {

        for (Task task : taskMapper.selectByEmployerId(taskVO.getEmployerId())) {
            if (task.getName().equals(taskVO.getName()))
                return new ResultVO<>(Constant.REQUEST_FAIL, "已存在同名任务！");
        }

        Task task=new Task(taskVO);
        if (taskMapper.insert(task)>0) {
            return new ResultVO<TaskVO>(Constant.REQUEST_SUCCESS, "任务创建成功。", new TaskVO(task, false, true));
        }
        return new ResultVO<>(Constant.REQUEST_FAIL, "服务器错误");
    }


    @Override
    public Task getByPrimaryKey(Integer taskId) {
        return taskMapper.selectByPrimaryKey(taskId);
    }

    //修改传入的task的状态属性并返回task
    private PageInfo<TaskVO> getTaskVOPageInfo(Integer uid, PageInfo<Task> po) {
        PageInfo<TaskVO> result = PageInfoUtil.convert(po, TaskVO.class);
        if (uid != null && uid > 0) {
            List<TaskVO> voList = result.getList();
            for (TaskVO vo : voList) {
//                Date now = new Date();
//                //如果当前时间超出结束时间
//                if (now.compareTo(vo.getEndTime()) > 0) {
//                    vo.setStatus(Constant.TASK_STATUS_INVALID);
//                }
                TodoTask todo = todoTaskService.queryMostRecentTask(uid, vo.getId());
                //若用户接受了该任务
                if (todo!= null)
                    vo.setAccepted(true);
                //若用户是该任务的发包方
                vo.setManageable(uid.equals(vo.getEmployerId()));
            }
        }
        return result;
    }




    @Override
    public List<TaskVO> getAllTasks() {
        List<TaskVO> ret = new ArrayList<>();
        List<Task> taskList = taskMapper.selectAll();
        for (Task task : taskList) {
            ret.add(new TaskVO(task, false, true));
        }
        return ret;
    }

    @Override
    public List<TaskVO> getRecommendTasks(Integer eid) {
//        RecommendRule usingRule;
int type=recommendRuleMapper.queryUsingRule().getType();
switch(type){
    case 1:
        return itemSimilarityService.recommend(eid);
    case  2:
        return userAttributeRecommendService.recommend(eid);
    case 3:
        return taskBasedService.recommend(eid);
}
return itemSimilarityService.recommend(eid);
    }

    //获取还在招募的任务
    @Override
    public List<Task> getAvailableTasks() {
        Date today = new Date();
        List<Task> tasks=taskMapper.selectValidTasks(today);
//        List<TaskVO> ret = new ArrayList<>();
//        for (Task task : tasks) {
//            ret.add(new TaskVO(task, false, true));
//        }
        return tasks;
    }

    @Override
    public int getTaskStatus(Integer taskId) {
        return taskMapper.selectByPrimaryKey(taskId).getStatus();
    }


}

package cn.welldone.collect.serviceImpl.recommend;


import cn.welldone.collect.mapperservice.recommend.RecommendRuleMapper;
import cn.welldone.collect.mapperservice.task.TaskMapper;
import cn.welldone.collect.mapperservice.task.TodoTaskMapper;
import cn.welldone.collect.mapperservice.user.PreferenceMapper;
import cn.welldone.collect.mapperservice.user.UserMapper;
import cn.welldone.collect.po.task.Task;
import cn.welldone.collect.po.task.TodoTask;
import cn.welldone.collect.po.user.Preference;
import cn.welldone.collect.service.recommend.RecommendService;
import cn.welldone.collect.service.task.TaskService;
import cn.welldone.collect.service.user.PreferenceService;
import cn.welldone.collect.vo.task.TaskVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("userAttributeRecommendServiceImpl")
public class userAttributeRecommendServiceImpl implements RecommendService {
    @Resource
    RecommendRuleMapper recommendRuleMapper;
    @Resource
    TaskService taskService;

    @Resource
    PreferenceService preferenceService;

    @Resource
    TodoTaskMapper todoTaskMapper;

    @Resource
    private TaskMapper taskMapper;

    @Resource
    PreferenceMapper preferenceMapper;

    @Resource
    private UserMapper userMapper;


    @Override
    public List<TaskVO> recommend(int uid) {
        List<Task> tasks = taskService.getAvailableTasks();
        List<TodoTask> todoTasks = todoTaskMapper.selectByEmployeeId(uid);

        HashMap<String, String> map = new HashMap<String, String>();

//杰卡德距离用两个集合中不同元素占所有元素的比例来衡量两个集合的区分度。
        Preference preference = preferenceMapper.selectByEmployeeId(uid);
        map.put("device", preference.getDevice());
        map.put("taskType", preference.getTaskType());
        TreeSet<Task> preList = new TreeSet<Task>(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                if (o1.getSimilarity() != o2.getSimilarity())
                    return (int) (o1.getSimilarity() - o2.getSimilarity()) * 100; //按任务相似度排序
                else if (o1.getRequiredNumber() != o2.getRequiredNumber()) {
                    return o1.getRequiredNumber() - o2.getRequiredNumber();
                }
                ///////////////////
                else {
                    return o1.getId() - o2.getId();
                }
            }
        }); //预处理的列表
        boolean flag = false;//判断任务是否被接收过
        for (Task task : tasks) {
            for (TodoTask todoTask : todoTasks) {
                if (todoTask.getTaskId() == task.getId()) {
                    flag = true;
                    break;
                }
            }
            if(flag==true){
                flag=false;
                continue;
            }
            int fenzi = 0;
            int fenmu = 0;
            if (map.get("device").equals(task.getDevice())) {//有相同属性
                fenzi++;
            }
            if (map.get("taskType").equals(task.getType())) {//有相同属性
                fenzi++;
            }
            fenmu = 4 - fenzi;
            double similarity = (double) fenzi / (double) fenmu;
            task.setSimilarity(((double) Math.round(similarity * 100) / 100) * 100);
            preList.add(task);

        }


        List<Task> recomLists = new ArrayList<>();      //生成的推荐结果
        for(int i = 0; preList.size()>0 && i<3; i++){
            recomLists.add(preList.pollLast()); //按相似度从高到低添加
            //       preList.pollLast();
        }
        if(recomLists.size()<5){
            //推荐数量不满5个, 补足喜欢数最高的文章
            //recomLists = Taskdao.findTopNTasks(recomLists);
            //  recomLists=taskMapper.selectAll();
        }
        List<TaskVO> ret = new ArrayList<>();
        for(Task task:recomLists){
            ret.add(new TaskVO(task));
        }
        return ret;
    }


}

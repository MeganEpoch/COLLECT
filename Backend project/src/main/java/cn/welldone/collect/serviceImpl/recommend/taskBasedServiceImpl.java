package cn.welldone.collect.serviceImpl.recommend;

import cn.welldone.collect.mapperservice.recommend.RecommendRuleMapper;
import cn.welldone.collect.mapperservice.task.TodoTaskMapper;
import cn.welldone.collect.mapperservice.user.UserMapper;
import cn.welldone.collect.po.task.Task;
import cn.welldone.collect.po.task.TodoTask;
import cn.welldone.collect.service.recommend.RecommendService;
import cn.welldone.collect.service.task.TaskService;
import cn.welldone.collect.vo.task.TaskVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;



@Service("taskBasedServiceImpl")
public class taskBasedServiceImpl implements RecommendService {
    @Resource
    RecommendRuleMapper recommendRuleMapper;

    @Resource
    TodoTaskMapper todoTaskMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
private TaskService taskService;



    @Override
    public List<TaskVO> recommend(int uid) {
        int tasksNum=todoTaskMapper.selectByEmployeeId(uid).size();
        double LinuxScore=(double)todoTaskMapper.countByDevice(uid,"Linux")/(double)tasksNum;
        double AndroidScore=(double)todoTaskMapper.countByDevice(uid,"Android")/(double)tasksNum;
        double IosScore=(double)todoTaskMapper.countByDevice(uid,"ios")/(double)tasksNum;
        double HarmonyScore=(double)todoTaskMapper.countByDevice(uid,"鸿蒙")/(double)tasksNum;

        double performanceScore=(double)todoTaskMapper.countByType(uid,"性能测试")/(double)tasksNum;;
        double functionScore=(double)todoTaskMapper.countByType(uid,"功能测试")/(double)tasksNum;;

        List<Task> tasks=taskService.getAvailableTasks();
        List<TodoTask> todoTasks=todoTaskMapper.selectByEmployeeId(uid);
        TreeSet<Task> preList = new TreeSet<Task>(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                if(o1.getSimilarity()!=o2.getSimilarity())
                    return (int) (o1.getSimilarity()-o2.getSimilarity())*100; //按任务相似度排序
                else if(o1.getRequiredNumber()!=o2.getRequiredNumber()){
                    return o1.getRequiredNumber()-o2.getRequiredNumber();
                }
                ///////////////////
                else{
                    return  o1.getId()-o2.getId();
                }
            }
        }); //预处理的列表

        boolean flag=false;//判断任务是否被接收过
for(Task task:tasks) {
for (TodoTask todoTask:todoTasks ){
    if(todoTask.getTaskId()==task.getId()){
        flag=true;
        break;
    }
}
if(flag==true){
    flag=false;
    continue;
}
    double similarity = 0;
    switch (task.getDevice()) {
        case "Linux":
            similarity += LinuxScore;
            break;

        case "Android":
            similarity += AndroidScore;
            break;
        case "ios":
            similarity += IosScore;
            break;
        case "鸿蒙":
            similarity += HarmonyScore;
            break;
    }

    switch (task.getType()) {
        case "性能测试":
            similarity += performanceScore;
            break;
        case "功能测试":
            similarity += functionScore;
            break;
    }
    task.setSimilarity(((double) Math.round(similarity * 100) / 100)*100);
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


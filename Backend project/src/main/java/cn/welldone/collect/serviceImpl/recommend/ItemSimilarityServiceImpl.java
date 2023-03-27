package cn.welldone.collect.serviceImpl.recommend;

import cn.welldone.collect.mapperservice.recommend.RecommendRuleMapper;
import cn.welldone.collect.mapperservice.task.TaskMapper;
import cn.welldone.collect.mapperservice.task.TodoTaskMapper;
import cn.welldone.collect.mapperservice.user.UserMapper;
import cn.welldone.collect.po.task.Task;
import cn.welldone.collect.po.task.TodoTask;
import cn.welldone.collect.po.user.User;
import cn.welldone.collect.service.recommend.RecommendService;
import cn.welldone.collect.vo.task.TaskVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("ItemSimilarityServiceImpl")
public class ItemSimilarityServiceImpl implements RecommendService {
    @Resource
    RecommendRuleMapper recommendRuleMapper;

    @Resource
    private TaskMapper taskMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private TodoTaskMapper todoTaskMapper;


    @Override
    public List<TaskVO> recommend(int uid) {

//        UserDao userdao = new UserDao();
//        TaskDao Taskdao = new TaskDao();
//        todoTaskDao todoTaskdao = new todoTaskDao();

        List<TodoTask> todoTaskLists;                                       //其他用户接受的任务列表

        List<User> users = userMapper.selectAll();                   //所有用户列表
        List<Task> Tasks = taskMapper.selectValidTasks(new Date());               //所有任务列表
        int[][] curMatrix = new int[Tasks.size()+5][Tasks.size()+5];   //当前矩阵 当前用户接受过的任务和用户i接受过的任务
        int[][] comMatrix = new int[Tasks.size()+5][Tasks.size()+5];   //共现矩阵
        int[] N = new int[Tasks.size()+5];                              //接受每个任务的人数


        for(User user: users){
            if(user.getId()==uid) continue;                    //当前用户则跳过

            todoTaskLists = todoTaskMapper.selectByEmployeeId(user.getId());//todoTaskdao.findtodoTasksByUser(user.getUid()); //用户接受过的任务

            for(int i = 0; i < Tasks.size(); i++)
                for(int j = 0; j < Tasks.size(); j++)
                    curMatrix[i][j] = 0;                               //清空矩阵

            for(int i = 0; i < todoTaskLists.size(); i++){
                int pid1 = todoTaskLists.get(i).getTaskId();//pid：被接取的任务
                ++N[pid1];
                for(int j = i+1; j < todoTaskLists.size(); j++){
                    int pid2 = todoTaskLists.get(j).getTaskId();
                    ++curMatrix[pid1][pid2];
                    ++curMatrix[pid2][pid1]; //两两加一
                }
            }
            //累加所有矩阵, 得到共现矩阵
            for(int i = 0; i < Tasks.size(); i++){
                for(int j = 0; j < Tasks.size(); j++){
                    int pid1 = Tasks.get(i).getId(), pid2 = Tasks.get(j).getId();
                    comMatrix[pid1][pid2] += curMatrix[pid1][pid2];
                    comMatrix[pid1][pid2] += curMatrix[pid1][pid2];
                }
            }
        }


        TreeSet<Task> preList = new TreeSet<Task>(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                if(o1.getSimilarity()!=o2.getSimilarity())
                    return (int) (o1.getSimilarity()-o2.getSimilarity())*100; //按任务相似度排序
                else if(o1.getRequiredNumber()!=o2.getRequiredNumber()){
                    return o1.getRequiredNumber()-o2.getRequiredNumber();
                }
                else{
                    return  o1.getId()-o2.getId();
                }
            }
        }); //预处理的列表

        todoTaskLists = todoTaskMapper.selectByEmployeeId(uid);////当前用户选择的任务列表
        boolean[] used = new boolean[Tasks.size()+5];  //判重数组
        for(TodoTask todoTask: todoTaskLists){
            int Nij = 0;                         //既喜欢i又喜欢j的人数
            double Wij;                          //相似度
            Task tmp;                           //当前的任务

            int i = todoTask.getTaskId();
            for(Task task: Tasks){
                if(todoTask.getTaskId() == task.getId()) continue; //如果接受过该任务就跳过
                int j = task.getId();

                Nij = comMatrix[i][j];
                if(Nij==0){
                    Wij=0;
                }
                else {
                    Wij = (double) Nij / Math.sqrt(N[i] * N[j]); //计算余弦相似度
                }
                tmp = taskMapper.selectByPrimaryKey(task.getId());//Taskdao.findTaskById(Task.getPid());
                tmp.setSimilarity(Wij);

                if(used[tmp.getId()]) continue;


                preList.add(tmp);
                used[tmp.getId()] = true;
            }
        }
        if(todoTaskMapper.selectByEmployeeId(uid).size()==0){
            for (Task task: Tasks){
                preList.add(task);
            }
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

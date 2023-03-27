package cn.welldone.collect.vo.task;

import cn.welldone.collect.po.task.Task;
import cn.welldone.collect.util.Constant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
//生成全参数构造函数供test使用
@AllArgsConstructor
public class TaskVO {
    private Integer id;

    private String name;

    private String type;

    private String intro;

    //private String picture;

    //private String school;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    private Integer requiredNumber;

    private Integer employerId;

    private String employerName;
    // 标记此任务是否过期
    private Integer status;


    // 标记当前用户（工人）是否已接受任务
    private boolean accepted;

    // 标记当前用户（发包方）是否有权限管理任务
    private boolean manageable;

//private MultipartFile file1;
//
//    private MultipartFile file2;
//
   private String fileName1;

    private String fileName2;

    private String device;

    private int difficulty;


    public TaskVO() {
        accepted = false;
    }

    public TaskVO(@NonNull Task task){
        id = task.getId();
        name = task.getName();
        type = task.getType();
        intro = task.getIntro();
        createTime = task.getCreateTime();
        endTime = task.getEndTime();
        requiredNumber=task.getRequiredNumber();
        employerId = task.getEmployerId();
        employerName = task.getEmployerName();
        accepted = false;
        manageable = false;
        status= Constant.TASK_STATUS_VALID;
        fileName1=task.getFileName1();
        fileName2=task.getFileName2();
        device=task.getDevice();
        difficulty=task.getDifficulty();
    }

    public TaskVO(@NonNull Task task, boolean accepted, boolean manageable){
        id = task.getId();
        name = task.getName();
        type = task.getType();
        intro = task.getIntro();
        createTime = task.getCreateTime();
        endTime = task.getEndTime();
        requiredNumber=task.getRequiredNumber();
        employerId = task.getEmployerId();
        employerName = task.getEmployerName();
        this.accepted = accepted;
        this.manageable = manageable;
        this.status=Constant.TASK_STATUS_VALID;
        fileName1=task.getFileName1();
        fileName2=task.getFileName2();
        device=task.getDevice();
        difficulty=task.getDifficulty();
    }


}

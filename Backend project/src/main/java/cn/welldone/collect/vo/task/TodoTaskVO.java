package cn.welldone.collect.vo.task;

import cn.welldone.collect.po.task.TodoTask;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;
import java.util.List;

@Data
public class TodoTaskVO {
    private Integer id;

    private Integer taskId;

    private String taskName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private Integer userId;

    private Integer status;



    public TodoTaskVO(@NonNull TodoTask todoTask,Integer status) {
        id = todoTask.getId();
        taskId = todoTask.getTaskId();
        taskName = todoTask.getTaskName();
        createTime = todoTask.getCreateTime();
        userId = todoTask.getUserId();
        this.status=status;
    }

    public TodoTaskVO(@NonNull TodoTask todoTask) {
        id = todoTask.getId();
        taskId = todoTask.getTaskId();
        taskName = todoTask.getTaskName();
        createTime = todoTask.getCreateTime();
        userId = todoTask.getUserId();
        status = todoTask.getStatus();
    }
}

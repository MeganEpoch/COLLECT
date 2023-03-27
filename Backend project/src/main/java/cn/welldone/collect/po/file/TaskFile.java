package cn.welldone.collect.po.file;

import cn.welldone.collect.vo.file.TaskFileVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskFile {
private Integer id;
private Integer taskId;
//0为可执行文件，1为测试文档
private Integer index;
private String name;

    public TaskFile(TaskFileVO taskFileVO){
        this.id=taskFileVO.getId();
        this.taskId=taskFileVO.getTaskId();
        this.index=taskFileVO.getIndex();
        this.name=taskFileVO.getName();
    }

}


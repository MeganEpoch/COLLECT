package cn.welldone.collect.vo.file;

import cn.welldone.collect.po.file.TaskFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskFileVO {
    private Integer id;
    private Integer taskId;
    //0为可执行文件，1为测试文档
    private Integer index;
    private String name;

    public TaskFileVO(TaskFile taskFile){
        this.id=taskFile.getId();
        this.taskId=taskFile.getTaskId();
        this.index=taskFile.getIndex();
        this.name=taskFile.getName();
    }
}

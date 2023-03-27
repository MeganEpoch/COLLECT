package cn.welldone.collect.mapperservice.file;

import cn.welldone.collect.po.file.TaskFile;
import org.apache.ibatis.annotations.Param;

public interface TaskFileMapper {
    int insert(TaskFile taskFile);
    TaskFile selectByTask(@Param("taskId")Integer taskId, @Param("index")Integer index);
}

package cn.welldone.collect.mapperservice.report;

import java.util.List;

import cn.welldone.collect.po.report.Report;
import org.apache.ibatis.annotations.Param;

public interface ReportMapper {
    List<Report> selectByTaskId(Integer taskId);

    Report selectByPrimaryKey(Integer id);

    int insert(Report report);

    int updateByPrimaryKey(Report report);

    void clearTable();

    int updateScore(@Param("reportId")Integer reportId, @Param("score")Double score);

    Report selectByTaskIdAndEmployeeId(@Param("taskId")Integer taskId, @Param("employeeId")Integer employeeId);

    int countReportNum(Integer taskId);
}

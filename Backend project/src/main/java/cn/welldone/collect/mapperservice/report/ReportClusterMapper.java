package cn.welldone.collect.mapperservice.report;

import cn.welldone.collect.po.report.ReportCluster;
import cn.welldone.collect.vo.report.ReportClusterVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReportClusterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReportCluster reportCluster);

    ReportCluster selectByPrimaryKey(Integer id);

    List<ReportCluster> selectByTaskId(Integer id);

    ReportCluster selectByReportId(Integer reportId);

    List<ReportCluster> selectByTaskIdAndCluster(@Param("taskId") Integer id, @Param("cluster") Integer cluster);

    List<ReportCluster> selectAll();

    int updateByPrimaryKey(ReportCluster reportCluster);
}
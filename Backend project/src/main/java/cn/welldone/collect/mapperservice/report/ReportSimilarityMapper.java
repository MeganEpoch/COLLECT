package cn.welldone.collect.mapperservice.report;

import cn.welldone.collect.po.report.ReportSimilarity;
import java.util.List;

public interface ReportSimilarityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReportSimilarity record);

    ReportSimilarity selectByPrimaryKey(Integer id);

    List<ReportSimilarity> selectAll();

    int updateByPrimaryKey(ReportSimilarity record);

    List<ReportSimilarity> selectByReportId1(Integer reportId1);
}
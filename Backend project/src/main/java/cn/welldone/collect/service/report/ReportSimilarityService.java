package cn.welldone.collect.service.report;

import cn.welldone.collect.vo.report.ReportSimilarityVO;

import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ReportSimilarityService {
    PageInfo<ReportSimilarityVO> getSimilarReports(Integer taskId, Integer page, Integer pageSize, Integer reportId);

    //计算当前报告和该任务下其他报告的相似度
    void calculateReportSimilarity(Integer taskId,Integer reportId,Integer oldReportId);

    //获取补充报告和原报告的相似度(reportSimilarityMapper中只有补充报告和原报告一个相似度，没有和其他的对比)
    ReportSimilarityVO getSimilarReport(Integer taskId,Integer reportId,Integer oldReportId);
}

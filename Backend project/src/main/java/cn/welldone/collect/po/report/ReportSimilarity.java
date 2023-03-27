package cn.welldone.collect.po.report;

import cn.welldone.collect.vo.report.ReportSimilarityVO;
import cn.welldone.collect.vo.report.ReportVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportSimilarity {
    private Integer id;
    private Integer reportId1;
    private Integer reportId2;
    private Double title;
    private Double intro;
    private Double steps;
    private Double device;
    private String picture;
    private Double bugForm;
    private Double severity;
    private Double recurrence;
    private Double similarity;

    public ReportSimilarity(ReportSimilarityVO reportSimilarityVO) {
        this(reportSimilarityVO.getId(), reportSimilarityVO.getReportId1(), reportSimilarityVO.getReportId2(),
                reportSimilarityVO.getTitle(), reportSimilarityVO.getIntro(),reportSimilarityVO.getSteps(),reportSimilarityVO.getDevice(),
                reportSimilarityVO.getPicture(), reportSimilarityVO.getBugForm(),reportSimilarityVO.getSeverity(),
                reportSimilarityVO.getRecurrence(),reportSimilarityVO.getSimilarity());
    }
}

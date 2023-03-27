package cn.welldone.collect.vo.report;

import cn.welldone.collect.po.report.Report;
import cn.welldone.collect.po.report.ReportSimilarity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportSimilarityVO {
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

    public ReportSimilarityVO(ReportSimilarity reportSimilarity) {
        this(reportSimilarity.getId(), reportSimilarity.getReportId1(), reportSimilarity.getReportId2(),
                reportSimilarity.getTitle(),reportSimilarity.getIntro(), reportSimilarity.getSteps(),
                reportSimilarity.getDevice(),reportSimilarity.getPicture(), reportSimilarity.getBugForm(),
                reportSimilarity.getSeverity(),reportSimilarity.getRecurrence(),reportSimilarity.getSimilarity());
    }
}

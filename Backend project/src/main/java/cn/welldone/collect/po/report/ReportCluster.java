package cn.welldone.collect.po.report;

import cn.welldone.collect.vo.report.ReportClusterVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportCluster {
    private Integer id;
    private Integer taskId;
    private Integer reportId;
    //reportCluster id
    private Integer reportCluster;
    //example中文字信息
    private String intro;
    //标志是否是主报告，0不是1是，默认是0
    private Integer status;
    //如果是主报告，extra   内容是补充点
    private String extra;

    public ReportCluster(ReportClusterVO reportClusterVO){
        this(reportClusterVO.getId(),reportClusterVO.getTaskId(),reportClusterVO.getReportId(),
                reportClusterVO.getReportCluster(),reportClusterVO.getIntro(),reportClusterVO.getStatus(),
                reportClusterVO.getExtra());
    }
}

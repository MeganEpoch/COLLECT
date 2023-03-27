package cn.welldone.collect.po.report;

import java.util.Date;

import cn.welldone.collect.vo.report.ReportVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    private Integer id;
    private Integer taskId;
    private Integer employeeId;
    private String employeeName;
    private Date reportTime;
    private String title;
    private String intro;
    private String steps;
    private String device;
    private String picture;
    private String bugForm;
    private String severity;
    private String recurrence;
    private Double score;

//    public Report(ReportVO reportVO) {
//        this.id=reportVO.getId();
//        this.taskId=reportVO.getTaskId();
//        this.employeeId=reportVO.getEmployeeId();
//        this.employeeName=reportVO.getEmployeeName();
//        this.name=reportVO.getName();
//        this.intro=reportVO.getIntro();
//        this.steps=reportVO.getSteps();
//        this.device=reportVO.getDevice();
//        this.picture=reportVO.getPicture();
//        this.manageable=reportVO.isManageable();
//        this.reportTime=reportVO.getReportTime();
//    }

    public Report(ReportVO reportVO) {
        this(reportVO.getId(), reportVO.getTaskId(), reportVO.getEmployeeId(), reportVO.getEmployeeName(),reportVO.getReportTime(),
                reportVO.getTitle(), reportVO.getIntro(),reportVO.getSteps(),reportVO.getDevice(),reportVO.getPicture(),
                reportVO.getBugForm(),reportVO.getSeverity(),reportVO.getRecurrence(),reportVO.getScore());
    }
}

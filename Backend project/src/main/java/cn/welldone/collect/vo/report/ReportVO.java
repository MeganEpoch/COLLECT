package cn.welldone.collect.vo.report;

import java.util.Date;

import cn.welldone.collect.po.report.Report;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportVO {
    private Integer id;
    private Integer taskId;
    private Integer employeeId;
    private String employeeName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date reportTime;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    private Date lastCommentTime;
    private String title;
    private String intro;
    private String steps;
    private String device;
    private String picture;
    private String bugForm;
    private String severity;
    private String recurrence;
    private Double score;


//    public ReportVO(Report report) {
//        id=report.getId();
//        taskId=report.getTaskId();
//        employeeId=report.getEmployeeId();
//        employeeName=report.getEmployeeName();
//        name=report.getName();
//        intro=report.getIntro();
//        steps=report.getSteps();
//        device=report.getDevice();
//        picture=report.getPicture();
//        manageable=report.isManageable();
//        reportTime=report.getReportTime();
//    }

    public ReportVO(Report report) {
        this(report.getId(), report.getTaskId(), report.getEmployeeId(), report.getEmployeeName(),
                report.getReportTime(),report.getTitle(),report.getIntro(),
                report.getSteps(),report.getDevice(),report.getPicture(),
                report.getBugForm(),report.getSeverity(),report.getRecurrence(),report.getScore());
    }

}

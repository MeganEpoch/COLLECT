package cn.welldone.collect.serviceImpl.report;

import cn.welldone.collect.mapperservice.report.ReportMapper;
import cn.welldone.collect.mapperservice.report.ReportSimilarityMapper;
import cn.welldone.collect.po.report.Report;
import cn.welldone.collect.po.report.ReportSimilarity;
import cn.welldone.collect.service.report.ReportSimilarityService;
import cn.welldone.collect.vo.report.ReportSimilarityVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.xm.similarity.text.CosineSimilarity;
import org.xm.similarity.text.TextSimilarity;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class ReportSimilarityServiceImpl implements ReportSimilarityService {

    @Resource
    ReportMapper reportMapper;
    @Resource
    ReportSimilarityMapper reportSimilarityMapper;

    @Override
    public PageInfo<ReportSimilarityVO> getSimilarReports(Integer taskId, Integer page, Integer pageSize, Integer reportId) {

        if (page == null || page < 1)
            page = 1;
        PageHelper.startPage(page, pageSize);
//计算当前报告和其他报告的相似度，获得report_similarity表
        calculateReportSimilarity(taskId, reportId,-1);

        // select similarReports in reports
        List<ReportSimilarityVO> similarReports = reportSimilarityMapper.selectByReportId1(reportId).stream().map((reportSimilarity) -> {
            ReportSimilarityVO vo = new ReportSimilarityVO(reportSimilarity);
            return vo;
        }).collect(Collectors.toList());

        // sort similarReports using similarity
        Comparator<ReportSimilarityVO> cmp = (a, b) -> b.getSimilarity().compareTo(a.getSimilarity());
        similarReports.sort(cmp);

        //页码超过最大时，返回空
        List<ReportSimilarity> reportsAll = reportSimilarityMapper.selectByReportId1(reportId);
        int maxPage = reportsAll.size() / pageSize;
        if (reportsAll.size() % pageSize != 0) {
            maxPage++;
        }
        if (page > maxPage) {
            reportsAll.clear();
            similarReports.clear();
        }

        return new PageInfo<>(similarReports);
    }


    @Override
    public void calculateReportSimilarity(Integer taskId, Integer reportId,Integer oldReportId) {
        List<ReportSimilarityVO> ret = new ArrayList<>();
        List<Report> reportList;
        Report report0 = reportMapper.selectByPrimaryKey(reportId);
        if(oldReportId==-1) {
            reportList = reportMapper.selectByTaskId(taskId);
            reportList.remove(report0);
        }else{
            reportList = Collections.singletonList(reportMapper.selectByPrimaryKey(oldReportId));
        }
        //如果已经保存当前报告和其他报告相似度则不再保存
        if (reportSimilarityMapper.selectByReportId1(reportId).size() != 0) {
            return;
        }
        //计算当前报告和其他报告的相似度
        ReportSimilarity reportSimilarity = new ReportSimilarity();
        reportSimilarity.setReportId1(reportId);
        reportSimilarity.setId(null);
        reportSimilarity.setPicture("picture");
        double title;
        double intro;
        double steps;
        double device;
        double bugForm;
        double severity;
        double recurrence;
        double similarity;
        TextSimilarity cosSimilarity = new CosineSimilarity();
        for (Report report1 : reportList) {
            reportSimilarity.setReportId2(report1.getId());
            title = cosSimilarity.getSimilarity(report0.getTitle(), report1.getTitle());
            reportSimilarity.setTitle(title);
            intro = cosSimilarity.getSimilarity(report0.getIntro(), report1.getIntro());
            reportSimilarity.setIntro(intro);
            steps = cosSimilarity.getSimilarity(report0.getSteps(), report1.getSteps());
            reportSimilarity.setSteps(steps);
            device = cosSimilarity.getSimilarity(report0.getDevice(), report1.getDevice());
            reportSimilarity.setDevice(device);
            bugForm = cosSimilarity.getSimilarity(report0.getBugForm(), report1.getBugForm());
            reportSimilarity.setBugForm(bugForm);
            severity = cosSimilarity.getSimilarity(report0.getSeverity(), report1.getSeverity());
            reportSimilarity.setSeverity(severity);
            recurrence = cosSimilarity.getSimilarity(report0.getRecurrence(), report1.getRecurrence());
            reportSimilarity.setRecurrence(recurrence);
            similarity = title * 0.1 + intro * 0.45 + steps * 0.3 + device * 0.05 + bugForm * 0.05 + severity * 0.025 + recurrence * 0.025;
            reportSimilarity.setSimilarity(similarity);
            reportSimilarityMapper.insert(reportSimilarity);
        }
    }

    @Override
    public ReportSimilarityVO getSimilarReport(Integer taskId,Integer reportId,Integer oldReportId){
        calculateReportSimilarity(taskId,reportId,oldReportId);
        List<ReportSimilarityVO> similarReports = reportSimilarityMapper.selectByReportId1(reportId).stream().map((reportSimilarity) -> {
            ReportSimilarityVO vo = new ReportSimilarityVO(reportSimilarity);
            return vo;
        }).collect(Collectors.toList());
        ReportSimilarityVO reportSimilarityVO=similarReports.get(0);
        return reportSimilarityVO;
    }

}

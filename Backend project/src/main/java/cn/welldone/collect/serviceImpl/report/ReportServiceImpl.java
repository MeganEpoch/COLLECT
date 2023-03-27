package cn.welldone.collect.serviceImpl.report;

import cn.welldone.collect.mapperservice.report.ReportMapper;
import cn.welldone.collect.mapperservice.task.TaskMapper;
import cn.welldone.collect.po.report.Report;
import cn.welldone.collect.service.task.TaskService;
import cn.welldone.collect.util.Constant;
import cn.welldone.collect.vo.report.ReportVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.welldone.collect.service.report.ReportService;
import cn.welldone.collect.vo.ResultVO;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
class ReportServiceImpl implements ReportService {
    @Resource
    ReportMapper reportMapper;
    @Resource
    TaskMapper taskMapper;
    @Autowired
    TaskService taskService;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public PageInfo<ReportVO> getReports(Integer taskId, Integer page, Integer pageSize, String sortMethod) {
        assert sortMethod.equals("by-report-time") || sortMethod.equals("by-latest-comment");
        if (page == null || page < 1)
            page = 1;
        PageHelper.startPage(page, pageSize);

        // select reports in tasks and calculate latest comment time
        List<ReportVO> reports = reportMapper.selectByTaskId(taskId).stream().map((report) -> {
            ReportVO vo = new ReportVO(report);
            return vo;
        }).collect(Collectors.toList());

        // sort reports using requested method
        Comparator<ReportVO> cmp = sortMethod.equals("by-report-time")
                ? ((a, b) -> b.getReportTime().compareTo(a.getReportTime()))
                : ((a, b) -> {
                    Comparator<Date> dcmp = Comparator.nullsLast(Comparator.reverseOrder());
                    return b.getReportTime().compareTo(a.getReportTime());
//                    return dcmp.compare(a.getLastCommentTime(), b.getLastCommentTime());
                });
        reports.sort(cmp);

        //页码超过最大时，返回空
        List<Report> postsAll = reportMapper.selectByTaskId(taskId);
        int maxPage = postsAll.size()/pageSize;
        if(postsAll.size()%pageSize!=0){
            maxPage++;
        }
        if(page>maxPage){
            postsAll.clear();
            reports.clear();
        }

        return new PageInfo<>(reports);
    }



    @Override
    public ResultVO<ReportVO> getReport(Integer id) {
        Report report = reportMapper.selectByPrimaryKey(id);
        if (report == null)
            return new ResultVO<>(Constant.REQUEST_FAIL, "没有该报告");

        return new ResultVO<ReportVO>(Constant.REQUEST_SUCCESS, "获取报告成功", new ReportVO(report));
    }

    @Override
    public ResultVO<ReportVO> saveReport(ReportVO reportVO) {
        int id;
        Integer errorCode;
        String message;
        try {
            // if the report does not exist, create a new report; otherwise update the report
            if (reportVO.getId() == null || reportMapper.selectByPrimaryKey(reportVO.getId()) == null) {

                reportVO.setReportTime(new Date());
//                reportVO.setLastCommentTime(null);

                Report report = new Report(reportVO);
                String url=report.getPicture();
                url="http://"+url;
                report.setPicture(url);
                reportMapper.insert(report);

                //如果全部报告都提交完
                int reportNum=reportMapper.countReportNum(reportVO.getTaskId());
                if(reportNum==taskMapper.selectByPrimaryKey(reportVO.getTaskId()).getRequiredNumber()){
                    taskMapper.updateStatusByPrimaryKey(reportVO.getTaskId(),Constant.TASK_STATUS_FINISH);
                }

                id = report.getId();
                errorCode = Constant.REQUEST_SUCCESS;
                message = "报告创建成功";
            } else {
                id=reportVO.getId();
                String url=reportVO.getPicture();
                url="http://"+url;
                reportVO.setPicture(url);
                reportMapper.updateByPrimaryKey(new Report(reportVO));
                errorCode = Constant.REQUEST_SUCCESS;
                message = "报告更新成功";
            }
        } catch (Exception e) {
            e.printStackTrace();
            errorCode = Constant.REQUEST_FAIL;
            message = "报告保存失败";
            id = -1;
        }

     /*   //提交报告，使任务结束
        Task task = taskService.getByPrimaryKey(reportVO.getTaskId());
        task.setStatus(TODO_STATUS_FINISHED);*/
        reportVO.setId(id);
        return new ResultVO<>(errorCode, message, reportVO);
    }

    @Override
    public ResultVO<ReportVO> getOldReport(Integer employeeId,Integer taskId) {
        Report report=reportMapper.selectByTaskIdAndEmployeeId(taskId,employeeId);
        if (report == null)
            return new ResultVO<>(Constant.REQUEST_FAIL, "没有填写过该任务的报告");

        return new ResultVO<ReportVO>(Constant.REQUEST_SUCCESS, "获取曾经填写报告成功", new ReportVO(report));
    }



}

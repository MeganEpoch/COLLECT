package cn.welldone.collect.service.report;

import cn.welldone.collect.vo.report.ReportVO;
import com.github.pagehelper.PageInfo;

import cn.welldone.collect.vo.ResultVO;

import java.util.List;


public interface ReportService {
    PageInfo<ReportVO> getReports(Integer taskId, Integer page, Integer pageSize, String sortMethod);

    ResultVO<ReportVO> getReport(Integer id);

    ResultVO<ReportVO> saveReport(ReportVO reportVO);

    ResultVO<ReportVO> getOldReport(Integer employeeId,Integer taskId);

}

package cn.welldone.collect.controller.report;

import cn.welldone.collect.po.report.ReportCluster;
import cn.welldone.collect.service.report.ReportClusterService;
import cn.welldone.collect.service.report.ReportService;
import cn.welldone.collect.service.report.ReportSimilarityService;
import cn.welldone.collect.util.Constant;
import cn.welldone.collect.vo.ResultVO;
import cn.welldone.collect.vo.report.ClusterTreeNodeVO;
import cn.welldone.collect.vo.report.ReportSimilarityVO;
import cn.welldone.collect.vo.report.ReportVO;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {
    //这个标签只能管下一行
    @Resource
    private ReportService reportService;
    @Resource
    private ReportSimilarityService reportSimilarityService;
    @Resource
    private ReportClusterService reportClusterService;

    @GetMapping("/{taskId}/{page}")
    public PageInfo<ReportVO> getReports(@PathVariable Integer taskId, @PathVariable Integer page) {
        return reportService.getReports(taskId, page, Constant.REPORT_PAGE_SIZE, "by-report-time");
    }


    @GetMapping("/by-id/{id}")
    public ResultVO<ReportVO> getReport(@PathVariable Integer id) {
        return reportService.getReport(id);
    }



    @PostMapping("/save")
    public ResultVO<ReportVO> saveReport(@RequestBody ReportVO reportVO) {
        return reportService.saveReport(reportVO);

    }

    @GetMapping("/{taskId}/{reportId}/{page}")
    public PageInfo<ReportSimilarityVO> getSimilarReports(@PathVariable Integer taskId, @PathVariable Integer reportId, @PathVariable Integer page) {
        return reportSimilarityService.getSimilarReports(taskId, page, Constant.REPORT_PAGE_SIZE, reportId);
    }

    @GetMapping("/cooperate/{taskId}/{reportId}/{oldReportId}")
    public ReportSimilarityVO getSimilarReport(@PathVariable Integer taskId, @PathVariable Integer reportId, @PathVariable Integer oldReportId) {
        return reportSimilarityService.getSimilarReport(taskId, reportId, oldReportId);
    }

    @GetMapping("/oldReport/{employeeId}/{taskId}")
    public ResultVO<ReportVO> getOldReport(@PathVariable Integer employeeId, @PathVariable Integer taskId) {
        return reportService.getOldReport(employeeId, taskId);
    }

    @GetMapping("/cluster/{id}")
    public List getReportCluster(@PathVariable Integer id) {
        return reportClusterService.getReportCluster(id);
    }


    @GetMapping("/cluster/tree/{id}")
    public List<ClusterTreeNodeVO> getClustersTreeView(@PathVariable Integer id) {
        return reportClusterService.getClustersTreeView(id);
    }

    @GetMapping("/mix/{taskId}/{cluster}")
    public int getMasterReport(@PathVariable Integer taskId, @PathVariable Integer cluster) {
        return reportClusterService.getMasterReport(taskId, cluster);
    }

    @GetMapping("/mix/extra/{id}")
    public List<ReportCluster> getReportExtra(@PathVariable Integer id) {
        return reportClusterService.getReportExtra(id);
    }

    @GetMapping("word_cloud/{taskId}/{cluster}")
    public String getWordCloud(@PathVariable Integer taskId, @PathVariable Integer cluster) {
        return reportClusterService.getWordCloud(taskId, cluster);
    }
}

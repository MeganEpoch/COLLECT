package cn.welldone.collect.service.report;

import cn.welldone.collect.po.report.ReportCluster;
import cn.welldone.collect.vo.ResultVO;
import cn.welldone.collect.vo.report.ClusterTreeNodeVO;
import cn.welldone.collect.vo.report.ReportVO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ReportClusterService {
    List<ReportCluster> getReportCluster(Integer id);

    int getMasterReport(Integer taskId, Integer cluster_id);

    List<ClusterTreeNodeVO> getClustersTreeView(Integer id);

    List<ReportCluster> getReportExtra(Integer id);

    String getWordCloud(Integer taskId, Integer cluster_id);
}

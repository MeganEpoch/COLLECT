package cn.welldone.collect.serviceImpl.report;

import cn.welldone.collect.mapperservice.report.ReportClusterMapper;
import cn.welldone.collect.mapperservice.report.ReportMapper;
import cn.welldone.collect.mapperservice.task.TaskMapper;
import cn.welldone.collect.po.report.Report;
import cn.welldone.collect.po.report.ReportCluster;
import cn.welldone.collect.service.report.ReportClusterService;
import cn.welldone.collect.util.PageRank;

import cn.welldone.collect.vo.report.ClusterTreeNodeVO;

import edu.emory.mathcs.backport.java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.xm.similarity.text.CosineSimilarity;
import org.xm.similarity.text.TextSimilarity;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportClusterServiceImpl implements ReportClusterService {
    @Resource
    TaskMapper taskMapper;
    @Resource
    ReportMapper reportMapper;
    @Resource
    ReportClusterMapper reportClusterMapper;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<ReportCluster> getReportCluster(Integer id) {
        List<ReportCluster> reportClusters = new ArrayList<>();
        if (reportClusterMapper.selectByTaskId(id).isEmpty()) {
            List<Report> reports = reportMapper.selectByTaskId(id);
            List<String> reportDescription = new ArrayList<>();
            for (Report report : reports) {
                reportDescription.add(report.getIntro());
                ReportCluster reportCluster = new ReportCluster();
                reportCluster.setTaskId(id);
                reportCluster.setReportId(report.getId());
                reportCluster.setIntro(report.getIntro());
                reportClusters.add(reportCluster);
            }
            for (Report report : reports) {
                reportDescription.add(report.getPicture());
            }
            String url = "http://124.222.219.151:8001/do_cluster";
//            String url1="http://0.0.0.0:8001/do_cluster";

            Map ret = restTemplate.postForObject(url, reportDescription, Map.class);

            if (ret != null) {
                for (int i = 0; i < reportClusters.size(); i++) {
                    //int len = ((List) ret.get("result")).get(i).toString().length();
                    //int cluster = (int) (((List) ret.get("result")).get(i).toString().charAt(len-2))-48;   //ascii码
                    int cluster = (int) (((List) ret.get("result")).get(i).toString().charAt(0)) - 48;   //ascii码
                    reportClusters.get(i).setReportCluster(cluster);
                    reportClusters.get(i).setStatus(0);
                }
            }
            for (int i = 0; i < reportClusters.size(); i++) {
                reportClusterMapper.insert(reportClusters.get(i));
            }
        } else {
            reportClusters = reportClusterMapper.selectByTaskId(id);
        }

//        按从小到大排序
        reportClusters = reportClusters.stream().sorted(Comparator.comparing(ReportCluster::getReportCluster))
                .collect(Collectors.toList());

        for(ReportCluster reportCluster:reportClusters) {
            String url=getWordCloud(reportCluster.getTaskId(),reportCluster.getReportCluster());
            reportCluster.setExtra(url);
        }
        return reportClusters;
    }

    @Override
    public int getMasterReport(Integer taskId, Integer cluster) {
        List<ReportCluster> clusters = reportClusterMapper.selectByTaskIdAndCluster(taskId, cluster);
        List<String> reportDescription = new ArrayList<>();
        List<String> pictureDescription = new ArrayList<>();
        for (ReportCluster reportcluster : clusters) {
            reportDescription.add(reportMapper.selectByPrimaryKey(reportcluster.getReportId()).getIntro());
            pictureDescription.add(reportMapper.selectByPrimaryKey(reportcluster.getReportId()).getPicture());
        }
        int n = pictureDescription.size();
        reportDescription.addAll(pictureDescription);
//        String url1 = "http://0.0.0.0:8001/get_similarity_matrix";
        String url = "http://124.222.219.151:8001/get_similarity_matrix";

        Map ret = restTemplate.postForObject(url, reportDescription, Map.class);
//        ArrayList<Double> similarity= (ArrayList<Double>)ret.get("result");
        Double[][] similarity = new Double[n][n];
        ArrayList<ArrayList<Double>> similarity_matrix = (ArrayList<ArrayList<Double>>) ret.get("result");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                similarity[i][j] = similarity_matrix.get(i).get(j);
            }
        }
        //            Double [][] similarity= (Double[][]) ret.get("result");

// 定义阻尼系数
        double damp = 0.85;
//定义一个初始数组并初始化，设所有的页面初始PR值都为1；
        double[] pr = new double[n];
        double[] init = new double[n];
        Arrays.fill(init, 1);
        pr = PageRank.pageRank(similarity, damp, init);


        //进行10次迭代计算pagerank的值
        for (int i = 0; i < 10; i++) {
            System.arraycopy(pr, 0, init, 0, n);
            pr = PageRank.pageRank(similarity, damp, pr);
        }
        for (int i = 0; i < n; i++) {
            System.out.println("PR(" + i + ") = " + pr[i]);
        }
        int max = 0;//最大值的下标
        for (int i = 1; i < n; i++) {
            if (Double.compare(pr[i], pr[max]) > 0) {
                max = i;
            }
        }
        return clusters.get(max).getReportId();
    }

    @Override
    public List<ClusterTreeNodeVO> getClustersTreeView(Integer id) {

        ClusterTreeNodeVO taskNode = new ClusterTreeNodeVO(10000, 0, taskMapper.selectByPrimaryKey(id).getName());
        List<ReportCluster> ret = getReportCluster(id);
        List<ClusterTreeNodeVO> result = new ArrayList<>();
        result.add(taskNode);
        //类簇数
        int clusterNum = ret.get(ret.size() - 1).getReportCluster() + 1;

        for (int i = 0; i < clusterNum; i++) {
            result.add(new ClusterTreeNodeVO(i + 1, 10000, Integer.toString(i + 1)));
        }
        for (int i = 0; i < ret.size(); i++) {
            String title = reportMapper.selectByPrimaryKey(ret.get(i).getReportId()).getTitle();
            result.add(new ClusterTreeNodeVO(ret.get(i).getReportId() + clusterNum, ret.get(i).getReportCluster() + 1,Integer.toString(ret.get(i).getReportId())));
        }
        return result;
    }

    public List<ReportCluster> getReportExtra(Integer id) {
        ReportCluster reportCluster1 = reportClusterMapper.selectByReportId(id);
        List<ReportCluster> reportClusters = reportClusterMapper.selectByTaskIdAndCluster(reportCluster1.getTaskId(), reportCluster1.getReportCluster());
        reportClusters.remove(reportCluster1);
        TextSimilarity cosSimilarity = new CosineSimilarity();
        Iterator<ReportCluster> iterator = reportClusters.iterator();
        while(iterator.hasNext()) {
            ReportCluster reportCluster = iterator.next();
            if (cosSimilarity.getSimilarity(reportCluster1.getIntro(), reportCluster.getIntro()) > 0.52)
                iterator.remove();   //注意这个地方
        }
//        for (ReportCluster reportCluster : reportClusters) {
//            if (cosSimilarity.getSimilarity(reportCluster1.getIntro(), reportCluster.getIntro()) > 0.5) {
//                reportClusters.remove(reportCluster);
//            }
//        }java.util.ConcurrentModificationException报错
        return reportClusters;
    }


    @Override
    public String getWordCloud(Integer taskId, Integer cluster) {
        List<ReportCluster> reportClusters = reportClusterMapper.selectByTaskIdAndCluster(taskId, cluster);
        List<String> text = new ArrayList<>();
        for (ReportCluster reportCluster : reportClusters) {
            text.add(reportCluster.getIntro());
        }
        if (reportClusters.get(0).getExtra() == null) {
            String url1 = "http://0.0.0.0:8001/word_cloud";
            String url0 = "http://124.222.219.151:8001/word_cloud";
            Map map = restTemplate.postForObject(url0, text, Map.class);
            String ret = (String) map.get("result");
            for (ReportCluster reportCluster : reportClusters) {
                reportCluster.setExtra(ret);
                reportClusterMapper.updateByPrimaryKey(reportCluster);
            }
            return ret;
        } else {
            return reportClusters.get(0).getExtra();
        }
    }
}

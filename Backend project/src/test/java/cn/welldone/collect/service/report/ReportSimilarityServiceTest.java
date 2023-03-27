package cn.welldone.collect.service.report;

import cn.welldone.collect.mapperservice.report.ReportMapper;
import cn.welldone.collect.vo.report.ReportSimilarityVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.xm.similarity.text.CosineSimilarity;
import org.xm.similarity.text.TextSimilarity;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportSimilarityServiceTest {

    @Resource
    ReportSimilarityService reportSimilarityService;
    @Resource
    ReportMapper reportMapper;

    //测试文本相似度
    @Test
    public void getSimilarityScore() throws Exception {
        String text1 = "对于俄罗斯来说，最大的战果莫过于夺取乌克兰首都基辅，也就是现任总统泽连斯基和他政府的所在地。目前夺取基辅的战斗已经打响。";
        String text2 = "迄今为止，俄罗斯的入侵似乎没有完全按计划成功执行——英国国防部情报部门表示，在乌克兰军队激烈抵抗下，俄罗斯军队已经损失数以百计的士兵。尽管如此，俄军在继续推进。";
        TextSimilarity cosSimilarity = new CosineSimilarity();
        double score1 = cosSimilarity.getSimilarity(text1, text2);
        System.out.println("cos相似度分值：" + score1);

    }

    @Test
    public void calculateReportSimilarityTest() {
        reportSimilarityService.calculateReportSimilarity(1, 8,-1);
    }

    static ReportSimilarityVO[] similarReports = {
            new ReportSimilarityVO(null, 1, 2, 0.5, 0.5, 0.5, 0.5, "", 0.5, 0.5, 0.5, 0.5),
            new ReportSimilarityVO(null, 1, 3, 0.5, 0.5, 0.5, 0.5, "", 0.5, 0.5, 0.5, 0.6),
            new ReportSimilarityVO(3,4,2,0.666667,0.857143,1.0,1.0,"picture",1.0,1.0,1.0,0.9023810500000001),
            new ReportSimilarityVO(4,4,3,0.666667,0.857143,1.0,1.0,"picture",1.0,1.0,1.0,0.9023810500000001),
            new ReportSimilarityVO(5,1,2,0.666667,0.857143,1.0,1.0,"picture",1.0,1.0,1.0,0.9023810500000001),
            new ReportSimilarityVO(8,12,11,0.0,0.0,0.0,1.0,"picture",0.333333,1.0,1.0,0.11666665000000001)

    };


    @Test
    public void testGetSimilarReports() {
        ReportSimilarityVO[] result = reportSimilarityService.getSimilarReports(1, 1, 10, 4).getList().toArray(new ReportSimilarityVO[0]);
        //Assert.assertArrayEquals(new ReportSimilarityVO[]{similarReports[2],similarReports[3]}, result);
    }
    @Test
    public void  testGetSimilarReport(){
        ReportSimilarityVO result= reportSimilarityService.getSimilarReport(4,1,2);
        //Assert.assertEquals(similarReports[4], result);
    }
}

package cn.welldone.collect.service.report;

import cn.welldone.collect.mapperservice.report.ReportMapper;
import cn.welldone.collect.util.Constant;
import cn.welldone.collect.vo.ResultVO;
import cn.welldone.collect.vo.report.ReportVO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;


import javax.annotation.Resource;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportServiceTest {
    @Resource
    ReportService reportService;
    @Resource
    ReportMapper reportMapper;


    static ReportVO[] reports = {
//            new ReportVO(null, 1, 1, null, null, "1", "1"),
//            new ReportVO(null, 2, 2, null, null, "2", "2"),
//            new ReportVO(null, 3, 3, null, null, "3", "3"),
//            new ReportVO(null, 1, 4, null, null, "4", "4"),
/*            new ReportVO(null,1,1,"null",null,"1","1","null","null","null"),
            new ReportVO(null,2,2,"null",null,"2","2","null","null","null"),
            new ReportVO(null,3,3,"null",null,"3","3","null","null","null"),
            new ReportVO(null,1,4,"null",null,"4","4","null","null","null")*/
            new ReportVO(8,1,8,"2",new Date(),"t","i","s","Android设备","collect-191250.oss-cn-nanjing.aliyuncs.com/upload/2022-05-21/fa3e8124037f4a00934e3ec21384d133.jpg","不正常退出","一般","无规律复现",null)    };

   // @Before
    public void addReports() {
        for (ReportVO p : reports) {
            ResultVO<ReportVO> result = reportService.saveReport(p);
            Assert.assertEquals(Constant.REQUEST_SUCCESS, result.getCode());
        }
    }

  /*  @After
    public void cleanup() {
        reportMapper.clearTable();
    }
*/
    @Test
    public void testGetReport() {
        for (ReportVO p : reports) {
            ReportVO vo = reportService.getReport(p.getId()).getData();
            Assert.assertNotNull(vo);
            //Assert.assertEquals(p, vo);
        }
    }

    @Test
    public void testGetReports() {
        ReportVO[] result = reportService.getReports(1, 1, 10, "by-report-time").getList().toArray(new ReportVO[0]);
        //Assert.assertArrayEquals(new ReportVO[]{reports[3], reports[0]}, result);
    }
//    @Test

//    public void saveReportsTest() {
//        reportService.saveReport(new ReportVO(null,1,2,"lrx",null,"title","intro","steps","device","picture","bugForm","severity","recurrence"));
//    }
    @Test
    public void saveReportTest(){
        reportService.saveReport(new ReportVO(8,1,8,"2",new Date(),"t","i","s","Android设备","collect-191250.oss-cn-nanjing.aliyuncs.com/upload/2022-05-21/fa3e8124037f4a00934e3ec21384d133.jpg","不正常退出","一般","无规律复现",null));
        reportService.saveReport(new ReportVO(16,7,1,"2",new Date(),"title","intro","step","Android设备","collect-191250.oss-cn-nanjing.aliyuncs.com/upload/2022-05-21/fa3e8124037f4a00934e3ec21384d133.jpg","不正常退出","严重","无规律复现",null));
    }
    @Test
    public void testGetOldReport(){
        ReportVO reportVO= reportService.getOldReport(1,2).getData();
        System.out.println(reportVO);
    }

//
//    @Test
//    public void testPostURL(){
//        List<String> reportDescription= Lists.newArrayList("1打开企鹅众测，登录并进入发现页面 2点击积分兑换 3点击兑换70元qq红包 4个人信息手机号填写111 5点击保存 结果：没有提示手机号格式错误，安全有很大问题",
//                "进入兴趣部落 在兴趣部落的帖子内无法进行回复操作。",
//                "1.点击我的 2.点击右上角设置 3.点击手机号绑定 让客户误以为没有绑定手机号，手机号已绑定应该显示手机号，再设置修改绑定手机号，",
//                "点击优秀Bug展示 没有显示“加载中”之类提示语以表明会出现更多内容，或者显示“已经到底”之类提示语以表明会不会出现更多内容，使得用户出现误解，不能及时判断是否已经加载完毕",
//                "点击分享按钮 使用不同账号登录，任务详情界面分享按钮图标不一致。",
//                "点击任务分享 选择QQ分享 点击返回企鹅众测 分享渠道选择浮层没有收起",
//                "领取任务“完善个人信息，即拿20积分” 完善个人信息 最后领取积分时未提示领取失败，但没领到20积分。");
//        String url="http://124.222.219.151:8001/train_predict";
//        //String url="http://124.222.219.151:8081/api/comment/score/1";
//       // List ret = restTemplate.postForObject(url, reportDescription, List.class);连不上
//        Map map = restTemplate.postForObject(url,reportDescription, Map.class);
//        List ret= (List) map.get("result");
//        //  Double restTemplateForObject = restTemplate.getForObject(url,  Double.class);
//        System.out.println(ret);
//        /* print(type({'result':[{"text": "进入兴趣部落 在兴趣部落的帖子内无法进行回复操作。","cluster": "0"}]}))
//          返回值是<class 'dict'>，{key:value,key:value,key:value}
//          返回值是list[{"text": "信息手机号填写111 5点击保存 结果：没有提示手机号格式错误，安全有很大问题","cluster": "0"}]
//          <class 'list'>*/
//    }
//
//    @Test
//    public void testGetCluster(){
//        //参数是报告内容和图片路径拼接的列表
//        List<String> reportDescription= Lists.newArrayList("点击抽奖按钮三秒 点击停止抽奖 预期结果：转盘成功停止转动，抽奖成功 实际结果： 抽奖失败",
//                "点击发现按钮进入发现页面 点击积分抽奖按钮 点击开始抽奖按钮 点击停止抽奖按钮 无法出现抽奖结果，出现错误：很抱歉，抽奖失败，请检查网络[java.lang.lllegalStateException:Expected BEGIN_OBJECT but was STRING at line 1 column 2]",
//                "1.点击我的按钮 2.点击右上角设置图标 3.点击个人信息；4.点击出生日期选择2017.10.21 预期输出：步骤4后系统提示出生日期不正确，不合常理； 实际输出：能够正常的保存完善个人信息；",
//                "点击任务广场按钮 点击1017-查询运动领域问法收集七选项 点击领取任务按钮 点击开始做任务 未出现任务信息",
//                "进入任务 点击1017-查询运动领域问法收集 点击领取任务 点击开始做任务 直接提示没有更多题目。不管是1017-查询运动领域问法收集二，1017-查询运动领域问法收集六，还是其他几个相似题目，都没有可做题目，。此BUG严重等级一般，这几个任务复现程度是必现。影响用户体验。",
//                "进入发现页面 点击寻找小伙伴  需要点击寻找小伙伴才能找到兴趣部落按钮",
//                "进入兴趣部落 点击周贡献榜 信息未能正常显示","http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508578034294/Screenshot_2017-10-21-15-32-54.png",
//                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508578078073/%E6%8A%BD%E5%A5%96.png",
//                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508582741034/%E6%88%AA%E5%B1%8F_20171021_184147.jpg",
//                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508577976200/%E4%BB%BB%E5%8A%A1.png",
//                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508582820700/QQ%E5%9B%BE%E7%89%8720171021172949.png",
//                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508580087898/%E5%9B%BE%E7%89%876.png",
//                "http://collect-191250.oss-cn-nanjing.aliyuncs.com/upload/2022-05-16/b23796dd764c44a78180db0624ff421a.jpg");
//        String url="http://124.222.219.151:8001/do_cluster";
//        String url1="http://0.0.0.0:8001/do_cluster";
//
//        Map map = restTemplate.postForObject(url,reportDescription, Map.class);
//        //返回值是每个报告对应的簇号 [1, 1, 0, 2, 2, 0, 0]
//        List ret= (List) map.get("result");
//        System.out.println(ret);
//
//    }


}
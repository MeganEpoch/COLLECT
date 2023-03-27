package cn.welldone.collect.service.report;

import cn.welldone.collect.mapperservice.report.ReportClusterMapper;
import cn.welldone.collect.po.report.ReportCluster;
import cn.welldone.collect.vo.report.ClusterTreeNodeVO;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportClusterServiceTest {

    @Resource
    ReportClusterService reportClusterService;
    @Autowired
    private RestTemplate restTemplate;


    @Test
    public void testPostURL(){
        List<String> reportDescription= Lists.newArrayList("1打开企鹅众测，登录并进入发现页面 2点击积分兑换 3点击兑换70元qq红包 4个人信息手机号填写111 5点击保存 结果：没有提示手机号格式错误，安全有很大问题",
                "进入兴趣部落 在兴趣部落的帖子内无法进行回复操作。",
                "1.点击我的 2.点击右上角设置 3.点击手机号绑定 让客户误以为没有绑定手机号，手机号已绑定应该显示手机号，再设置修改绑定手机号，",
                "点击优秀Bug展示 没有显示“加载中”之类提示语以表明会出现更多内容，或者显示“已经到底”之类提示语以表明会不会出现更多内容，使得用户出现误解，不能及时判断是否已经加载完毕",
                "点击分享按钮 使用不同账号登录，任务详情界面分享按钮图标不一致。",
                "点击任务分享 选择QQ分享 点击返回企鹅众测 分享渠道选择浮层没有收起",
                "领取任务“完善个人信息，即拿20积分” 完善个人信息 最后领取积分时未提示领取失败，但没领到20积分。");
        String url="http://124.222.219.151:8001/train_predict";
        String url1="http://0.0.0.0:8001/train_predict";

        //String url="http://124.222.219.151:8081/api/comment/score/1";
        // List ret = restTemplate.postForObject(url, reportDescription, List.class);连不上
        Map map = restTemplate.postForObject(url,reportDescription, Map.class);
        List ret= (List) map.get("result");
        //  Double restTemplateForObject = restTemplate.getForObject(url,  Double.class);
        System.out.println(ret);
        /* print(type({'result':[{"text": "进入兴趣部落 在兴趣部落的帖子内无法进行回复操作。","cluster": "0"}]}))
          返回值是<class 'dict'>，{key:value,key:value,key:value}
          返回值是list[{"text": "信息手机号填写111 5点击保存 结果：没有提示手机号格式错误，安全有很大问题","cluster": "0"}]
          <class 'list'>*/
    }

    @Test
    public void testGetReportCluster(){
        List<ReportCluster> ret=reportClusterService.getReportCluster(7);
        for(ReportCluster reportCluster:ret){
            System.out.println("reportID:"+reportCluster.getReportId()+" cluster:"+reportCluster.getReportCluster());

        }
        System.out.println(ret);
    }

    @Test
    public void testGetMasterReport(){
        int ret=reportClusterService.getMasterReport(7,1);
        System.out.println(ret);
    }

    @Test
   public void testGetClustersTreeView(){
        List<ClusterTreeNodeVO> ret=reportClusterService.getClustersTreeView(7);
        System.out.println(ret);
    }

    @Test
    public void testPostPicURL(){
        List<String> picUrl= Lists.newArrayList("http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508578034294/Screenshot_2017-10-21-15-32-54.png",
                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508578078073/%E6%8A%BD%E5%A5%96.png",
                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508582741034/%E6%88%AA%E5%B1%8F_20171021_184147.jpg",
                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508577976200/%E4%BB%BB%E5%8A%A1.png",
                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508582820700/QQ%E5%9B%BE%E7%89%8720171021172949.png",
                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508580087898/%E5%9B%BE%E7%89%876.png");

        HashMap<String, Object> paramMap = new HashMap<>();



        paramMap.put("image_path",picUrl);
        paramMap.put("n_clusters",2);

        String url="http://124.222.219.151:8001/train_picture";
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<HashMap<String, Object>> request = new HttpEntity<>(paramMap);
//        Map map = restTemplate.postForObject(url,request, Map.class);
//
//        List ret= (List) map.get("result");
//        System.out.println(ret);

    }

//    @Test
//    public void testGetCluster(){
//        //参数是报告内容和图片路径拼接的列表
//        List<String> reportDescription= Lists.newArrayList("点击抽奖按钮三秒 点击停止抽奖 预期结果：转盘成功停止转动，抽奖成功 实际结果： 抽奖失败",
//                "点击发现按钮进入发现页面 点击积分抽奖按钮 点击开始抽奖按钮 点击停止抽奖按钮 无法出现抽奖结果，出现错误：很抱歉，抽奖失败，请检查网络[java.lang.lllegalStateException:Expected BEGIN_OBJECT but was STRING at line 1 column 2]",
//                "1.点击我的按钮 2.点击右上角设置图标 3.点击个人信息；4.点击出生日期选择2017.10.21 预期输出：步骤4后系统提示出生日期不正确，不合常理； 实际输出：能够正常的保存完善个人信息；",
//                "点击任务广场按钮 点击1017询运动领域问法收集七选项 点击领取任务按钮 点击开始做任务 未出现任务信息",
//                "进入任务 点击1017-查询运动领域问法收集 点击领取任务 点击开始做任务 直接提示没有更多题目。不管是1017-查询运动领域问法收集二，1017-查询运动领域问法收集六，还是其他几个相似题目，都没有可做题目，。此BUG严重等级一般，这几个任务复现程度是必现。影响用户体验。",
//                "进入发现页面 点击寻找小伙伴  需要点击寻找小伙伴才能找到兴趣部落按钮",
//                "进入兴趣部落 点击周贡献榜 信息未能正常显示",
//                "进入兴趣部落页面 点击周贡献榜 出现网页无法打开。",
//                "进入企鹅众测的兴趣部落 点击精华的标签，周贡献版等 显示无法打开页面",
//                "点击兴趣部落 无法打开网页，与预期结果不符",
//                "点击部落头像 显示无法打开网页",
//                "进入发现 点击兴趣部落 点击头像 显示无法打开网页",
//                "点击继续测试 提示：请按照任务详情说明，认真做任务",
//                "点击继续测试按钮 点击之后弹出提示，没有任何其他功能。与预期结果不符。",
//                "点击领取任务 该任务的被领取次数没有改变。如图1,该任务有8422人领取，测试过程中领取完以后领取人数依然是8422并没有增长，如图2。",
//                "进入兴趣部落 随便点开一个人发的帖子 拉到最下面点击去兴趣部落app 查看全部评论 必定出现找不到网页",
//                "点击去兴趣部落APP，查看更多内容 返回找不到网页",
//                "进入用户发表的言论详情页（带图片） 点击图片 跳转到一个无法打开的网页",
//                "进入兴趣部落 随意打开一个带图片的帖子 点击帖子就会出现错误页面。",
//                "多次频繁点赞/取消点赞 概率性出现点赞失败和取消点赞失败。",
//                "快速连续地点击优秀bug展示右边的点赞按钮 会出现 点赞失败，已点赞过。",
//                "点击任务广场 点击右上+ 输入反馈标题aa，反馈详情：bb 点击提交反馈 提示反馈标题要简要概括该反馈的内容 ",
//                "点击提交反馈 用户无法提交反馈意见并且不显示用户输入的内容是否正常",
//                "进入优秀反馈展示 点击图片 提示点击图片可以旋转图片，但没有反应",
//                "点击照片 没有出现照片旋转或者关于照片旋转的提示",
//                "进入发现界面 点击优秀bug反馈 预期结果：成功进入优秀bug反馈界面，实际结果：发现界面无优秀bug反馈按钮，只有优秀bug展示",
//                "点击积分抽奖 提示网络错误，导致用户无法正常抽奖。",
//                "1.点击进入积分抽奖页  2.查看中奖名单 3.积分抽奖页-中奖名单没有去重，同一条记录多次展示",
//                "查看规格参数 只能显示部分硬件信息ROM类型unknownCPU内存信息为空",
//                "点击我的 实际结果为已完成任务有多个，不是0，与预期结果不符",
//                "点击领取任务 选择未完成任务 在我的头像下显示已完成任务",
//                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508578034294/Screenshot_2017-10-21-15-32-54.png",
//                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508578078073/%E6%8A%BD%E5%A5%96.png",
//                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508582741034/%E6%88%AA%E5%B1%8F_20171021_184147.jpg",
//                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508577976200/%E4%BB%BB%E5%8A%A1.png",
//                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508582820700/QQ%E5%9B%BE%E7%89%8720171021172949.png",
//                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508580087898/%E5%9B%BE%E7%89%876.png",
//                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508579439093/A251C109DD3DC4F95AA802B9143DF7E0.jpg",
//                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508580633017/S71021-180938.jpg",
//                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508583032964/Screenshot_20171021-184937.jpg",
//                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508577993503/3.png",
//                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508578273003/bug4.png",
//                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508579601628/%E5%A4%B4%E5%83%8F.jpg",
//                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508580591068/Screenshot_2017-10-21-18-08-47-769_com.tencent.te.png",
//                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508581727848/Screenshot_2017-10-21-18-28-21-026_com.tencent.te.png",
//                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508583375292/21.2.jpg",
//                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508580216565/zhaobudao.png",
//                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508581549575/7.2.jpg",
//                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508580948268/%E6%88%AA%E5%B1%8F2017102101.png",
//                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508583029228/19.jpg",
//                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508580079553/Screenshot_2017-10-21-17-57-46-0461522940.png",
//                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508583100689/12.jpg",
//                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508580976995/5.png",
//                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508582331676/11.jpg",
//                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508580117500/321.jpg",
//                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508583040249/QQ%E6%88%AA%E5%9B%BE20171021185028.png",
//                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508578140076/Screenshot_2017-10-21-15-43-50.png",
//                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508578150812/3.jpg",
//                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508578244793/%E6%88%AA%E5%B1%8F2017102101.png",
//                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508578322756/5.1.png",
//                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508578426661/Screenshot_2017-10-21-17-32-54-988_com.tencent.te.png",
//                "http://mooctest-site.oss-cn-shanghai.aliyuncs.com/app/1508578640855/530CBAB891FB4840C2071D6D5ADA231F.jpg");
//
//        String url="http://124.222.219.151:8001/do_cluster";
//        String url1="http://0.0.0.0:8001/do_cluster";
//
//        Map map = restTemplate.postForObject(url1,reportDescription, Map.class);
//        //返回值是每个报告对应的簇号 [1, 1, 0, 2, 2, 0, 0]
//        List ret= (List) map.get("result");
//        //int cluster = (int) (((List) map.get("result")).get(0).toString().charAt(0))-48;   //ascii码
//
//        System.out.println(ret);
//
//    }

    @Test
    public void testGetWordCloud(){
        String ret=reportClusterService.getWordCloud(7,3);
        System.out.println(ret);
    }

    @Test
    public void testGetReportExtra(){
        List<ReportCluster> ret=reportClusterService.getReportExtra(344);
        System.out.println(ret);
    }

}

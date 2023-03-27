package cn.welldone.collect.service.recommend;

import cn.welldone.collect.mapperservice.task.TaskMapper;
import cn.welldone.collect.mapperservice.task.TodoTaskMapper;
import cn.welldone.collect.mapperservice.user.UserMapper;
import cn.welldone.collect.vo.task.TaskVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecommendServiceTest {
    @Resource
            @Qualifier("ItemSimilarityServiceImpl")
    RecommendService itemSimilarityService;

    @Resource
    @Qualifier("userAttributeRecommendServiceImpl")
    private RecommendService userAttributeRecommendService;

    @Resource
    @Qualifier("taskBasedServiceImpl")
    private RecommendService taskBasedServiceImpl;


    @Resource
    TaskMapper taskMapper;

    @Resource
    private UserMapper userMapper;
    @Resource
    private TodoTaskMapper todoTaskMapper;

    @Test
    public void itemSimilarityRecommendTest(){
        System.out.println(itemSimilarityService.recommend(9));
    }

    @Test
    public void userAttributeRecommendTest(){
        //System.out.println(userAttributeRecommendService.recommend(2));
//        for(TaskVO taskVO:userAttributeRecommendService.recommend(10)){
//            System.out.println(taskVO.getId());
//        }
    }

    @Test
    public void taskBasedRecommendTest(){
//        System.out.println(taskBasedServiceImpl.recommend(7));
        for(TaskVO taskVO:taskBasedServiceImpl.recommend(7)){
            System.out.println(taskVO.getId());
        }
    }

}

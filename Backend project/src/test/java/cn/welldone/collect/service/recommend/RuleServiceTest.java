package cn.welldone.collect.service.recommend;

import cn.welldone.collect.util.Constant;
import cn.welldone.collect.vo.recommend.RecommendRuleVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RuleServiceTest {
    @Resource
    RuleService ruleService;

    @Test
    @Transactional
    public void testChooseRule(){
        Assert.assertEquals(ruleService.chooseRule(new RecommendRuleVO(1,"xietong",2,1,null)).getCode(), Constant.REQUEST_SUCCESS);
    }

    @Test
    public void testGetRecommendRules(){
        System.out.println(ruleService.getRecommendRules());
    }

    @Test
    @Transactional
    public void testAddRule(){
        Assert.assertEquals(ruleService.addRule(new RecommendRuleVO(null,"xietong",0,0,null)).getCode(),Constant.REQUEST_SUCCESS);
    }

    @Test
    public void testGetCurrentRule(){
        System.out.println(ruleService.getCurrentRule());
    }
}

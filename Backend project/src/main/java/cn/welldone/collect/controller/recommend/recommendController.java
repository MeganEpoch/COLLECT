package cn.welldone.collect.controller.recommend;


import cn.welldone.collect.service.recommend.RecommendService;
import cn.welldone.collect.service.recommend.RuleService;
import cn.welldone.collect.vo.ResultVO;
import cn.welldone.collect.vo.recommend.RecommendRuleVO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/recommend")
public class recommendController {


    @Resource
    RuleService  ruleService;

    //指定生效规则 把规则转变状态存在表里 get任务方法中在表里查是哪种规则，再调用相关方法
    @PostMapping("/choose")
    public ResultVO<RecommendRuleVO> chooseValidRule(@RequestBody RecommendRuleVO recommendRuleVO){
return ruleService.chooseRule(recommendRuleVO);
    }

    @GetMapping("/rules")
public List<RecommendRuleVO> getRules(){
        return ruleService.getRecommendRules();
    }

    @PostMapping("/add")
    public ResultVO<RecommendRuleVO> addRule(@RequestBody RecommendRuleVO recommendRuleVO){
        return ruleService.addRule(recommendRuleVO);
    }

    @GetMapping("/current_rule")
    public RecommendRuleVO getCurrentRule(){
        return ruleService.getCurrentRule();
    }

}

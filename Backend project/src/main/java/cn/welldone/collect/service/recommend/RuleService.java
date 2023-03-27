package cn.welldone.collect.service.recommend;

import cn.welldone.collect.vo.ResultVO;
import cn.welldone.collect.vo.recommend.RecommendRuleVO;

import java.util.List;

public interface RuleService {
    ResultVO<RecommendRuleVO> chooseRule(RecommendRuleVO recommendRuleVO);
    List<RecommendRuleVO> getRecommendRules();
    ResultVO<RecommendRuleVO> addRule(RecommendRuleVO recommendRuleVO);
    RecommendRuleVO getCurrentRule();
}

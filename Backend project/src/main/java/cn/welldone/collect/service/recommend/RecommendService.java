package cn.welldone.collect.service.recommend;

import cn.welldone.collect.po.task.Task;
import cn.welldone.collect.vo.ResultVO;
import cn.welldone.collect.vo.recommend.RecommendRuleVO;
import cn.welldone.collect.vo.task.TaskVO;

import java.util.ArrayList;
import java.util.List;

public interface RecommendService {
//    ResultVO<RecommendRuleVO> chooseRule(RecommendRuleVO recommendRuleVO);
    List<TaskVO> recommend(int uid);
//    List<RecommendRuleVO> getRecommendRules();
//    ResultVO<RecommendRuleVO> addRule(RecommendRuleVO recommendRuleVO);

}

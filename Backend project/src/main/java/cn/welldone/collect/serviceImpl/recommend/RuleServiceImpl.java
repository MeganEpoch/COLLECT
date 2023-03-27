package cn.welldone.collect.serviceImpl.recommend;

import cn.welldone.collect.mapperservice.recommend.RecommendRuleMapper;
import cn.welldone.collect.po.rule.RecommendRule;
import cn.welldone.collect.service.recommend.RuleService;
import cn.welldone.collect.util.Constant;
import cn.welldone.collect.vo.ResultVO;
import cn.welldone.collect.vo.recommend.RecommendRuleVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RuleServiceImpl implements RuleService {

    @Resource
    RecommendRuleMapper recommendRuleMapper;

    @Override
    public ResultVO<RecommendRuleVO> chooseRule(RecommendRuleVO recommendRuleVO) {
        if(recommendRuleMapper.selectById(recommendRuleVO.getId()).getStatus()==0){

        }
        else{
            recommendRuleMapper.update();//把之前使用的规则status设为1

            recommendRuleMapper.updateById(recommendRuleVO.getId());

        }
        return new ResultVO<>(Constant.REQUEST_SUCCESS, "规则创建成功。",recommendRuleVO );

    }

    @Override
    public List<RecommendRuleVO> getRecommendRules() {
        List<RecommendRule> recommendRules=recommendRuleMapper.selectAll();
        List<RecommendRuleVO> ret=new ArrayList<>();
        for(RecommendRule recommendRule:recommendRules){
            ret.add(new RecommendRuleVO(recommendRule));
        }
        return ret;
    }

    @Override
    public ResultVO<RecommendRuleVO> addRule(RecommendRuleVO recommendRuleVO) {
        RecommendRule recommendRule=new RecommendRule(recommendRuleVO);
        recommendRule.setChangeTime(new Date());
recommendRule.setStatus(0);
        if(recommendRuleMapper.insert(recommendRule)>0){
            return new ResultVO<>(Constant.REQUEST_SUCCESS, "规则创建成功。",new RecommendRuleVO(recommendRule) );
        }
        return new ResultVO<>(Constant.REQUEST_FAIL, "服务器错误");

    }

    @Override
    public RecommendRuleVO getCurrentRule() {
        return new RecommendRuleVO(recommendRuleMapper.queryUsingRule());
    }
}

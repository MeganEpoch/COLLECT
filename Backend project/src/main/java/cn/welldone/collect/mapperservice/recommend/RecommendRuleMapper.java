package cn.welldone.collect.mapperservice.recommend;

import cn.welldone.collect.po.rule.RecommendRule;

import java.util.List;

public interface RecommendRuleMapper {
    int insert(RecommendRule recommendRule);

    RecommendRule queryMostRecentRule();

    List<RecommendRule> selectAll();

    RecommendRule selectById(int id);

int update();

    int updateById(int id);

    RecommendRule queryUsingRule();

}

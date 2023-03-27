package cn.welldone.collect.vo.recommend;


import cn.welldone.collect.po.rule.RecommendRule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendRuleVO {
    private Integer id;
    private String content;
    private int status;//0为无用，1为有用,2为正在使用
    private int type;//1为协同
    private Date changeTime;

    public RecommendRuleVO(@NonNull RecommendRule recommendRule){
        this.id=recommendRule.getId();
        this.content=recommendRule.getContent();
        this.status=recommendRule.getStatus();
        this.type=recommendRule.getType();
        this.changeTime=recommendRule.getChangeTime();

    }
}

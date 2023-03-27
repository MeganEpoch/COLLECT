package cn.welldone.collect.po.rule;

import cn.welldone.collect.vo.recommend.RecommendRuleVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

//记录状态的变更
public class RecommendRule {
    private Integer id;
    private String content;
    private int status;//0为无用，1为有用
    private int type;//1为协同
    private Date changeTime;


    public RecommendRule(@NonNull RecommendRuleVO recommendRuleVO){
        this.id=recommendRuleVO.getId();
        this.content=recommendRuleVO.getContent();
        this.status=recommendRuleVO.getStatus();
        this.type=recommendRuleVO.getType();
        this.changeTime=recommendRuleVO.getChangeTime();

    }

}


package cn.welldone.collect.vo.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClusterTreeNodeVO {
    private Integer id;
    private Integer parentId;
    private String label;
}

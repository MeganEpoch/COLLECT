package cn.welldone.collect.po.report;


import cn.welldone.collect.vo.report.CommentVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Integer id;
    private Integer reportId;
    private Integer employeeId;
    private Integer score;
    private String content;

    public Comment(CommentVO commentVO){
        this(commentVO.getId(),commentVO.getReportId(),commentVO.getEmployeeId(),commentVO.getScore(),commentVO.getContent());
    }
}

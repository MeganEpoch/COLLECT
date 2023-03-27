package cn.welldone.collect.vo.report;


import cn.welldone.collect.po.report.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO {
    private Integer id;
    private Integer reportId;
    private Integer employeeId;
    private Integer score;
    private String content;

    public CommentVO(Comment comment){
        this(comment.getId(),comment.getReportId(),comment.getEmployeeId(),comment.getScore(),comment.getContent());
    }
}

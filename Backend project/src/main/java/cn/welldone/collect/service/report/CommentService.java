package cn.welldone.collect.service.report;


import cn.welldone.collect.po.report.Comment;
import cn.welldone.collect.vo.ResultVO;
import cn.welldone.collect.vo.report.CommentVO;

import java.util.List;

public interface CommentService {
    ResultVO<CommentVO> createComment(CommentVO commentVO);
    List<CommentVO> getComments(int reportId);
    Double getScore(Integer reportId);
}

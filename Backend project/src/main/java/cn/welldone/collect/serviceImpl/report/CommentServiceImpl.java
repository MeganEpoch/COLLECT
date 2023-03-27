package cn.welldone.collect.serviceImpl.report;


import cn.welldone.collect.mapperservice.report.CommentMapper;
import cn.welldone.collect.mapperservice.report.ReportMapper;
import cn.welldone.collect.po.report.Comment;
import cn.welldone.collect.service.report.CommentService;
import cn.welldone.collect.util.Constant;
import cn.welldone.collect.vo.ResultVO;
import cn.welldone.collect.vo.report.CommentVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    CommentMapper commentMapper;

    @Resource
    ReportMapper reportMapper;
    @Override
    public ResultVO<CommentVO> createComment(CommentVO commentVO) {
        if(reportMapper.selectByPrimaryKey(commentVO.getReportId()).getEmployeeId()==commentVO.getEmployeeId()){
            return new ResultVO<>(Constant.REQUEST_FAIL, " 请勿评论自己的报告！");
        }
        for(Comment comment:commentMapper.selectByEmployeeId(commentVO.getEmployeeId())){
            if(commentVO.getReportId()==comment.getReportId()){
                return new ResultVO<>(Constant.REQUEST_FAIL, " 请勿重复提交评论！");
            }
        }
            Comment comment=new Comment(commentVO);

        if(commentMapper.insert(comment)>0){
            double score=0;
            //更新报告综合评分
            for(Comment comment1:commentMapper.selectByReportId(commentVO.getReportId())){
                score+=comment1.getScore()*2;
            }
            score=score/(double)commentMapper.selectByReportId(commentVO.getReportId()).size();
          reportMapper.updateScore(commentVO.getReportId(),score);
            return new ResultVO<CommentVO>(Constant.REQUEST_SUCCESS, "评论提交成功。",new CommentVO(comment) );

        }

        return new ResultVO<>(Constant.REQUEST_FAIL, "服务器错误");
    }

    @Override
    public List<CommentVO> getComments(int reportId) {
        List<CommentVO> ret=new ArrayList<>();
        List<Comment> comments=commentMapper.selectByReportId(reportId);
        for(Comment comment:comments){
            ret.add(new CommentVO(comment));
        }
        return ret;
    }

    @Override
    public Double getScore(Integer reportId) {

        return reportMapper.selectByPrimaryKey(reportId).getScore();
    }
}

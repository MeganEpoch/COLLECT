package cn.welldone.collect.controller.report;

import cn.welldone.collect.po.report.Comment;
import cn.welldone.collect.service.report.CommentService;
import cn.welldone.collect.vo.ResultVO;
import cn.welldone.collect.vo.report.CommentVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Resource
    CommentService commentService;

    //增加评价（含评分）
    @PostMapping("/create")
    public ResultVO<CommentVO> createComment(@RequestBody CommentVO commentVO){
        return commentService.createComment(commentVO);
    }

    //发包方查看一个报告的所有评价
    @GetMapping("/{reportId}")
    public List<CommentVO> getComments(@PathVariable Integer reportId){
        return commentService.getComments(reportId);
    }

    //查看一个报告的综合评分
    @GetMapping("score/{reportId}")
public Double getScore(@PathVariable Integer reportId){
        return commentService.getScore(reportId);
    }
}

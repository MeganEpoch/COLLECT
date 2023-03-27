package cn.welldone.collect.service.report;


import cn.welldone.collect.po.report.Comment;
import cn.welldone.collect.vo.report.CommentVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTest {
    @Resource
    CommentService commentService;

    @Test
    @Transactional
    public void createCommentTest(){
        CommentVO commentVO=new CommentVO(null,1,4,3,"good");
        commentService.createComment(commentVO);
    }

    @Test
    public void getCommentsTest(){
        List<CommentVO> commentList=commentService.getComments(1);
        for(CommentVO commentVO:commentList){
            System.out.println(commentVO);

        } }

    @Test
    public void getScoreTest(){
        System.out.println(commentService.getScore(1));
 //       Assert.assertEquals((commentService.getScore(1)),(Double)7.5);
    }
}

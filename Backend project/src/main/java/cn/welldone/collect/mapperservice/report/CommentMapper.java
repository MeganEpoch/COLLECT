package cn.welldone.collect.mapperservice.report;

import cn.welldone.collect.po.report.Comment;

import java.util.List;

public interface CommentMapper {
    int insert(Comment comment);
    List<Comment> selectByEmployeeId(int eid);
    List<Comment> selectByReportId(int reportId);
}

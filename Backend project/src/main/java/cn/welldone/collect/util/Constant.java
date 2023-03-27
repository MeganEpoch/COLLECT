package cn.welldone.collect.util;

/**
 * 定义一些常量
 */
public class Constant {

    // 一个展示任务的页面内包含的课程数量
    public static final Integer TASK_PAGE_SIZE = 4;

    // 请求操作成功返回码
    public static final Integer REQUEST_SUCCESS = 1;
    // 请求操作失败返回码
    public static final Integer REQUEST_FAIL = 0;
    // 成功接受任务的状态码
    public static final Integer TODO_STATUS_SUCCESS = 1;
    //任务进行中的状态码
    public static final Integer TODO_STATUS_DOING = 3;
    // 已完成任务的状态码
    public static final Integer TODO_STATUS_FINISHED = 2;

    public static final Integer REPORT_PAGE_SIZE = 10;
//正在招募的任务
    public static final Integer TASK_STATUS_VALID=4;
    //过期的任务
    public static final Integer TASK_STATUS_INVALID=5;
//全部报告提交完的任务
    public static final Integer TASK_STATUS_FINISH=2;

}

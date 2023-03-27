//package cn.welldone.collect.util;
//
//import cn.welldone.collect.po.task.Task;
//
//import java.awt.print.Paper;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class test {
//    public static void main(String[] args) {
//        ItemSimilarity it=new ItemSimilarity();
//        //测试小Demo
//        System.out.println("请输入用户希望推荐的用户id : ");
//        Scanner input = new Scanner(System.in);
//        int uid = input.nextInt();
//        ArrayList<Task> v = it.recommend(uid);
//
//        System.out.println("正在生成针对用户id为"+uid+"的推荐...");
//        for(int i = 0; i < v.size(); i++)
//            System.out.println("第"+(i+1)+"个推荐: 题目:"+v.get(i).getName()+"");
//    }
//}

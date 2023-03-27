package cn.welldone.collect.util;

public class PageRank {
    /*计算pagerank的函数
     * @param link 页面的链接图
     * @param damp 设置的阻尼系数，佩奇设的是0.85
     * @param linkout 每个页面的链接总数
     * @param init 上次迭代的结果
     *
     * */
    public  static double[] pageRank(Double[][] link, double damp,
                                    double[] init) {

        double[] pr = new double[init.length];//
        double common = (1 - damp) / link.length;
//        while (true)
//        {
//            for (Node n : graph)
//            {
//                double sum = 0.0;
//                for (int nodeId : n.getNeighbors())
//                {
//                    Node nb = getNodeById(nodeId,graph);
//                    sum += nb.getPR() / nb.getDegree();
//                }
//                double newPR = common + d * sum;
//                //如果尚未收敛，赋新值，否则结束迭代
//                if (Math.abs(n.getPR() - newPR) > distance)
//                    n.setPR(newPR);
//                else
//                    return;
//            }
//        }


        for (int i = 0; i < init.length; i++) {
            double sum = 0.0;
            for (int j = 0; j < init.length; j++) {
//pr*权重/
                if (i != j){
                    //结点 j连出的权重和
                    double sumWkj=0.0;
                    for (int k=0;k<init.length;k++){
                        if(k!=j){
                            sumWkj+=link[j][k];
                        }
                    }
sum+=init[j]*link[i][j]/sumWkj
;
                }
            }
// pagerank的计算公式
            pr[i] = common +damp* sum;
        }
        return pr;
    }

}

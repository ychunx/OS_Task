package Utils;

import Main.JCB;
import java.util.ArrayList;
import java.util.Collections;

public class RR {

    //设置时间片T
    public static double T = 0.5;

    //简单轮转法调度算法（RR）
    public static ArrayList<JCB> RR(ArrayList<JCB> JCBArr) {

        //根据到达时间将输入队列排序，同时到达的进程根据序号排序
        //轮换时间算法在排序确定以后都是按照这个顺序进行调度的
        for(int i=0;i<JCBArr.size();i++) {
            for(int k=i+1;k<JCBArr.size();k++) {
                if(JCBArr.get(i).getArriveTime()>JCBArr.get(k).getArriveTime()) {
                    Collections.swap(JCBArr, i, k);
                }
            }
        }

        //记录当前时间
        double nowTime = JCBArr.get(0).getArriveTime();

        int flag = 1;   //标志是否全部作业都已完成
        while(flag == 1){
            for (int i=0;i<JCBArr.size();i++){
                //设置开始运行时间
                if(JCBArr.get(i).getServiceTime() == 0){
                    JCBArr.get(i).setBeginTime(nowTime);
                }

                //判断作业是否已经完成，完成则跳过此次循环，没有则继续更新时间
                if(JCBArr.get(i).getServiceTime() >= JCBArr.get(i).getWorkTime()) {
                    continue;
                } else {
                    //每次轮换更新当前时间
                    nowTime += T;
                    //每次轮换更新一次已服务时间
                    JCBArr.get(i).setServiceTime(JCBArr.get(i).getServiceTime() + T);
                    //每次轮换完后都将作业的完成时间暂时设置成当前时间，如果作业已完成则不需更新，如果还未完成则继续更新
                    JCBArr.get(i).setFinshTime(nowTime);
                }

                //如果最后flag还是为1的话那么说明还有作业没有完成，继续循环
                flag = 0;
                for (int k=0;k<JCBArr.size();k++){
                    if(JCBArr.get(k).getServiceTime() < JCBArr.get(k).getWorkTime()) {
                        flag = 1;
                    }
                }
            }
        }
        return JCBArr;
    }
}

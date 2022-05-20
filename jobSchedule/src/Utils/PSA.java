package Utils;

import Main.JCB;
import java.util.ArrayList;

public class PSA {

    //优先权优先调度算法（PSA），设定优先数越小优先级越高
    public static ArrayList<JCB> PSA(ArrayList<JCB> JCBArr) {

        //根据到达时间获取第一个到达并且优先权最高的进程
        JCB first;
        first = JCBArr.get(0);
        for(int i=0;i<JCBArr.size();i++) {
            if(first.getArriveTime() == JCBArr.get(i).getArriveTime()) {
                if(first.getPriority() > JCBArr.get(i).getPriority()) {
                    first = JCBArr.get(i);
                }
            } else if(first.getArriveTime() > JCBArr.get(i).getArriveTime()) {
                first = JCBArr.get(i);
            }
        }

        //复制原PCB数组
        ArrayList<JCB> tempArr = new ArrayList<JCB>();
        for (int i=0;i<JCBArr.size();i++) {
            tempArr.add(JCBArr.get(i));
        }

        //第一个进入工作队列中
        ArrayList<JCB> workArr = new ArrayList<JCB>();  //存储结果数组
        workArr.add(first);
        workArr.get(0).setBeginTime(first.getArriveTime());
        workArr.get(0).setFinshTime(first.getArriveTime()+first.getWorkTime());

        //删除已经进入工作队列的第一个进程的PCB
        tempArr.remove(first);

        //剩下的进程通过优先权优先调度算法依次进入工作队列
        while(!tempArr.isEmpty()) {
            ArrayList<JCB> temp = new ArrayList<JCB>();
            double lastFinshTime = workArr.get(workArr.size()-1).getFinshTime();

            //筛选出在上一个进程结束前到达的进程放入temp数组
            for(JCB j : tempArr) {
                if(j.getArriveTime() < lastFinshTime) {
                    temp.add(j);
                }
            }

            if(temp.isEmpty()){
                for(JCB j : tempArr) {
                        temp.add(j);
                }
            }

            //筛选出temp数组中优先权最高的进程first2
            JCB first2;
            first2 = temp.get(0);
            for(int i=0;i<temp.size();i++) {
                if(first2.getPriority() > temp.get(i).getPriority())
                    first2 = temp.get(i);
            }

            //将first2对应进程放入工作队列并运行，同时在临时复制的PCB（tempArr)中删除该进程的PCB
            first2.setBeginTime(lastFinshTime);
            first2.setFinshTime(first2.getBeginTime()+first2.getWorkTime());
            workArr.add(first2);
            tempArr.remove(first2);
            temp.clear();
        }
        return workArr;
    }
}

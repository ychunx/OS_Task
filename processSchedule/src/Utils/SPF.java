package Utils;

import Main.PCB;
import java.util.ArrayList;

public class SPF {

    //最短进程优先算法（SPF）
    public static ArrayList<PCB> SPF(ArrayList<PCB> PCBArr) {

        //根据到达时间获取第一个到达并且服务时间较短的进程
        PCB first;
        first = PCBArr.get(0);
        for(int i=0;i<PCBArr.size();i++) {
            if(first.getArriveTime() == PCBArr.get(i).getArriveTime()) {
                if(first.getWorkTime() > PCBArr.get(i).getWorkTime()) {
                    first = PCBArr.get(i);
                }
            } else if(first.getArriveTime() > PCBArr.get(i).getArriveTime()) {
                first = PCBArr.get(i);
            }
        }

        //复制原PCB数组
        ArrayList<PCB> tempArr = new ArrayList<PCB>();
        for (int i=0;i<PCBArr.size();i++) {
            tempArr.add(PCBArr.get(i));
        }

        //第一个进入工作队列中
        ArrayList<PCB> workArr = new ArrayList<PCB>();  //存储结果数组
        workArr.add(first);
        workArr.get(0).setBeginTime(first.getArriveTime());
        workArr.get(0).setFinshTime(first.getArriveTime()+first.getWorkTime());

        //删除已经进入工作队列的第一个进程的PCB
        tempArr.remove(first);

        //剩下的进程通过最短进程优先调度算法依次进入工作队列
        while(!tempArr.isEmpty()) {
            ArrayList<PCB> temp = new ArrayList<PCB>();
            double lastFinshTime = workArr.get(workArr.size()-1).getFinshTime();

            //筛选出在上一个进程结束前到达的进程放入temp数组
            for(PCB p : tempArr) {
                if(p.getArriveTime() < lastFinshTime) {
                    temp.add(p);
                }
            }

            if(temp.isEmpty()){
                for(PCB p : tempArr)
                    temp.add(p);
            }

            //筛选出temp数组中最短的进程first2
            PCB first2;
            first2 = temp.get(0);
            for(int i=0;i<temp.size();i++) {
                if(first2.getWorkTime() > temp.get(i).getWorkTime())
                    first2 = temp.get(i);
            }

            //将first2对应进程放入工作队列并运行，同时在临时复制的PCB（tempArr)中删除该进程的PCB
            first2.setBeginTime(lastFinshTime);
            first2.setFinshTime(first2.getBeginTime()+first2.getWorkTime());
            workArr.add(first2);
            tempArr.remove(first2);
        }
        return workArr;
    }
}
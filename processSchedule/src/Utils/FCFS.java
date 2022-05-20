package Utils;

import Main.PCB;
import java.util.ArrayList;
import java.util.Collections;

public class FCFS {

    //先来先服务算法（FCFS）
    public static ArrayList<PCB> FCFS(ArrayList<PCB> PCBArr) {

        //根据到达时间将输入队列排序，同时到达的进程根据序号排序
        for(int i=0;i<PCBArr.size();i++) {
            for(int j=i+1;j<PCBArr.size();j++) {
                if(PCBArr.get(i).getArriveTime()>PCBArr.get(j).getArriveTime()) {
                    Collections.swap(PCBArr, i, j);
                }
            }
        }

        //复制原PCB数组
        ArrayList<PCB> tempArr = PCBArr;
        //存储结果数组
        ArrayList<PCB> workArr = new ArrayList<PCB>();
        for(PCB p : tempArr) {
            if(workArr.size() == 0){    //执行第一个进程
                p.setBeginTime(p.getArriveTime());
                p.setFinshTime(p.getBeginTime()+p.getWorkTime());
            } else {
                p.setBeginTime(workArr.get(workArr.size()-1).getFinshTime());
                p.setFinshTime(p.getBeginTime()+p.getWorkTime());
            }
            workArr.add(p);
        }

        return workArr;
    }
}
package Utils;

import Main.JCB;
import java.util.ArrayList;
import java.util.Collections;

public class FCFS {

    //先来先服务调度算法（FCFS）
    public static ArrayList<JCB> FCFS(ArrayList<JCB> JCBArr) {

        //根据到达时间将输入队列排序，同时到达的进程根据序号排序
        for(int i=0;i<JCBArr.size();i++) {
            for(int k=i+1;k<JCBArr.size();k++) {
                if(JCBArr.get(i).getArriveTime()>JCBArr.get(k).getArriveTime()) {
                    Collections.swap(JCBArr, i, k);
                }
            }
        }

        //存储结果数组
        ArrayList<JCB> workArr = new ArrayList<JCB>();
        for(JCB j : JCBArr) {
            if(workArr.size() == 0){    //执行第一个进程
                j.setBeginTime(j.getArriveTime());
                j.setFinshTime(j.getBeginTime()+j.getWorkTime());
            } else {
                j.setBeginTime(workArr.get(workArr.size()-1).getFinshTime());
                j.setFinshTime(j.getBeginTime()+j.getWorkTime());
            }
            workArr.add(j);
        }

        return workArr;
    }
}

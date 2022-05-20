package Utils;

import Main.PCB;
import java.util.ArrayList;

public class HRN {

    //响应比高者优先（HRN）算法，响应比 =（等待时间 + 工作时间）/ 工作时间
    public static ArrayList<PCB> HRN(ArrayList<PCB> PCBArr) {

        //由于刚开始多个进程如果同时第一个到达，其响应比都为1，所以不做判断，只根据到达时间获取第一个到达的进程
        PCB first;
        first = PCBArr.get(0);
        for(int i=0;i<PCBArr.size();i++) {
            if(first.getArriveTime() > PCBArr.get(i).getArriveTime()) {
                first = PCBArr.get(i);
            }
        }

        //复制原PCB数组
        ArrayList<PCB> tempArr = new ArrayList<PCB>();
        for (int i=0;i<PCBArr.size();i++) {
            tempArr.add(PCBArr.get(i));
        }

        //第一个进入工作队列中
        ArrayList<PCB> workArr = new ArrayList<PCB>();    //存储结果数组
        workArr.add(first);
        workArr.get(0).setBeginTime(first.getArriveTime());
        workArr.get(0).setFinshTime(first.getArriveTime()+first.getWorkTime());

        //删除已经进入工作队列的第一个进程的PCB
        tempArr.remove(first);

        //剩下的进程通过响应比高者优先调度算法依次进入工作队列
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

            //筛选出temp数组中响应比最高的进程first2
            PCB first2;
            first2 = temp.get(0);

            //记录当前first2指定进程的响应比
            double largeHrn = ((workArr.get(workArr.size()-1).getFinshTime()-first2.getArriveTime()) + first2.getWorkTime()) / first2.getWorkTime();

            for(int i=0;i<temp.size();i++) {
                double hrn = ((workArr.get(workArr.size()-1).getFinshTime()-temp.get(i).getArriveTime()) + temp.get(i).getWorkTime()) / temp.get(i).getWorkTime();
                if(largeHrn < hrn) {
                    largeHrn = hrn;
                    first2 = temp.get(i);
                }
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
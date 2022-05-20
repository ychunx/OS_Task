package Main;

import java.util.ArrayList;
import java.util.Vector;
import Utils.*;
import UI.*;

public class main {

    public static String flag = "";
    public static ArrayList<PCB> pcbArr = new ArrayList<PCB>();
    public static ArrayList<PCB> rArr = new ArrayList<PCB>();

    public static void main(String[] args) { new p_UI().setVisible(true); }

    public static void start() {

        if(flag == "先来先服务（FCFS）") {
            rArr = FCFS.FCFS(pcbArr);
        } else if(flag == "最短进程优先（SPF）") {
            rArr = SPF.SPF(pcbArr);
        } else if(flag == "响应比高者优先（HRN）"){
            rArr = HRN.HRN(pcbArr);
        }

        //刷新表格数据
        p_UI.data.clear();

        for(int i=0;i<rArr.size();i++) {
            Vector aData = new Vector();
            aData.add(rArr.get(i).id);
            aData.add(rArr.get(i).name);
            aData.add(rArr.get(i).priority);
            aData.add(rArr.get(i).workTime);
            aData.add(rArr.get(i).arriveTime);
            aData.add(rArr.get(i).beginTime);
            aData.add(rArr.get(i).finshTime);
            //计算周转时间和带权周转时间
            rArr.get(i).setTAT(rArr.get(i).getFinshTime()-rArr.get(i).getArriveTime());
            rArr.get(i).setWTAT(rArr.get(i).getTAT()/rArr.get(i).getWorkTime());
            aData.add(rArr.get(i).TAT);
            aData.add(rArr.get(i).WTAT);
            p_UI.data.add(aData);
        }

        //刷新表格
        p_UI.dtm.setDataVector(p_UI.data, p_UI.colName);
        p_UI.table.setModel(p_UI.dtm);
    }

    //撤销执行
    public static void revoke() {

        p_UI.tTAT.setText("");
        p_UI.tWTAT.setText("");
        p_UI.data.clear();

        for (int i=0;i<pcbArr.size();i++) {
            Vector aData = new Vector();
            aData.add(pcbArr.get(i).id);
            aData.add(pcbArr.get(i).name);
            aData.add(pcbArr.get(i).priority);
            aData.add(pcbArr.get(i).workTime);
            aData.add(pcbArr.get(i).arriveTime);
            p_UI.data.add(aData);
        }

        p_UI.dtm.setDataVector(p_UI.data, p_UI.colName);
        p_UI.table.setModel(p_UI.dtm);
    }
}
package Main;

import Utils.*;
import java.util.ArrayList;
import java.util.Vector;
import UI.*;

public class main {

    public static String flag = "";
    public static ArrayList<JCB> jcbArr = new ArrayList<JCB>();
    public static ArrayList<JCB> rArr = new ArrayList<JCB>();

    public static void main(String[] args) { new j_UI().setVisible(true); }

    public static void start() {

        if(flag == "先来先服务（FCFS）") {
            rArr = FCFS.FCFS(jcbArr);
        } else if(flag == "优先权优先（PSA）") {
            rArr = PSA.PSA(jcbArr);
        } else if(flag == "简单轮转法（RR）"){
            rArr = RR.RR(jcbArr);
        }

        //刷新表格数据
        j_UI.data.clear();

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
            j_UI.data.add(aData);
        }

        //刷新表格
        j_UI.dtm.setDataVector(j_UI.data, j_UI.colName);
        j_UI.table.setModel(j_UI.dtm);
    }

    //撤销执行
    public static void revoke() {

        j_UI.tTAT.setText("");
        j_UI.tWTAT.setText("");
        j_UI.data.clear();

        for (int i=0;i<jcbArr.size();i++) {
            Vector aData = new Vector();
            aData.add(jcbArr.get(i).id);
            aData.add(jcbArr.get(i).name);
            aData.add(jcbArr.get(i).priority);
            aData.add(jcbArr.get(i).workTime);
            aData.add(jcbArr.get(i).arriveTime);
            j_UI.data.add(aData);
        }

        j_UI.dtm.setDataVector(j_UI.data, j_UI.colName);
        j_UI.table.setModel(j_UI.dtm);
    }
}

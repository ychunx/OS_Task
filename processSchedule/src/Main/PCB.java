package Main;

public class PCB {
    public int id;            //序号
    public String name;        //进程名
    public int priority;       //优先级
    public double workTime;    //服务时间
    public double arriveTime;  //到达时间
    public double beginTime;   //开始运行时间
    public double finshTime;   //运行结束时间
    public double TAT;         //周转时间
    public double WTAT;        //带权周转时间

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getPriority() {return priority;}
    public void setPriority(int priority) {this.priority = priority;}
    public double getWorkTime() {return workTime;}
    public void setWorkTime(double workTime) {this.workTime = workTime;}
    public double getArriveTime() {return arriveTime;}
    public void setArriveTime(double arriveTime) {this.arriveTime = arriveTime;}
    public double getBeginTime() {return beginTime;}
    public void setBeginTime(double beginTime) {this.beginTime = beginTime;}
    public double getFinshTime() {return finshTime;}
    public void setFinshTime(double finshTime) {this.finshTime = finshTime;}
    public double getTAT() {return TAT;}
    public void setTAT(double tAT) {TAT = tAT;}
    public double getWTAT() {return WTAT;}
    public void setWTAT(double wTAT) {WTAT = wTAT;}

    public PCB() {}
    public PCB(int id, String name, double arrivetime, double workTime, int priority) {
        setId(id);
        setName(name);
        setArriveTime(arrivetime);
        setWorkTime(workTime);
        setPriority(priority);
    }
}
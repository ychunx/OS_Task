package Main;

import UI.ui;

import java.util.LinkedList;

//保存一些数据
public class main {

    public static final int minSize = 5;    //碎片最小值
    public static LinkedList<partition> partitionList = new LinkedList<>(); //存储分区信息
    public static int pointer = 0;  //作指针，指向上一次查找结束的地址
    public static int flag = 0;     //记录每次执行后错误的类型
    public static int maxSize = 1024;   //内存最大值

    public static void main(String[] args) {
        new ui().setVisible(true);
    }
}
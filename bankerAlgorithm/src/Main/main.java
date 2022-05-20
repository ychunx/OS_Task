package Main;

import UI.ui;
import Utils.*;
import java.util.Arrays;
import java.util.Scanner;

//图形化程序时为保存数据结构类，命令行程序时为主函数（请取消注释代码）
public class main {

    public static int n = 0;//系统中进程总数n
    public static int m = 0;//系统中资源种类总数m
    public static int[] aMax = new int[3];//图形化用保存最大系统资源总量
    public static int[] Available = new int[100];//系统资源可用资源量
    public static int[][] Max = new int[100][100];//进程最大需求资源矩阵
    public static int[][] Allocation = new int[100][100];//当前已分配给进程的各种资源数量
    public static int[][] Need = new int[100][100];//当前每个进程还需分配的各种资源数量
    public static int[] Work = new int[100];//当前系统剩余可分配的资源
    public static boolean[] Finish = new boolean[100];//进程是否已完成
    public static int[] result = new int[101];//结果
    public static int[] request = new int[100];//需求
    public static int flag = 0;//存放请求分配时发生事件的类型有1、2、3、4

    public static void main(String[] args) {
        new ui().setVisible(true);
    }

    /*
    //使用命令行界面时将注释代码取消注释即可使用

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Arrays.fill(Finish,false);  //初值为false，即所有进程一开始都是设置为未完成

        //进程和资源信息输入
        infoIO.input();

        //计算进程还需资源Need和系统剩余可用资源Available
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                Need[i][j] = Max[i][j] - Allocation[i][j];
                Available[j] = Available[j] - Allocation[i][j];
            }
        }

        //功能选择
        int select = 0;
        while (true){
            infoIO.function();
            select = scan.nextInt();

            if (select == 1){
                //显示当前资源情况
                infoIO.show();
            } else if (select == 2){
                Arrays.fill(main.result,-1);
                //当前状态安全检查
                judge.safety();
                if (result[0] == 1){
                    System.out.print("存在此安全序列：");
                    for (int i = 1;result[i] != -1; i++){
                        System.out.print("P" + result[i]);
                    }
                    System.out.println("");
                    System.out.println("******状态安全******");
                }else{
                    System.out.println("******状态不安全******");
                }
            } else if (select == 3){
                //请求资源分配
                int num;
                System.out.print("请输入发出请求的进程：P");
                num = scan.nextInt();
                System.out.println("请输入请求各资源的数量：");
                for (int i = 0; i < m; i++){
                    request[i] = scan.nextInt();
                }
                //判断的同时如果分配可行，则已经分配
                if (judge.allocate(num)){
                    System.out.println("******分配成功******");
                    //每次分配后都要判断进程是否已经完成，已完成则释放资源
                    judge.end(num);
                }else{
                    System.out.println("******分配失败！！！******");
                }
            } else if (select == 0){
                System.out.println("******成功退出******");
                break;
            }
        }
    }

    */
}
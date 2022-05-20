package Utils;

import Main.*;
import java.util.Scanner;

//命令行界面保留
public class infoIO {

    static Scanner scan = new Scanner(System.in);

    //显示功能选择
    public static void function() {
        System.out.println("******功能选择******");
        System.out.println("1.显示当前资源情况");
        System.out.println("2.当前状态安全检查");
        System.out.println("3.请求资源分配");
        System.out.println("0.退出程序");
        System.out.println("*******请选择*******");
    }

    //进程和资源信息输入
    public static void input() {

        System.out.println("请输入进程总数n：");
        main.n = scan.nextInt();
        System.out.println("请输入资源种类总数m：");
        main.m = scan.nextInt();
        System.out.println("请依次输入各种资源数量：");
        for(int i=0;i<main.m;i++) {
            main.Available[i] = scan.nextInt();
        }
        for(int i=0;i<main.n;i++) {
            System.out.println("******请输入进程P" + i + "的信息******");
            System.out.println("请输入该进程所需各资源的最大量：");
            for(int j=0;j<main.m;j++) {
                main.Max[i][j] = scan.nextInt();
            }
            System.out.println("请输入该进程初始分配各资源量：");
            for(int k=0;k<main.m;k++) {
                main.Allocation[i][k] = scan.nextInt();
            }
        }
    }

    //显示当前资源情况
    public static void show() {

        System.out.println("******当前资源分配情况******");
        System.out.print("进程\t");
        for(int i=0;i<main.m;i++) {
            System.out.print("资源" + i + "\t");
        }
        System.out.println("");
        for(int i=0;i<main.n;i++) {
            System.out.print("P" + i + "\t");
            for(int j=0;j<main.m;j++) {
                System.out.print(main.Allocation[i][j] + "\t\t");
            }
            System.out.println("");
        }

        System.out.println("******当前资源需求情况******");
        System.out.print("进程\t");
        for(int i=0;i<main.m;i++) {
            System.out.print("资源" + i + "\t");
        }
        System.out.println("");
        for(int i=0;i<main.n;i++) {
            System.out.print("P" + i + "\t");
            for(int j=0;j<main.m;j++) {
                System.out.print(main.Need[i][j] + "\t\t");
            }
            System.out.println("");
        }

        System.out.println("******当前资源剩余情况******");
        for(int i=0;i<main.m;i++) {
            System.out.print("资源" + i + "\t");
        }
        System.out.println("");
        for(int i=0;i<main.m;i++) {
            System.out.print(main.Available[i] + "\t\t");
        }
        System.out.println("");

        System.out.println("************当前进程执行情况************");
        for(int i=0;i<main.n;i++) {
            System.out.print("进程" + i + "\t");
        }
        System.out.println("");
        for(int i=0;i<main.n;i++) {
            if (main.Finish[i]) {
                System.out.print("true\t");
            } else {
                System.out.print("false\t");
            }
        }
        System.out.println("");
    }
}
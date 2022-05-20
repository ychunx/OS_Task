package Utils;

import Main.*;
import java.util.Arrays;

public class judge {

    //判断是不是所有进程均已执行完
    public static boolean judge_end(boolean[] f) {
        boolean flag = true;
        for(int i=0;i<main.m;i++) {
            if (!f[i]) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    //状态安全检查
    public static void safety() {
        //复制初始化Work
        for(int i=0;i<main.m;i++) {
            main.Work[i] = main.Available[i];
        }
        //复制初始化finish
        boolean[] finish = new boolean[100];
        for(int i=0;i<main.n;i++) {
            finish[i] = main.Finish[i];
        }
        int count = 1;
        int flag1 = 0;
        int flag2 = 1;
        for(int i=0;i<main.n;i++) {
            if (i == 0)
                flag1 = 0;
            //跳过已经完成的进程
            if (finish[i] == true)
                continue;
            //Work与Need比较
            int flag3 = 1;
            for(int j=0;j<main.m;j++) {
                if (main.Work[j] < main.Need[i][j]) {
                    flag3 = 0;
                    break;
                }
            }
            //若资源不够则跳过这一进程
            if (flag3 != 1) {
                //是不是本次循环进程一个都没能结束
                if (i == main.n - 1 && flag1 != 1){
                    //分析一个都没能结束的原因
                    //是不是全部进程已经都执行完了
                    if (judge_end(finish))
                        break;
                    else{
                        //存在没结束的进程但无法结束
                        flag2 = 0;
                        break;
                    }
                }
                continue;
            } else {
                //若资源够，则执行完该进程，释放该进程占用的资源
                main.result[count] = i;
                count++;
                for (int j = 0; j < main.m; j++){
                    main.Work[j] = main.Work[j] + main.Allocation[i][j];
                }
                finish[i] = true;
                flag1 = 1;//标记这一轮找到了可以执行完的进程
                i = -1;//从头再开始遍历进程集合
            }
        }
        main.result[0] = flag2;
    }

    //请求资源分配
    public static boolean allocate(int num){
        int flag = 1;
        for (int i = 0; i < main.m; i++){
            if (main.request[i] > main.Need[num][i]){
                flag = 0;
                break;
            }
        }
        if (flag == 0){
            main.flag = 1;  //图形化界面需求
            System.out.println("请求大于该进程还需要的资源量，请求不合法");
            return false;
        }
        for (int i = 0; i < main.m; i++){
            if (main.request[i] > main.Available[i]){
                flag = 0;
                break;
            }
        }
        if (flag == 0){
            main.flag = 2;  //图形化界面需求
            System.out.println("请求大于当前系统剩余的资源量，请求不合法");
            return false;
        }

        // 尝试分配
        for (int i = 0; i < main.m; i++){
            main.Need[num][i] = main.Need[num][i] - main.request[i];
            main.Allocation[num][i] = main.Allocation[num][i] + main.request[i];
            main.Available[i] = main.Available[i] - main.request[i];
        }

        //分配后的安全性判断，会生成安全序列
        Arrays.fill(main.result,-1);
        safety();
        if (main.result[0] == 1){
            main.flag = 3;  //图形化界面需求
            System.out.print("存在此安全序列：");
            for (int i = 1;main.result[i] != -1; i++){
                System.out.print("P" + main.result[i]);
            }
            System.out.println("");
            return true;
        }else{
             main.flag = 4; //图形化界面需求
            System.out.println("根据安全性检查，本次分配不安全");
            //撤销分配
            for (int i = 0; i < main.m; i++){
                main.Need[num][i] = main.Need[num][i] + main.request[i];
                main.Allocation[num][i] = main.Allocation[num][i] - main.request[i];
                main.Available[i] = main.Available[i] + main.request[i];
            }
            return false;
        }
    }

    //判断进程是否已经完成，已完成则释放资源
    public static void end(int num) {

        int flag = 1;
        for (int i = 0; i < main.m; i++){
            if (main.Need[num][i] > 0){
                flag = 0;
                break;
            }
        }
        //释放资源
        if (flag == 1){
            System.out.println("进程P"+ num + "执行完成");
            main.Finish[num] = true;
            for (int i = 0; i < main.m; i++){
                main.Available[i] = main.Available[i] + main.Allocation[num][i];
                main.Allocation[num][i] = 0;
            }
        }
    }
}

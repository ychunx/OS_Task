package Utils;

import Main.*;

//循环首次适应算法
public class nextFit {

    public static void next(int size) {

        //从上次分配分区位置地址开始遍历分区链表
        partition temp = main.partitionList.get(main.pointer);
        int t = main.pointer;
        int i = (t + 1) % main.partitionList.size();    //使其循环

        //因为for循环的判定条件原因，所以先要查询一个分区
        if (temp.state && (temp.size > size)){
            allocate.toDo(size, t, temp);
            main.pointer = t + 1;   //记录本次查询位置地址
        } else {
            for (;i != t;i = (i+1) % main.partitionList.size()){    //循环
                temp = main.partitionList.get(i);
                //首次找到可用分区则分配
                if (temp.state && (temp.size >= size)){
                    allocate.toDo(size, i, temp);
                    break;
                }
            }
            main.pointer = i;   //记录本次查询位置地址
        }

        //遍历结束后未找到可用分区，即指针循环回到原点, 则内存分配失败
        if (t == i) {
            main.flag = 1;  //记录错误类型
        }
    }
}
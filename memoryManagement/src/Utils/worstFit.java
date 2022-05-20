package Utils;

import Main.*;

//最坏适应算法
public class worstFit {

    public static void worst(int size) {

        int flag = -1;  //记录适应分区位置首地址
        int max = 0;    //记录最大适应分区
        //遍历完全链表
        for (int i=0;i < main.partitionList.size();i++){
            partition temp = main.partitionList.get(i);
            if (temp.state && (temp.size >= size)){
                if (max < temp.size - size){
                    max = temp.size - size;
                    flag = i;   //记录适合的最大分区索引
                }
            }
        }
        main.pointer = flag + 1;    //记录本次查询位置地址

        //如果flag没改变说明没右合适的分区进行分配，提示用户错误
        if (flag != -1){
            allocate.toDo(size, flag, main.partitionList.get(flag));
        }else {
            //遍历结束后未找到可用分区, 则内存分配失败
            main.flag = 1;
        }
    }
}
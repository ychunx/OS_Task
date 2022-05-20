package Utils;

import Main.*;

//最佳适应算法
public class bestFit {

    public static void best(int size) {

        int flag = -1;  //记录所改变分区的索引号
        int min = main.maxSize; //记录内存内最小的适应分区
        for (int i=0;i < main.partitionList.size();i++){
            partition temp = main.partitionList.get(i);
            if (temp.state && (temp.size >= size)){
                if (min > temp.size - size){
                    min = temp.size - size;
                    flag = i;   //记录适合的最小分区索引
                }
            }
        }
        main.pointer = flag + 1;    //记录本次查询的地址位置

        //如果flag没改变说明没右合适的分区进行分配，提示用户错误
        if (flag != -1){
            allocate.toDo(size, flag, main.partitionList.get(flag));
        }else {
            //遍历结束后未找到可用分区, 则内存分配失败
            main.flag = 1;  //记录错误类型
        }
    }
}
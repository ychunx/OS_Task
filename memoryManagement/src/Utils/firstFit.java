package Utils;

import Main.*;

//首次适应算法
public class firstFit {

    public static void first(int size) {

        int i=0; //从最高地址开始找
        for (;i < main.partitionList.size();i++){
            partition temp = main.partitionList.get(i);
            //找到第一个可用分区即开始分配
            if (temp.state && (temp.size >= size)){
                allocate.toDo(size,i,temp);
                break;
            }
        }
        main.pointer = i;   //记录位置
        //遍历完全链表结束后未找到可用分区, 则内存分配失败
        if (i == main.partitionList.size()) {
            main.flag = 1;  //记录错误类型
        }
    }
}
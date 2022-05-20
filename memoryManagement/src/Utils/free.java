package Utils;

import Main.*;

public class free {

    public static void toDo(int index) {

        //输入分区编号不合法
        if (index >= main.partitionList.size() || index < 0){
            main.flag = 2;
            return;
        }

        //输入分区编号的状态为空闲，即无需回收
        partition temp = main.partitionList.get(index);
        if (temp.state) {
            main.flag = 3;
            return;
        }

        //课本的四种情况：
        if (index > 0
                && index < main.partitionList.size() - 1
                && main.partitionList.get(index - 1).state
                && main.partitionList.get(index + 1).state) {
            //第三种情况：如果回收分区前后分区都为空闲，则三个合并，首地址位前分区首地址，大小为三者之和
            partition temp4 = main.partitionList.get(index - 1);
            partition temp5 = main.partitionList.get(index + 1);
            temp4.size = temp4.size + temp.size + temp5.size;
            main.partitionList.remove(temp);
            main.partitionList.remove(temp5);
        } else if (index > 0 && main.partitionList.get(index - 1).state){
            //第一种情况：如果回收分区不是首分区且前一个分区为空闲, 则与前分区合并，首地址为前分区首地址，大小为两者之和
            partition temp2 = main.partitionList.get(index - 1);
            temp2.size += temp.size;
            main.partitionList.remove(index);
        } else if (index < main.partitionList.size() - 1 && main.partitionList.get(index + 1).state){
            //第二种情况：如果回收分区不是尾分区且后一个分区为空闲, 则与后分区合并，首地址为原首地址，大小为两者之和
            partition temp3 = main.partitionList.get(index + 1);
            temp.size += temp3.size;
            main.partitionList.remove(temp3);
            main.partitionList.get(index).state = true;
        } else {
            //第四种情况：如果回收分区前后分区都为繁忙，则直接将回收分区状态设为空闲 或 回收分区为首、尾分区
            main.partitionList.get(index).state = true;
        }
    }
}
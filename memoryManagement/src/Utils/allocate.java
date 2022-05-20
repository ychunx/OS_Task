package Utils;

import Main.*;

//分配分区
public class allocate {

    public static void toDo(int size, int index, partition temp) {

        //如果分割后分区剩余大小小于minSize，则将分区全部分配，否则分割为两个分区
        if (temp.size - size > main.minSize) {
            //分割
            partition temp2 = new partition(temp.head + size, temp.size - size);
            main.partitionList.add(index + 1, temp2);

            temp.size = size;
        }
        temp.state = false;     //两种情况都是将原表项的状态设为繁忙
    }
}
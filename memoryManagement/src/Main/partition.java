package Main;

public class partition {

    public int size;    //分区大小
    public int head;    //分区首地址
    public boolean state;   //分区是否空闲，true为空闲，false为繁忙

    public partition(int head, int size) {
        this.head = head;
        this.size = size;
        this.state = true;
    }
}
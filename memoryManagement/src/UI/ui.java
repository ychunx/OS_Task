package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

import Main.*;
import Utils.*;

public class ui extends JFrame {

    public String[] colName = {"分区编号","起始地址","分区大小","是否空闲"};
    String[][] data;

    public ui() {

        super("模拟存储管理（测试无误，重启解决一切问题）");

        int WIDTH = 720;
        int HEIGHT = 540;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((int)(screenSize.getWidth()-WIDTH)/2,(int)(screenSize.getHeight()-HEIGHT)/2,WIDTH,HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        init();
    }

    public void init() {

        JPanel JP = new JPanel();
        JP.setLayout(null);
        JP.add(setPage());
        this.add(JP);
    }

    public JPanel setPage() {

        JPanel jp = new JPanel();
        jp.setBounds(0,0,720,540);
        jp.setLayout(null);

        //分区信息表格
        main.partitionList.add(new partition(0, 1024));
        data = new String[main.partitionList.size()][4];
        for (int i=0;i<main.partitionList.size();i++) {
            data[i][0] = String.valueOf(i);
            data[i][1] = String.valueOf(main.partitionList.get(i).head);
            data[i][2] = String.valueOf(main.partitionList.get(i).size);
            data[i][3] = String.valueOf(main.partitionList.get(i).state);
        }
        JTable dataT = new JTable(data,colName);

        //设置表格文字居中
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(JLabel.CENTER);
        dataT.setDefaultRenderer(Object.class, tcr);

        JScrollPane jsp = new JScrollPane(dataT);
        jsp.setBounds(10,10,399,485);
        jp.add(jsp);

        //右
        JLabel jl1 = new JLabel("初始内存大小为1MB，仅限使用程序前设置");
        jl1.setBounds(420,10,280,30);
        jp.add(jl1);
        JLabel jl11 = new JLabel("设置内存大小,单位KB：");
        jl11.setBounds(420,30,280,30);
        jp.add(jl11);

        JTextField SIZE = new JTextField();
        SIZE.setBounds(420,70,100,30);
        jp.add(SIZE);

        JButton SIZEB = new JButton("设置");
        SIZEB.setBounds(530,70,60,29);
        SIZEB.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int SIZET = Integer.parseInt(SIZE.getText());
                main.maxSize = SIZET;
                partition temp = new partition(0,SIZET);
                main.partitionList.set(0,temp);
                SIZE.setText("");
                String[][] tempS = new String[main.partitionList.size()][4];
                for (int i=0;i<main.partitionList.size();i++) {
                    tempS[i][0] = String.valueOf(i);
                    tempS[i][1] = String.valueOf(main.partitionList.get(i).head);
                    tempS[i][2] = String.valueOf(main.partitionList.get(i).size);
                    tempS[i][3] = String.valueOf(main.partitionList.get(i).state);
                }
                TableModel tm = new DefaultTableModel(tempS,colName);
                dataT.setModel(tm);
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        jp.add(SIZEB);

        JLabel jl2 = new JLabel("选择算法申请空间：");
        jl2.setBounds(420,105,280,30);
        jp.add(jl2);

        JComboBox a = new JComboBox();
        a.addItem("首次适应算法");
        a.addItem("循环首次适应算法");
        a.addItem("最佳适应算法");
        a.addItem("最差适应算法");
        a.setBounds(420,145,130,30);
        jp.add(a);

        JTextField size = new JTextField();
        size.setBounds(560,145,60,32);
        jp.add(size);

        JButton sizeB = new JButton("申请");
        sizeB.setBounds(630,145,60,30);
        sizeB.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String select = Objects.requireNonNull(a.getSelectedItem()).toString();
                int tsize = Integer.parseInt(size.getText());
                if (Objects.equals(select, "首次适应算法")) {
                    firstFit.first(tsize);
                } else if (Objects.equals(select, "循环首次适应算法")) {
                    nextFit.next(tsize);
                } else if (Objects.equals(select, "最佳适应算法")) {
                    bestFit.best(tsize);
                } else {
                    worstFit.worst(tsize);
                }
                size.setText("");

                if (main.flag == 1) {
                    JOptionPane.showMessageDialog(jp,"无可用分区","注意事项",JOptionPane.ERROR_MESSAGE);
                    main.flag = 0;
                }

                String[][] tempS = new String[main.partitionList.size()][4];
                for (int i=0;i<main.partitionList.size();i++) {
                    tempS[i][0] = String.valueOf(i);
                    tempS[i][1] = String.valueOf(main.partitionList.get(i).head);
                    tempS[i][2] = String.valueOf(main.partitionList.get(i).size);
                    tempS[i][3] = String.valueOf(main.partitionList.get(i).state);
                }
                TableModel tm2 = new DefaultTableModel(tempS,colName);
                dataT.setModel(tm2);
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        jp.add(sizeB);

        JLabel jl3 = new JLabel("回收空间：");
        jl3.setBounds(420,185,280,30);
        jp.add(jl3);

        JLabel jl4 = new JLabel("分区编号：");
        jl4.setBounds(420,225,70,30);
        jp.add(jl4);

        JTextField index = new JTextField();
        index.setBounds(490,225,100,32);
        jp.add(index);

        JButton indexB = new JButton("回收");
        indexB.setBounds(610,225,60,30);
        indexB.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int id = Integer.parseInt(index.getText());
                free.toDo(id);
                index.setText("");
                if (main.flag == 2) {
                    JOptionPane.showMessageDialog(jp,"无此编号分区","注意事项",JOptionPane.ERROR_MESSAGE);
                    main.flag = 0;
                }
                if (main.flag == 3) {
                    JOptionPane.showMessageDialog(jp,"此分区未被分配，无需回收","注意事项",JOptionPane.ERROR_MESSAGE);
                    main.flag = 0;
                }
                String[][] tempS = new String[main.partitionList.size()][4];
                for (int i=0;i<main.partitionList.size();i++) {
                    tempS[i][0] = String.valueOf(i);
                    tempS[i][1] = String.valueOf(main.partitionList.get(i).head);
                    tempS[i][2] = String.valueOf(main.partitionList.get(i).size);
                    tempS[i][3] = String.valueOf(main.partitionList.get(i).state);
                }
                TableModel tm3 = new DefaultTableModel(tempS,colName);
                dataT.setModel(tm3);
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        jp.add(indexB);

        JLabel jl5 = new JLabel("碎片最小值为5，");
        jl5.setBounds(420,265,280,30);
        jp.add(jl5);
        JLabel jl6 = new JLabel("分配时剩余分区小于5时直接分配不做分区");
        jl6.setBounds(420,285,280,30);
        jp.add(jl6);

        return jp;
    }
}
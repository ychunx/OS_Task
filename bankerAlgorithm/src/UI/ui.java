package UI;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Objects;
import Utils.*;
import Main.*;

public class ui extends JFrame {

    public ui() {

        super("模拟银行家算法（测试无误，重启解决一切问题）");

        int WIDTH = 720;
        int HEIGHT = 540;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((int)(screenSize.getWidth()-WIDTH)/2,(int)(screenSize.getHeight()-HEIGHT)/2,WIDTH,HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        init();
    }

    public void init() {

        Arrays.fill(main.Finish,false);  //初值为false，即所有进程一开始都是设置为未完成
        main.m = 3;

        JPanel P = new JPanel();
        P.setLayout(null);

        P.add(setPage());

        this.add(P);
    }

    public JPanel setPage() {

        JPanel jp = new JPanel();
        jp.setBounds(0,0,720,540);
        jp.setLayout(null);

        //左上
        String[] colName1 = {"资源","进程最大需求量","进程已分配资源","进程还需资源量","是否完成"};
        String[][] d = new String[0][0];
        JTable titleT = new JTable(d,colName1);
        //设置表头行高列宽
        JTableHeader header = titleT.getTableHeader();
        header.setPreferredSize(new Dimension(titleT.getWidth(),60));
        titleT.getColumnModel().getColumn(0).setPreferredWidth(51);
        titleT.getColumnModel().getColumn(1).setPreferredWidth(150);
        titleT.getColumnModel().getColumn(2).setPreferredWidth(150);
        titleT.getColumnModel().getColumn(3).setPreferredWidth(150);
        titleT.getColumnModel().getColumn(4).setPreferredWidth(50);

        JScrollPane jsp1 = new JScrollPane(titleT);
        jsp1.setBounds(0,0,551,60);
        jp.add(jsp1);

        //左中
        String[] colName2 = {"进程","A","B","C","A","B","C","A","B","C","T/F"};
        String[][] data = new String[10][11];
        JTable dataT = new JTable(data,colName2);
        dataT.setRowHeight(37);
        for (int i=0;i<main.n;i++){
            data[i][0] = "P"+i;
            data[i][1] = String.valueOf(main.Max[i][0]);
            data[i][2] = String.valueOf(main.Max[i][1]);
            data[i][3] = String.valueOf(main.Max[i][2]);
            data[i][4] = String.valueOf(main.Allocation[i][0]);
            data[i][5] = String.valueOf(main.Allocation[i][1]);
            data[i][6] = String.valueOf(main.Allocation[i][2]);
            data[i][7] = String.valueOf(main.Need[i][0]);
            data[i][8] = String.valueOf(main.Need[i][1]);
            data[i][9] = String.valueOf(main.Need[i][2]);
        }
        //设置表格文字居中
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr2.setHorizontalAlignment(JLabel.CENTER);
        dataT.setDefaultRenderer(Object.class, tcr2);

        JScrollPane jsp2 = new JScrollPane(dataT);
        jsp2.setBounds(0,59,551,393);
        jp.add(jsp2);

        //脚部
        JPanel footer = new JPanel();
        footer.setBounds(0,453,550,50);
        footer.setLayout(null);
        jp.add(footer);

        JLabel jl1 = new JLabel("选择功能：");
        jl1.setBounds(10,0,70,50);
        footer.add(jl1);

        JComboBox combox = new JComboBox();
        combox.addItem("设置系统资源");
        combox.addItem("添加/修改进程");
        combox.addItem("请求资源分配");
        combox.setBounds(75,10,110,30);
        footer.add(combox);

        JLabel jl2 = new JLabel("PID：");
        jl2.setBounds(195,0,35,50);
        footer.add(jl2);

        JTextField pidT = new JTextField();
        pidT.setBounds(235,10,40,30);
        footer.add(pidT);

        JLabel a = new JLabel("A：");
        a.setBounds(280,0,25,50);
        footer.add(a);

        JTextField aT = new JTextField();
        aT.setBounds(300,10,40,30);
        footer.add(aT);

        JLabel b = new JLabel("B：");
        b.setBounds(345,0,25,50);
        footer.add(b);

        JTextField bT = new JTextField();
        bT.setBounds(365,10,40,30);
        footer.add(bT);

        JLabel c = new JLabel("C：");
        c.setBounds(410,0,25,50);
        footer.add(c);

        JTextField cT = new JTextField();
        cT.setBounds(430,10,40,30);
        footer.add(cT);

        JButton footB = new JButton("确定");
        footB.setBounds(490,10,60,30);
        footer.add(footB);

        //右上
        JLabel mrt = new JLabel("系统最大资源量：");
        mrt.setBounds(560,30,100,30);
        jp.add(mrt);

        String[] colName3 = {"A","B","C"};
        String[][] mrtData = new String[1][3];
        for(int i=0;i<3;i++){
            mrtData[0][i] = String.valueOf(main.Available[i]);
        }
        JTable mrT = new JTable(mrtData,colName3);
        //设置表头行高列宽
        JTableHeader header3 = mrT.getTableHeader();
        header3.setPreferredSize(new Dimension(mrT.getWidth(),30));
        mrT.setRowHeight(50);
        //设置表格文字居中
        DefaultTableCellRenderer tcr3 = new DefaultTableCellRenderer();
        tcr3.setHorizontalAlignment(JLabel.CENTER);
        mrT.setDefaultRenderer(Object.class, tcr3);
        JScrollPane jsp3 = new JScrollPane(mrT);
        jsp3.setBounds(558,60,141,83);
        jp.add(jsp3);

        //右中
        JLabel remaint = new JLabel("系统剩余资源量：");
        remaint.setBounds(560,160,100,30);
        jp.add(remaint);

        String[] colName4 = {"A","B","C"};
        String[][] remaintData = new String[1][3];
        for(int i=0;i<3;i++){
            remaintData[0][i] = String.valueOf(main.Available[i]);
        }
        JTable remainT = new JTable(remaintData,colName4);
        //设置表头行高列宽
        JTableHeader header4 = remainT.getTableHeader();
        header4.setPreferredSize(new Dimension(remainT.getWidth(),30));
        remainT.setRowHeight(50);
        //设置表格文字居中
        DefaultTableCellRenderer tcr4 = new DefaultTableCellRenderer();
        tcr4.setHorizontalAlignment(JLabel.CENTER);
        remainT.setDefaultRenderer(Object.class, tcr4);
        JScrollPane jsp4 = new JScrollPane(remainT);
        jsp4.setBounds(558,190,141,83);
        jp.add(jsp4);

        //右下注意事项
        JButton tipsB = new JButton("查看注意事项");
        tipsB.setBounds(560,463,129,30);
        tipsB.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(jp, """
                                1.使用程序先设置系统资源最大量，PID留空。
                                2.如果修改进程后最大需求量小于已分配量，则还需求量会变为负值为正常现象。
                                3.已完成的进程还需求量为最大需求量。
                                4.发生错误多次后最好重启软件并规范合法输入，避免发生错误。
                                5.由于页面实现确实比较困难，图形化程序只允许三种系统资源和显示十个以内进程的模拟，
                                如需无限种系统资源和无限个进程的模拟，请使用命令行界面，代码中有保留。"""
                        ,"注意事项",JOptionPane.PLAIN_MESSAGE);
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
        jp.add(tipsB);

        //设置监听响应事件
        //确定按钮
        footB.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String PID = pidT.getText();
                String A = aT.getText();
                String B = bT.getText();
                String C = cT.getText();

                if(Objects.equals(combox.getSelectedItem(), "设置系统资源")) {
                    //保存原剩余资源，没有设置时为0
                    int[] tempA = new int[3];
                    for(int i=0;i<3;i++) {
                        tempA[i] = main.Available[i];
                    }
                    //第一次设置时保存系统资源最大量到aMax
                    if((main.Available[0] == 0)&&(main.Available[1] == 0)&&(main.Available[2] == 0)) {
                        main.aMax[0] = Integer.parseInt(A);
                        main.aMax[1] = Integer.parseInt(B);
                        main.aMax[2] = Integer.parseInt(C);
                    }
                    if((main.Available[0] != 0)&&(main.Available[1] != 0)&&(main.Available[2] != 0)) {
                        remaintData[0][0] = String.valueOf(tempA[0] + Integer.parseInt(A) - main.aMax[0]);
                        remaintData[0][1] = String.valueOf(tempA[1] + Integer.parseInt(B) - main.aMax[1]);
                        remaintData[0][2] = String.valueOf(tempA[2] + Integer.parseInt(C) - main.aMax[2]);
                        main.Available[0] = tempA[0] + Integer.parseInt(A) - main.aMax[0];
                        main.Available[1] = tempA[1] + Integer.parseInt(B) - main.aMax[1];
                        main.Available[2] = tempA[2] + Integer.parseInt(C) - main.aMax[2];
                        main.aMax[0] = Integer.parseInt(A);
                        main.aMax[1] = Integer.parseInt(B);
                        main.aMax[2] = Integer.parseInt(C);
                    } else {
                        main.Available[0] = Integer.parseInt(A);
                        main.Available[1] = Integer.parseInt(B);
                        main.Available[2] = Integer.parseInt(C);
                        for(int i=0;i<3;i++){
                            remaintData[0][i] = String.valueOf(main.Available[i]);
                        }
                    }
                    mrtData[0][0] = A;
                    mrtData[0][1] = B;
                    mrtData[0][2] = C;
                    TableModel tm3 = new DefaultTableModel(mrtData,colName3);
                    mrT.setModel(tm3);
                    TableModel tm4 = new DefaultTableModel(remaintData,colName4);
                    remainT.setModel(tm4);
                    aT.setText("");
                    bT.setText("");
                    cT.setText("");
                    //设置完系统资源后检查安全性
                    main.request[0] = 0;
                    main.request[1] = 0;
                    main.request[2] = 0;
                    for(int i=0;i<main.n;i++) {
                        if(!judge.allocate(i)) {
                            JOptionPane.showMessageDialog(jp,"设置系统资源不安全！\n根据安全性检查，本次设置不安全,即可能会发生死锁。\n请重新设置资源量并大于此次设置资源量。","失败",JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                    }
                } else if(Objects.equals(combox.getSelectedItem(), "添加/修改进程")) {
                    int index = Integer.parseInt(PID.substring(PID.indexOf("P")+1));
                    if((main.Max[index][0] == 0) && (main.Max[index][1] == 0) && (main.Max[index][2] == 0)) {
                        main.n++;
                    }
                    main.Max[index][0] = Integer.parseInt(A);
                    main.Max[index][1] = Integer.parseInt(B);
                    main.Max[index][2] = Integer.parseInt(C);
                    main.Finish[index] = false;
                    data[index][10] = String.valueOf(main.Finish[index]);
                    main.Need[index][0] = main.Max[index][0] - main.Allocation[index][0];
                    main.Need[index][1] = main.Max[index][1] - main.Allocation[index][1];
                    main.Need[index][2] = main.Max[index][2] - main.Allocation[index][2];
                    judge.end(index);   //如果修改后的进程最大需求量都小于已分配量，则会释放
                    data[index][0] = "P" + index;
                    data[index][1] = String.valueOf(main.Max[index][0]);
                    data[index][2] = String.valueOf(main.Max[index][1]);
                    data[index][3] = String.valueOf(main.Max[index][2]);
                    data[index][4] = String.valueOf(main.Allocation[index][0]);
                    data[index][5] = String.valueOf(main.Allocation[index][1]);
                    data[index][6] = String.valueOf(main.Allocation[index][2]);
                    data[index][7] = String.valueOf(main.Need[index][0]);
                    data[index][8] = String.valueOf(main.Need[index][1]);
                    data[index][9] = String.valueOf(main.Need[index][2]);
                    TableModel tm2 = new DefaultTableModel(data,colName2);
                    dataT.setModel(tm2);

                    pidT.setText("");
                    aT.setText("");
                    bT.setText("");
                    cT.setText("");
                    //添加完进程后检查安全性
                    main.request[0] = 0;
                    main.request[1] = 0;
                    main.request[2] = 0;
                    for(int i=0;i<main.n;i++) {
                        if(!judge.allocate(i)) {
                            JOptionPane.showMessageDialog(jp,"添加/修改进程不安全！\n根据安全性检查，本次设置不安全,即可能会发生死锁。\n请重新设置进程最大需求量或增大系统资源总量。","失败",JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                    }
                } else if(Objects.equals(combox.getSelectedItem(), "请求资源分配")) {
                    int index = Integer.parseInt(PID.substring(PID.indexOf("P")+1));

                    main.request[0] = Integer.parseInt(A);
                    main.request[1] = Integer.parseInt(B);
                    main.request[2] = Integer.parseInt(C);

                    if (judge.allocate(index)){
                        if(main.flag == 3) {
                            String str = "";
                            for (int i = 1;main.result[i] != -1; i++){
                                str += "P" + main.result[i];
                            }
                            JOptionPane.showMessageDialog(jp,"请求资源分配成功！\n存在此安全序列：" + str,"成功",JOptionPane.PLAIN_MESSAGE);
                        }
                        //每次分配后都要判断进程是否已经完成，已完成则释放资源
                        judge.end(index);
                    }else{
                        if(main.flag == 1) {
                            JOptionPane.showMessageDialog(jp,"请求资源分配失败！\n请求大于该进程还需要的资源量，请求不合法。","失败", JOptionPane.ERROR_MESSAGE);
                        } else if(main.flag == 2) {
                            JOptionPane.showMessageDialog(jp,"请求资源分配失败！\n请求大于当前系统剩余的资源量，请求不合法。","失败", JOptionPane.ERROR_MESSAGE);
                        } else if(main.flag == 4) {
                            JOptionPane.showMessageDialog(jp,"请求资源分配失败！\n根据安全性检查，本次分配不安全,即可能会发生死锁。","失败",JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    data[index][4] = String.valueOf(main.Allocation[index][0]);
                    data[index][5] = String.valueOf(main.Allocation[index][1]);
                    data[index][6] = String.valueOf(main.Allocation[index][2]);
                    data[index][7] = String.valueOf(main.Need[index][0]);
                    data[index][8] = String.valueOf(main.Need[index][1]);
                    data[index][9] = String.valueOf(main.Need[index][2]);
                    data[index][10] = String.valueOf(main.Finish[index]);
                    TableModel tm2 = new DefaultTableModel(data,colName2);
                    dataT.setModel(tm2);

                    for(int i=0;i<3;i++){
                        remaintData[0][i] = String.valueOf(main.Available[i]);
                    }
                    TableModel tm4 = new DefaultTableModel(remaintData,colName4);
                    remainT.setModel(tm4);

                    pidT.setText("");
                    aT.setText("");
                    bT.setText("");
                    cT.setText("");
                }
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

        return jp;
    }
}
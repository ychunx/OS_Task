package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import Main.*;
import Utils.*;

public class j_UI extends JFrame {

    public static Vector data = new Vector(); //存放作业信息
    public static Vector colName = new Vector();
    public static DefaultTableModel dtm = new DefaultTableModel();
    public static JTable table = new JTable();
    public static JLabel tTAT = new JLabel("");
    public static JLabel tWTAT = new JLabel("");

    public j_UI() {

        super("作业调度模拟（测试无误，重启解决一切问题）");

        int WIDTH = 720;
        int HEIGHT = 540;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((int)(screenSize.getWidth()-WIDTH)/2,(int)(screenSize.getHeight()-HEIGHT)/2,WIDTH,HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        init();
    }

    public void init() {

        JPanel P = new JPanel();
        P.setLayout(null);
        JScrollPane table = new JScrollPane(setTable());
        table.setBounds(3,150,700,350);

        P.add(setPage());
        P.add(table);

        this.add(P);
    }

    //设置页面
    public JPanel setPage() {

        JPanel jp = new JPanel();
        jp.setBounds(0,0,720,150);
        jp.setLayout(null);

        JLabel id = new JLabel("序号：");
        id.setBounds(10,30,40,30);
        jp.add(id);

        JTextField idT = new JTextField();
        idT.setBounds(45,30,60,30);
        jp.add(idT);

        JLabel name = new JLabel("作业名：");
        name.setBounds(115,30,50,30);
        jp.add(name);

        JTextField nameT = new JTextField();
        nameT.setBounds(165,30,60,30);
        jp.add(nameT);

        JLabel priority = new JLabel("优先级：");
        priority.setBounds(235,30,50,30);
        jp.add(priority);

        JTextField priorityT = new JTextField();
        priorityT.setBounds(285,30,60,30);
        jp.add(priorityT);

        JLabel workTime = new JLabel("服务时间：");
        workTime.setBounds(355,30,70,30);
        jp.add(workTime);

        JTextField workTimeT = new JTextField();
        workTimeT.setBounds(415,30,60,30);
        jp.add(workTimeT);

        JLabel arriveTime = new JLabel("到达时间：");
        arriveTime.setBounds(485,30,70,30);
        jp.add(arriveTime);

        JTextField arriveTimeT = new JTextField();
        arriveTimeT.setBounds(545,30,60,30);
        arriveTimeT.addKeyListener(new KeyAdapter() {
            @Override
            //enter 添加事件
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if(e.getKeyChar() == KeyEvent.VK_ENTER ) {
                    String id = idT.getText();
                    idT.setText("");
                    String name = nameT.getText();
                    nameT.setText("");
                    String priority = priorityT.getText();
                    priorityT.setText("");
                    String arriveTime = arriveTimeT.getText();
                    arriveTimeT.setText("");
                    String workTime = workTimeT.getText();
                    workTimeT.setText("");

                    //传递进程信息到main的执行程序
                    int idInt = Integer.parseInt(id);
                    int priorityInt = Integer.parseInt(priority);
                    double arriveTimeInt = Double.parseDouble(arriveTime);
                    double workTimeInt = Double.parseDouble(workTime);
                    main.jcbArr.add(new JCB(idInt, name, arriveTimeInt, workTimeInt, priorityInt));

                    //传递进程信息到表格显示
                    Vector aData = new Vector();//一行表格信息
                    aData.add(id);
                    aData.add(name);
                    aData.add(priority);
                    aData.add(workTime);
                    aData.add(arriveTime);
                    data.add(aData);
                    dtm.setDataVector(data, colName);
                    table.setModel(dtm);
                }
            }
        });
        jp.add(arriveTimeT);

        JButton add = new JButton("添加");
        add.setBounds(625,30,60,30);
        add.setBackground(new Color(202,204,209));
        add.setBorderPainted(false);
        add.setFocusPainted(false);
        add.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String id = idT.getText();
                idT.setText("");
                String name = nameT.getText();
                nameT.setText("");
                String priority = priorityT.getText();
                priorityT.setText("");
                String arriveTime = arriveTimeT.getText();
                arriveTimeT.setText("");
                String workTime = workTimeT.getText();
                workTimeT.setText("");

                //传递进程信息到main的执行程序
                int idInt = Integer.parseInt(id);
                int priorityInt = Integer.parseInt(priority);
                double arriveTimeInt = Double.parseDouble(arriveTime);
                double workTimeInt = Double.parseDouble(workTime);
                main.jcbArr.add(new JCB(idInt, name, arriveTimeInt, workTimeInt, priorityInt));

                //传递进程信息到表格显示
                Vector aData = new Vector();//一行表格信息
                aData.add(id);
                aData.add(name);
                aData.add(priority);
                aData.add(workTime);
                aData.add(arriveTime);
                data.add(aData);
                dtm.setDataVector(data, colName);
                table.setModel(dtm);
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
        jp.add(add);


        JLabel aTAT = new JLabel("平均周转时间：");
        aTAT.setBounds(10,90,90,30);
        jp.add(aTAT);

        tTAT.setBounds(100,90,50,30);
        jp.add(tTAT);

        JLabel aWTAT = new JLabel("平均带权周转时间：");
        aWTAT.setBounds(150,90,115,30);
        jp.add(aWTAT);

        tWTAT.setBounds(265,90,50,30);
        jp.add(tWTAT);

        JComboBox type = new JComboBox();
        type.addItem("先来先服务（FCFS）");
        type.addItem("优先权优先（PSA）");
        type.addItem("简单轮转法（RR）");
        type.setBounds(370,90,165,30);
        jp.add(type);

        JButton start = new JButton("执行");
        start.setBounds(555,90,60,30);
        start.setBackground(new Color(202,204,209));
        start.setBorderPainted(false);
        start.setFocusPainted(false);
        start.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                main.flag = (String)type.getSelectedItem();

                if(main.flag == "简单轮转法（RR）") {
                    String r = (String)JOptionPane.showInputDialog(jp,"请输入时间片：","时间片默认为0.5",JOptionPane.PLAIN_MESSAGE,null,null,"请输入double类数字");
                    double re = Double.parseDouble(r);
                    RR.T = re;
                }

                main.start();
                double aveTAT = 0;
                double aveWTAT = 0;
                for (int i=0;i<data.size();i++) {
                    aveTAT += main.rArr.get(i).TAT;
                    aveWTAT += main.rArr.get(i).WTAT;
                }
                aveTAT /= data.size();
                aveWTAT /= data.size();
                tTAT.setText(String.valueOf(aveTAT));
                tWTAT.setText(String.valueOf(aveWTAT));
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
        jp.add(start);

        JButton back = new JButton("撤销");
        back.setBounds(625,90,60,30);
        back.setBackground(new Color(202,204,209));
        back.setBorderPainted(false);
        back.setFocusPainted(false);
        back.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                main.revoke();
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
        jp.add(back);

        return jp;
    }

    //信息表格
    public JTable setTable() {

        colName.add("序号");
        colName.add("作业名");
        colName.add("优先级");
        colName.add("服务时间");
        colName.add("到达时间");
        colName.add("开始运行时间");
        colName.add("结束运行时间");
        colName.add("周转时间");
        colName.add("带权周转时间");

        //设置表格文字居中
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, tcr);

        dtm.setDataVector(data, colName);
        table.setModel(dtm);

        return table;
    }
}
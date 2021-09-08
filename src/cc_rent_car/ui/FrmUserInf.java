package cc_rent_car.ui;

import cc_rent_car.model.BeanUser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmUserInf extends JDialog implements ActionListener {
    private Object tableTitles[] = {"用户编号", "用户名", "性别", "密码", "手机号", "邮箱", "所在城市", "注册时间"};
    private Object tblData[][];
    DefaultTableModel tablmod = new DefaultTableModel();
    private JTable dataTable = new JTable(tablmod);

    private void reloadTable() {

        try {
            tblData = new Object[1][8];
            for (int i = 0; i < 8; i++) {
                tblData[0][i] = BeanUser.currentLoginUser.getCell(i);
            }
//			tblData[0][0]=BeanUser.currentLoginUser.getUser_id();
//			tblData[0][1]=BeanUser.currentLoginUser.getUser_name();
//			tblData[0][2]=BeanUser.currentLoginUser.getUser_sex();
//			tblData[0][3]=BeanUser.currentLoginUser.getUser_pwd();
//			tblData[0][4]=BeanUser.currentLoginUser.getUser_phone_number();
//			tblData[0][5]=BeanUser.currentLoginUser.getUser_email();
//			tblData[0][6]=BeanUser.currentLoginUser.getUser_city();
//			tblData[0][7]=BeanUser.currentLoginUser.getUser_register_time();

            tablmod.setDataVector(tblData, tableTitles);
            this.dataTable.validate();
            this.dataTable.repaint();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    public FrmUserInf(FrmMain frmMain, String string, boolean b) {
        // TODO Auto-generated constructor stub
        this.reloadTable();
        this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);

        // 屏幕居中显示
        this.setSize(800, 600);
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }

}

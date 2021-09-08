package cc_rent_car.ui;

import cc_rent_car.control.UserManager;
import cc_rent_car.model.BeanUser;
import cc_rent_car.util.BaseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FrmShoppingiInf extends JDialog implements ActionListener {
    Object tblUserTitle[] = BeanUser.tableTitles;
    Object tblUserData[][];
    DefaultTableModel tabUserModel = new DefaultTableModel();
    JTable dataTableUser = new JTable(tabUserModel);
    List<BeanUser> allUser = null;

    private void reloadUserTable() {//这是测试数据，需要用实际数替换
        try {
            allUser = UserManager.loadAllUser();
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        tblUserData = new Object[allUser.size()][BeanUser.tableTitles.length];
        for (int i = 0; i < allUser.size(); i++) {
            for (int j = 0; j < BeanUser.tableTitles.length; j++)
                tblUserData[i][j] = allUser.get(i).getCell(j);
        }
        tabUserModel.setDataVector(tblUserData, tblUserTitle);
        this.dataTableUser.validate();
        this.dataTableUser.repaint();
    }

    public FrmShoppingiInf(FrmMain frmMain, String string, boolean b) {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }

}

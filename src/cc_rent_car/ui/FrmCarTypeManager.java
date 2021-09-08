package cc_rent_car.ui;

import cc_rent_car.control.CarManager;
import cc_rent_car.model.BeanCarType;
import cc_rent_car.util.BaseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class FrmCarTypeManager extends JDialog implements ActionListener {


    private JPanel toolBar = new JPanel();
    private JButton btnAdd = new JButton("添加汽车类别");
    private JButton btnModify = new JButton("修改汽车类别信息");
    private JButton btnDelete = new JButton("删除汽车类别");
    private Object tblTitle[] = {"汽车类别ID", "汽车类别名称", "汽车类别描述"};
    private Object tblData[][];
    DefaultTableModel tablmod = new DefaultTableModel();
    private JTable carTypeTable = new JTable(tablmod);

    private void reloadTable() {
        try {
            List<BeanCarType> types = CarManager.loadAllCarType();
            tblData = new Object[types.size()][3];
            for (int i = 0; i < types.size(); i++) {
                tblData[i][0] = types.get(i).getCar_type_ID() + "";
                tblData[i][1] = types.get(i).getCar_type_name();
                tblData[i][2] = types.get(i).getCar_type_inf() + "";
            }
            tablmod.setDataVector(tblData, tblTitle);
            this.carTypeTable.validate();
            this.carTypeTable.repaint();
        } catch (BaseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public FrmCarTypeManager(FrmMain f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        toolBar.add(btnAdd);
        toolBar.add(btnModify);
        toolBar.add(this.btnDelete);
        this.getContentPane().add(toolBar, BorderLayout.NORTH);
        //提取现有数据
        this.reloadTable();
        this.getContentPane().add(new JScrollPane(this.carTypeTable), BorderLayout.CENTER);

        // 屏幕居中显示
        this.setSize(800, 600);
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();

        this.btnAdd.addActionListener(this);
        this.btnModify.addActionListener(this);
        this.btnDelete.addActionListener(this);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //System.exit(0);
            }
        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == this.btnAdd) {
            FrmAddCarType dlg = new FrmAddCarType(this, "添加汽车类别", true);
            dlg.setVisible(true);

            this.reloadTable();

        } else if (e.getSource() == this.btnModify) {
            int i = this.carTypeTable.getSelectedRow();
            if (i < 0) {
                JOptionPane.showMessageDialog(null, "请选择汽车类别", "提示", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int n = Integer.parseInt(this.tblData[i][0].toString());
            BeanCarType cartype = new BeanCarType();
            cartype.setCar_type_ID(n);
            cartype.setCar_type_name(this.tblData[i][1].toString());
            cartype.setCar_type_inf(this.tblData[i][2].toString());
            FrmModifyCarType dlg = new FrmModifyCarType(this, "修改汽车类别", true, cartype);
            dlg.setVisible(true);
            if (dlg.getCartype() != null) {//刷新表格
                this.reloadTable();
            }
        } else if (e.getSource() == this.btnDelete) {
            int i = this.carTypeTable.getSelectedRow();
            if (i < 0) {
                JOptionPane.showMessageDialog(null, "请选择汽车类别", "提示", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (JOptionPane.showConfirmDialog(this, "确定删除该汽车类别吗？", "确认", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                int n = Integer.parseInt(this.tblData[i][0].toString());
                try {
                    (new CarManager()).deleteCarType(n);
                    this.reloadTable();
                } catch (BaseException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                }

            }
        }
    }

}

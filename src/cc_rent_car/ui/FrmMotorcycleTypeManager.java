package cc_rent_car.ui;

import cc_rent_car.control.CarManager;
import cc_rent_car.model.BeanMotorcycleType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class FrmMotorcycleTypeManager extends JDialog implements ActionListener {
    private JPanel toolBar = new JPanel();
    private JButton btnAdd = new JButton("添加车型信息");
    private JButton btnModify = new JButton("修改车型信息");
    private JButton btnDelete = new JButton("删除车型信息");
    private Object tblTitle[] = BeanMotorcycleType.tableTitles;
    private Object tblData[][];
    DefaultTableModel tablmod = new DefaultTableModel();
    private JTable motorcycleTypeTable = new JTable(tablmod);

    private void reloadTable() {
        try {
            List<BeanMotorcycleType> types = CarManager.loadAllBeanMotorcycleType();
            tblData = new Object[types.size()][tblTitle.length];
            for (int i = 0; i < types.size(); i++) {
                for (int j = 0; j < tblTitle.length; j++) {
                    tblData[i][j] = types.get(i).getCell(j) + "";
                }
            }
            tablmod.setDataVector(tblData, tblTitle);
            this.motorcycleTypeTable.validate();
            this.motorcycleTypeTable.repaint();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public FrmMotorcycleTypeManager(FrmMain f, String s, boolean b) {
        // TODO Auto-generated constructor stub
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        toolBar.add(btnAdd);
        toolBar.add(btnModify);
        toolBar.add(this.btnDelete);
        this.getContentPane().add(toolBar, BorderLayout.NORTH);
        //提取现有数据
        this.reloadTable();
        this.getContentPane().add(new JScrollPane(this.motorcycleTypeTable), BorderLayout.CENTER);

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
            FrmAddMotorcycleTypeManager dlg = new FrmAddMotorcycleTypeManager(this, "添加车型", true);
            dlg.setVisible(true);

            this.reloadTable();

        } else if (e.getSource() == this.btnModify) {
            int i = this.motorcycleTypeTable.getSelectedRow();
            if (i < 0) {
                JOptionPane.showMessageDialog(null, "请选择车型", "提示", JOptionPane.ERROR_MESSAGE);
                return;
            }

            BeanMotorcycleType mtype = new BeanMotorcycleType();
            mtype.setType_ID(Integer.parseInt(this.tblData[i][0].toString()));
            mtype.setCar_type_ID(Integer.parseInt(this.tblData[i][1].toString()));
            mtype.setType_name(this.tblData[i][2].toString());
            mtype.setBrand(this.tblData[i][3].toString());
            mtype.setDisplacement(this.tblData[i][4].toString());
            mtype.setGear(this.tblData[i][5].toString());
            mtype.setSeats_count(this.tblData[i][6].toString());
            mtype.setPricee(Integer.parseInt(this.tblData[i][7].toString()));

            FrmModifyMotorcycleTypeManager dlg = new FrmModifyMotorcycleTypeManager(this, "修改车型", true, mtype);
            dlg.setVisible(true);
            if (dlg.getMotocycleType() != null) {//刷新表格
                this.reloadTable();
            }
        } else if (e.getSource() == this.btnDelete) {
            int i = this.motorcycleTypeTable.getSelectedRow();
            if (i < 0) {
                JOptionPane.showMessageDialog(null, "请选择车型", "提示", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (JOptionPane.showConfirmDialog(this, "确定删除该车型吗？", "确认", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                int n = Integer.parseInt(this.tblData[i][0].toString());
                try {
                    (new CarManager()).deleteMotorcycleType(n);
                    this.reloadTable();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                }

            }
        }
    }

}

package cc_rent_car.ui;

import cc_rent_car.control.CarManager;
import cc_rent_car.model.BeanCar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class FrmCarManager extends JDialog implements ActionListener {
    private JPanel toolBar = new JPanel();
    private JButton btnAdd = new JButton("添加");
    private JButton btnModify = new JButton("修改");
    private JButton btnScrap = new JButton("报废");
    String[] str1 = {"free", "renting", "underRepair", "scrapped"};
    private JComboBox cmbState = new JComboBox(str1);
    private JButton btnSearch = new JButton("查询");
    private Object tblTitle[] = {"车辆编号", "车牌号", "报废ID", "所属车型ID", "所属车型", "所在网点ID", "当前状态"};
    ;
    private Object tblData[][];
    List<BeanCar> cars = null;
    DefaultTableModel tablmod = new DefaultTableModel();
    private JTable carTable = new JTable(tablmod);

    private void reloadTable() {
        try {
            cars = (new CarManager()).searchCar(String.valueOf(this.cmbState.getSelectedItem()));
//			System.out.print(String.valueOf(this.cmbState.getSelectedItem()));
            tblData = new Object[cars.size()][7];
            for (int i = 0; i < cars.size(); i++) {
                tblData[i][0] = cars.get(i).getCar_ID();
                tblData[i][1] = cars.get(i).getLicense_plate();
                tblData[i][2] = cars.get(i).getScrap_ID() + "";
                tblData[i][3] = cars.get(i).getType_ID();
                tblData[i][4] = cars.get(i).getType_name();
                tblData[i][5] = cars.get(i).getBranch_id();
                tblData[i][6] = cars.get(i).getState() + "";
            }
            tablmod.setDataVector(tblData, tblTitle);
            this.carTable.validate();
            this.carTable.repaint();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public FrmCarManager(FrmMain f, String s, boolean b) {
        // TODO Auto-generated constructor stub
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        toolBar.add(btnAdd);
        toolBar.add(btnModify);
        toolBar.add(btnScrap);
        toolBar.add(btnSearch);
        toolBar.add(cmbState);


        this.getContentPane().add(toolBar, BorderLayout.NORTH);
        //提取现有数据
//		this.reloadTable();
        this.getContentPane().add(new JScrollPane(this.carTable), BorderLayout.CENTER);

        // 屏幕居中显示
        this.setSize(800, 600);
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();

        this.btnAdd.addActionListener(this);
        this.btnModify.addActionListener(this);
        this.btnScrap.addActionListener(this);
        this.btnSearch.addActionListener(this);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == this.btnSearch) {
            this.reloadTable();
        } else if (e.getSource() == this.btnAdd) {
            FrmAddCar dlg = new FrmAddCar(this, "添加汽车", true);
            dlg.setVisible(true);
            if (dlg.getCar() != null) {//刷新表格
                this.reloadTable();
            }
        } else if (e.getSource() == this.btnModify) {
            int i = this.carTable.getSelectedRow();
            if (i < 0) {
                JOptionPane.showMessageDialog(null, "请选择汽车", "提示", JOptionPane.ERROR_MESSAGE);
                return;
            }
            BeanCar car = this.cars.get(i);

            FrmModifyCar dlg = new FrmModifyCar(this, "修改汽车", true, car);
            dlg.setVisible(true);
            if (dlg.getCar() != null) {//刷新表格
                this.reloadTable();
            }
        } else if (e.getSource() == this.btnScrap) {
            int i = this.carTable.getSelectedRow();
            if (i < 0) {
                JOptionPane.showMessageDialog(null, "请选择汽车", "提示", JOptionPane.ERROR_MESSAGE);
                return;
            }
            BeanCar car = this.cars.get(i);
            if (!"free".equals(car.getState())) {
                JOptionPane.showMessageDialog(null, "当前汽车非空闲状态", "提示", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (JOptionPane.showConfirmDialog(this, "确定报废" + car.getCar_ID() + "号汽车吗？", "确认", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                car.setState("scrapped");
                try {
                    (new CarManager()).ScrapCar(car);
                    this.reloadTable();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getSource() == this.btnSearch) {
            this.reloadTable();
        }
    }

}

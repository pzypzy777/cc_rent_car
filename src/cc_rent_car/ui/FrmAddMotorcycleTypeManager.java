package cc_rent_car.ui;

import cc_rent_car.control.CarManager;
import cc_rent_car.model.BeanCarType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmAddMotorcycleTypeManager extends JDialog implements ActionListener {
    private BeanCarType cartype = null;
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JButton btnOk = new JButton("确定");
    private JButton btnCancel = new JButton("取消");
    private JLabel labelName = new JLabel("车型名称：");
    private JTextField edtName = new JTextField(20);
    private JLabel labelTypeId = new JLabel("汽车类别编号：");
    private JTextField edtTypeId = new JTextField(20);
    private JLabel labelBrand = new JLabel("品牌：");
    private JTextField edtBrand = new JTextField(20);
    private JLabel labelDisplacement = new JLabel("排量：");
    private JTextField edtDisplacement = new JTextField(20);
    private JLabel labelGear = new JLabel("排挡：");
    private JTextField edtGear = new JTextField(20);
    private JLabel labelSeats = new JLabel("座位数：");
    private JTextField edtSeats = new JTextField(20);
    private JLabel labelPrice = new JLabel("价格：");
    private JTextField edtPrice = new JTextField(20);

    public FrmAddMotorcycleTypeManager(FrmMotorcycleTypeManager f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.add(labelName);
        workPane.add(edtName);
        workPane.add(labelTypeId);
        workPane.add(edtTypeId);
        workPane.add(labelBrand);
        workPane.add(edtBrand);
        workPane.add(labelDisplacement);
        workPane.add(edtDisplacement);
        workPane.add(labelGear);
        workPane.add(edtGear);
        workPane.add(labelSeats);
        workPane.add(edtSeats);
        workPane.add(labelPrice);
        workPane.add(edtPrice);
        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(280, 500);
        // 屏幕居中显示
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();
        this.btnOk.addActionListener(this);
        this.btnCancel.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == this.btnCancel) {
            this.setVisible(false);
            return;
        } else if (e.getSource() == this.btnOk) {
            String name = this.edtName.getText();
            Integer typeId = Integer.parseInt(this.edtTypeId.getText());
            String brand = this.edtBrand.getText();
            String displacement = this.edtDisplacement.getText();
            String gear = this.edtGear.getText();
            String seats = this.edtSeats.getText();
            Integer price = Integer.parseInt(this.edtPrice.getText());
            try {
                CarManager.addMotorcycleType(name, typeId, name, brand, displacement, gear, seats, price);
//				this.reloadtable;
                this.setVisible(false);
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }


}

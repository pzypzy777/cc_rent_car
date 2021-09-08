package cc_rent_car.ui;

import cc_rent_car.control.BranchManager;
import cc_rent_car.model.BeanBranch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmAddBranch extends JDialog implements ActionListener {
    private BeanBranch branch = null;
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JButton btnOk = new JButton("确定");
    private JButton btnCancel = new JButton("取消");
    private JLabel labelLocation = new JLabel("网点位置：");
    private JLabel labelName = new JLabel("网点名称：");
    private JLabel labelCity = new JLabel("所在城市：");
    private JLabel labelAddress = new JLabel("地址：");
    private JLabel labelPhone = new JLabel("网点电话：");
    private JTextField edtLocation = new JTextField(20);
    private JTextField edtName = new JTextField(20);
    private JTextField edtCity = new JTextField(20);
    private JTextField edtAddress = new JTextField(20);
    private JTextField edtPhone = new JTextField(20);

    public FrmAddBranch(FrmBranchManager f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.add(labelLocation);
        workPane.add(edtLocation);
        workPane.add(labelName);
        workPane.add(edtName);
        workPane.add(labelCity);
        workPane.add(edtCity);
        workPane.add(labelAddress);
        workPane.add(edtAddress);
        workPane.add(labelPhone);
        workPane.add(edtPhone);
        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(270, 390);
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

            String location = (this.edtLocation.getText());
            String name = (this.edtName.getText());
            String city = (this.edtCity.getText());
            String site = (this.edtAddress.getText());
            String number = (this.edtPhone.getText());

            try {
                BranchManager.addBranch(location, name, city, site, number);
//				this.reloadtable;
                this.setVisible(false);
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }
}
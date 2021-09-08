package cc_rent_car.ui;

import cc_rent_car.control.BranchManager;
import cc_rent_car.model.BeanBranch;
import cc_rent_car.util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmModifyBranch extends JDialog implements ActionListener {
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

    public FrmModifyBranch(JDialog f, String s, boolean b, BeanBranch bc) {
        super(f, s, b);
        this.branch = bc;
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.add(labelLocation);
        this.edtLocation.setText(bc.getBranch_location());
        workPane.add(edtLocation);
        workPane.add(labelName);
        this.edtName.setText(bc.getBranch_name());
        workPane.add(edtName);
        workPane.add(labelCity);
        this.edtCity.setText(bc.getBranch_city());
        workPane.add(edtCity);
        workPane.add(labelAddress);
        this.edtAddress.setText(bc.getBranch_site());
        workPane.add(edtAddress);
        workPane.add(labelPhone);
        this.edtPhone.setText(bc.getBranch_phone_number());
        workPane.add(edtPhone);
        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(340, 390);
        // 屏幕居中显示
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();
        this.btnOk.addActionListener(this);
        this.btnCancel.addActionListener(this);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                FrmModifyBranch.this.branch = null;
            }
        });
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnCancel) {
            this.setVisible(false);
            this.branch = null;
            return;
        } else if (e.getSource() == this.btnOk) {
            this.branch.setBranch_location(this.edtLocation.getText());
            this.branch.setBranch_name(this.edtName.getText());
            this.branch.setBranch_city(this.edtCity.getText());
            this.branch.setBranch_site(this.edtAddress.getText());
            this.branch.setBranch_phone_number(this.labelPhone.getText());


            try {
                (new BranchManager()).modifyBranch(this.branch);
                this.setVisible(false);
            } catch (BaseException e1) {
                this.branch = null;
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        }

    }


    public BeanBranch getBranch() {
        return branch;
    }
}





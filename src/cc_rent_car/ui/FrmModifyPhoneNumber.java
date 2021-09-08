package cc_rent_car.ui;

import cc_rent_car.control.UserManager;
import cc_rent_car.model.BeanUser;
import cc_rent_car.util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmModifyPhoneNumber extends JDialog implements ActionListener {

    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JButton btnOk = new JButton("确定");
    private JButton btnCancel = new JButton("取消");

    private JLabel labelPhoneNumberOld = new JLabel("原手机号：");
    private JLabel labelPhoneNumber = new JLabel("新手机号：");
    private JLabel labelPhoneNumber1 = new JLabel("确认新手机号：");
    private JTextField edtPhoneNumberOld = new JTextField(20);
    private JTextField edtPhoneNumber = new JTextField(20);
    private JTextField edtPhoneNumber1 = new JTextField(20);

    public FrmModifyPhoneNumber(Frame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(this.btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.add(labelPhoneNumberOld);
        workPane.add(edtPhoneNumberOld);
        workPane.add(labelPhoneNumber);
        workPane.add(edtPhoneNumber);
        workPane.add(labelPhoneNumber1);
        workPane.add(edtPhoneNumber1);
        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(300, 300);
        this.btnCancel.addActionListener(this);
        this.btnOk.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == this.btnCancel)
            this.setVisible(false);
        else if (e.getSource() == this.btnOk) {
            try {
                if (BeanUser.currentLoginUser == null) System.out.println("kon");
                UserManager.changePhoneNumber(BeanUser.currentLoginUser, edtPhoneNumberOld.getText(), edtPhoneNumber.getText(), edtPhoneNumber1.getText());
                this.setVisible(false);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }


    }

}

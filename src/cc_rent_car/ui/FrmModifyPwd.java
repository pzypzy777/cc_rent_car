package cc_rent_car.ui;

import cc_rent_car.control.UserManager;
import cc_rent_car.model.BeanUser;
import cc_rent_car.util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FrmModifyPwd extends JDialog implements ActionListener {
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JButton btnOk = new JButton("确定");
    private JButton btnCancel = new JButton("取消");

    private JLabel labelPwdOld = new JLabel("原密码：");
    private JLabel labelPwd = new JLabel("新密码：");
    private JLabel labelPwd2 = new JLabel("新密码：");
    private JPasswordField edtPwdOld = new JPasswordField(20);
    private JPasswordField edtPwd = new JPasswordField(20);
    private JPasswordField edtPwd2 = new JPasswordField(20);

    public FrmModifyPwd(Frame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(this.btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.add(labelPwdOld);
        workPane.add(edtPwdOld);
        workPane.add(labelPwd);
        workPane.add(edtPwd);
        workPane.add(labelPwd2);
        workPane.add(edtPwd2);
        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(300, 180);
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
                UserManager.changePwd(BeanUser.currentLoginUser, new String(edtPwdOld.getPassword()), new String(edtPwd.getPassword()), new String(edtPwd2.getPassword()));
                this.setVisible(false);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }


    }


}

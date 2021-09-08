package cc_rent_car.ui;

import cc_rent_car.control.UserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmRegister extends JDialog implements ActionListener {
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JButton btnOk = new JButton("注册");
    private JButton btnCancel = new JButton("取消");

    private JLabel labelUser = new JLabel("账号：(请小于10000)");
    private JLabel labelPwd = new JLabel("密码：");
    private JLabel labelPwd2 = new JLabel("确认密码：");
    private JLabel labelName = new JLabel("姓名：");
    private JLabel labelSex = new JLabel("性别：");
    private JLabel labelPhoneNumber = new JLabel("手机号：");
    private JLabel labelEmail = new JLabel("邮箱：");
    private JLabel labelCity = new JLabel("城市：");
    private JTextField edtUserId = new JTextField(20);
    private JPasswordField edtPwd = new JPasswordField(20);
    private JPasswordField edtPwd2 = new JPasswordField(20);
    private JTextField edtName = new JTextField(20);
    private JTextField edtSex = new JTextField(20);
    private JTextField edtPhoneNumber = new JTextField(20);
    private JTextField edtEmail = new JTextField(20);
    private JTextField edtCity = new JTextField(20);

    public FrmRegister(Dialog f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(this.btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.add(labelUser);
        workPane.add(edtUserId);
        workPane.add(labelPwd);
        workPane.add(edtPwd);
        workPane.add(labelPwd2);
        workPane.add(edtPwd2);
        workPane.add(labelSex);
        workPane.add(edtSex);
        workPane.add(labelName);
        workPane.add(edtName);
        workPane.add(labelPhoneNumber);
        workPane.add(edtPhoneNumber);
        workPane.add(labelEmail);
        workPane.add(edtEmail);
        workPane.add(labelCity);
        workPane.add(edtCity);
        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(280, 500);
        this.btnCancel.addActionListener(this);
        this.btnOk.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnCancel)
            this.setVisible(false);
        else if (e.getSource() == this.btnOk) {
            Integer userid = Integer.parseInt(this.edtUserId.getText());
            String pwd1 = new String(this.edtPwd.getPassword());
            String pwd2 = new String(this.edtPwd2.getPassword());
            String name = new String(this.edtName.getText());
            String sex = new String(this.edtSex.getText());
            String phone = new String(this.edtPhoneNumber.getText());
            String email = new String(this.edtEmail.getText());
            String city = new String(this.edtCity.getText());
            try {
                UserManager user = new UserManager();
                user.registerUser(userid, pwd1, pwd2, name, sex, phone, email, city);
                this.setVisible(false);
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

        }


    }


}

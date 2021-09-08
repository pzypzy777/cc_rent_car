package cc_rent_car.ui;

import cc_rent_car.control.UserManager;
import cc_rent_car.model.BeanUser;
import cc_rent_car.util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmModifyEmail extends JDialog implements ActionListener {

    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JButton btnOk = new JButton("确定");
    private JButton btnCancel = new JButton("取消");

    private JLabel labelEmailOld = new JLabel("原邮箱：");
    private JLabel labelEmail = new JLabel("新邮箱：");
    private JLabel labelEmail1 = new JLabel("确认新邮箱：");
    private JTextField edtEmailOld = new JTextField(20);
    private JTextField edtEmail = new JTextField(20);
    private JTextField edtEmail1 = new JTextField(20);

    public FrmModifyEmail(Frame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(this.btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.add(labelEmailOld);
        workPane.add(edtEmailOld);
        workPane.add(labelEmail);
        workPane.add(edtEmail);
        workPane.add(labelEmail1);
        workPane.add(edtEmail1);
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
                UserManager.changeEmail(BeanUser.currentLoginUser, new String(edtEmailOld.getText()), new String(edtEmail.getText()), new String(edtEmail1.getText()));
                this.setVisible(false);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }


    }


}

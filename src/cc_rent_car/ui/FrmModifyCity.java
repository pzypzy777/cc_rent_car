package cc_rent_car.ui;

import cc_rent_car.control.UserManager;
import cc_rent_car.model.BeanUser;
import cc_rent_car.util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmModifyCity extends JDialog implements ActionListener {
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JButton btnOk = new JButton("确定");
    private JButton btnCancel = new JButton("取消");

    private JLabel labelCityOld = new JLabel("原城市：");
    private JLabel labelCity = new JLabel("新城市：");
    private JTextField edtCityOld = new JTextField(20);
    private JTextField edtCity = new JTextField(20);

    public FrmModifyCity(Frame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(this.btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.add(labelCityOld);
        workPane.add(edtCityOld);
        workPane.add(labelCity);
        workPane.add(edtCity);
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
                UserManager.changeCity(BeanUser.currentLoginUser, new String(edtCityOld.getText()), new String(edtCity.getText()));
                this.setVisible(false);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }


    }


}

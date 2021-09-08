package cc_rent_car.ui;

import cc_rent_car.control.StaffManager;
import cc_rent_car.control.UserManager;
import cc_rent_car.model.BeanStaff;
import cc_rent_car.model.BeanUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class FrmLogin extends JDialog implements ActionListener {
    public static boolean isStaff = true;
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JButton btnRegister = new JButton("注册");
    private JButton btnLogin = new JButton("登陆");
    private JButton btnCancel = new JButton("退出");
    private JLabel labelUser = new JLabel("账号：");
    private JLabel labelPwd = new JLabel("密码：");
    private JTextField edtUserId = new JTextField(20);
    private JPasswordField edtPwd = new JPasswordField(20);

    public FrmLogin(Frame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(btnRegister);
        toolBar.add(btnLogin);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.add(labelUser);
        workPane.add(edtUserId);
        workPane.add(labelPwd);
        workPane.add(edtPwd);
        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(600, 140);
        // 屏幕居中显示
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();

        btnRegister.addActionListener(this);
        btnLogin.addActionListener(this);
        btnCancel.addActionListener(this);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnLogin) {
            UserManager u = new UserManager();
            StaffManager s = new StaffManager();
            Integer id = Integer.parseInt(this.edtUserId.getText());
            String pwd = new String(this.edtPwd.getPassword());
            try {
                if (id >= 10000) {
                    BeanStaff.currentLoginStaff = StaffManager.login(id, pwd);
                    BeanStaff staff = s.loadStaff(id);
                    if (pwd.equals(staff.getPwd())) {
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "密码错误", "错误提示", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    isStaff = false;
                    BeanUser.currentLoginUser = UserManager.login(id, pwd);
                    BeanUser user = u.loadUser(id);
                    if (pwd.equals(user.getUser_pwd())) {
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "密码错误", "错误提示", JOptionPane.ERROR_MESSAGE);
                    }
                }

            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误提示", JOptionPane.ERROR_MESSAGE);
            }


        } else if (e.getSource() == this.btnCancel) {
            System.exit(0);
        } else if (e.getSource() == this.btnRegister) {
            FrmRegister dlg = new FrmRegister(this, "注册", true);
            dlg.setVisible(true);
        }
    }

}

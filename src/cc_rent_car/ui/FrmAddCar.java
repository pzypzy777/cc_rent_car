package cc_rent_car.ui;

import cc_rent_car.control.CarManager;
import cc_rent_car.model.BeanCar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmAddCar extends JDialog implements ActionListener {
    private BeanCar carr = null;

    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JButton btnOk = new JButton("确定");
    private JButton btnCancel = new JButton("取消");
    private JLabel labelLP = new JLabel("牌照号：");
    private JTextField edtLP = new JTextField(20);
    private JLabel labelTypeId = new JLabel("车型编号：");
    private JTextField edtTypeId = new JTextField(20);
    private JLabel labelBranchId = new JLabel("所属网点：");
    private JTextField edtBranchId = new JTextField(20);

    public FrmAddCar(FrmCarManager f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.add(labelLP);
        workPane.add(edtLP);
        workPane.add(labelTypeId);
        workPane.add(edtTypeId);
        workPane.add(labelBranchId);
        workPane.add(edtBranchId);

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

    public BeanCar getCar() {
        return carr;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
// TODO Auto-generated method stub
        if (e.getSource() == this.btnCancel) {
            this.setVisible(false);
            return;
        } else if (e.getSource() == this.btnOk) {
            String licensePlate = this.edtLP.getText();
            Integer typeId = Integer.parseInt(this.edtTypeId.getText());
            Integer branchId = Integer.parseInt(this.edtBranchId.getText());

            try {
                CarManager.addCar(licensePlate, typeId, branchId);
//				this.reloadtable;
                this.setVisible(false);
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }
}

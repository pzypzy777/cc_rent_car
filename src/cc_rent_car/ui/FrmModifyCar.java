package cc_rent_car.ui;

import cc_rent_car.control.CarManager;
import cc_rent_car.model.BeanCar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmModifyCar extends JDialog implements ActionListener {
    private BeanCar carr = null;

    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JButton btnOk = new JButton("确定");
    private JButton btnCancel = new JButton("取消");

    private JLabel labelCarid = new JLabel("汽车编号：");
    private JLabel labelscrapid = new JLabel("报废ID：");
    private JLabel labeltypeid = new JLabel("车型ID：");
    private JLabel labelBranchid = new JLabel("网点ID：");
    private JLabel labelstate = new JLabel("汽车状态：");


    private JTextField edtCarid = new JTextField(20);
    private JTextField edtscrapid = new JTextField(20);
    private JTextField edttypeid = new JTextField(20);
    private JTextField edtBranchid = new JTextField(20);
    String[] str1 = {"free", "renting", "underRepair", "scrapped"};
    private JComboBox jmbState = new JComboBox(str1);

    public FrmModifyCar(FrmCarManager f, String s, boolean b, BeanCar car) {
        super(f, s, b);
        this.carr = car;
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);

        workPane.add(labelCarid);
        this.edtCarid.setText(car.getCar_ID().toString());
        workPane.add(edtCarid);

        workPane.add(labelscrapid);
        this.edtscrapid.setText(car.getScrap_ID().toString());
        workPane.add(edtscrapid);

        workPane.add(labeltypeid);
        this.edttypeid.setText(car.getType_ID().toString());
        workPane.add(edttypeid);

        workPane.add(labelBranchid);
        this.edtBranchid.setText(car.getBranch_id().toString());
        workPane.add(edtBranchid);

        workPane.add(labelstate);
        this.jmbState.setSelectedItem(car.getState());
        workPane.add(jmbState);

        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(250, 400);
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
                FrmModifyCar.this.carr = null;
            }
        });
    }

    public BeanCar getCar() {
        return carr;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnCancel) {
            this.setVisible(false);
            this.carr = null;
            return;
        } else if (e.getSource() == this.btnOk) {

//            Integer carid = Integer.parseInt(this.edtCarid.getText());
//            Integer scrapid = Integer.parseInt(this.edtscrapid.getText());
//            Integer typeid = Integer.parseInt(this.edttypeid.getText());
//            Integer branchid = Integer.parseInt(this.edtBranchid.getText());
//            String sstt = this.edtstate.getText();


            this.carr.setCar_ID(Integer.parseInt(this.edtCarid.getText()));
            this.carr.setScrap_ID(Integer.parseInt(this.edtscrapid.getText()));
            this.carr.setType_ID(Integer.parseInt(this.edttypeid.getText()));
            this.carr.setBranch_id(Integer.parseInt(this.edtBranchid.getText()));
            this.carr.setState((String) this.jmbState.getSelectedItem());


            try {
                (new CarManager()).modifyCar(this.carr);

                this.setVisible(false);
            } catch (Exception e1) {
                this.carr = null;
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

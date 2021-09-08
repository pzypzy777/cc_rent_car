package cc_rent_car.ui;

import cc_rent_car.control.CarManager;
import cc_rent_car.model.BeanMotorcycleType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmModifyMotorcycleTypeManager extends JDialog implements ActionListener {
    private BeanMotorcycleType motorcycletype = null;

    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JButton btnOk = new JButton("确定");
    private JButton btnCancel = new JButton("取消");

    private JLabel labelCarTypeID = new JLabel("汽车类别ID：");
    private JLabel labelName = new JLabel("车型名称：");
    private JLabel labelBrand = new JLabel("品牌：");
    private JLabel labeldp = new JLabel("排量：");
    private JLabel labelgear = new JLabel("排挡：");
    private JLabel labelsc = new JLabel("座位数：");
    private JLabel labelp = new JLabel("价格：");

    private JTextField edtCarTypeID = new JTextField(20);
    private JTextField edtName = new JTextField(20);
    private JTextField edtBrand = new JTextField(20);
    private JTextField edtdp = new JTextField(20);
    private JTextField edtgear = new JTextField(20);
    private JTextField edtsc = new JTextField(20);
    private JTextField edtp = new JTextField(20);

    public FrmModifyMotorcycleTypeManager(FrmMotorcycleTypeManager f, String s, boolean b,
                                          BeanMotorcycleType mt) {
        // TODO Auto-generated constructor stub
        super(f, s, b);
        this.motorcycletype = mt;
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);

        workPane.add(labelCarTypeID);
        this.edtCarTypeID.setText(mt.getCar_type_ID().toString());
        workPane.add(edtCarTypeID);

        workPane.add(labelName);
        this.edtName.setText(mt.getType_name() + "");
        workPane.add(edtName);

        workPane.add(labelBrand);
        this.edtBrand.setText(mt.getBrand() + "");
        workPane.add(edtBrand);

        workPane.add(labeldp);
        this.edtdp.setText(mt.getDisplacement() + "");
        workPane.add(edtdp);

        workPane.add(labelgear);
        this.edtgear.setText(mt.getGear() + "");
        workPane.add(edtgear);

        workPane.add(labelsc);
        this.edtsc.setText(mt.getSeats_count() + "");
        workPane.add(edtsc);

        workPane.add(labelp);
        this.edtp.setText(mt.getPricee() + "");
        workPane.add(edtp);

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
                FrmModifyMotorcycleTypeManager.this.motorcycletype = null;
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == this.btnCancel) {
            this.setVisible(false);
            this.motorcycletype = null;
            return;
        } else if (e.getSource() == this.btnOk) {


            String name = this.edtName.getText();
            Integer typeId = Integer.parseInt(this.edtCarTypeID.getText());
            String brand = this.edtBrand.getText();
            String displacement = this.edtdp.getText();
            String gear = this.edtgear.getText();
            String seats = this.edtsc.getText();
            Integer price = Integer.parseInt(this.edtp.getText());

            this.motorcycletype.setBrand(brand);
            this.motorcycletype.setCar_type_ID(typeId);
            this.motorcycletype.setType_name(name);
            this.motorcycletype.setDisplacement(displacement);
            this.motorcycletype.setGear(gear);
            this.motorcycletype.setSeats_count(seats);
            this.motorcycletype.setPricee(price);


            try {
                (new CarManager()).modifyMotorcycleType(this.motorcycletype);

                this.setVisible(false);
            } catch (Exception e1) {
                this.motorcycletype = null;
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public BeanMotorcycleType getMotocycleType() {
        // TODO Auto-generated method stub
        return motorcycletype;
    }

}

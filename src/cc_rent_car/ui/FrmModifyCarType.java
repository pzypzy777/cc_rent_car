package cc_rent_car.ui;

import cc_rent_car.control.CarManager;
import cc_rent_car.model.BeanCarType;
import cc_rent_car.util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmModifyCarType extends JDialog implements ActionListener {
	private BeanCarType cartype = null;

	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("确定");
	private JButton btnCancel = new JButton("取消");
	private JLabel labelName = new JLabel("汽车类别名称：");
	private JLabel labelInf = new JLabel("汽车类别描述：");

	private JTextField edtName = new JTextField(20);
	private JTextField edtInf = new JTextField(20);

	public FrmModifyCarType(JDialog f, String s, boolean b, BeanCarType rt) {
		super(f, s, b);
		this.cartype = rt;
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelName);
		this.edtName.setText(rt.getCar_type_name());
		workPane.add(edtName);
		workPane.add(labelInf);
		this.edtInf.setText(rt.getCar_type_inf() + "");
		workPane.add(edtInf);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(360, 140);
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
				FrmModifyCarType.this.cartype = null;
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnCancel) {
			this.setVisible(false);
			this.cartype = null;
			return;
		} else if (e.getSource() == this.btnOk) {
			String name = this.edtName.getText();
			String inf = this.edtInf.getText();

			this.cartype.setCar_type_name(name);
			this.cartype.setCar_type_inf(inf);

			try {
				(new CarManager()).modifyCarType(this.cartype);
				this.setVisible(false);
			} catch (BaseException e1) {
				this.cartype = null;
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	public BeanCarType getCartype() {
		return cartype;
	}
}

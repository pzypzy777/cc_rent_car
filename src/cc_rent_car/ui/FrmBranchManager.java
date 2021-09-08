package cc_rent_car.ui;

import cc_rent_car.control.BranchManager;
import cc_rent_car.model.BeanBranch;
import cc_rent_car.util.BaseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class FrmBranchManager extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JButton btnAdd = new JButton("添加网点");
	private JButton btnModify = new JButton("修改网点信息");
	private JButton btnDelete = new JButton("删除网点");
	private Object tblTitle[] = BeanBranch.tableTitles;
	private Object tblData[][];
	DefaultTableModel tablmod = new DefaultTableModel();
	private JTable branchTable = new JTable(tablmod);

	private void reloadTable() {
		try {
			List<BeanBranch> types = BranchManager.loadAllBranch();
			tblData = new Object[types.size()][6];
			for (int i = 0; i < types.size(); i++) {
				for (int j = 0; j < 6; j++) {
					tblData[i][j] = types.get(i).getCell(j);
				}
			}
			tablmod.setDataVector(tblData, tblTitle);
			this.branchTable.validate();
			this.branchTable.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public FrmBranchManager(FrmMain f, String s, boolean b) {
		// TODO Auto-generated constructor stub
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		toolBar.add(btnModify);
		toolBar.add(btnDelete);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		//提取现有数据
		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.branchTable), BorderLayout.CENTER);

		// 屏幕居中显示
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		this.btnAdd.addActionListener(this);
		this.btnModify.addActionListener(this);
		this.btnDelete.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.btnAdd) {
			FrmAddBranch dlg = new FrmAddBranch(this, "添加网点", true);
			dlg.setVisible(true);

			this.reloadTable();

		} else if (e.getSource() == this.btnModify) {
			int i = this.branchTable.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "请选择网点", "提示", JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanBranch branch = new BeanBranch();
			int n = Integer.parseInt(this.tblData[i][0].toString());
			branch.setBranch_id(n);
			branch.setBranch_location(this.tblData[i][1].toString());
			branch.setBranch_name(this.tblData[i][2].toString());
			branch.setBranch_city(this.tblData[i][3].toString());
			branch.setBranch_site(this.tblData[i][4].toString());
			branch.setBranch_phone_number(this.tblData[i][5].toString());
			FrmModifyBranch dlg = new FrmModifyBranch(this, "修改网点信息", true, branch);
			dlg.setVisible(true);
			if (dlg.getBranch() != null) {//刷新表格
				this.reloadTable();
			}
		} else if (e.getSource() == this.btnDelete) {
			int i = this.branchTable.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "请选择网点", "提示", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (JOptionPane.showConfirmDialog(this, "确定删除该网点吗？", "确认", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				int n = Integer.parseInt(this.tblData[i][0].toString());
				try {
					(new BranchManager()).deleteBranch(n);
					this.reloadTable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
				}

			}
		}
	}

}

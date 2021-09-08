package cc_rent_car.ui;

import cc_rent_car.model.BeanStaff;
import cc_rent_car.model.BeanUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmMain extends JFrame implements ActionListener {
    private JMenuBar menubar = new JMenuBar();
    //首页
    private JMenu menu_SysManager = new JMenu("系统管理");
    private JMenu menu_RentSys = new JMenu("租车系统");
    private JMenu menu_LimitTime = new JMenu("限时优惠");
    private JMenu menu_Coupon = new JMenu("优惠券");
    private JMenu menu_Scrapcar = new JMenu("报废车辆(员工操作)");
    private JMenu menu_Order = new JMenu("订单查询");
    private JMenu menu_UserinfManager = new JMenu("个人信息管理");
    //系统管理界面
    private JMenuItem menuItem_CarTypeManager = new JMenuItem("汽车类别管理");
    private JMenuItem menuItem_MotorcycleTypeManager = new JMenuItem("车型管理");
    private JMenuItem menuItem_CarManager = new JMenuItem("车辆管理");
    private JMenuItem menuItem_BranchManager = new JMenuItem("网点管理");
    private JMenuItem menuItem_UserShoppinginfManager = new JMenuItem("查询用户消费情况");

    //租车界面
    private JMenuItem menuItem_Rent = new JMenuItem("租车");
    private JMenuItem menuItem_Return = new JMenuItem("还车");
    //限时优惠界面
    private JMenuItem menuItem_QuerylimitTime = new JMenuItem("查询限时优惠信息");
    private JMenuItem menuItem_AddlimitTime = new JMenuItem("添加限时优惠信息");
    //优惠券界面
    private JMenuItem menuItem_QueryCoupon = new JMenuItem("查询优惠券信息");
    private JMenuItem menuItem_GetCoupon = new JMenuItem("领取优惠券");
    private JMenuItem menuItem_AddCoupon = new JMenuItem("添加优惠券(员工操作)");

    //订单查询界面
    private JMenuItem menu_ProgressingOrder = new JMenuItem("进行中订单");
    private JMenuItem menu_FinishedOrder = new JMenuItem("已完成订单");
    //报废车辆界面

    //个人信息管理界面
    private JMenuItem menuItem_QueryUserinf = new JMenuItem("查询用户个人信息");
    private JMenuItem menuItem_ModifyPhonenumber = new JMenuItem("修改手机号");
    private JMenuItem menuItem_ModifyEmail = new JMenuItem("修改邮箱");
    private JMenuItem menuItem_ModifyPwd = new JMenuItem("修改密码");
    private JMenuItem menuItem_ModifyCity = new JMenuItem("修改所在城市");
    private FrmLogin dlgLogin = null;
    private JPanel statusBar = new JPanel();


    public FrmMain() {
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setTitle("CC租车系统");
        dlgLogin = new FrmLogin(this, "登陆", true);
        dlgLogin.setVisible(true);
        //菜单
        if (BeanStaff.currentLoginStaff != null) {
            if (FrmLogin.isStaff = true && BeanStaff.currentLoginStaff.getBranch_id() == 0) {
                menu_SysManager.add(menuItem_CarTypeManager);
                menuItem_CarTypeManager.addActionListener(this);
                menu_SysManager.add(menuItem_MotorcycleTypeManager);
                menuItem_MotorcycleTypeManager.addActionListener(this);
                menu_SysManager.add(menuItem_CarManager);
                menuItem_CarManager.addActionListener(this);

                menu_SysManager.add(menuItem_BranchManager);
                menuItem_BranchManager.addActionListener(this);
                menu_SysManager.add(menuItem_UserShoppinginfManager);
                menuItem_UserShoppinginfManager.addActionListener(this);
                menubar.add(menu_SysManager);
            }
        }
        menu_RentSys.add(this.menuItem_Rent);
        menuItem_Rent.addActionListener(this);
        menu_RentSys.add(this.menuItem_Return);
        menuItem_Return.addActionListener(this);
        menubar.add(menu_RentSys);

        menu_LimitTime.add(this.menuItem_QuerylimitTime);
        menuItem_QuerylimitTime.addActionListener(this);
        if (FrmLogin.isStaff) {
            menu_LimitTime.add(this.menuItem_AddlimitTime);
            menuItem_AddlimitTime.addActionListener(this);
        }
        menubar.add(this.menu_LimitTime);

        menu_Coupon.add(this.menuItem_QueryCoupon);
        menuItem_QueryCoupon.addActionListener(this);
        menu_Coupon.add(this.menuItem_GetCoupon);
        menuItem_GetCoupon.addActionListener(this);
        if (FrmLogin.isStaff) {
            menu_Coupon.add(this.menuItem_AddCoupon);
            menuItem_AddCoupon.addActionListener(this);
        }
        menubar.add(this.menu_Coupon);
        menu_Order.add(this.menu_ProgressingOrder);
        menu_ProgressingOrder.addActionListener(this);
        menu_Order.add(this.menu_FinishedOrder);
        menu_FinishedOrder.addActionListener(this);
        menubar.add(this.menu_Order);


        menu_UserinfManager.add(this.menuItem_QueryUserinf);
        menuItem_QueryUserinf.addActionListener(this);
        menu_UserinfManager.add(this.menuItem_ModifyPhonenumber);
        menuItem_ModifyPhonenumber.addActionListener(this);
        menu_UserinfManager.add(this.menuItem_ModifyEmail);
        menuItem_ModifyEmail.addActionListener(this);
        menu_UserinfManager.add(this.menuItem_ModifyPwd);
        menuItem_ModifyPwd.addActionListener(this);
        menu_UserinfManager.add(this.menuItem_ModifyCity);
        menuItem_ModifyCity.addActionListener(this);
        menubar.add(this.menu_UserinfManager);


        this.setJMenuBar(menubar);
        //状态栏
        statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        if (BeanUser.currentLoginUser != null) {
            JLabel label = new JLabel("您好!" + BeanUser.currentLoginUser.getUser_name());
            statusBar.add(label);
        } else {
            JLabel label = new JLabel("您好!" + BeanStaff.currentLoginStaff.getStaff_name());
            statusBar.add(label);
        }

        this.getContentPane().add(statusBar, BorderLayout.SOUTH);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setVisible(true);
    }

    @Override


    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == this.menuItem_CarTypeManager) {
            FrmCarTypeManager dlg = new FrmCarTypeManager(this, "汽车类别管理", true);
            dlg.setVisible(true);
        } else if (e.getSource() == this.menuItem_MotorcycleTypeManager) {
            FrmMotorcycleTypeManager dlg = new FrmMotorcycleTypeManager(this, "车型管理", true);
            dlg.setVisible(true);
        } else if (e.getSource() == this.menuItem_CarManager) {
            FrmCarManager dlg = new FrmCarManager(this, "车辆管理", true);
            dlg.setVisible(true);
        } else if (e.getSource() == this.menuItem_BranchManager) {
            FrmBranchManager dlg = new FrmBranchManager(this, "网点管理", true);
            dlg.setVisible(true);
        } else if (e.getSource() == this.menuItem_UserShoppinginfManager) {
            FrmShoppingiInf dlg = new FrmShoppingiInf(this, "查询用户消费情况", true);
            dlg.setVisible(true);
        } else if (e.getSource() == this.menuItem_Rent) {
            FrmRentCar dlg = new FrmRentCar(this, "租车", true);
            dlg.setVisible(true);
        } else if (e.getSource() == this.menuItem_Return) {
            FrmReturnCar dlg = new FrmReturnCar(this, "还车", true);
            dlg.setVisible(true);
        } else if (e.getSource() == this.menuItem_QuerylimitTime) {
            FrmLimitTime dlg = new FrmLimitTime(this, "查询限时优惠信息", true);
            dlg.setVisible(true);
        } else if (e.getSource() == this.menuItem_QuerylimitTime) {
            FrmAddLimitTime dlg = new FrmAddLimitTime(this, "添加限时优惠信息", true);
            dlg.setVisible(true);
        } else if (e.getSource() == this.menuItem_QueryCoupon) {
            FrmSearchCouponInf dlg = new FrmSearchCouponInf(this, "查询优惠券信息", true);
            dlg.setVisible(true);
        } else if (e.getSource() == this.menuItem_GetCoupon) {
            FrmGetCoupon dlg = new FrmGetCoupon(this, "领取优惠券", true);
            dlg.setVisible(true);
        } else if (e.getSource() == this.menuItem_AddCoupon) {
            FrmAddCoupon dlg = new FrmAddCoupon(this, "添加优惠券", true);
            dlg.setVisible(true);
        } else if (e.getSource() == this.menu_ProgressingOrder) {
            FrmProgressing dlg = new FrmProgressing(this, "进行中订单", true);
            dlg.setVisible(true);
        } else if (e.getSource() == this.menu_FinishedOrder) {
            FrmFinished dlg = new FrmFinished(this, "已完成订单", true);
            dlg.setVisible(true);
        }

        //报废车辆界面

        else if (e.getSource() == this.menuItem_QueryUserinf) {
            FrmUserInf dlg = new FrmUserInf(this, "查询用户个人信息", true);
            dlg.setVisible(true);
        } else if (e.getSource() == this.menuItem_ModifyPhonenumber) {
            FrmModifyPhoneNumber dlg = new FrmModifyPhoneNumber(this, "修改手机号", true);
            dlg.setVisible(true);
        } else if (e.getSource() == this.menuItem_ModifyEmail) {
            FrmModifyEmail dlg = new FrmModifyEmail(this, "修改邮箱", true);
            dlg.setVisible(true);
        } else if (e.getSource() == this.menuItem_ModifyPwd) {
            FrmModifyPwd dlg = new FrmModifyPwd(this, "修改密码", true);
            dlg.setVisible(true);
        } else if (e.getSource() == this.menuItem_ModifyCity) {
            FrmModifyCity dlg = new FrmModifyCity(this, "修改所在城市", true);
            dlg.setVisible(true);
        }


    }
}

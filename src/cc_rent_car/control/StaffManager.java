package cc_rent_car.control;

import cc_rent_car.model.BeanStaff;
import cc_rent_car.util.BaseException;
import cc_rent_car.util.BusinessException;
import cc_rent_car.util.DBUtil;
import cc_rent_car.util.DbException;

import java.sql.Connection;
import java.sql.SQLException;

public class StaffManager {
    public void add_coupon() {

    }

    public void scrap_car(String license_plate) {

    }

    public static BeanStaff login(Integer staffid, String pwd) throws BaseException {
        // TODO Auto-generated method stub
        if (staffid == null || "".equals(staffid)) throw new BusinessException("账号不能为空");
        if (pwd == null || "".equals(pwd)) throw new BusinessException("密码不能为空");
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select pwd from staff_information where staff_id=?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, staffid);
            java.sql.ResultSet rs = pst.executeQuery();
            if (!rs.next()) {
                rs.close();
                pst.close();
                throw new BusinessException("员工不存在");
            }
            String staff_pwd = rs.getString("pwd");
            if (!staff_pwd.equals(pwd)) {
                throw new BusinessException("密码错误");
            }
            sql = "select * from staff_information where staff_id=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, staffid);
            rs = pst.executeQuery();
            BeanStaff s = new BeanStaff();
            if (rs.next()) {
                s.setStaff_id(rs.getInt(1));
                s.setBranch_id(rs.getInt(2));
                s.setStaff_name(rs.getString(3));
                s.setPwd(rs.getString(4));
            }
            return s;

        } catch (SQLException ex) {
            // TODO: handle exception
            throw new DbException(ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
        }
    }

    public BeanStaff loadStaff(Integer id) throws BaseException {
        // TODO Auto-generated method stub
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM staff_information where staff_id = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, BeanStaff.currentLoginStaff.getStaff_id());
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                BeanStaff s = new BeanStaff();
                s.setStaff_id(rs.getInt(1));
                s.setBranch_id(rs.getInt(2));
                s.setStaff_name(rs.getString(3));
                s.setPwd(rs.getString(4));
                return s;
            }
            rs.close();
            pst.close();


        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException("error");
        } finally {
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return null;
    }


}

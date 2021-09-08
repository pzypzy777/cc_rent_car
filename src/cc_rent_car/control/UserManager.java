package cc_rent_car.control;

import cc_rent_car.model.BeanUser;
import cc_rent_car.util.BaseException;
import cc_rent_car.util.BusinessException;
import cc_rent_car.util.DBUtil;
import cc_rent_car.util.DbException;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class UserManager {
    public void get_coupon() {

    }

    public BeanUser loadUser(Integer userid) throws BaseException {
        // TODO Auto-generated method stub
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM user where user_id = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, BeanUser.currentLoginUser.getUser_id());
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                BeanUser u = new BeanUser();
                u.setUser_id(rs.getInt(1));
                u.setUser_name(rs.getString(2));
                u.setUser_sex(rs.getString(3));
                u.setUser_pwd(rs.getString(4));
                u.setUser_phone_number(rs.getString(5));
                u.setUser_email(rs.getString(6));
                u.setUser_city(rs.getString(7));
                u.setUser_register_time(rs.getTime(8));
                return u;
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

    public static List<BeanUser> loadAllUser() throws BaseException {
        List<BeanUser> result = new ArrayList<BeanUser>();
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM user";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                BeanUser p = new BeanUser();
                p.setUser_id(rs.getInt(1));
                p.setUser_name(rs.getString(2));
                p.setUser_sex(rs.getString(3));
                p.setUser_pwd(rs.getString(4));
                p.setUser_phone_number(rs.getString(5));
                p.setUser_email(rs.getString(6));
                p.setUser_city(rs.getString(7));
                p.setUser_register_time(rs.getTime(8));
                result.add(p);
            }
            rs.close();
            pst.close();
            return result;
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
    }

    public BeanUser registerUser(Integer userid, String pwd1, String pwd2, String name, String sex, String phone,
                                 String email, String city) throws BusinessException, DbException {
        // TODO Auto-generated method stub
        if (userid > 10000) throw new BusinessException("账号不能大于10000");
        if (userid == null || "".equals(userid)) throw new BusinessException("账号不能为空");
        if (pwd1 == null || "".equals(userid)) throw new BusinessException("密码不能为空");
        if (!pwd1.equals(pwd2)) throw new BusinessException("两次输入密码不一致");
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select user_id from user where user_id = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, userid);
            java.sql.ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                rs.close();
                pst.close();
                throw new BusinessException("用户已存在");
            }
            rs.close();
            pst.close();
            sql = "insert into user(user_id,user_name,user_sex,user_pwd,user_phone_number,user_email,user_city,user_register_time) values(?,?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, userid);
            pst.setString(2, name);
            pst.setString(3, sex);
            pst.setString(4, pwd1);
            pst.setString(5, phone);
            pst.setString(6, email);
            pst.setString(7, city);
            pst.setTimestamp(8, new java.sql.Timestamp(System.currentTimeMillis()));
            pst.execute();
            BeanUser user = new BeanUser();
            user.setUser_register_time(new Date());
            user.setUser_id(userid);
            user.setUser_name(name);
            user.setUser_sex(sex);
            user.setUser_pwd(pwd1);
            user.setUser_phone_number(phone);
            user.setUser_email(email);
            user.setUser_city(city);
            return user;


        } catch (SQLException ex) {
            // TODO: handle exception
            throw new DbException(ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static BeanUser login(Integer userid, String pwd) throws BaseException {
        // TODO Auto-generated method stub
        if (userid == null || "".equals(userid)) throw new BusinessException("账号不能为空");
        if (pwd == null || "".equals(pwd)) throw new BusinessException("密码不能为空");
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select user_pwd from user where user_id=?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, userid);
            java.sql.ResultSet rs = pst.executeQuery();
            if (!rs.next()) {
                rs.close();
                pst.close();
                throw new BusinessException("用户不存在");
            }
            String user_pwd = rs.getString("user_pwd");
            if (!user_pwd.equals(pwd)) {
                throw new BusinessException("密码错误");
            }
            sql = "select * from user where user_id=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, userid);
            rs = pst.executeQuery();
            BeanUser user = new BeanUser();
            if (rs.next()) {
                user.setUser_id(rs.getInt(1));
                user.setUser_name(rs.getString(2));
                user.setUser_sex(rs.getString(3));
                user.setUser_pwd(rs.getString(4));
                user.setUser_phone_number(rs.getString(5));
                user.setUser_email(rs.getString(6));
                user.setUser_city(rs.getString(7));
                user.setUser_register_time(rs.getDate(8));
            }
            return user;

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

    public static void changePwd(BeanUser user, String oldPwd, String newPwd,
                                 String newPwd2) throws BaseException {
        // TODO Auto-generated method stub
        if (oldPwd == null || "".equals(oldPwd)) throw new BusinessException("原密码不能为空");
        if (newPwd == null || "".equals(newPwd)) throw new BusinessException("新密码不能为空");
        if (newPwd2 == null || "".equals(newPwd2)) throw new BusinessException("新密码不能为空");
        if (!newPwd2.equals(newPwd)) throw new BusinessException("两次密码不相同");
        Connection conn = null;
        if (!oldPwd.equals(user.getUser_pwd())) throw new BusinessException("原密码错误");
        try {
            conn = DBUtil.getConnection();
            String sql = "update user set user_pwd=? where user_id=?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, newPwd);
            pst.setInt(2, user.getUser_id());
            pst.execute();
            pst.close();
            JOptionPane.showMessageDialog(null, "修改成功");


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

    public static void changeCity(BeanUser user, String oldCity, String newCity) throws BaseException {
        // TODO Auto-generated method stub
        if (oldCity == null || "".equals(oldCity)) throw new BusinessException("原城市不能为空");
        if (newCity == null || "".equals(newCity)) throw new BusinessException("新城市不能为空");
        Connection conn = null;
        if (!oldCity.equals(user.getUser_city())) throw new BusinessException("原城市错误");
        try {
            conn = DBUtil.getConnection();
            String sql = "update user set user_city=? where user_id=?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, newCity);
            pst.setInt(2, user.getUser_id());
            pst.execute();
            pst.close();
            JOptionPane.showMessageDialog(null, "修改成功");


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

    public static void changeEmail(BeanUser user, String OldEmail, String NewEmail, String NewEmail1) throws DbException, BusinessException {
        // TODO Auto-generated method stub
        if (OldEmail == null || "".equals(OldEmail)) throw new BusinessException("原邮箱不能为空");
        if (NewEmail == null || "".equals(NewEmail)) throw new BusinessException("新邮箱不能为空");
        if (NewEmail1 == null || "".equals(NewEmail1)) throw new BusinessException("请再确认一次新邮箱");
        if (!NewEmail.equals(NewEmail1)) throw new BusinessException("两次邮箱不相同");
        Connection conn = null;
        if (!OldEmail.equals(user.getUser_email())) throw new BusinessException("原邮箱错误");
        try {
            conn = DBUtil.getConnection();
            String sql = "update user set user_email=? where user_id=?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, NewEmail);
            pst.setInt(2, user.getUser_id());
            pst.execute();
            pst.close();
            JOptionPane.showMessageDialog(null, "修改成功");


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

    public static void changePhoneNumber(BeanUser user, String edtPhoneNumberOld, String edtPhoneNumber, String edtPhoneNumber1) throws DbException, BusinessException {
        // TODO Auto-generated method stub
        Connection conn = null;
        if (edtPhoneNumberOld == null || "".equals(edtPhoneNumberOld)) throw new BusinessException("原手机号不能为空");
        if (edtPhoneNumber == null || "".equals(edtPhoneNumber)) throw new BusinessException("新手机号不能为空");
        if (edtPhoneNumber1 == null || "".equals(edtPhoneNumber1)) throw new BusinessException("请再确认一次新手机号");
        if (!edtPhoneNumber.equals(edtPhoneNumber1)) throw new BusinessException("两次手机号不相同");

        System.out.print(edtPhoneNumberOld);
        System.out.print(user.getUser_phone_number());
        if (!edtPhoneNumberOld.equals(user.getUser_phone_number())) throw new BusinessException("原手机号错误");
        try {
            conn = DBUtil.getConnection();
            String sql = "update user set user_phone_number=? where user_id=?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, edtPhoneNumber);
            pst.setInt(2, user.getUser_id());
            pst.execute();
            pst.close();
            JOptionPane.showMessageDialog(null, "修改成功");


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


}

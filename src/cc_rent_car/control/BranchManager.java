package cc_rent_car.control;

import cc_rent_car.model.BeanBranch;
import cc_rent_car.util.BaseException;
import cc_rent_car.util.BusinessException;
import cc_rent_car.util.DBUtil;
import cc_rent_car.util.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchManager {
    public static List<BeanBranch> loadAllBranch() {
        List<BeanBranch> result = new ArrayList<BeanBranch>();
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM branch";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                BeanBranch b = new BeanBranch();
                b.setBranch_id(rs.getInt(1));
                b.setBranch_location(rs.getString(2));
                b.setBranch_name(rs.getString(3));
                b.setBranch_city(rs.getString(4));
                b.setBranch_site(rs.getString(5));
                b.setBranch_phone_number(rs.getString(6));
                result.add(b);
            }
            rs.close();
            pst.close();


        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return result;
    }

    public static BeanBranch addBranch(String location, String name, String city, String address, String phone) throws BaseException {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            int count = 0;
            String sql = "SELECT MAX(branch_id) FROM branch";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            pst.close();
            rs.close();
            System.out.println(location);
            System.out.println(name);
            sql = "insert into branch (branch_id, branch_location, branch_name, branch_city, branch_site, branch_phone_number) VALUES (?,?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, count + 1);
            pst.setString(2, location);
            pst.setString(3, name);
            pst.setString(4, city);
            pst.setString(5, address);
            pst.setString(6, phone);
            pst.execute();
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


    public void modifyBranch(BeanBranch bc) throws BusinessException, DbException {
        // TODO Auto-generated method stub
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
//            String sql="select * from branch where branch_id="+branch.getBranch_id();
//            java.sql.Statement st=conn.createStatement();
//            java.sql.ResultSet rs=st.executeQuery(sql);

//            rs.close();
//            st.close();

            String sql = "update branch set branch_location=?,branch_name=?,branch_city=?,branch_site=?,branch_phone_number=? where branch_id=?";
            java.sql.PreparedStatement pst;
            pst = conn.prepareStatement(sql);
            pst.setString(1, bc.getBranch_location());
            pst.setString(2, bc.getBranch_name());
            pst.setString(3, bc.getBranch_city());
            pst.setString(4, bc.getBranch_site());
            pst.setString(5, bc.getBranch_phone_number());
            pst.setInt(6, bc.getBranch_id());
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException(e);
        } finally {
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
    }

    public void deleteBranch(int id) throws BaseException {
        if (id <= 0) {
            throw new BusinessException("网点ID必须是大于0的整数");
        }
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select branch_name from branch where branch_id=" + id;
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);
            if (!rs.next()) throw new BusinessException("网点名称不存在");
            String carTypeName = rs.getString(1);
            rs.close();
            sql = "select count(*) from car where branch_id=" + id;
            rs = st.executeQuery(sql);
            rs.next();
            int n = rs.getInt(1);

            String branch_name = rs.getString(1);
            if (n > 0) throw new BusinessException("已经有" + n + "辆车是" + branch_name + "的，不能删除");
            st.execute("delete from branch where branch_id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException(e);
        } finally {
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
    }
}

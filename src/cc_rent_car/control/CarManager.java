package cc_rent_car.control;

import cc_rent_car.model.BeanCar;
import cc_rent_car.model.BeanCarType;
import cc_rent_car.model.BeanMotorcycleType;
import cc_rent_car.util.BaseException;
import cc_rent_car.util.BusinessException;
import cc_rent_car.util.DBUtil;
import cc_rent_car.util.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CarManager {
	public static BeanCarType addCarType(String cartype, String cartypeinf) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			int count = 0;
			String sql = "SELECT car_type_ID FROM car_type WHERE car_type_name=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, cartype);
			java.sql.ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				pst.close();
				rs.close();
				throw new BusinessException("已经存在该汽车类别,请勿重复添加");
			}
			sql = "SELECT MAX(car_type_ID) FROM car_type";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
			pst.close();
			rs.close();

			sql = "INSERT INTO car_type (car_type_ID,car_type_name,car_type_inf) VALUES (?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, count + 1);
			pst.setString(2, cartype);
			pst.setString(3, cartypeinf);
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

	public static List<BeanCarType> loadAllCarType() throws DbException {
		// TODO Auto-generated method stub
		List<BeanCarType> result = new ArrayList<BeanCarType>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from car_type";
			java.sql.Statement st = conn.createStatement();
			java.sql.ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				BeanCarType rt = new BeanCarType();
				rt.setCar_type_ID(rs.getInt(1));
				rt.setCar_type_name(rs.getString(2));
				rt.setCar_type_inf(rs.getString(3));
				result.add(rt);
			}
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
		return result;
	}


	public void modifyCarType(BeanCarType ct) throws BaseException {
		if (ct.getCar_type_ID() <= 0) {
			throw new BusinessException("汽车类别ID必须是大于0的整数");
		}
		if (ct.getCar_type_name() == null || "".equals(ct.getCar_type_name()) || ct.getCar_type_name().length() > 20) {
			throw new BusinessException("汽车类别名称必须是1-20个字");
		}

		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from car_type where car_type_ID=" + ct.getCar_type_ID();
			java.sql.Statement st = conn.createStatement();
			java.sql.ResultSet rs = st.executeQuery(sql);
			if (!rs.next()) throw new BusinessException("汽车类别不存在");
			rs.close();
			st.close();
			sql = "select * from car_type where car_type_name=? and car_type_ID<>" + ct.getCar_type_ID();
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, ct.getCar_type_name());
			rs = pst.executeQuery();
			if (rs.next()) throw new BusinessException("汽车类别名称已经被占用");
			rs.close();
			pst.close();
			sql = "update  car_type set car_type_name=?,car_type_inf=? where car_type_ID=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, ct.getCar_type_name());
			pst.setString(2, ct.getCar_type_inf());
			pst.setInt(3, ct.getCar_type_ID());
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

	public void deleteCarType(int id) throws BaseException {
		if (id <= 0) {
			throw new BusinessException("汽车类别ID必须是大于0的整数");
		}
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select car_type_name from car_type where car_type_ID=" + id;
			java.sql.Statement st = conn.createStatement();
			java.sql.ResultSet rs = st.executeQuery(sql);
			if (!rs.next()) throw new BusinessException("汽车类别不存在");
			String carTypeName = rs.getString(1);
			rs.close();
			sql = "select count(*) from motorcycle_type where car_type_ID=" + id;
			rs = st.executeQuery(sql);
			rs.next();
			int n = rs.getInt(1);
			if (n > 0) throw new BusinessException("已经有" + n + "种车型是" + carTypeName + "的，不能删除");
			st.execute("delete from car_type where car_type_ID=" + id);
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

	public static List<BeanMotorcycleType> loadAllBeanMotorcycleType() throws DbException {
		// TODO Auto-generated method stub
		List<BeanMotorcycleType> result = new ArrayList<BeanMotorcycleType>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from motorcycle_type";
			java.sql.Statement st = conn.createStatement();
			java.sql.ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				BeanMotorcycleType mt = new BeanMotorcycleType();
				mt.setType_ID(rs.getInt(1));
				mt.setCar_type_ID(rs.getInt(2));
				mt.setType_name(rs.getString(3));
				mt.setBrand(rs.getString(4));
				mt.setDisplacement(rs.getString(5));
				mt.setGear(rs.getString(6));
				mt.setSeats_count(rs.getString(7));
				mt.setPricee(rs.getInt(8));

				result.add(mt);
			}
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
		return result;
	}

	public static BeanMotorcycleType addMotorcycleType(String motorcycleType, Integer typeid, String typename, String brand, String displacement, String gear, String seatscount, Integer price) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			int count = 0;
			String sql = "SELECT type_ID FROM motorcycle_type WHERE type_name=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, typename);
			java.sql.ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				pst.close();
				rs.close();
				throw new BusinessException("已经存在该车型,请勿重复添加");
			}
			sql = "SELECT MAX(type_ID) FROM motorcycle_type";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
			pst.close();
			rs.close();

			sql = "INSERT INTO motorcycle_type (type_ID,car_type_ID,type_name,brand,displacement,gear,seats_count,pricee) VALUES (?,?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, count + 1);
			pst.setInt(2, typeid);
			pst.setString(3, typename);
			pst.setString(4, brand);
			pst.setString(5, displacement);
			pst.setString(6, gear);
			pst.setString(7, seatscount);
			pst.setInt(8, price);
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

	public void deleteMotorcycleType(int id) throws BusinessException, DbException {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select type_name from motorcycle_type where type_ID=" + id;
			java.sql.Statement st = conn.createStatement();
			java.sql.ResultSet rs = st.executeQuery(sql);
			if (!rs.next()) throw new BusinessException("车型不存在");
			String motorcycleTypeName = rs.getString(1);
			rs.close();
			st.execute("delete from motorcycle_type where type_ID=" + id);
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

	public void modifyMotorcycleType(BeanMotorcycleType mt) throws BusinessException, DbException {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from motorcycle_type where type_ID=" + mt.getType_ID();
			java.sql.Statement st = conn.createStatement();
			java.sql.ResultSet rs = st.executeQuery(sql);
			if (!rs.next()) throw new BusinessException("车型不存在");
			rs.close();
			st.close();
			sql = "select * from motorcycle_type where type_name=? and type_ID<>" + mt.getType_ID();
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, mt.getType_name());
			rs = pst.executeQuery();
			if (rs.next()) throw new BusinessException("汽车类别名称已经被占用");
			rs.close();
			pst.close();
			sql = "update  motorcycle_type set car_type_ID=?,type_name=? ,brand=?,displacement=?,gear=?,seats_count=?,pricee=? where type_ID=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, mt.getCar_type_ID());
			pst.setString(2, mt.getType_name());
			pst.setString(3, mt.getBrand());
			pst.setString(4, mt.getDisplacement());
			pst.setString(5, mt.getGear());
			pst.setString(6, mt.getSeats_count());
			pst.setInt(7, mt.getPricee());
			pst.setInt(8, mt.getType_ID());
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

	public List<BeanCar> searchCar(String str) throws BusinessException {
		Connection conn = null;
		List<BeanCar> result = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from car where state=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, str);
			java.sql.ResultSet rs = pst.executeQuery();
//			if (!rs.next()) throw new BusinessException("不存在该状态的车");
			while (rs.next()) {
				BeanCar c = new BeanCar();
				c.setCar_ID(Integer.parseInt(rs.getString(1)));
				c.setLicense_plate(rs.getString(2));
				c.setScrap_ID(Integer.parseInt(rs.getString(3)));
				c.setType_ID(Integer.parseInt(rs.getString(4)));
				c.setType_name(rs.getString(5));
				c.setBranch_id(Integer.parseInt(rs.getString(6)));
				c.setState(rs.getString(7));
				result.add(c);
			}

			rs.close();
			pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;


	}

	public void ScrapCar(BeanCar car) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "update car set state=? where car_ID=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);

			pst = conn.prepareStatement(sql);
			pst.setString(1, "scrapped");
			pst.setInt(2, car.getCar_ID());

			pst.execute();
			pst.close();
		} catch (Exception e) {
			e.printStackTrace();
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

	public static BeanCar addCar(String licensePlate, Integer typeId, Integer branchId) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			int count = 0;
			String typename = null;
			String sql = "SELECT MAX(car_ID) FROM car";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
			pst.close();
			rs.close();

			sql = "SELECT type_name FROM motorcycle_type where type_ID=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, typeId);
			rs = pst.executeQuery();
			if (rs.next()) {
				typename = rs.getString(1);
			}
			pst.close();
			rs.close();
			sql = "INSERT INTO car (car_ID,license_plate,scrap_ID,type_ID,type_name,branch_id,state) VALUES (?,?,0,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, count + 1);
			pst.setString(2, licensePlate);

			pst.setInt(3, typeId);
			pst.setString(4, typename);
			pst.setInt(5, branchId);
			pst.setString(6, "空闲");
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

	public void modifyCar(BeanCar carr) throws BusinessException, DbException {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from car where car_ID=" + carr.getCar_ID();
			java.sql.Statement st = conn.createStatement();
			java.sql.ResultSet rs = st.executeQuery(sql);
			if (!rs.next()) throw new BusinessException("该车不存在");
			rs.close();
			st.close();

//            private JTextField edtCarid = new JTextField(20);
//            private JTextField edtscrapid= new JTextField(20);
//            private JTextField edttypeid  = new JTextField(20);
//            private JTextField edtBranchid = new JTextField(20);
//            private JTextField edtstate = new JTextField(20);
			sql = "select license_plate from car where car_ID=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, carr.getCar_ID());
			rs = pst.executeQuery();
			String pl = "";
			if (rs.next()) {
				pl = rs.getString(1);
			} else throw new BusinessException("没有这辆车");
			sql = "update car set car_ID=?,scrap_ID=? ,type_ID=?,branch_id=?,state=? where license_plate=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, carr.getCar_ID());
			pst.setInt(2, carr.getScrap_ID());
			pst.setInt(3, carr.getType_ID());
			pst.setInt(4, carr.getBranch_id());
			pst.setString(5, carr.getState());
			pst.setString(6, pl);

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
}

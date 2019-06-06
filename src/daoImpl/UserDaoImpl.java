package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import utils.DBUtil;
import beans.User;
import dao.UserDao;
import beans.Page;
public class UserDaoImpl implements UserDao{
	
	@Override
	public User login(String userName, String password) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = DBUtil.getConn();
			String sql = "select * from user where userName=? and password=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userName);
			pstm.setString(2, password);
			rs = pstm.executeQuery();
			if(rs.next()){
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, conn, pstm);
		}
		return user;
	}

	@Override
	public boolean regist(String userName, String password) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConn();
			String sql = "insert into user(userName,password) values(?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userName);
			pstm.setString(2, password);
			int count = pstm.executeUpdate();
			if(count > 0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(null, conn, pstm);
		}
		return false;
	}

	@Override
	public List<User> queryAll(Page page) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<User> userList = new ArrayList<User>();
		User user = null;
		try {
			conn = DBUtil.getConn();
			String sql = "select * from user limit ?,?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, page.getBeginRow());
			pstm.setInt(2, page.getPageSize());
			rs = pstm.executeQuery();
			while(rs.next()){
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				userList.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, conn, pstm);
		}
		return userList;
	}

	@Override
	public List<User> queryByUserName(String userName) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<User> userList = new ArrayList<User>();
		try {
			conn = DBUtil.getConn();
			String sql = "select * from user where userName like ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%"+userName+"%");
			rs = pstm.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				userList.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(null, conn, pstm);
		}
		return userList;
	}

	@Override
	public boolean deleteById(int id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = DBUtil.getConn();
			String sql = "delete from user where id=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			int count = pstm.executeUpdate();
			if(count > 0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(null, conn, pstm);
		}
		return false;
	}

	@Override
	public boolean updateUserPassword(String userName,String password) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = DBUtil.getConn();
			String sql = "update user set password=? where userName=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, password);
			pstm.setString(2, userName);
			int count = pstm.executeUpdate();
			if(count > 0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(null, conn, pstm);
		}
		return false;
	}

	@Override
	public int rowCount() {
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	int count = 0;
	try {
		conn = DBUtil.getConn();
		String sql = "select count(*) from user";
		pstm = conn.prepareStatement(sql);
		rs = pstm.executeQuery();
		rs.next();
		count = rs.getInt(1);
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		DBUtil.close(rs, conn, pstm);
	}
		return count;
	}

}

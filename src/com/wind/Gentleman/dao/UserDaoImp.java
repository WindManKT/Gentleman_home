package com.wind.Gentleman.dao;

import com.wind.Gentleman.domain.User;
import com.wind.Gentleman.utils.JdbcUtil;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;


@Component
public class UserDaoImp implements UserDao {
	PreparedStatement ps = null;
	ResultSet resultSet = null;
	Connection conn = null;

	//添加用户，成功返回true
	@Override
	public boolean addUser(User user) {
		try {
			conn = JdbcUtil.getConn();
			ps = conn.prepareStatement("insert into user values(null,?,?,?,?,?)");
			ps.setString(1, user.getRename());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.setInt(4, user.getBalance());
			ps.setString(5, user.getDate() == null ? null : user.getDate().getTime() + "");
			int a = ps.executeUpdate();
			System.out.println(a);
			if (a > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			JdbcUtil.close(conn, ps, resultSet);
		}
	}

	//根据user里的email查询用户是否存在，存在返回true
	@Override
	public boolean findByEmail(User user) {
		try {
			conn = JdbcUtil.getConn();
			ps = conn.prepareStatement("select * from user where email=?");
			ps.setString(1, user.getEmail());
			resultSet = ps.executeQuery();
			if (resultSet.next()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			JdbcUtil.close(conn, ps, resultSet);
		}
	}

	//根据邮箱查询用户的所有信息，存在User里，返回User，要包含id
	@Override
	public User findAllByEmail(User user) {
		User emUser = new User();
		try {
			conn = JdbcUtil.getConn();
			ps = conn.prepareStatement("select * from user where email=?");
			ps.setString(1, user.getEmail());
			resultSet = ps.executeQuery();
			if (resultSet.next()) {
				emUser.setId(resultSet.getInt("id"));
				emUser.setRename(resultSet.getString("re_name"));
				emUser.setEmail(resultSet.getString("email"));
				emUser.setPassword(resultSet.getString("password"));
				emUser.setBalance(resultSet.getInt("balance"));
				Date date = new Date();
				String stDate = resultSet.getString("date");

				if (stDate == null || Long.parseLong(stDate) < new Date().getTime()) {
					emUser.setDate(null);
				} else {
					date.setTime(Long.parseLong(stDate));
					emUser.setDate(date);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			JdbcUtil.close(conn, ps, resultSet);
		}
		return emUser;
	}

	//根据id查询User 用户信息
	@Override
	public User findById(User user) {
		User idUser = new User();
		try{
			conn = JdbcUtil.getConn();
			ps = conn.prepareStatement("select * from user where id=?");
			ps.setInt(1, user.getId());
			resultSet = ps.executeQuery();
			if (resultSet.next()) {
				idUser.setId(resultSet.getInt("id"));
				idUser.setRename(resultSet.getString("re_name"));
				idUser.setEmail(resultSet.getString("email"));
				idUser.setPassword(resultSet.getString("password"));
				idUser.setBalance(resultSet.getInt("balance"));
				Date date = new Date();
				String stDate = resultSet.getString("date");

				if (stDate == null || Long.parseLong(stDate) < new Date().getTime()) {
					idUser.setDate(null);
				} else {
					date.setTime(Long.parseLong(stDate));
					idUser.setDate(date);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			JdbcUtil.close(conn, ps, resultSet);
		}
		return idUser;
	}

	//根据User里的id查询到用户，进行用户信息刷新
	@Override
	public User updateUserInfo(User user) {
		try{
			conn = JdbcUtil.getConn();
			ps = conn.prepareStatement(" update user set re_name=?,email=?,password=?,balance=?,date=? where id=?;");
			ps.setString(1, user.getRename());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.setInt(4, user.getBalance());
			ps.setString(5,user.getDate()==null?null:user.getDate().getTime()+"");
			ps.setInt(6,user.getId());
			int i = ps.executeUpdate();
		}catch (Exception e){
			System.out.println(e.getMessage());
		}finally {
			JdbcUtil.close(conn,ps,resultSet);
		}
		return user;
	}






}

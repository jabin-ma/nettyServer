package com.majipeng.nettyServer.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

import com.majipeng.nettyServer.dao.UserDao;
import com.majipeng.nettyServer.utils.SqlConnectionManager;

import majipeng.model.User;
import majipeng.utils.Log;

public class UserDaoImpl implements UserDao {
 public static final String TAG="UserDao";
	@Override
	public boolean add(User user) {
		Connection con=SqlConnectionManager.getConnection();
		QueryRunner runner=new QueryRunner();
		int count=0;
		try {
			count = runner.update(con,"insert into user(name,password) values (?,?)", user.getName(),user.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Log.d(TAG, "插入数据:"+count);
		DbUtils.closeQuietly(con);
		return count>=1;
	}

	@Override
	public void update(User user) {
	}

	@Override
	public void delete(int id) {
	}

	@Override
	public void findById(int id) {
	}

	@Override
	public void logon(User user) {
	}
	
	//TEST
	public static final ArrayList<User> user=new ArrayList<>();
	
	

}

package com.dit.chatapp.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.dit.chatapp.dto.UserDTO;
import com.dit.chatapp.utils.Encryption;

// USER CRUD
public class UserDao {
	
	public boolean isLogin(UserDTO userDTO) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		final String SQL = "select userid from users where userid=? and password=?"; 
		try {
			con=CommonDao.createConnection();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, userDTO.getUserid());
			pstmt.setString(2, Encryption.passwordEncrypter(new String(userDTO.getPassword())));
			rs = pstmt.executeQuery();
			return rs.next();
		}
		finally {
			if(rs!=null) {
				rs.close();
			}
			if(pstmt!=null) {
				pstmt.close();
			}
			if(con!=null) {
				con.close();
			}
		}
	}
	
	
	public int add(UserDTO userdto) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		System.out.println("Recieve " + userdto.getUserid() + " "+userdto.getPassword());
		Connection connection = null;
		Statement stmt= null;
		try {
		connection = CommonDao.createConnection();
		
		stmt = connection.createStatement();
		int record= stmt.executeUpdate("insert into users(userid, password) values('"+ userdto.getUserid()+"','"+ Encryption.passwordEncrypter(new String(userdto.getPassword()))+"')");
		return record;
		}
		finally {
			if(stmt!=null) {
				stmt.close();
			}
			if(connection!=null) {
				connection.close();
			}
		}
	}
}

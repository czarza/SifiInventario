package com.intsol.SifiInventario.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.security.authentication.dao.SystemWideSaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import com.intsol.SifiInventario.beans.Util;

public class CustomJdbcDaoImpl extends JdbcDaoImpl {
	@Autowired private PasswordEncoder passwordEncoder;

	/**
	 * Encrypt Database plain text passwords.
	 * See sifi_receptoria-base.xml and you'll find commented the bean instatiation.
	 */
	public void secureDatabase() {
		getJdbcTemplate().query("select us_user, us_password from IvUsuario ",
		new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				String username = rs.getString(1);
				String password = rs.getString(2);
				System.out.print("**********  updating.... user=" + username + " unencripted password=" + password);
				getJdbcTemplate().update("update IvUsuario set us_password = ? where us_user = ? and length(us_password) < 20", getEncriptedPassword(password),username);
				System.out.print("**********  update done.");
		}
		});
	}
	
	/**
	 * Change user passwords
	 */
	public void changePassword(String username, String password) {
		
		String encodedPassword = passwordEncoder.encodePassword(password, null);
		getJdbcTemplate().update("update IvUsuario set us_password = ? where us_user = ?", encodedPassword, username);		
	}

	/**
	 * Save new users.
	 * @param username
	 * @param password
	 */
	public void newUser (String username, String password) {
		String encodedPassword = passwordEncoder.encodePassword(password, null);
		getJdbcTemplate().update("insert into IvUsuario (us_user, us_password) values(?,?)", username, encodedPassword);
	}
	
	private String getEncriptedPassword(String password) {
		if (Util.isEmpty(password)) return null;
		
		SystemWideSaltSource salt = new SystemWideSaltSource();
		salt.setSystemWideSalt("CfXj69"); // This salt must be the same as the one declared on the  <autenticationManager> security.xml
		String p = passwordEncoder.encodePassword(password, salt.getSalt(null));
		
		return p;
	}
	
}

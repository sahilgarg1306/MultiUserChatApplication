package com.dit.chatapp.views;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import com.dit.chatapp.dao.UserDao;
import com.dit.chatapp.dto.UserDTO;
import com.dit.chatapp.utils.UserInfo;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UserScreen extends JFrame{
	private JTextField useridtxt;
	private JPasswordField passwordField;

	public static void main(String[] args) {		
		UserScreen window = new UserScreen();
	}
	
	UserDao userDao= new UserDao();
	private void doLogin() {
		String userId= useridtxt.getText();
		char[] password=passwordField.getPassword();
		
		UserDTO userDTO= new UserDTO(userId, password);
		try {
			String message="";
			if(userDao.isLogin(userDTO)){
				message = "Welcome " + userId;
				UserInfo.USER_NAME = userId;
				JOptionPane.showMessageDialog(this, message);
				setVisible(false);
				dispose();
				DashBoard dashBoard = new DashBoard(message);
				dashBoard.setVisible(true);
			}
			else {
				message="Invalid UserId or Password";
				JOptionPane.showMessageDialog(this, message);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void register() throws ClassNotFoundException, SQLException {
		String userId= useridtxt.getText();
		char[] password=passwordField.getPassword();
		//UserDao userDao= new UserDao();
		UserDTO userDTO= new UserDTO(userId, password);
		try {
		int result= userDao.add(userDTO);
		if(result>0) {
			JOptionPane.showMessageDialog(this, "Register Succesfull");
		}
		else {
			JOptionPane.showMessageDialog(this, "Register Unsuccesfull");
		}
		}
		catch(ClassNotFoundException | SQLException ex) {
			System.out.println("DB Issue....");
			ex.printStackTrace();
		}
		catch(Exception ex) {
			System.out.println("Some generic exception raise");
			ex.printStackTrace();
		}
		//System.out.println("UserId "+ userId+ "Password"+password);
	}
	
	public UserScreen() {
		setResizable(false);
		setTitle("LogIn");
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(217, 10, 183, 115);
		getContentPane().add(lblNewLabel);
		
		JLabel useridlbl = new JLabel("UserID");
		useridlbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		useridlbl.setHorizontalAlignment(SwingConstants.CENTER);
		useridlbl.setBounds(81, 124, 107, 41);
		getContentPane().add(useridlbl);
		
		JLabel pwdlbl = new JLabel("Password");
		pwdlbl.setHorizontalAlignment(SwingConstants.CENTER);
		pwdlbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		pwdlbl.setBounds(81, 193, 133, 69);
		getContentPane().add(pwdlbl);
		
		useridtxt = new JTextField();
		useridtxt.setBounds(231, 135, 301, 19);
		getContentPane().add(useridtxt);
		useridtxt.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(237, 222, 295, 19);
		getContentPane().add(passwordField);
		
		JButton loginbt = new JButton("Login");
		loginbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});
		loginbt.setFont(new Font("Tahoma", Font.BOLD, 20));
		loginbt.setBounds(132, 302, 127, 33);
		getContentPane().add(loginbt);
		
		JButton registerbt = new JButton("Register");
		registerbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					register();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		registerbt.setFont(new Font("Tahoma", Font.BOLD, 20));
		registerbt.setBounds(370, 302, 127, 33);
		getContentPane().add(registerbt);
		setBackground(Color.WHITE);
		setSize(663, 396);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}

package com.yeyunlin.ui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.yeyunlin.dao.Dao;
import com.yeyunlin.util.LimitManager;

public class Login extends JFrame {
	private JPanel jPanel;
	private JLabel jLabelName = new JLabel("用户名");
	private JLabel jLabelPass = new JLabel("密    码");
	private JTextField jFieldName = new JTextField();
	private JPasswordField jFieldPass = new JPasswordField();
	private JButton jButtonCon = new JButton("确认");
	private JButton jButtonCan = new JButton("取消");
	Font font = new Font("微软雅黑", Font.PLAIN, 14);

	public Login() {
		super("点餐系统登陆界面");
		jPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				ImageIcon icon = new ImageIcon("image/background.png");
				g.drawImage(icon.getImage(), 0, 0, this.getSize().width,
						this.getSize().height, this);
			}

		};
		jPanel.setLayout(null);
		jLabelName.setBounds(60, 100, 50, 25);
		jLabelName.setFont(font);
		jPanel.add(jLabelName);
		jLabelPass.setBounds(60, 130, 50, 25);
		jLabelPass.setFont(font);
		jPanel.add(jLabelPass);
		jButtonCon.setBounds(100, 170, 100, 25);
		jButtonCon.setFont(font);
		jPanel.add(jButtonCon);
		jButtonCon.addActionListener(new ContainListener());
		jButtonCan.setBounds(210, 170, 100, 25);
		jButtonCan.setFont(font);
		jButtonCan.addActionListener(new CancelListener());
		jPanel.add(jButtonCan);
		jFieldName.setBounds(115, 100, 180, 25);
		jPanel.add(jFieldName);
		jFieldPass.setBounds(115, 130, 180, 25);
		jFieldPass.setEchoChar('*');
		jPanel.add(jFieldPass);
		this.add(jPanel);
		this.setSize(400, 250);
		this.setResizable(false);
		this.setLocation(500, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * 监听确定按钮
	 * 
	 * @author yeyunlin
	 * 
	 */
	public class ContainListener implements ActionListener {

		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			String name = jFieldName.getText().toString().trim();
			String password = jFieldPass.getText().toString().trim();
			if (name.equals("")) {
				JOptionPane.showMessageDialog(null, "请输入用户名！");
				return;
			}
			if (password.equals("")) {
				JOptionPane.showMessageDialog(null, "请输入密码！");
				return;
			}
			if (Dao.checkPassword(name, password)) {
				LimitManager.setLanded(true);
				setVisible(false);
				PrimaryWindow primaryWindow = new PrimaryWindow();
				primaryWindow.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "用户名或密码发生错误，请重新输入！");
				jFieldName.setText("");
				jFieldPass.setText("");
			}
		}
	}

	/**
	 * 监听取消按钮
	 * 
	 * @author yeyunlin
	 * 
	 */

	public class CancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			PrimaryWindow primaryWindow = new PrimaryWindow();
			primaryWindow.setVisible(true);
		}
	}
}
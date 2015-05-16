package com.yeyunlin.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;

import com.yeyunlin.dao.Dao;
import com.yeyunlin.info.UserInfo;

public class UserInformation extends JFrame{
	private JTable jTable;
	private String[] columnNames = { "姓名", "账号", "密码", "积分" };

	public UserInformation() {
		Object[][] obj = initData();
		jTable = new JTable(obj, columnNames);

		/*
		 * 设置JTable的列默认的宽度和高度
		 */
		TableColumn column = null;
		int colunms = jTable.getColumnCount();
		for (int i = 0; i < colunms; i++) {
			column = jTable.getColumnModel().getColumn(i);
			/* 将每一列的默认宽度设置为100 */
			column.setPreferredWidth(100);
		}

		/*
		 * 设置JTable自动调整列表的状态，此处设置为关闭
		 */
		jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// 用JScrollPane装载JTable，这样超出范围的列就可以通过滚动条来查看 */
		JScrollPane scroll = new JScrollPane(jTable);

		this.add(scroll);
		this.setVisible(true);
		this.setSize(500, 200);
		this.setResizable(false);
		this.setLocation(500, 200);
	}

	private Object[][] initData() {
		List<UserInfo> userInfos = Dao.getAllUsers();
		int row = userInfos.size();
		Object[][] obj = new Object[row][columnNames.length];

		for (int i = 0; i < row; i++) {
			UserInfo userInfo = userInfos.get(i);
			for (int j = 0; j < columnNames.length; j++) {
				switch (j) {
				case 0:
					obj[i][j] = userInfo.getName();
					System.out.println(userInfo.getName());
					break;
				case 1:
					obj[i][j] = userInfo.getAccount();
					break;
				case 2:
					obj[i][j] = userInfo.getPassword();
					break;
				case 3:
					obj[i][j] = userInfo.getIntegral();
					break;
				default:
					break;
				}
			}
		}
		return obj;
	}
}

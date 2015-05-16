package com.yeyunlin.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.yeyunlin.dao.Dao;
import com.yeyunlin.info.FoodInfo;

public class FoodLook extends JFrame{
	String[] columNames;
	Object[][] results;
	JTable jTable;
	JButton conJButton, canJButton;
	JPanel jPanel2, jPanel3;
	JComboBox jComboBox;
	DefaultTableModel model;
	Font font = new Font("微软雅黑", Font.PLAIN, 14);

	public FoodLook() {
		super("菜单查看");
		jPanel2 = new JPanel();
		String[] types = new String[] { "主食面点", "爽口凉菜", "精美小炒", "特色炖菜", "汤羹食品", "锅仔系列", "生猛海鲜", "酒水饮料" };
		jComboBox = new JComboBox(types);
		jComboBox.setFont(font);
		columNames = new String[] { "编号", "菜名", "价格", "类别", "图片",
				"描述" };
		conJButton = new JButton("查询");
		conJButton.setFont(font);
		conJButton.addActionListener(new ConListener());
		jPanel2.add(jComboBox);
		jPanel2.add(conJButton);

		jPanel3 = new JPanel();
		results = getFileStates(Dao.getFoods("主食面点"));
		model = new DefaultTableModel();
		model.setDataVector(results, columNames);
		jTable = new JTable();
		jTable.setModel(model);
		jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		JScrollPane scroll = new JScrollPane(jTable);
		this.add(jPanel2, BorderLayout.NORTH);
		this.add(scroll, BorderLayout.CENTER);
		this.setSize(600, 400);
		this.setResizable(false);
		this.setLocation(500, 200);
		this.setVisible(true);
	}

	private Object[][] getFileStates(List<FoodInfo> foodInfos) {
		System.out.print("foodInfos.size():  "+foodInfos.size());
		Object[][] obj = new Object[foodInfos.size()][columNames.length];
		for (int i = 0; i < foodInfos.size(); i++) {
			FoodInfo foodInfo = foodInfos.get(i);
			for (int j = 0; j < columNames.length; j++) {
				switch (j) {
				case 0:
					obj[i][j] = foodInfo.getNumber();
					break;
				case 1:
					obj[i][j] = foodInfo.getName();
					break;
				case 2:
					obj[i][j] = foodInfo.getPrice();
					break;
				case 3:
					obj[i][j] = foodInfo.getType();
					break;
				case 4:
					obj[i][j] = foodInfo.getIcon();
					break;
				case 5:
					obj[i][j] = foodInfo.getDescription();
					break;
				default:
					break;
				}
			}
		}
		return obj;
	}

	public class ConListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String type = jComboBox.getSelectedItem().toString();
			String sql = null;
			List<FoodInfo> foodInfos = Dao.getFoods(type);
			if (foodInfos.size() > 0) {
				results = getFileStates(foodInfos);
				jTable.setModel(new DefaultTableModel(results, columNames));
			}
		}
	}
}

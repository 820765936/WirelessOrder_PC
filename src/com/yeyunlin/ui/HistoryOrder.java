package com.yeyunlin.ui;

import java.awt.Font;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.yeyunlin.dao.Dao;
import com.yeyunlin.info.OrderInfo;

public class HistoryOrder extends JFrame{
	private DefaultTableModel model;
	private JTable jTable;
	private Object[][] obj;
	private String[] columnNames = { "姓名", "订单号", "菜名", "桌号", "时间" };
	private Font font = new Font("微软雅黑", Font.PLAIN, 14);

	public HistoryOrder() {
		super("历史订单");
		
		obj = initData("");
		model = new DefaultTableModel();
		model.setDataVector(obj, columnNames);
		jTable = new JTable();
		jTable.setModel(model);

		// 用JScrollPane装载JTable，这样超出范围的列就可以通过滚动条来查看 */
		JScrollPane scroll = new JScrollPane(jTable);

		this.add(scroll);
		this.setVisible(true);
		this.setSize(1000, 400);
		this.setResizable(false);
		this.setLocation(0, 0);
	}

	private Object[][] initData(String name) {
		List<OrderInfo> orderInfos = Dao.getAllOrder(name);

		int row = orderInfos.size();
		Object[][] obj = new Object[row][columnNames.length];

		for (int i = 0; i < row; i++) {
			OrderInfo orderInfo = orderInfos.get(i);
			for (int j = 0; j < columnNames.length; j++) {
				switch (j) {
				case 0:
					obj[i][j] = orderInfo.getUsername();
					break;
				case 1:
					obj[i][j] = orderInfo.getOrderId();
					break;
				case 2:
					String foodName = Dao.getFoodName(orderInfo.getFoodid());
					obj[i][j] = foodName;
					break;
				case 3:
					obj[i][j] = orderInfo.getDeskid();
					break;
				case 4:
					obj[i][j] = orderInfo.getTime();
					break;
				default:
					break;
				}
			}
		}
		return obj;
	}
}

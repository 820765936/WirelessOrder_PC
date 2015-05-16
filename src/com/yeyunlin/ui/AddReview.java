package com.yeyunlin.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.yeyunlin.dao.Dao;

public class AddReview extends JFrame implements ActionListener{
	private JPanel jPanel1, jPanel2,jPanel3,jPanel4;
	private JLabel jLabel1, jLabel2, jLabel3;
	private JTextField jTextField1, jTextField2, jTextField3;
	private JButton jButton1, jButton2;
	private Font font = new Font("微软雅黑", Font.PLAIN, 14);
	
	public AddReview() {
		super("添加评论");
		
		jLabel1 = new JLabel("姓    名：");
		jTextField1 = new JTextField(15);
		jPanel1 = new JPanel();
		jPanel1.add(jLabel1);
		jPanel1.add(jTextField1);
		
		jLabel2 = new JLabel("评    论：");
		jTextField2 = new JTextField(15);
		jPanel2 = new JPanel();
		jPanel2.add(jLabel2);
		jPanel2.add(jTextField2);
		
		jLabel3 = new JLabel("时    间：");
		jTextField3 = new JTextField(15);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日    HH:mm:ss");
		Date    curDate    =   new    Date(System.currentTimeMillis());
		jTextField3.setText(simpleDateFormat.format(curDate));
		jTextField3.setEditable(false);
		jPanel3 = new JPanel();
		jPanel3.add(jLabel3);
		jPanel3.add(jTextField3);
		
		jButton1 = new JButton("确定");
		jButton2 = new JButton("取消");
		jButton1.addActionListener(this);
		jButton2.addActionListener(this);
		jPanel4 = new JPanel();
		jPanel4.add(jButton1);
		jPanel4.add(jButton2);
		
		this.setLayout(new GridLayout(5, 1));
		this.add(new JPanel());
		this.add(jPanel1);
		this.add(jPanel2);
		this.add(jPanel3);
		this.add(jPanel4);
		
		this.setSize(400, 250);
		this.setResizable(false);
		this.setLocation(500, 200);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jButton1) {
			String name = jTextField1.getText().trim();
			String content = jTextField2.getText().trim();
			String time = jTextField3.getText().trim();
			if (name.equals("") || content.equals("")) {
				JOptionPane.showMessageDialog(null, "不能为空！");
				return;
			} else {
				Dao.addReview(name, content, time);
				jTextField1.setText("");
				jTextField2.setText("");
				JOptionPane.showMessageDialog(null, "添加评论成功！");
			}
		}
		if (e.getSource() == jButton2) {
			setVisible(false);
		}
	}
}

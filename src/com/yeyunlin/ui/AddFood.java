package com.yeyunlin.ui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.yeyunlin.dao.Dao;

@SuppressWarnings("serial")
public class AddFood extends JFrame {
	private JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6;
	private JTextField jField1, jField2, jField3, jField4, jField5, jField6;
	@SuppressWarnings("rawtypes")
	private JComboBox jComboBox;
	private JButton conJButton, canJButton;
	Font font = new Font("微软雅黑", Font.PLAIN, 14);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AddFood() {
		super("菜单添加");
		this.setLayout(new GridLayout(5, 2));
		this.add(new JPanel());
		this.add(new JPanel());

		jLabel1 = new JLabel("编        号：");
		jLabel1.setFont(font);
		jField1 = new JTextField(8);
		jField1.setFont(font);
		JPanel jPanel1 = new JPanel();
		jPanel1.add(jLabel1);
		jPanel1.add(jField1);
		this.add(jPanel1);

		jLabel2 = new JLabel("菜        名：");
		jField2 = new JTextField(8);
		jLabel2.setFont(font);
		jField2.setFont(font);
		JPanel jPanel2 = new JPanel();
		jPanel2.add(jLabel2);
		jPanel2.add(jField2);
		this.add(jPanel2);

		jLabel3 = new JLabel("价        格：");
		jField3 = new JTextField(8);
		jLabel3.setFont(font);
		jField3.setFont(font);
		JPanel jPanel3 = new JPanel();
		jPanel3.add(jLabel3);
		jPanel3.add(jField3);
		this.add(jPanel3);

		jLabel4 = new JLabel("类        别：");
		jLabel4.setFont(font);
		String[] types = new String[] { "主食面点", "爽口凉菜", "精美小炒", "特色炖菜", "汤羹食品", "锅仔系列", "生猛海鲜", "酒水饮料" };
		jComboBox = new JComboBox(types);
		jComboBox.setFont(font);
		JPanel jPanel4 = new JPanel();
		jPanel4.add(jLabel4);
		jPanel4.add(jComboBox);
		this.add(jPanel4);

		jLabel5 = new JLabel("图        标：");
		jField5 = new JTextField(8);
		jLabel5.setFont(font);
		jField5.setFont(font);
		JPanel jPanel5 = new JPanel();
		jPanel5.add(jLabel5);
		jPanel5.add(jField5);
		this.add(jPanel5);

		jLabel6 = new JLabel("描        述：");
		jField6 = new JTextField(8);
		jLabel6.setFont(font);
		jField6.setFont(font);
		JPanel jPanel6 = new JPanel();
		jPanel6.add(jLabel6);
		jPanel6.add(jField6);
		this.add(jPanel6);

		conJButton = new JButton("添加");
		conJButton.setFont(font);
		conJButton.addActionListener(new ContainListener());
		JPanel jPanel51 = new JPanel();
		jPanel51.add(conJButton);
		this.add(jPanel51);

		canJButton = new JButton("关闭");
		canJButton.setFont(font);
		canJButton.addActionListener(new CancelListener());
		JPanel jPanel52 = new JPanel();
		jPanel52.add(canJButton);
		this.add(jPanel52);

		setSize(450, 300);
		setResizable(false);
		setLocation(500, 200);
		setVisible(true);
	}

	public class ContainListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String number = jField1.getText().trim();
			String name = jField2.getText().trim();
			String price = jField3.getText().trim();
			String type = jComboBox.getSelectedItem().toString();
			String icon = jField5.getText().trim();
			String description = jField6.getText().trim();
			if (number.equals("") || name.equals("") || price.equals("")) {
				JOptionPane.showMessageDialog(null, "不能为空！");
				return;
			}
			if (Dao.isFoodHave(Integer.parseInt(number))) {
				JOptionPane.showMessageDialog(null, "该编号已经存在！");
				jField1.setText("");
				return;
			} else {
				Dao.insertFood(Integer.parseInt(number), name,
						Integer.parseInt(price), type, icon, description);
				JOptionPane.showMessageDialog(null, "添加成功！");
				jField1.setText("");
				jField2.setText("");
				jField3.setText("");
				jField5.setText("");
				jField6.setText("");
			}
		}
	}

	public class CancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
	}
}

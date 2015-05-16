package com.yeyunlin.ui;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.yeyunlin.util.LimitManager;
import com.yeyunlin.util.Tools;

public class PrimaryWindow extends JFrame implements ActionListener {
	JLabel mlabel;
	JPanel jp = new JPanel();// 用来填放子模块
	JMenuBar menu = new JMenuBar();
	JMenu m1 = new JMenu("系统管理");
	JMenu m2 = new JMenu("会员管理");
	JMenu m3 = new JMenu("菜单管理");
	JMenu m4 = new JMenu("订单管理");
	JMenu m5 = new JMenu("评论管理");
	JMenu m6 = new JMenu("更多功能");
	JMenuItem item11 = new JMenuItem("登录");
	JMenuItem item12 = new JMenuItem("退出登录");
	JMenuItem item13 = new JMenuItem("退出系统");
	JMenuItem item21 = new JMenuItem("会员信息查询");
	JMenuItem item22 = new JMenuItem("会员消费记录");
	JMenuItem item31 = new JMenuItem("菜单查看");
	JMenuItem item32 = new JMenuItem("菜单添加");
	JMenuItem item41 = new JMenuItem("历史订单查看");
	JMenuItem item42 = new JMenuItem("未付订单查看");
	JMenuItem item51 = new JMenuItem("评论查看");
	JMenuItem item52 = new JMenuItem("添加好评");
	JMenuItem item61 = new JMenuItem("每日推荐生成");
	JMenuItem item62 = new JMenuItem("统计消费习惯");
	Font myFont = new Font("微软雅黑", Font.PLAIN, 14);

	public PrimaryWindow() {
		super("点餐系统");

		m1.setFont(myFont);
		m2.setFont(myFont);
		m3.setFont(myFont);
		m4.setFont(myFont);
		m5.setFont(myFont);
		m6.setFont(myFont);
		item11.setFont(myFont);
		item12.setFont(myFont);
		item13.setFont(myFont);
		item21.setFont(myFont);
		item22.setFont(myFont);
		item31.setFont(myFont);
		item32.setFont(myFont);
		item41.setFont(myFont);
		item42.setFont(myFont);
		item51.setFont(myFont);
		item52.setFont(myFont);
		item61.setFont(myFont);
		item62.setFont(myFont);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		Container c = getContentPane();
		setSize(640, 360);
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(400, 150);
		c.add(menu);
		menu.add(m1);
		menu.add(m2);
		menu.add(m3);
		menu.add(m4);
		menu.add(m5);
		menu.add(m6);

		// 系统管理菜单项
		m1.add(item11);
		m1.add(item12);
		m1.add(item13);
		item11.addActionListener(this);
		item12.addActionListener(this);
		item13.addActionListener(this);
		// 查询管理菜单项
		m2.add(item21);
		m2.add(item22);
		item21.addActionListener(this);
		item22.addActionListener(this);
		// 书籍管理菜单项
		m3.add(item31);
		m3.add(item32);
		item31.addActionListener(this);
		item32.addActionListener(this);
		// 借阅管理菜单项
		m4.add(item41);
		m4.add(item42);
		item41.addActionListener(this);
		item42.addActionListener(this);
		m5.add(item51);
		m5.add(item52);
		item51.addActionListener(this);
		item52.addActionListener(this);
		m6.add(item61);
		m6.add(item62);
		item61.addActionListener(this);
		item62.addActionListener(this);

		setItemEnable(LimitManager.isLanded());
		// 菜单的放置以及窗口的显示
		setJMenuBar(menu);
		mlabel = new JLabel();
		mlabel.setIcon(new ImageIcon("image/mainback.png"));
		add(mlabel);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == item11) {
			setVisible(false);
			Login login = new Login();
			login.setVisible(true);
		}
		if (event.getSource() == item12) {
			LimitManager.setLanded(false);
			setVisible(false);
			PrimaryWindow primaryWindow = new PrimaryWindow();
			primaryWindow.setVisible(true);
		}
		if (event.getSource() == item13) {
			System.exit(1);
		}
		if (event.getSource() == item21) {
			new UserInformation();
		}
		if (event.getSource() == item22) {
			new UserSpending();
		}
		if (event.getSource() == item31) {
			new FoodLook();
		}
		if (event.getSource() == item32) {
			new AddFood();
		}
		if (event.getSource() == item41) {
			new HistoryOrder();
		}
		if (event.getSource() == item42) {
			new UnpayOrder();
		}
		if (event.getSource() == item51) {
			new GetReview();
		}
		if (event.getSource() == item52) {
			new AddReview();
		}
		if (event.getSource() == item61) {
			Tools tools = new Tools();
			tools.GetTopFood();
		}
		if (event.getSource() == item62) {
			Tools tools = new Tools();
			tools.onInterestingFood("");
		}
	}
	
	private void setItemEnable(boolean isEnable){
		m2.setEnabled(isEnable);
		m3.setEnabled(isEnable);
		m4.setEnabled(isEnable);
		m5.setEnabled(isEnable);
		m6.setEnabled(isEnable);
	}

	public static void main(String[] args) {
		PrimaryWindow primaryFrame = new PrimaryWindow();
		primaryFrame.setVisible(true);
	}
}

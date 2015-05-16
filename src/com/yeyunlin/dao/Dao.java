package com.yeyunlin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yeyunlin.info.FoodInfo;
import com.yeyunlin.info.OrderInfo;
import com.yeyunlin.info.ReviewInfo;
import com.yeyunlin.info.UserInfo;
import com.yeyunlin.util.DBUtil;
import com.yeyunlin.util.Tools;

public class Dao {
	public static boolean checkPassword(String name, String password) {
		String sql = " select * from managers where name = ? and password = ?";
		DBUtil dbUtil = new DBUtil();
		Connection connection = dbUtil.openConn();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			return resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConn(connection);
		}
		return false;
	}

	public static List<UserInfo> getAllUsers() {
		List<UserInfo> userInfos = new ArrayList<UserInfo>();
		String sql;
		sql = " select * from users";
		DBUtil dbUtil = new DBUtil();
		Connection connection = dbUtil.openConn();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				UserInfo userInfo = new UserInfo();
				userInfo.setName(resultSet.getString(1));
				userInfo.setAccount(resultSet.getString(2));
				userInfo.setPassword(resultSet.getString(3));
				userInfo.setIntegral(resultSet.getInt(4));
				userInfo.setIcon(resultSet.getString(5));
				userInfos.add(userInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConn(connection);
		}
		return userInfos;
	}

	public static List<OrderInfo> getAllOrder(String name) {
		List<OrderInfo> orderInfos = new ArrayList<OrderInfo>();
		String sql;
		if (name.equals("")) {
			sql = " select * from foodorder";
		} else {
			sql = " select * from foodorder where user_name = '" + name + "' ";
		}
		DBUtil dbUtil = new DBUtil();
		Connection connection = dbUtil.openConn();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				OrderInfo orderInfo = new OrderInfo();
				orderInfo.setOrderId(resultSet.getString(1));
				orderInfo.setUsername(resultSet.getString(2));
				orderInfo.setFoodid(resultSet.getInt(3));
				orderInfo.setDeskid(resultSet.getInt(4));
				orderInfo.setTime(resultSet.getString(5));
				orderInfo.setPaytype(resultSet.getString(6));
				orderInfos.add(orderInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConn(connection);
		}
		return orderInfos;
	}

	public static List<OrderInfo> getUnpayOrder() {
		List<OrderInfo> orderInfos = new ArrayList<OrderInfo>();
		String sql;
		sql = " select * from foodorder where paytype = ? ";
		DBUtil dbUtil = new DBUtil();
		Connection connection = dbUtil.openConn();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			preparedStatement.setString(1, "0");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				OrderInfo orderInfo = new OrderInfo();
				orderInfo.setOrderId(resultSet.getString(1));
				orderInfo.setUsername(resultSet.getString(2));
				orderInfo.setFoodid(resultSet.getInt(3));
				orderInfo.setDeskid(resultSet.getInt(4));
				orderInfo.setTime(resultSet.getString(5));
				orderInfo.setPaytype(resultSet.getString(6));
				orderInfos.add(orderInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConn(connection);
		}
		return orderInfos;
	}

	public static String getFoodName(int number) {
		String sql;
		sql = " select name from food where number = ?";
		DBUtil dbUtil = new DBUtil();
		Connection connection = dbUtil.openConn();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			preparedStatement.setInt(1, number);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConn(connection);
		}
		return null;
	}

	public static List<FoodInfo> getFoods(String type) {
		List<FoodInfo> foodInfos = new ArrayList<FoodInfo>();
		String sql;
		sql = " select * from food where type = ?";
		DBUtil dbUtil = new DBUtil();
		Connection connection = dbUtil.openConn();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			preparedStatement.setString(1, type);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				FoodInfo foodInfo = new FoodInfo();
				foodInfo.setNumber(resultSet.getInt(1));
				foodInfo.setName(resultSet.getString(2));
				foodInfo.setPrice(resultSet.getInt(3));
				foodInfo.setType(resultSet.getString(4));
				foodInfo.setIcon(resultSet.getString(5));
				foodInfo.setDescription(resultSet.getString(6));
				foodInfos.add(foodInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foodInfos;
	}

	public static boolean isFoodHave(int number) {
		String sql;
		sql = " select name from food where number = ?";
		DBUtil dbUtil = new DBUtil();
		Connection connection = dbUtil.openConn();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			preparedStatement.setInt(1, number);
			ResultSet resultSet = preparedStatement.executeQuery();
			return resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConn(connection);
		}
		return false;
	}

	public static void insertFood(int number, String name, int price,
			String type, String icon, String description) {
		String sql = " insert into food(number , name , price , type , icon , description )values(?,?,?,?,?,?) ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConn();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);

			preparedStatement.setInt(1, number);
			preparedStatement.setString(2, name);
			preparedStatement.setInt(3, price);
			preparedStatement.setString(4, type);
			preparedStatement.setString(5, icon);
			preparedStatement.setString(6, description);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
	}

	public static List<ReviewInfo> getReviews() {
		List<ReviewInfo> reviewInfos = new ArrayList<ReviewInfo>();
		String sql;
		sql = " select * from reviews";
		DBUtil dbUtil = new DBUtil();
		Connection connection = dbUtil.openConn();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ReviewInfo reviewInfo = new ReviewInfo();
				reviewInfo.setName(resultSet.getString(1));
				reviewInfo.setContent(resultSet.getString(2));
				reviewInfo.setTime(resultSet.getString(3));
				reviewInfos.add(reviewInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConn(connection);
		}
		return reviewInfos;
	}

	public static void addReview(String name, String content, String time) {
		String sql = " insert into reviews(name, content, time)values(?,?,?) ";
		DBUtil dbUtil = new DBUtil();
		Connection connection = dbUtil.openConn();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, content);
			preparedStatement.setString(3, time);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConn(connection);
		}
	}

	public static Map getAllFoodId() {
		String sql;
		sql = " select food_id from foodorder";
		DBUtil dbUtil = new DBUtil();
		Connection connection = dbUtil.openConn();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			Map foodIdMap = new HashMap();
			while (resultSet.next()) {
				int foodId = resultSet.getInt(1);
				if (foodIdMap.containsKey(foodId)) {
					int value = (int) foodIdMap.get(foodId);
					foodIdMap.put(foodId, value + 1);
				} else {
					foodIdMap.put(foodId, 1);
				}
			}
			return foodIdMap;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConn(connection);
		}
		return null;
	}

	public static Map getOrderesFoodId(String name) {
		String sql;
		sql = " select food_id from foodorder where user_name = ?";
		DBUtil dbUtil = new DBUtil();
		Connection connection = dbUtil.openConn();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			Map foodIdMap = new HashMap();
			while (resultSet.next()) {
				int foodId = resultSet.getInt(1);
				if (foodIdMap.containsKey(foodId)) {
					int value = (int) foodIdMap.get(foodId);
					foodIdMap.put(foodId, value + 1);
				} else {
					foodIdMap.put(foodId, 1);
				}
			}
			return foodIdMap;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConn(connection);
		}
		return null;
	}

	public static void deleteAllInteresting() {
		String sql = " delete from allinteresting ";
		DBUtil dbUtil = new DBUtil();
		Connection connection = dbUtil.openConn();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConn(connection);
		}
	}
	
	public static void insertInteresting(int[] array){
		String sql = " insert into allinteresting(food_id)values(?) ";
		DBUtil dbUtil = new DBUtil();
		Connection connection = dbUtil.openConn();
		try {
			connection.setAutoCommit(false);
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			int i = 0;
			while (i < Tools.ALL_INTERESTING_NUMBER) {
				preparedStatement.setInt(1, array[i]);
				preparedStatement.addBatch();
				i++;
			}
			preparedStatement.executeBatch();
			connection.commit();
			preparedStatement.clearBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConn(connection);
		}
	}
	
	public static void insertUserInteresting(String name , int[] array){
		String sql = " insert into userinteresting(user_name, food_id)values(?, ?) ";
		DBUtil dbUtil = new DBUtil();
		Connection connection = dbUtil.openConn();
		try {
			connection.setAutoCommit(false);
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			int i = 0;
			while (i < Tools.USER_INTERESTING_NUMBER) {
				preparedStatement.setString(1, name);
				preparedStatement.setInt(2, array[i]);
				preparedStatement.addBatch();
				i++;
			}
			preparedStatement.executeBatch();
			connection.commit();
			preparedStatement.clearBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConn(connection);
		}
	}
	
	public static List<String> getSimilarName(int foodId){
		List<String> userNames = new ArrayList<String>();
		String sql = " select user_name from userinteresting where food_id = ? ";
		DBUtil dbUtil = new DBUtil();
		Connection connection = dbUtil.openConn();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			preparedStatement.setInt(1, foodId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String name = resultSet.getString(1);
				userNames.add(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConn(connection);
		}
		return userNames;
	}
	
	public static void addRecommemdFood(int id) {
		String sql = " insert into recommemd(food_id)values(?) ";
		DBUtil dbUtil = new DBUtil();
		Connection connection = dbUtil.openConn();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConn(connection);
		}
	}
}

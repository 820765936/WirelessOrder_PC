package com.yeyunlin.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.yeyunlin.dao.Dao;
import com.yeyunlin.info.UserInfo;

public class Tools {
	private Map userLikeMap = new HashMap();
	private Map oldDegreeMap = new LinkedHashMap();
	private Map foodIdMap;
	private int[] userFoodId;
	private static final int COUNT_LIMIT = 10;
	public static final int ALL_INTERESTING_NUMBER = 10;
	public static final int USER_INTERESTING_NUMBER = 6;
	public static final int RELATION_USER_NUMBER = 3;

	public void GetTopFood() {
		foodIdMap = Dao.getAllFoodId();
		int[] array = getSortArrar(ALL_INTERESTING_NUMBER);

		Dao.deleteAllInteresting();
		Dao.insertInteresting(array);
		System.out.println("------------------");
		for (int j = 0; j < array.length; j++) {
			System.out.println(array[j] + ":" + foodIdMap.get(array[j]));
		}
	}

	public void onInterestingFood(String userName) {
		List<UserInfo> userInfos = Dao.getAllUsers();
		for (UserInfo userInfo : userInfos) {
			String name = userInfo.getName(); 
			foodIdMap = Dao.getOrderesFoodId(name);
			if (foodIdMap.size() > COUNT_LIMIT) {
				//通过堆排序获取用户最常点的几个菜
				int[] array = getSortArrar(USER_INTERESTING_NUMBER);
				if (name.equals(userName)) {
					userFoodId = array;
				}
				//将用户名 喜欢的食物放入map中
				userLikeMap.put(name, array);
				//插入每个用户最常点的前几个食物
				Dao.insertUserInteresting(name, array);
			}
		}
		//通过该用户的喜欢食物的反查表得到与该用户相似的用户
		Set<String> similarUsers = getSimilarUser(userName);
		//计算所有最相邻的用户与该用户的相似度
		for (String similarName : similarUsers) {
			int[] array1 = (int[]) userLikeMap.get(userName);
			int[] array2 = (int[]) userLikeMap.get(similarName);
			int degree = (int) (100*getSimilarDegree(array1, array2));
			//将用户名 相似度放入到map中
			oldDegreeMap.put(similarName, degree);
		}
		if (oldDegreeMap.size() > RELATION_USER_NUMBER) {
			//对相似度进行排序
			oldDegreeMap = sortMap(oldDegreeMap);
			//获取相似度前RELATION_USER_NUMBER的食物编号
			Set<String> foods = getInterestingFoodId(oldDegreeMap);
			//将这些食物编号写入数据库作为推荐食物
			for (String string : foods) {
				Dao.addRecommemdFood(Integer.parseInt(string));
			}
		}
	}
	
	public Set<String> getInterestingFoodId(Map map){
		Set<String> foodIdList = new HashSet<String>();
		Iterator iterator = map.entrySet().iterator();
		for (int i = 0; i < RELATION_USER_NUMBER; i++) {
			Entry entry = (Entry) iterator.next();
			String name = (String) entry.getKey();
			int [] array = (int[]) userLikeMap.get(name);
			for (int j = 0; j < array.length; j++) {
				foodIdList.add(array[i]+"");
			}
		}
		for (int i = 0; i < userFoodId.length; i++) {
			foodIdList.remove(userFoodId[i]+"");
		}
		return foodIdList;
	}
	
	public Map sortMap(Map oldMap){
		ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(oldMap.entrySet());  
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {  
  
            @Override  
            public int compare(Entry<java.lang.String, Integer> arg0,  
                    Entry<java.lang.String, Integer> arg1) {  
                return arg0.getValue() - arg1.getValue();  
            }  
        });  
        Map newMap = new LinkedHashMap();  
        for (int i = 0; i < list.size(); i++) {  
            newMap.put(list.get(i).getKey(), list.get(i).getValue());  
        }  
        return newMap;  
    }  
	
	public Set<String> getSimilarUser(String userName){
		Set<String> names = new HashSet<String>();
		for (int i = 0; i < userFoodId.length; i++) {
			List<String> userNames = Dao.getSimilarName(userFoodId[i]);
			for (String name : userNames) {
				names.add(name);
			}
		}
		names.remove(userName);
		return names;
	}
	
	public float getSimilarDegree(int[] array1 , int[] array2){
		int counts = array1.length + array2.length;
		Set<String> foodIdSet = new HashSet<String>();
		for (int i = 0; i < array1.length; i++) {
			foodIdSet.add(array1[i]+"");
		}
		for (int i = 0; i < array2.length; i++) {
			foodIdSet.add(array2[i]+"");
		}
		int union = foodIdSet.size();
		int mixed = counts - union;
		return (float)mixed/union;
	}

	public int[] getSortArrar(int number) {
		int[] array = new int[number];
		int i = 0;
		Iterator iterator = foodIdMap.keySet().iterator();
		while (iterator.hasNext()) {
			Object object = iterator.next();
			int count = (int) foodIdMap.get(object);
			if (i < (number - 1)) {
				array[(number - 1) - i] = (int) object;
			} else if (i == (number - 1)) {
				array[0] = (int) object;
				Sort(array);
			} else if (count > (int) foodIdMap.get(array[0])) {
				array[0] = (int) object;
				Sort(array);
			}
			i++;
		}
		return array;
	}

	public void swap(int[] array, int i, int j) {
		if (i != j) {
			int tmp = array[j];
			array[j] = array[i];
			array[i] = tmp;
		}
	}

	public void Sort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			creatMaxHeap(array, array.length - 1 - i);
			swap(array, 0, array.length - 1 - i);
		}
	}

	public void creatMaxHeap(int[] array, int lastIndex) {
		for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
			int k = i;
			while (2 * k + 1 <= lastIndex) {
				int big = 2 * k + 1;
				if (big < lastIndex) {
					int left = (int) foodIdMap.get(array[big]);
					int right = (int) foodIdMap.get(array[big + 1]);
					if (left < right) {
						big++;
					}
				}
				int father = (int) foodIdMap.get(array[k]);
				int son = (int) foodIdMap.get(array[big]);
				if (father < son) {
					swap(array, k, big);
					k = big;
				} else {
					break;
				}
			}
		}
	}
}

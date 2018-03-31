package com.lj.gps.frame.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CombinationUtils {

	
	// 去掉重复元素,放入list
	public static void removeDuplicate(String s, Set<Character> list){
		for(char x:s.toCharArray()){
			list.add(x);
		}
	}
	// 将 sets 转 list 
	public static void convert(List<Character> list, Set<Character> sets){
		Iterator<Character> iter = sets.iterator();
		while(iter.hasNext()){
			list.add(iter.next());
		}
	}
	// 检测符合条件的元素组合
	public static List<String> check(Set<Character> sets){
		List<String> reslist = new ArrayList<String>();
		List<Character> list = new ArrayList<Character>();
		convert(list,sets);	// 为方便操作 将 sets 转 list 
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<list.size()-2;i++){
			for(int j=i+1;j+1<list.size();j++){	// 向后添加两位,所以 j+1<list()
				for(int k=j+1;k<list.size();k++){
					sb.append(list.get(i));
					sb.append(list.get(j));
					sb.append(list.get(k));
					reslist.add(sb.toString());
					sb.setLength(0);	// 清空
				}
			}
		}
		return reslist;	
	}
	
//	public static void main(String[] args){
//
//		Set<Character> sets = new LinkedHashSet<Character>();
//		for(int i=0;i<6;i++){
//			removeDuplicate(i+"",sets);	// 去掉重复元素,放入lis
//		}
//		List<String> list = check(sets);
//		System.out.println(list.size());	// 检测符合条件的元素组合
//		System.out.println(list.toString());
//	}

}

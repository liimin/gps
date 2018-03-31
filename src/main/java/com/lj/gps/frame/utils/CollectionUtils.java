package com.lj.gps.frame.utils;

import java.util.*;

/**
 * 容器工具类
 *
 * @author Jay
 * @version 1.0
 * @history created by Jay 2014年9月28日
 */
public class CollectionUtils extends org.apache.commons.collections.CollectionUtils {

    /**
     * 去除列表中重复的元素
     *
     * @param list
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void removeDuplicate(List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
    }

    /**
     * 查找值最小的key
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static String getKeyByMinValue(Map<String, Double> map) {
        TreeMap<Double, String> treeMap = new TreeMap<Double, String>();
        Iterator keys = map.keySet().iterator();
        while (keys.hasNext()) {
            Object key = keys.next();
            treeMap.put(map.get(key), key.toString());
        }
        return treeMap.firstEntry().getValue();
    }

    public static Set<String> getSames(String[] a, String[] b) {
        Set<String> same = new HashSet<String>();  //用来存放两个数组中相同的元素
        Set<String> temp = new HashSet<String>();  //用来存放数组a中的元素

        for (int i = 0; i < a.length; i++) {
            temp.add(a[i]);   //把数组a中的元素放到Set中，可以去除重复的元素
        }

        for (int j = 0; j < b.length; j++) {
            //把数组b中的元素添加到temp中
            //如果temp中已存在相同的元素，则temp.add（b[j]）返回false
            if (!temp.add(b[j]))
                same.add(b[j]);
        }
        return same;
    }

    public static <T> List<T> compare(T[] t1, T[] t2) {
        List<T> list1 = Arrays.asList(t1);
        List<T> list2 = new ArrayList<T>();
        for (T t : t2) {
            if (!list1.contains(t)) {
                list2.add(t);
            }
        }
        return list2;
    }

    public static Object[] List2Array(List<Object> oList) {
        Object[] oArray = oList.toArray(new Object[]{});
        // 需要在用到的时候另外写方法，不支持泛型的Array.   
        return oArray;
    }

    public static Object[] Set2Array(Set<Object> oSet) {
        Object[] oArray = oSet.toArray(new Object[]{});
        // 需要在用到的时候另外写方法，不支持泛型的Array.   
        return oArray;
    }

    public static <T extends Object> List<T> Set2List(Set<T> oSet) {
        List<T> tList = new ArrayList<T>(oSet);
        // 需要在用到的时候另外写构造，根据需要生成List的对应子类。   
        return tList;
    }

    public static <T extends Object> List<T> Array2List(T[] tArray) {
        List<T> tList = Arrays.asList(tArray);
        // 单纯的asList()返回的tList无法add(),remove(),clear()等一些影响集合个数的操作，   
        // 因为Arrays$ArrayList和java.util.ArrayList一样，都是继承AbstractList，   
        // 但是Arrays$ArrayList没有override这些方法，而java.util.ArrayList实现了。   
        // 建议使用List的子类做返回，而不是Arrays$ArrayList。根据需要吧。如下行注释:   
        // List<T> tList = new ArrayList<T>(Arrays.asList(tArray));   
        return tList;
    }

    public static <T extends Object> Set<T> List2Set(List<T> tList) {
        Set<T> tSet = new HashSet<T>(tList);
        //具体实现看需求转换成不同的Set的子类。   
        return tSet;
    }

    public static <T extends Object> Set<T> Array2Set(T[] tArray) {
        Set<T> tSet = new HashSet<T>(Arrays.asList(tArray));
        // 没有一步到位的方法，根据具体的作用，选择合适的Set的子类来转换。   
        return tSet;
    }

//    public static void main(String[] args) {
//
//        Integer[] array2 = {1, 2, 3, 4, 44};
//        Integer[] array1 = {1, 2, 3, 41};
//
//
//        List<Integer> list = compare(array1, array2);
//        for (Integer integer : list) {
//            System.out.println(integer);
//        }
//    }
}

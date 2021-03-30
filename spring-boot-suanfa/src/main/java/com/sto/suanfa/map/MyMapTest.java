/**
 * Copyright (C), 2015-2021, 优度宽带有限公司
 * FileName: MyMapTest
 * Author:   admin
 * Date:     2021/1/6 16:18
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sto.suanfa.map;

import java.util.HashSet;
import java.util.Set;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author admin
 * @create 2021/1/6
 * @since 1.0.0
 */
public class MyMapTest {
    public static void main(String[] args) {
        MyMap<String, String> myMap1 = new MyMap();
        System.out.println(myMap1.threshold);
        System.out.println(myMap1.loadFactor());
        System.out.println( myMap1.capacity());
        myMap1.put("key1", "value1");
        System.out.println(myMap1.threshold);
        System.out.println( myMap1.capacity());

        MyMap<String, String> myMap = new MyMap(8, 0.5F);
        System.out.println(myMap.threshold);
        System.out.println(myMap.loadFactor());
        System.out.println( myMap.capacity());
        myMap.put("key1", "value1");
        myMap.put("key2", "value1");
        myMap.put("key3", "value1");
        myMap.put("key4", "value1");
        String value1 = myMap.get("key1");
        float v = myMap.loadFactor();
        System.out.println(v);
        System.out.println( myMap.capacity());
        System.out.println(myMap.threshold);
        Set<String> keySet = myMap.keySet();
        String next = keySet.iterator().next();
        System.out.println(next);

    }
}

/**
 * Copyright (C), 2015-2020, 优度宽带有限公司
 * FileName: svgTest
 * Author:   admin
 * Date:     2020/8/3 15:50
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sto.suanfa.capter1;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author admin
 * @create 2020/8/3
 * @since 1.0.0
 */
public class svgTest {

    static int n = 10;
    static int[] array = new int[n];
    static int count = 0;

    static void insert(int val){
        if (count == array.length) {
            int sum = 0;
            for (int i = 0; i < array.length; i++) {
                sum = sum + array[i];
            }
            array[0] = sum;
            count = 1;
        }
        array[count] = val;
        ++count;
        System.out.println(array[0]);
        System.out.println(array[1]);
    }

    public static void main(String[] args) {
        insert(10);
    }
}

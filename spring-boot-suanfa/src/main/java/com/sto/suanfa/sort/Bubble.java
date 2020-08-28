/**
 * Copyright (C), 2015-2020, 优度宽带有限公司
 * FileName: Bubble
 * Author:   admin
 * Date:     2020/8/24 11:06
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sto.suanfa.sort;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author admin
 * @create 2020/8/24
 * @since 1.0.0
 */
public class Bubble {

    // 冒泡排序，a 表示数组，n 表示数组大小
    public void bubbleSort(int[] a, int n){
        if (n <= 1 ) return;
        for (int i = 0; i < n; i++) {
            // 提前退出冒泡循环的标志位
            boolean flag = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (a[j] > a[j+1]) {  // 交换数据
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                    flag = true; // 表示有数据交换
                }
            }
            if (!flag) break;  // 没有数据交换，提前退出
        }
    }

    // 插入排序，a 表示数组，n 表示数组大小
    public void insertionSort(int[] a, int n){
        if (n <= 1 ) return;
        for (int i = 1; i < n; i++) {
            int value = a[i];
            int j = i - 1;
            // 查找插入的位置
            for (; j >=0; --j) {
                if (a[j] > value) {
                    a[j+1] = a[j];
                }else break;
            }
            a[j+1] = value;
        }

    }

    public static void main(String[] args) {
        int[] a = new int[]{4,5,6,3,2,7,0};
        Bubble bubble = new Bubble();
        System.out.println("排序前："+bubble.toString(a));
        // bubble.bubbleSort(a, 7);
        // System.out.println("冒泡排序后："+bubble.toString(a));
        bubble.insertionSort(a, 7);
        System.out.println("插入排序后："+bubble.toString(a));
    }

    public String toString(int[] items) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.length; i++) {
            sb.append(items[i]);
            if (i < items.length - 1) {
                sb.append(" , ");
            }
        }
        return "bubbleSort [" + sb.toString() +"]";
    }
}

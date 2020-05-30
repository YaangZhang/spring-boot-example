/**
 * Copyright (C), 2018-2019, 申通快递有限公司
 * FileName: SuanFaTest
 * Author:   thinkpad
 * Date:     2019-07-08 21:58
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sto;
/**
 * Created by thinkpad on 2019-07-08.
 */

import java.util.Arrays;
import java.util.Random;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author thinkpad
 * @create 2019-07-08
 * @since 1.0.0
 */
public class SuanFaTest {
    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            Random random = new Random();
            arr[i] = random.nextInt(100);
        }
        System.out.println(Arrays.toString(arr));
        /**
         * **************冒泡排序***************************8
         */
        System.out.println("****************冒泡排序开始****************************");
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int t = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = t;
                }
            }
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("****************冒泡排序结束****************************");
        System.out.println(Arrays.toString(arr));

    }
}

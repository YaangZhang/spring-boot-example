/**
 * Copyright (C), 2015-2021, 优度宽带有限公司
 * FileName: listTest
 * Author:   admin
 * Date:     2021/3/1 16:14
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sto.suanfa.list;

import java.util.Vector;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author admin
 * @create 2021/3/1
 * @since 1.0.0
 */
public class listTest {
    public static void main(String[] args) {
        Vector vector = new Vector();

        vector.add(123);
        System.out.println(vector.get(0));
        vector.set(0, "qwer");
        System.out.println(vector.get(0));
    }
}

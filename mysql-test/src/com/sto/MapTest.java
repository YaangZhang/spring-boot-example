/**
 * Copyright (C), 2018-2019, 申通快递有限公司
 * FileName: MapTest
 * Author:   thinkpad
 * Date:     2019-07-06 17:20
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sto;
/**
 * Created by thinkpad on 2019-07-06.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author thinkpad
 * @create 2019-07-06
 * @since 1.0.0
 */
public class MapTest {
    public static void main(String[] args) {
        Map map = new HashMap(2);
        map.put("abc", 10);
        map.put("price", 12.34);
        map.put("name", "lock");

        System.out.println(map);
    }
}

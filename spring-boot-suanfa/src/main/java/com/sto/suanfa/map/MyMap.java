/**
 * Copyright (C), 2015-2021, 优度宽带有限公司
 * FileName: MyMap
 * Author:   admin
 * Date:     2021/1/6 16:19
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sto.suanfa.map;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author admin
 * @create 2021/1/6
 * @since 1.0.0
 */
public class MyMap<K,V> extends AbstractMap<K,V> implements Map<K,V>, Cloneable, Serializable {
    private static final long serialVersionUID = 362498820763181265L;

    /**
     * 默认初始化容量大小，必须为2的指数.
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

    /**
     * 默认容量最大值，必须为2的指数.
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * 默认扩容因子.
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * 链表转树结构的临界值，最小为8.
     */
    static final int TREEIFY_THRESHOLD = 8;

    /**
     * 树转列表临界值，比TREEIFY_THRESHOLD小.
     */
    static final int UNTREEIFY_THRESHOLD = 6;

    /**
     * The smallest table capacity for which bins may be treeified.
     * (Otherwise the table is resized if too many nodes in a bin.)
     * Should be at least 4 * TREEIFY_THRESHOLD to avoid conflicts
     * between resizing and treeification thresholds.
     */
    static final int MIN_TREEIFY_CAPACITY = 64;
    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}

/**
 * Copyright (C), 2018-2019, 申通快递有限公司
 * FileName: MyHashMap
 * Author:   thinkpad
 * Date:     2019-07-11 21:49
 * Description: 手写hashMap
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sto.map;
/**
 * Created by thinkpad on 2019-07-11.
 */

/**
 * 〈一句话功能简述〉<br> 
 * 〈手写hashMap〉
 *
 * @author thinkpad
 * @create 2019-07-11
 * @since 1.0.0
 */
public class MyHashMap<K, V> {
    //底层用数组来保存数据对象
    private Entry<K, V>[] table;
    //默认初始化大小 为 8
    private static final Integer CAPACITY = 8;

    public void put(K k, V v){
        Entry entry = new Entry<K, V>();
        if (null == table) {
            inflate();
        }
        //存entry
        int hashCode = hash(k);
        int index = indexFor(hashCode);
        addEntry(index, entry);
    }

    //存入到数组
    private void addEntry(int index, Entry entry) {
        table[index] = entry;
    }

    //根据k计算在数组中的位置
    private int indexFor(int hashCode){
        return hashCode % table.length;
    }

    //自定义的hash算法
    private int hash(K k){
        return k.hashCode();
    }
    //数组初始化
    private void inflate(){
        table = new Entry[CAPACITY];
    }

    class Entry<K, V>{
        public K key;
        public V value;

        public Entry() {
        }

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

}

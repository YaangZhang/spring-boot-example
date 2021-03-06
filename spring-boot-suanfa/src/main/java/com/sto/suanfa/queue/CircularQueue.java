/**
 * Copyright (C), 2015-2020, 优度宽带有限公司
 * FileName: CircularQueue
 * Author:   admin
 * Date:     2020/8/20 17:34
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sto.suanfa.queue;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author admin
 * @create 2020/8/20
 * @since 1.0.0
 */
public class CircularQueue {
    // 数组：items，数组大小：n
    private String[] items;
    private int n = 0;
    // head 标识队头下标， tail 标识队尾下标
    private int head = 0;
    private int tail = 0;

    // 申请一个大小为 capacity 的数组
    public CircularQueue(int capacity) {
        items = new String[capacity];
        this.n = capacity;
    }

    // 入队
    public boolean enqueue(String item){
        // 队列满了
        if ((tail + 1) % n == head) return false;
        items[tail] = item;
        tail = (tail + 1) % n;
        return true;
    }

    // 出队
    public String dequeue(){
        // 如果 head == tail 标识队列为空
        if ( head == tail) return null;
        head = (head + 1) % n;
        return items[head];
    }

    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue(5);
        boolean a = queue.enqueue("a");
        System.out.println(queue);
        boolean b = queue.enqueue("b");
        System.out.println(queue);
        boolean c = queue.enqueue("c");
        System.out.println(queue);
        System.out.println("出队 head = "+queue.head);
        // String dequeue = queue.dequeue();
        // System.out.println("出队："+dequeue+", head = "+queue.head);
        // System.out.println(queue);
        // System.out.println("出队2 head = "+queue.head);
        // String dequeue2 = queue.dequeue();
        // System.out.println("出队2："+dequeue2+", head = "+queue.head);
        // System.out.println(queue);
        // String dequeue3 = queue.dequeue();
        // System.out.println("出队3："+dequeue3+", head = "+queue.head);
        // System.out.println(queue);

        boolean d = queue.enqueue("d");
        System.out.println(queue);
        boolean e = queue.enqueue("e");
        System.out.println(queue);
        boolean f = queue.enqueue("f");
        System.out.println(queue);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.length; i++) {
            sb.append(items[i]);
            if (i < items.length - 1) {
                sb.append(" , ");
            }
        }
        return "ArrayQueue [" + sb.toString() +"]";
    }
}

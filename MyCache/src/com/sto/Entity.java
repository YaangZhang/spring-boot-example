package com.sto;

import java.util.concurrent.Future;

/**
     * 缓存实体类
     */
    public class Entity{
        //键值对的value
        private Object value;
        //定时器Future
        private Future future;

        public Entity(Object value, Future future) {
            this.value = value;
            this.future = future;
        }

        public Object getValue() {
            return value;
        }

        public Future getFuture() {
            return future;
        }

        @Override
        public String toString() {
            return "Entity{" +
                    "value=" + value +
                    ", future=" + future +
                    '}';
        }
    }

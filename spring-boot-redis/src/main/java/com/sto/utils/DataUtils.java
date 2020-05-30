package com.sto.utils;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataUtils {

    /**
     * 生成指定长度的随机字符串
     *
     * @param len 长度
     * @return 随机字符串
     */
    public final static String getRandomString(int len) {
        String buffer = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return getRandom(buffer, len);
    }

    /**
     * 生成指定长度的随机字符串
     *
     * @param len 长度
     * @return 随机字符串
     */
    public final static String getRandomNumber(int len) {
        String buffer = "0123456789";
        return getRandom(buffer, len);
    }

    private final static String getRandom(String buffer, int len) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int range = buffer.length();
        for (int i = 0; i < len; i++) {
            sb.append(buffer.charAt(random.nextInt(range)));
        }
        return sb.toString().trim();
    }

    /**
     * get uuid string
     *
     * @param splitEnable split enable
     * @return uuid string
     */
    public final static String getUUIDString(boolean splitEnable) {
        String result = UUID.randomUUID().toString().toUpperCase();
        if (!splitEnable) {
            result = result.replace("-", "");
        }
        return result;
    }

    /**
     * get word count for zh-cn
     *
     * @param word word
     * @return word count
     * @throws Exception exception info
     */
    public final static int getWordCountForZHCN(String word) throws Exception {

        /*
         * data init
         */
        int count = 0;

        /*
         * validation
         */
        boolean status = word != null && !(word = word.replace(" ", "")).isEmpty();
        if (status) {
            final String regEx = "[\u4e00-\u9fa5]";
            Pattern pattern = Pattern.compile(regEx);
            Matcher matcher = pattern.matcher(word);
            while (matcher.find()) {
                for (int i = 0; i <= matcher.groupCount(); i++) {
                    count = count + 1;
                }
            }
        }

        /*
         * return
         */
        return count;
    }

    /**
     * get word count for zh-cn
     *
     * @param word      word
     * @param duplicate duplicate enable
     * @return word count
     * @throws Exception exception info
     */
    public final static int getWordCountForZHCN(String word, boolean duplicate) throws Exception {
        /*
         * data init
         */
        int count = 0;

        /*
         * validation
         */
        boolean status = word != null && !(word = word.replace(" ", "")).isEmpty();
        if (status) {
            if (!duplicate) {
                StringBuilder stringBuilder = new StringBuilder();
                Set<String> dataSet = new HashSet<>();
                for (int i = 0; i < word.length(); i++) {
                    char data = word.charAt(i);
                    String temp = String.valueOf(data);
                    if (!dataSet.contains(temp)) {
                        stringBuilder.append(data);
                        dataSet.add(temp);
                    }
                }
                word = stringBuilder.toString();
            }
            count = getWordCountForZHCN(word);
        }

        /*
         * return
         */
        return count;
    }

    /**
     * whether the data is empty
     *
     * @param data data
     * @return empty status
     */
    public final static boolean isEmpty(String data) {

        /*
         * execute
         */
        boolean status = data == null || (data.replace(" ", "")).isEmpty() || data.equalsIgnoreCase("null");

        /*
         * return
         */
        return status;
    }

    /**
     * whether the data is less than the value
     *
     * @param data data
     * @param val  value
     * @return less status
     */
    public final static boolean isLt(int data, int val) {
        /*
         * execute
         */
        boolean status = data < val;

        /*
         * return
         */
        return status;
    }

    /**
     * whether the data is less or equals the value
     *
     * @param data data
     * @param val  value
     * @return less or equals status
     */
    public final static boolean isLte(double data, int val) {
        /*
         * execute
         */
        boolean status = data <= val;

        /*
         * return
         */
        return status;
    }

    /**
     * get field from field array
     *
     * @param fieldArray field array
     * @param name       name
     * @return field
     */
    public static final Field getField(Field[] fieldArray, String name) {

        /*
         * data init
         */
        Field field = null;

        /*
         * validation
         */
        if (isEmpty(name)) {
            return null;
        }
        if (fieldArray == null || fieldArray.length == 0) {
            return null;
        }

        /*
         * execute
         */
        for (Field fieldItem : fieldArray) {
            if (fieldItem.getName().equalsIgnoreCase(name)) {
                field = fieldItem;
                field.setAccessible(true);
                break;
            }
        }

        /*
         * return
         */
        return field;
    }


    /**
     * make sort action offset to collection
     *
     * @param collection  data collection
     * @param entityClass entity class
     * @param name        name
     * @param asc         asc:true, desc:false
     * @param <T>         entity class
     * @return collection after sorting action
     * @throws Exception exception info
     */
    public static final <T> List<T> sort(List<T> collection, Class<T> entityClass, String name, final boolean asc) throws Exception {
        return sort(collection, entityClass, name, String.class, asc);
    }

    /**
     * make sort action offset to collection
     *
     * @param collection  data collection
     * @param entityClass entity class
     * @param name        name
     * @param nameClass   name class
     * @param asc         asc:true, desc:false
     * @param <T>         entity class
     * @param <P>         entity class
     * @return collection after sorting action
     * @throws Exception exception info
     */
    public static final <T, P> List<T> sort(List<T> collection, final Class<T> entityClass, String name, final Class<P> nameClass, final boolean asc) throws Exception {

        /*
         * data init
         */
        Field field = null;
        boolean status = collection != null && !collection.isEmpty();

        /*
         * get field
         */
        if (status) {
            Class<?> entityClassSuperclass = null;
            Field[] fieldArray = entityClass.getDeclaredFields();
            while (fieldArray == null || (field = getField(fieldArray, name)) == null) {
                if (entityClassSuperclass == null) {
                    entityClassSuperclass = entityClass.getSuperclass();
                } else {
                    entityClassSuperclass = entityClassSuperclass.getSuperclass();
                }
                if (entityClassSuperclass != null) {
                    fieldArray = entityClassSuperclass.getDeclaredFields();
                    continue;
                } else {
                    break;
                }
            }
            status = field != null;
        }

        /*
         * execute
         */
        if (status) {

            /*
             * data init
             */
            final Field fieldResult = field;

            /*
             * execute
             */
            Collections.sort(collection, new Comparator<T>() {
                @Override
                public int compare(T o1, T o2) {

                    try {

                        /*
                         * data init
                         */
                        Object object1 = fieldResult.get(o1);
                        Object object2 = fieldResult.get(o2);
                        boolean status = object1 != null && object2 != null;

                        /*
                         * validation
                         */
                        if (status) {
                            if (Number.class.isAssignableFrom(nameClass)) {
                                Number values1 = (Number) object1;
                                Number values2 = (Number) object2;
                                if (values1 == values2) {
                                    return 0;
                                } else {
                                    if (asc) {
                                        return values1.doubleValue() < values2.doubleValue() ? -1 : 1;
                                    } else {
                                        return values1.doubleValue() < values2.doubleValue() ? 1 : -1;
                                    }
                                }
                            }
                            if (String.class.isAssignableFrom(nameClass)) {
                                String values1 = object1.toString();
                                String values2 = object2.toString();
                                return values1.compareTo(values2) * (asc ? 1 : -1);
                            }
                            if (Timestamp.class.isAssignableFrom(nameClass)) {
                                Timestamp values1 = (Timestamp) object1;
                                Timestamp values2 = (Timestamp) object2;
                                return values1.compareTo(values2) * (asc ? 1 : -1);
                            }
                        }

                    } catch (Exception ex) {
                        try {
                            throw ex;
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }

                    /*
                     * return
                     */
                    return 0;
                }
            });
        }

        /*
         * return
         */
        return collection;
    }
}

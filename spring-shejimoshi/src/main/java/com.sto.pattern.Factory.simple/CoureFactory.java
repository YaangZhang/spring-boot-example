package com.sto.pattern.Factory.simple;

/**
 * @author zhy
 * @create 2020-08-20-22:12
 */
public class CoureFactory {

    public ICoure create(String name){
        if ("java".equals(name)) {
            return new JavaCoure();
        } else if ("python".equals(name)) {
            return new PythodCoure();
        }else return null;
    }

     public ICoure create2(Class<? extends ICoure> className){
         if (null != className) {
             try {
                 Class.forName()
                 return className.newInstance();
             } catch (Exception e) {
                 e.printStackTrace();
             }
         }
         return null;
    }


}

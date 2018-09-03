package reflect;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ReflectUtil {


    static class UserWarper{

        User frozens;
        User broow;
        User free;

        public UserWarper(User frozens, User broow, User free) {
            this.frozens = frozens;
            this.broow = broow;
            this.free = free;
        }
    }

    static class Wraper {
        String free ;

//        public Wraper(int free, String broow, String frozen) {
//            this.free = free;
//            this.broow = broow;
//            this.frozen = frozen;
//        }

        String broow;
        String frozen;

        String name;

        @Override
        public String toString() {
            return "Wraper{" +
                    "free='" + free + '\'' +
                    ", broow='" + broow + '\'' +
                    ", frozen='" + frozen + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }


    public static void main(String[] args) {

       User user =  new User("10","a1","adress1");
       User user1 =  new User("11","a2","adress2");
       User user2 =  new User("12","a3","adress3");

       UserWarper wraper = new UserWarper(user,user1,user2);

       List<Wraper> waaperObjects = new ArrayList<>();

       String[] fields = fieldames(user);
       for (String name :fields ) {
           System.out.println(name);

           Wraper wraper1 = new Wraper();
           wraper1.name = name;
           wraper1.broow = (String) getFieldValue(user,name);
            waaperObjects.add(wraper1);
       }

        for (int i = 0 ; i < fields.length ; i ++ ) {
           Wraper wrapper2 = waaperObjects.get(i);
            wrapper2.free = (String) getFieldValue(user1,fields[i]);
        }

        for (int i = 0 ; i < fields.length ; i ++ ) {
            Wraper wrapper2 = waaperObjects.get(i);
            wrapper2.frozen = (String) getFieldValue(user2,fields[i]);
        }


        System.out.println(waaperObjects);

    }


    public static Object getFieldValue(Object obj, String fieldName) {
        if (obj == null ) {
            return null;
        }

        Class<?> clazz = obj.getClass();
        while (clazz != Object.class) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field.get(obj);
            } catch (Exception e) {
            }
            clazz = clazz.getSuperclass();
        }
        return null;
    }
    public static String[] fieldames(Object object){
        Field[] fields = object.getClass().getDeclaredFields();
        String[] names = new String[fields.length];
        for (int i = 0 ; i < fields.length ; i ++) {
            names[i] = fields[i].getName();
        }
        return names;
    }


}

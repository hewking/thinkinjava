package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CurrentModifiedDemo {

    public static void main(String[] args) {

//        List<String> list = Arrays.asList(new String[]{"aa","bb","cc","ee","dd"});
        List<String> list2 = new ArrayList<String>();
        list2.add("aa");
        list2.add("bb");
        list2.add("cc");
        list2.add("dd");
        list2.add("ee");

        List<String> list3 = new ArrayList<>();
        list3.addAll(list2);
        for (String str : list3) {
            System.out.println("str : " + str);
            if (str.equals("cc")) {
                System.out.println("str : " + str);
                list2.remove(str);
            }
        }

    }

}

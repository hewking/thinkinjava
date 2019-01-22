package list;

import java.util.ArrayList;
import java.util.List;

public class ListTest {

    public static void main(String[] args) {
        List<String> lists = new ArrayList<>();
        lists.add("1");
        lists.add("2");
        lists.add("3");

        for (String str : lists) {
            System.out.println(str);
        }

        List<String> list2 = new ArrayList<>();
        list2.add("02");
        list2.add("03");

        lists.addAll(1, list2);

        for (String s : lists) {
            System.out.println(s);
        }
    }
}

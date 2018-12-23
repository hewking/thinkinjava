package string.regex;

import java.util.regex.Pattern;

public class RegexDemo {

    public static void main(String[] args) {
        System.out.println(task1("Adfsdf ."));

        System.out.println(task3("afsdfsesdsisfdsosdu"));

        System.out.println(task4("tronbet.io"));
    }

    public static String task4(String url){
        String regex1 = "\\?r=";
        String regex2 = "tronbet.io";
        String regex3 = "www.tronbet.io/whitepaper.xxx.pdf";

        if (url.matches(regex1) || url.matches(regex3)) {
            return url;
        }

        if (url.matches(regex2)) {
            return url + "?r=" + "guildchat";
        } else {
            return url;
        }
    }

    /**
     * 297页 编写一个正则表达式，检查一个句子是否以大写字母开头，以句号结尾
     */
    public static boolean task1(String text){
        return Pattern.matches("^[A-Z].*(\\.|。)$",text);
    }

    /**
     * 在the 和 you 分开句子
     * @param text
     * @return
     */
    public static String[] task2(String text) {
        return text.split("the|you");
    }

    /**
     * 用下划线替换所有句子中的元音字符
     */
    public static String task3(String text){
        return text.replaceAll("a|e|i|o|u","_");
    }

}

package string.regex;

public class RegexDemo {
    public static void main(String[] args) {

        String url = "www.tronbet.io/#";

        //?r=
        //tronbet.io
        // https://www.tronbet.io/pdf/whitebook_zh-CN.pdf

//        String regex = "\\w+\\?r=";
//
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(url);
//        boolean result = matcher.matches();
//
//        System.out.println("result : " + result);
        System.out.print(urlPrefix(url));
    }

    public static String urlPrefix(String url) {
        String regex1 = "?r=";
        String regex2 = "tronbet.io/pdf/whitebook_zh-CN.pdf";
        String regex3 = "tronbet.io";
        String regex4 = "guildchat";
        if (url.contains(regex1) || url.contains(regex2)) {
            return url;
        }
        if (url.contains(regex3)) {
            return url + regex1 + regex4;
        } else {
            return url;
        }
    }
}

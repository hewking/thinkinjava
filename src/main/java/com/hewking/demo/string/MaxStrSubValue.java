package com.hewking.demo.string;

public class MaxStrSubValue {

    public static void main(String args[]) {

        String str1 = "asdfslshkkffjjayysdlf";
        String str2 = "fskfkkffjjjldskflsdjysdlfkfsljl";

        String result = getMaxSubStr(str1, str2);
        System.out.println(result);
    }

    private static String getMaxSubStr(String str1, String str2) {

        String str = str1.length() > str2.length() ? str1 : str2;
        String subStr = str1;
        if (str.equals(str1)) {
            subStr = str2;
        } else {
            subStr = str1;
        }

        String maxStr = "";
        for (int i = 0; i < subStr.length(); i++) {
            for (int j = i + 1; j < subStr.length() + 1; j++) {
                String sub = subStr.substring(i, j);

                if (str.indexOf(sub) != -1 && sub.length() > maxStr.length()) {
                    maxStr = sub;
                }
            }
        }


        return maxStr;
    }

}

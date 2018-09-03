package huobi;

import java.util.Arrays;

public class SymbolTest {

    public static void main(String[] args) {

//        String symbol = "BNBTC";
//
//        // BNB BTC ETH USDT
//
//        int index = symbol.lastIndexOf("BNB");
//        int index2 = symbol.lastIndexOf("BTC");
//        int index3 = symbol.lastIndexOf("ETH");
//        int index4 = symbol.lastIndexOf("USDT");
//
//        int max = index;
//        int[] indexs = {index,index2,index3,index4};
//        for ( int i = 0 ;i < indexs.length ; i ++) {
//            if (indexs[i] > max) {
//                max = indexs[i];
//            }
//        }
//
//        if (max > 2) {
//            String last = symbol.substring(max,symbol.length());
//            String firs = symbol.substring(0,max);
//            System.out.println("index : " + max);
//            System.out.println("last : " + last);
//            System.out.println("first : " + firs);
//        }


        // ltc_btc

//        String symbol = "ltc_btc";
//
//        String[] symbols = symbol.split("_");
//
//        if (symbols.length == 2) {
//            System.out.println("first : " + symbols[0]);
//            System.out.println("last : " + symbols[1]);
//        }


        //BTC_USDT

        String symbol = "BtC/USDT".toUpperCase();

        String[] symbols = symbol.split("/");

        if (symbols.length == 2) {
            System.out.println("first : " + symbols[0]);
            System.out.println("last : " + symbols[1]);
        }


    }

}

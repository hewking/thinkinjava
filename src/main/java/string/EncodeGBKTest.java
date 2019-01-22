package string;

import java.io.UnsupportedEncodingException;

public class EncodeGBKTest {

    public static void main(String[] args) {

        try {
            String encoderStr = new String("81318339813184398131843781318732".getBytes("GBK"), "UTF-8");
            System.out.println(encoderStr);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}

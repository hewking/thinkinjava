package huobi;

import java.util.HashMap;
import java.util.Map;

public class OkexTest {

    /**
     * apiKey  614c63bb-bfe8-47f9-a9ae-56e7a3493f55
     * 备注名android测试
     * 权限提币交易
     * secretKey  6458BCCEE6C71155EBFE989DDA78DC5D
     *
     * @param args
     */

    public static void main(String[] args) {

        String api_key = "614c63bb-bfe8-47f9-a9ae-56e7a3493f55";
        String secret_key = "6458BCCEE6C71155EBFE989DDA78DC5D";

        // 构造参数签名
        Map<String, String> params = new HashMap<String, String>();
        params.put("api_key", api_key);
        String sign = MD5Util.buildMysignV1(params, secret_key);
        params.put("sign", sign);


        String url_pre = "https://www.okex.com/api/v1/ticker.do?symbol=ltc_btc";


    }


}

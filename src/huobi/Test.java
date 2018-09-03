package huobi;

import com.sun.deploy.net.HttpUtils;
import crypt.rsa.Base64Utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {

    public static String ACCESS_KEY = "4362a836-2016e192-3b179c30-371d1";
    public static String SECRET_KEY = "0cce8298-96168d95-c9cf2391-4c0c3";

    public static void main(String[] args) throws UnsupportedEncodingException {

        String str = "GET\n" +
                "api.huobi.pro\n" +
                "/v1/order/orders\n" +
                "AccessKeyId=e2xxxxxx-99xxxxxx-84xxxxxx-7xxxx&SignatureMethod=HmacSHA256&SignatureVersion=2&Timestamp=2017-05-11T15%3A19%3A30&order-id=1234567890";

        String secretStr = "b0xxxxxx-c6xxxxxx-94xxxxxx-dxxxx";

        //4F65x5A2bLyMWVQj3Aqp+B4w+ivaA7n5Oi2SuYtCJ9o=

        //6mipKHbLJDE5R0RQwsQaovFhfNk7VZMK1nq31CRw4qc=

        //hN+Pa3YvofqiSk53KpaOJpeJ/UVuAWLlsdzJvS7fwhQ=
//timestamp : 2018-04-09T08:28:33
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss"); // utc 时间 世界标准时间
//        System.out.println(format.format(new Date()));
//
//        System.out.println(sha256_HMAC(str,secretStr));

        System.out.println("test secret : " + sha256_HMAC("aaa", SECRET_KEY));

        String test = "GET\napi.huobi.pro\n/v1/account/accounts\nAccessKeyId=9df86d4d-7ce4ce48-55446ddf-35a53&SignatureMethod=HmacSHA256&SignatureVersion=2&Timestamp=2018-04-09T14%3A02%3A38";

//        System.out.println(sha256_HMAC(test,"44216202-cf4cb3ea-574942e5-8c562"));
//        System.out.println("apkKeyGet : " + apkKeyGet(params,""));
//        String urlStr = BASE_URL +"/v1/account/accounts?"+ apkKeyGet(params,"");
        HashMap<String,String> params = new HashMap<>();
//        params.put("account-id","113952");
//        String urlbalanceStr = BASE_URL +"/v1/account/accounts/113952/balance?"+ apkKeyGet(params,"/v1/account/accounts/113952/balance");
        String url = BASE_URL +"/v1/account/accounts?"+ apkKeyGet(params,"/v1/account/accounts");
        System.out.println("url urlbalanceStr : " + url);
        huobi.HttpUtils.doGetAsyn(url, new huobi.HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {
                System.out.println("result doget : " + result);
            }
        });


    }

    /**
     * 正确的
     *  GET
     api.huobi.pro
     /v1/account/accounts
     AccessKeyId=6f44b9c3-59e4815f-882580e4-14423&SignatureMethod=HmacSHA256&SignatureVersion=2&Timestamp=2018-04-09T12%3A56%3A32

     我得

     GET
     api.huobi.pro
     /v1/account/accounts
     AccessKeyId=6f44b9c3-59e4815f-882580e4-14423&SignatureMethod=HmacSHA256&SignatureVersion=2&Timestamp=2018-04-09T20%3A57%3A13
     */


    public static String BASE_URL = "https://api.huobi.pro";

    public static String HOST_NAME = "api.huobi.pro";

    /**
     * https://api.huobi.pro/v1/order/orders?
     * AccessKeyId=e2xxxxxx-99xxxxxx-84xxxxxx-7xxxx
     * &order-id=1234567890
     * &SignatureMethod=HmacSHA256
     * &SignatureVersion=2
     * &Timestamp=2017-05-11T15%3A19%3A30
     * &Signature=4F65x5A2bLyMWVQj3Aqp%2BB4w%2BivaA7n5Oi2SuYtCJ9o%3D
     * @return
     */
    public static String apkKeyGet(HashMap<String,String> params,String path) throws UnsupportedEncodingException {
        // 组装参数

        // time 2018-23-09T08%3A23%3A58
        String method = "GET";
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"); // utc 时间 世界标准时间
//        String date = format.format(new Date());
        String date = GetUTCTimeUtil.getUTCTimeStr();
//        String date = "2018-04-13T16:18:13";
        System.out.println(date);

        /**
         * 正确  2018-04-09T13:14:33
         *
         * 我的
         */

        /**
         *     params.update({'AccessKeyId': ACCESS_KEY,
         'SignatureMethod': 'HmacSHA256',
         'SignatureVersion': '2',
         'Timestamp': timestamp})
         */

        params.put("AccessKeyId",ACCESS_KEY);
        params.put("SignatureMethod","HmacSHA256");
        params.put("SignatureVersion","2");
        params.put("Timestamp",date);

        String sign = createSign(params,method,path);
        System.out.println("sign : " + sign);
        params.put("Signature",/*URLEncoder.encode(sign,"UTF-8")*/sign);

        return params(params);
    }

    /**
     * "GET\n" +
     "api.huobi.pro\n" +
     "/v1/order/orders\n" +
     "AccessKeyId=e2xxxxxx-99xxxxxx-84xxxxxx-7xxxx&SignatureMethod=HmacSHA256&SignatureVersion=2&Timestamp=2017-05-11T15%3A19%3A30&order-id=1234567890";
     * @return
     */
    public static String createSign(HashMap<String,String> params , String method , String path) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        sb.append(method)
                .append("\n")
                .append(HOST_NAME)
                .append("\n")
                .append(path)
                .append("\n");
        List<String> signParam = new ArrayList<>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            signParam.add(entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), "UTF-8"));
//            sb.append(entry.getKey())
//                    .append("=")
//                    .append(entry.getValue())
//                    .append("&");
        }

        Collections.sort(signParam);
        for (String str : signParam) {
            sb.append(str).append("&");
        }

        String message = safe(sb);
        System.out.println("message : \n" + message);
//        message = URLEncoder.encode(message,"UTF-8");
        System.out.println("encode msg : " + message);
        return sha256_HMAC(message,SECRET_KEY);
    }

    public static String params(HashMap<String,String> params) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<String,String>> it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String,String> entry = it.next();
            sb.append(entry.getKey())
                    .append("=")
                    .append(URLEncoder.encode(entry.getValue(),"UTF-8"))
                    .append("&");
        }
        return safe(sb);
    }

    /**
     * 安全的去掉字符串的最后一个字符
     */
    public static String safe(StringBuilder stringBuilder) {
        return stringBuilder.substring(0, Math.max(0, stringBuilder.length() - 1));
    }

    public static <T> String connect(List<T> list) {
        if (list == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (T bean : list) {
            builder.append(bean.toString());
            builder.append(",");
        }

        return safe(builder);
    }

    /**
     * sha256_HMAC加密
     * @param message 消息
     * @param secret  秘钥
     * @return 加密后字符串
     */
    private static String sha256_HMAC(String message, String secret) {
        String hash = "";
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] bytes = sha256_HMAC.doFinal(message.getBytes());
            hash = Base64Utils.encode(bytes);
            System.out.println(hash);
        } catch (Exception e) {
            System.out.println("Error HmacSHA256 ===========" + e.getMessage());
        }
        return hash;
    }

}

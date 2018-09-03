package crypt;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class StringUtils {
    /**
     * 单字节数值掩码
     */
    private static final int BYTE_MASK = 0xFF;
    /**
     * 十六进制个位掩码
     */
    private static final int HEX_MASK = 0x0F;
    /**
     * 小写的第一个字母
     */
    private static final char FIRST_LOWER_LETTER = 'a';
    /**
     * 大写的第一个字母
     */
    private static final char FIRST_UPPER_LETTER = 'A';
    /**
     * 第一个数字
     */
    private static final char FIRST_NUMBER = '0';
    /**
     * 十六进制字母的最小值
     */
    private static final int HEX_LETTER_BEGIN = 10;

    /**
     * 十六进制高位填充
     */
    private static final String HEX_PADDING = "0";

    /**
     * 十六进制高位移位
     */
    private static final int HEX_SHIFT = 4;

    /**
     * 数值连接符
     */
    private static final String SIG_SPLIT = "&";

    /**
     * 键值链接符
     */
    private static final String SIG_EQUAL = "=";

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isNullOrEmpty(String str) {
        return (null == str || str.isEmpty());
    }

    

    /**
     * 十六进制字符串转字节流
     *
     * @param hexStr
     * @return
     */
    public static byte[] hexString2Bytes(String hexStr) {
        if (StringUtils.isNullOrEmpty(hexStr)) {
            return null;
        }
        byte[] b = new byte[hexStr.length() / 2];
        int j = 0;
        for (int i = 0; i < b.length; i++) {
            char c = hexStr.charAt(j++);
            char cNext = hexStr.charAt(j++);
            b[i] = (byte) ((parse(c) << HEX_SHIFT) | parse(cNext));
        }
        return b;
    }

    /**
     * 字节流转十六进制字符串
     *
     * @param bytes
     * @return
     */
    public static String bytes2HexString(byte[] bytes) {
        if (null == bytes) {
            return null;
        }
        StringBuilder strBuilder = new StringBuilder();
        for (byte b : bytes) {
            if (b < 0x10 && b >= 0) {
                strBuilder.append(HEX_PADDING);
            }
            strBuilder.append(Integer.toHexString(((int) b) & BYTE_MASK));
        }
        return strBuilder.toString().toUpperCase();
    }

    /**
     * 十六进制字符转化为对应的十进制值
     *
     * @param c
     * @return
     */
    private static int parse(char c) {
        if (c >= FIRST_LOWER_LETTER) {
            return (c - FIRST_LOWER_LETTER + HEX_LETTER_BEGIN) & BYTE_MASK;
        }
        if (c >= FIRST_UPPER_LETTER) {
            return (c - FIRST_UPPER_LETTER + HEX_LETTER_BEGIN) & BYTE_MASK;
        }
        return (c - FIRST_NUMBER) & HEX_MASK;
    }

    /**
     * 比较两个字符串对象是否相等
     *
     * @param one
     * @param another
     * @return
     */
    public static boolean isEqual(String one, String another) {
        if (one == another) {
            return true;
        }

        if (null != one) {
            return one.equals(another);
        }

        return false;
    }

    /**
     * 把键值对拼接成字符串，
     * e.g.
     * {"name":"zhangsan","age":"18",} ->age=18&name=zhangsan
     *
     * @param para
     * @param sort   是否按键名排序
     * @param encode
     * @return
     */
    public static String createLinkString(Map<String, String> para,
                                          boolean sort, boolean encode) {

        List<String> keys = new ArrayList<String>(para.keySet());

        if (sort) {
            Collections.sort(keys);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = para.get(key);

            if (encode) {
                try {
                    value = URLEncoder.encode(value, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                }
            }

            //拼接时，不包括最后一个&字符
            if (i == keys.size() - 1) {
                sb.append(key).append(SIG_EQUAL).append(value);
            } else {
                sb.append(key).append(SIG_EQUAL).append(value).append(SIG_SPLIT);
            }
        }
        return sb.toString();
    }

    /**
     * 获取指定json键的值的原始串
     *
     * @param json
     * @param key
     * @return
     */
    public static String getJsonRawValue(String json, String key) {
        if (isNullOrEmpty(json) || isNullOrEmpty(key)) {
            return null;
        }

        String jsonKey = "\"" + key + "\"";
        int start = json.indexOf(jsonKey);
        if (start < 0) {
            return null;
        }

        start += jsonKey.length();

        //跳过空格和":"
        for (int i = start; i < json.length(); i++) {
            char c = json.charAt(i);
            if (!Character.isWhitespace(c) && c != ':') {
                start = i;
                break;
            }
        }

        //值部分的第一个字符
        char leftQuote = json.charAt(start);
        //值部分最后一个字符
        char rightQuote = 0;
        int end = 0;
        switch (leftQuote) {
            case '{':
                rightQuote = '}';
                break;
            case '[':
                rightQuote = ']';
                break;

            case '\"':
                rightQuote = '\"';
                break;

            default:
                break;
        }

        if (rightQuote == 0) {
            end =json.indexOf(',', start);
            if (end < 0) {
                end = json.indexOf('}', start);
            }

        } else {
            int needMatch = 1;

            for (int i = start + 1; i < json.length(); i++) {
                char c = json.charAt(i);
                if (c == leftQuote && leftQuote != '\"') {
                    needMatch++;
                }

                if (c == rightQuote) {
                    needMatch--;
                }

                if (needMatch == 0) {
                    end = i + 1;
                    break;
                }
            }

            if (needMatch > 0) {
                return null;
            }
        }

        return json.substring(start, end);
    }
}

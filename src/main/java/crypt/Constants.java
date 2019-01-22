package crypt;

public class Constants {
    //默认使用UTF-8编码
    public static final String ENCODING = "UTF-8";
    //密码加密掩码
    public static final String PASS_MASK = "jIaNgSuYcNlInGiNjIaNgSuYiNlInGjIjIcN";
    //密码加密噪音
    public static final String PASS_NOISE = "+ko(*o9N(*nldf8GH(7n,hs";
    //AES IV
    public static final String ENCRYPTION_IV = "pytIa738ewdljkdF";
    //Default AES Key
    public static final String AES_KEY = "*>df6Xdf;aPoew3f";

    public static final String TOKEN = "Token";

    public static final String DEVICE_ID = "device_id";
    /**
     * 中国手机号校验的正则表达式
     */
    public static final String CHINAPHONE = "^[1][34578]\\d{9}$";
    /**
     * 手机验证码
     */
    public static final String VCODE = "vcode";
    //谷歌验证账号host
    public static final String GOOGLE_HOST = "ope";
    //存谷歌验证密钥的key
    public static final String GOOGLE_SECRET = "secret";
    //存谷歌验证密钥url的key
    public static final String GOOGLE_SECRET_URL = "secretUrl";
    //密钥过期时间
    public static final int KEY_EXPIRED = 24 * 60 * 60;
    //初始化用户名
    public static final String INIT_USERNAME = "opechain";
    //分页offSet
    public static final int OFFSET = 0;
    //分页pageSize
    public static final int PAGESIZE = 8;
}

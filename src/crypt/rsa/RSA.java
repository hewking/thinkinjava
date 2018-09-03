package crypt.rsa;


import crypt.StringUtils;

/**
 * Copyright (C) 2016,深圳市红鸟网络科技股份有限公司 All rights reserved.
 * 项目名称：
 * 类的描述：红鸟RSA加密算法工具,可以正常使用
 * 创建人员：Robi
 * 创建时间：2016/12/12 16:49
 * 修改人员：Robi
 * 修改时间：2016/12/12 16:49
 * 修改备注：
 * Version: 1.0.0
 */
public class RSA {

    /**
     * 加密
     */
//    public static String encode(String data) {
//        return encode(data, false);
//    }

//    public static String encode(String data, boolean useSalt) {
//        String data_ = data;
//        if (useSalt) {
////            Calendar cal = Calendar.getInstance();
////            int month = cal.get(Calendar.MONTH) + 1;
////            int year = cal.get(Calendar.YEAR);
////            int day = cal.get(Calendar.DAY_OF_MONTH);
////            data_ = data + "&" + sdbmHash(month * year * day + "");
////            data_ = data + "&" + E_SALT;
//        }
//
//        String encode = "";
//        byte[] encodedData;
//        try {
//            encodedData = RSAUtils.encryptByPublicKey(data_.getBytes(), PUBLIC_KEY);
//            encode = Base64Utils.encode(encodedData);
//        } catch (Exception e) {
//        }
//        return encode.replaceAll("\\n", "");
//    }
//
//    public static String encodeInfo(String data) {
//        String data_ = data;
////        if (useSalt) {
////            data_ = data + "&" + E_SALT;
////        }
//
//        String encode = "";
//        byte[] encodedData;
//        try {
//            encodedData = RSAUtils.encryptByPublicKey(data_.getBytes(), PUBLIC_KEY_INFO);
//            encode = Base64Utils.encode(encodedData);
//        } catch (Exception e) {
//        }
//        return encode.replaceAll("\\n", "");
//    }

    /**
     * 解密
     */
    public static String decode(String encode, String privateKey) {
        String target = "";
        byte[] decodedData;
        try {
//            decodedData = RSAUtils.decryptByPrivateKey(Base64Utils.decode(encode), privateKey);
            decodedData = RSAUtils.decryptByPrivateKeyHex(StringUtils.hexString2Bytes(encode), privateKey);
            target = new String(decodedData);
        } catch (Exception e) {
        }
        return target;
    }

    public static void main(String[] args) {
        // publickey
        // privatekey
        // 公钥加密过的密钥
        String publicKey = "30819F300D06092A864886F70D010101050003818D0030818902818100996D3F6ADB8C42342B2CD03A9A3CD5C5BCD99A03EAB4622A31AE6C360A222177AD4BB407AD74AE44BD2A2B70A3AFF530F8E90F34AF5B25C8B4A01D837C2C726866CFFED4BF10301B6A88A82EFE510279EB981DAC015BCAE195C9DFF845F6D0D1FFBB4DBDA00B1EF5F8481B630E10DE5ADD5D0E2A7BC53AE65506360537305FF50203010001";
        String privateKey = "30820276020100300D06092A864886F70D0101010500048202603082025C02010002818100996D3F6ADB8C42342B2CD03A9A3CD5C5BCD99A03EAB4622A31AE6C360A222177AD4BB407AD74AE44BD2A2B70A3AFF530F8E90F34AF5B25C8B4A01D837C2C726866CFFED4BF10301B6A88A82EFE510279EB981DAC015BCAE195C9DFF845F6D0D1FFBB4DBDA00B1EF5F8481B630E10DE5ADD5D0E2A7BC53AE65506360537305FF5020301000102818074E028233AC409DABBD91376D0EFEED90717AC9FB7375A605220510689899E7D10624EEE5A503C40D8E93AD63F9B56E8CB749EC4BFA06B940D24BBD6E1CCEA0937F00F824F02605CB175DBA9BF5BBD324D2E8627A0AAB2DB49255D6EAD992E1DC7D528D5FED405EF2110857D7839330E34F7A4C3492C4420BB491AB9D269E131024100C8C3B5F8EE737D6451276A3FDF6AD8BDB39EEE25AB67DD9C96BA95220D3780D03081FC5CE83D6EA9E9101E7F04D591810485D96A1F53A1A6DE11EECFDF64CD27024100C3A36F2E90881BACFA5016BD3E16C58C555755D95EFC80C155C7146BA14B2380CDFFA8543A7846B5BCF87E1F19767A884B62D6580D45DCB43CE0ADFED18293830240590657FA139AC8BEA431EB2E799EC28F9447E46875D26FF5D0ECD9D6A8AFB5021C90775CA76B1E585FCDD3081617B216D8EF95923663FA69A94DA06D332D5ABF0240238DA1F8EE5F2DD863AD0654E1E1B588E94173E28AF68C579645159AF1B6FBFEBE7105A038F7FEB1AC63E457B682E785B95D7EE28596FAA2EEA54E398ECD920D024100BA047C42BAE3CDD9DEDF6E17CE5D5D6BEAFB4825D2560382BEC55F1D47A7B08D5FCD5B093C4130CF62E1A3065CF9E2ECDA29E59D56E2F44A4243B63C54047D14";

        String target = "hello";

        try {
            String encryptData  = StringUtils.bytes2HexString(RSAUtils.encryptByPublicKeyHex(target.getBytes(), publicKey));
            String result = decode(encryptData,privateKey);

            System.out.println("result data " + result);


        } catch (Exception e) {
            e.printStackTrace();
        }


//        String data = "3F380CC8A5046D6A511028908600F9C15CEAA58F223FB3B02B6AEDDF4E65BFBB44FAEF9C25F504709D16811E9E15DB08F62CCCAE55CC108C52DEEEB66D93566E598DF0A764E57EF44A010EC2AFA348A5CAAD92EC0371DF4602158944448665010D9961128C5A49ABE4109D75176A9F98B92B4F127D9E320B2BC8D923457934230B0B193B82EFEF46EBF2F51E73AD906B30C8E902C40EE81ECF636DE62989FAC71A2DAE43A06727006AFA3C70DB321FF706287BFDCC9891FE83BA2B9274CC20965CEB6D7C66ED83C56B73978E4863F6B0C2D55621398FD7B2C4EF47CBEAD7865D183E998CD2D6BBE55AB9989C08FB188EFCD097EC91CD999E5A1F1E42726F9F36623202AF04D630BD63BFFC0A2F69AFD0";
//        String result = decode(data,privateKey);
//
//        System.out.println("result " + result);

        //

    }

    public static long sdbmHash(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        long hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = (str.charAt(i) + (hash << 6) + (hash << 16)) - hash;
        }
        return hash & 0x7FFFFFFF;

    }

}

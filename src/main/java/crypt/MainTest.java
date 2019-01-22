package crypt;

import com.sun.org.apache.xml.internal.security.utils.Base64;

public class MainTest {

    public static void main(String[] args) throws Exception {
        String filepath = "G:/tmp/";

        //RSAEncrypt.genKeyPair(filepath);
        String publicKey = "30819F300D06092A864886F70D010101050003818D0030818902818100996D3F6ADB8C42342B2CD03A9A3CD5C5BCD99A03EAB4622A31AE6C360A222177AD4BB407AD74AE44BD2A2B70A3AFF530F8E90F34AF5B25C8B4A01D837C2C726866CFFED4BF10301B6A88A82EFE510279EB981DAC015BCAE195C9DFF845F6D0D1FFBB4DBDA00B1EF5F8481B630E10DE5ADD5D0E2A7BC53AE65506360537305FF50203010001";
        String privateKey = "30820276020100300D06092A864886F70D0101010500048202603082025C02010002818100996D3F6ADB8C42342B2CD03A9A3CD5C5BCD99A03EAB4622A31AE6C360A222177AD4BB407AD74AE44BD2A2B70A3AFF530F8E90F34AF5B25C8B4A01D837C2C726866CFFED4BF10301B6A88A82EFE510279EB981DAC015BCAE195C9DFF845F6D0D1FFBB4DBDA00B1EF5F8481B630E10DE5ADD5D0E2A7BC53AE65506360537305FF5020301000102818074E028233AC409DABBD91376D0EFEED90717AC9FB7375A605220510689899E7D10624EEE5A503C40D8E93AD63F9B56E8CB749EC4BFA06B940D24BBD6E1CCEA0937F00F824F02605CB175DBA9BF5BBD324D2E8627A0AAB2DB49255D6EAD992E1DC7D528D5FED405EF2110857D7839330E34F7A4C3492C4420BB491AB9D269E131024100C8C3B5F8EE737D6451276A3FDF6AD8BDB39EEE25AB67DD9C96BA95220D3780D03081FC5CE83D6EA9E9101E7F04D591810485D96A1F53A1A6DE11EECFDF64CD27024100C3A36F2E90881BACFA5016BD3E16C58C555755D95EFC80C155C7146BA14B2380CDFFA8543A7846B5BCF87E1F19767A884B62D6580D45DCB43CE0ADFED18293830240590657FA139AC8BEA431EB2E799EC28F9447E46875D26FF5D0ECD9D6A8AFB5021C90775CA76B1E585FCDD3081617B216D8EF95923663FA69A94DA06D332D5ABF0240238DA1F8EE5F2DD863AD0654E1E1B588E94173E28AF68C579645159AF1B6FBFEBE7105A038F7FEB1AC63E457B682E785B95D7EE28596FAA2EEA54E398ECD920D024100BA047C42BAE3CDD9DEDF6E17CE5D5D6BEAFB4825D2560382BEC55F1D47A7B08D5FCD5B093C4130CF62E1A3065CF9E2ECDA29E59D56E2F44A4243B63C54047D14";

        String target = "hello";

        System.out.println("--------------公钥加密私钥解密过程-------------------");
        String plainText = "ihep_公钥加密私钥解密";
        //公钥加密过程
        byte[] cipherData = RSAEncrypt.encrypt(RSAEncrypt.loadPublicKeyByStr(publicKey), plainText.getBytes());
        String cipher = Base64.encode(cipherData);
        //私钥解密过程
        byte[] res = RSAEncrypt.decrypt(RSAEncrypt.loadPrivateKeyByStr(privateKey), Base64.decode(cipher));
        String restr = new String(res);
        System.out.println("原文：" + plainText);
        System.out.println("加密：" + cipher);
        System.out.println("解密：" + restr);
        System.out.println();

        System.out.println("--------------私钥加密公钥解密过程-------------------");
        plainText = "ihep_私钥加密公钥解密";
        //私钥加密过程
        cipherData = RSAEncrypt.encrypt(RSAEncrypt.loadPrivateKeyByStr(RSAEncrypt.loadPrivateKeyByFile(filepath)), plainText.getBytes());
        cipher = Base64.encode(cipherData);
        //公钥解密过程
        res = RSAEncrypt.decrypt(RSAEncrypt.loadPublicKeyByStr(RSAEncrypt.loadPublicKeyByFile(filepath)), Base64.decode(cipher));
        restr = new String(res);
        System.out.println("原文：" + plainText);
        System.out.println("加密：" + cipher);
        System.out.println("解密：" + restr);
        System.out.println();

//        System.out.println("---------------私钥签名过程------------------");
//        String content="ihep_这是用于签名的原始数据";
//        String signstr= RSASignature.sign(content,RSAEncrypt.loadPrivateKeyByFile(filepath));
//        System.out.println("签名原串："+content);
//        System.out.println("签名串："+signstr);
//        System.out.println();
//
//        System.out.println("---------------公钥校验签名------------------");
//        System.out.println("签名原串："+content);
//        System.out.println("签名串："+signstr);
//
//        System.out.println("验签结果："+RSASignature.doCheck(content, signstr, RSAEncrypt.loadPublicKeyByFile(filepath)));
//        System.out.println();

    }
}


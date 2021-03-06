package com.hewking.utils.crypt;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtils {
    /**
     * 得到公钥
     *
     * @param key 密钥字符串（十六进制字符串）
     * @throws Exception
     */
    public static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = StringUtils.hexString2Bytes(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    public static PrivateKey getPrivatecKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = StringUtils.hexString2Bytes(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    /**
     * 使用公钥对明文进行加密
     *
     * @param publicKey 公钥
     * @param plainText 明文
     * @return
     */
    public static String encrypt(String publicKey, String plainText) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
            byte[] enBytes = cipher.doFinal(plainText.getBytes());
            return StringUtils.bytes2HexString(enBytes);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static String decrypt(String privateKey,String plainText) {
//        try {
//            Cipher cipher = Cipher.getInstance("RSA");
//            cipher.init(Cipher.DECRYPT_MODE,getPrivatecKey(privateKey));
//            byte[] enBytes = cipher.doFinal(StringUtils.hexString2Bytes(plainText));
//            return StringUtils.bytes2HexString(enBytes);
//        } catch (InvalidKeyException e) {
//            e.printStackTrace();
//        } catch (IllegalBlockSizeException e) {
//            e.printStackTrace();
//        } catch (BadPaddingException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//
//    }

    public static byte[] decrypt(String privateKey, String plainText) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, getPrivatecKey(privateKey));
            byte[] enBytes = cipher.doFinal(StringUtils.hexString2Bytes(plainText));
            return enBytes;
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decryptByStr(String privateKey, String plainText) {
        try {
//            if (TextUtils.isEmpty(plainText)) {
//                return "";
//            }
            byte[] data = decrypt(privateKey, plainText);
            if (data == null) {
                return "";
            }
            return new String(data, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {

        String publicKey = "30819F300D06092A864886F70D010101050003818D0030818902818100996D3F6ADB8C42342B2CD03A9A3CD5C5BCD99A03EAB4622A31AE6C360A222177AD4BB407AD74AE44BD2A2B70A3AFF530F8E90F34AF5B25C8B4A01D837C2C726866CFFED4BF10301B6A88A82EFE510279EB981DAC015BCAE195C9DFF845F6D0D1FFBB4DBDA00B1EF5F8481B630E10DE5ADD5D0E2A7BC53AE65506360537305FF50203010001";
        String privateKey = "30820276020100300D06092A864886F70D0101010500048202603082025C02010002818100996D3F6ADB8C42342B2CD03A9A3CD5C5BCD99A03EAB4622A31AE6C360A222177AD4BB407AD74AE44BD2A2B70A3AFF530F8E90F34AF5B25C8B4A01D837C2C726866CFFED4BF10301B6A88A82EFE510279EB981DAC015BCAE195C9DFF845F6D0D1FFBB4DBDA00B1EF5F8481B630E10DE5ADD5D0E2A7BC53AE65506360537305FF5020301000102818074E028233AC409DABBD91376D0EFEED90717AC9FB7375A605220510689899E7D10624EEE5A503C40D8E93AD63F9B56E8CB749EC4BFA06B940D24BBD6E1CCEA0937F00F824F02605CB175DBA9BF5BBD324D2E8627A0AAB2DB49255D6EAD992E1DC7D528D5FED405EF2110857D7839330E34F7A4C3492C4420BB491AB9D269E131024100C8C3B5F8EE737D6451276A3FDF6AD8BDB39EEE25AB67DD9C96BA95220D3780D03081FC5CE83D6EA9E9101E7F04D591810485D96A1F53A1A6DE11EECFDF64CD27024100C3A36F2E90881BACFA5016BD3E16C58C555755D95EFC80C155C7146BA14B2380CDFFA8543A7846B5BCF87E1F19767A884B62D6580D45DCB43CE0ADFED18293830240590657FA139AC8BEA431EB2E799EC28F9447E46875D26FF5D0ECD9D6A8AFB5021C90775CA76B1E585FCDD3081617B216D8EF95923663FA69A94DA06D332D5ABF0240238DA1F8EE5F2DD863AD0654E1E1B588E94173E28AF68C579645159AF1B6FBFEBE7105A038F7FEB1AC63E457B682E785B95D7EE28596FAA2EEA54E398ECD920D024100BA047C42BAE3CDD9DEDF6E17CE5D5D6BEAFB4825D2560382BEC55F1D47A7B08D5FCD5B093C4130CF62E1A3065CF9E2ECDA29E59D56E2F44A4243B63C54047D14";

        //30820276020100300D06092A864886F70D0101010500048202603082025C02010002818100996D3F6ADB8C42342B2CD03A9A3CD5C5BCD99A03EAB4622A31AE6C360A222177AD4BB407AD74AE44BD2A2B70A3AFF530F8E90F34AF5B25C8B4A01D837C2C726866CFFED4BF10301B6A88A82EFE510279EB981DAC015BCAE195C9DFF845F6D0D1FFBB4DBDA00B1EF5F8481B630E10DE5ADD5D0E2A7BC53AE65506360537305FF5020301000102818074E028233AC409DABBD91376D0EFEED90717AC9FB7375A605220510689899E7D10624EEE5A503C40D8E93AD63F9B56E8CB749EC4BFA06B940D24BBD6E1CCEA0937F00F824F02605CB175DBA9BF5BBD324D2E8627A0AAB2DB49255D6EAD992E1DC7D528D5FED405EF2110857D7839330E34F7A4C3492C4420BB491AB9D269E131024100C8C3B5F8EE737D6451276A3FDF6AD8BDB39EEE25AB67DD9C96BA95220D3780D03081FC5CE83D6EA9E9101E7F04D591810485D96A1F53A1A6DE11EECFDF64CD27024100C3A36F2E90881BACFA5016BD3E16C58C555755D95EFC80C155C7146BA14B2380CDFFA8543A7846B5BCF87E1F19767A884B62D6580D45DCB43CE0ADFED18293830240590657FA139AC8BEA431EB2E799EC28F9447E46875D26FF5D0ECD9D6A8AFB5021C90775CA76B1E585FCDD3081617B216D8EF95923663FA69A94DA06D332D5ABF0240238DA1F8EE5F2DD863AD0654E1E1B588E94173E28AF68C579645159AF1B6FBFEBE7105A038F7FEB1AC63E457B682E785B95D7EE28596FAA2EEA54E398ECD920D024100BA047C42BAE3CDD9DEDF6E17CE5D5D6BEAFB4825D2560382BEC55F1D47A7B08D5FCD5B093C4130CF62E1A3065CF9E2ECDA29E59D56E2F44A4243B63C54047D14

        String target = "ed149ecfe57c4019bcb41c2e0698bbff";

//        String encrytDat = RSAUtils.encrypt(publicKey, target);
        String encrytDat = "0AABF75861AED0F90774C40346E304ED1BDF03711D6776D2791F9833EDB7E48754CD7EBE8AE429C4E9460F6AECE8A4236E24103AE5C30F326196675873408925FF5DF20027A8E3A15A1F85EF78F325990767FAAFA7FCC79F39CCE7F6CFEDA82E5BAEF1500704BEED7A6BA6B16397B7FBFEB29B73D321E6B52FBDC27249A1ABB7";
//82EC77C6CB2209DD718B67B6E312885A7EDB8B4F7C42069DE5BB06849DD09B1A1CB3DDAA41A8F552D1351C737F010AA1963BF710B2D53120A7D9291E0EB91A49A958B6D9C696D3D039ECACA3A7B9879D41F774DA1C20BF65E0B21ADACE8B5A767FFC21DC18E77CBAE18817B43A537E11680A468EA409979AF9E63253E5A06D7C37D82A070D20721610233E9232F149A34401E327AAB9C9F49B0B379F53E40DC4224DFDB906E1642C374B58E7C23B69360157001B0B82B1EA37C0935D6253F3BD6CCDC5797C45269F033D5231ABB115642D67D450F794C0F8DE7B2D439B7B667EC5464809719A1C30180E42FDC96837622EAE81F19C13CBBE512CE7148940AF68CDA5B2501B9D2DB2AAABC6B6D58074D1

        //88EF67A4A2551E6894A99B5D8FD088C0251E5AB9B39C5B0DA13B482EDE548785BE51F4E3A0DE9D698D6B5C87BCC2CD0849DCA82877678629AAFB649F1D6BED827CD9EE20FFAD39DE966794F8BED09869D3A6F1A4E6130265FFFFC34B654ED3B51DBD97CF716DD47180304672D95CB0703BE05C738257A83A8478F7BA6803986D8517F658CEE6EAEC934426129E408B0A50EC28FBC6E5370A3B3E91523691BF39BFC1400ADC9C76DD2605E0050276A3976551510CC14A0BCA35F371027C30A75B952AD984E46D5DF367AC50D0EE023C865504CC0C0A94E8A7BF78B33958C40D506961132FFF050F1317BF905A98C23E3581593ECD8C16B6627BBE31C4DE5EF67EAB2B5F76D3AC074F101BC5C5B810C45F
        System.out.println(encrytDat);

//        try {
//            System.out.println(new String(StringUtils.hexString2Bytes(RSAUtils.decrypt(privateKey, encrytDat)),"utf-8"));
        System.out.println(RSAUtils.decryptByStr(privateKey, encrytDat));
//            System.out.println(RSA.decode(encrytDat,privateKey));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }


    }
}

package com.xiaojiangtun.util;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESCBCUtil {
    private static final String AES_CBC = "AES/CBC/PKCS5Padding";

    public static final String DEFAULT_ENCODING_FORMAT = "UTF-8";

    // 加密
    public static String encrypt(String sSrc, String encodingFormat, String raw, String ivParameter) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_CBC);
        SecretKeySpec skeySpec = new SecretKeySpec(Hex.decodeHex(raw.toCharArray()), "AES");

        // 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        IvParameterSpec iv = new IvParameterSpec(Hex.decodeHex(ivParameter.toCharArray()));
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes(encodingFormat));

        // 此处使用BASE64做转码。
        return new String(Hex.encodeHex(encrypted));
    }

    public static String decrypt(String sSrc, String encodingFormat, String sKey, String ivParameter) throws Exception {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(Hex.decodeHex(sKey.toCharArray()), "AES");
            Cipher cipher = Cipher.getInstance(AES_CBC);
            IvParameterSpec iv = new IvParameterSpec(Hex.decodeHex(ivParameter.toCharArray()));
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            //先用base64解密
            byte[] encrypted1 = parseHexStr2Byte(sSrc);
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original,encodingFormat);
            return originalString;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 将十六进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        } else {
            byte[] result = new byte[hexStr.length() / 2];
            for (int i = 0; i < hexStr.length() / 2; i++) {
                int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
                int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
                result[i] = (byte) (high * 16 + low);
            }
            return result;
        }
    }

    public static void main(String[] args) throws Exception {
        //测试环境秘钥
        String aesSecretKey = "e8c8c4494f404165a9913bcea32b3f92";
        String ivParameter = "31323334353637383930313233343536";

        //加密
        String requestBodyDecrypt = "{\"a\":1,\"b\":\"2\",\"c\":3}";
        String encrypt = AESCBCUtil.encrypt(requestBodyDecrypt, DEFAULT_ENCODING_FORMAT, aesSecretKey, ivParameter);
        System.out.println("加密后: " + encrypt);

        //解密
        String body = "8e7c4d509e9bada743488f26347a0c3399e2d09962d9f52e996e070fd332dc4fe5eb1f84ad547c4cd92caf3e6b6f7f90b4fe874cea6ba2a0d39e37cdd18420fd8effaf7490303095f44d7a8c53101f92928c0fa6e569eff5cf987d88a5dd38bfa0db803f1592a6e8cde6122816694ac63693c3e6019fd57969a543f86592e533a5bbe487809d4f89a0043d8820dd92a32f4c27355dbde824b5ffcc1b33230c485744a286ff1442db2b522236fe5c1354249fe077142ee4fc489910043954ff0902d27b4a09f9e802b434822eed6a647530a02ef45042694d3f6e50ea1ed9dd0bb9b4b48201607c4b9a1a8c019a5d5b5c09f8991b6dfd5669bdf4ac034e3e86d3d2d339468c37630a804d9bf5ed62aafca46c231db2612910af3d240ed269acd52727af33aeff2d06146b9a60ae7517ca8edad4f682fd1216999744e9541e24075b08942a69b5960176243ea1817530f142aa6b03bb03a482d3c89d8f911da9fbd047474f3c9a650699537f18ffa72108c93a2fd95bf28fea1410cdd10628369092e06bf972b4e83df27ef4d1e1d7fe67d9b2df25505d293dfcadc845225c4bd96d32d22045f06b88a51beb24b95044b753060072843e5db2e8f0a8a433bf98d018e3cb6cd78b53a5c64508ddbf91e2e65a24c297d1068b85a6fbecdc16b106ecbd5d4c9c5929bde24ebcc341877ad6caf3b136146af3a49687b800b658ae64cded06c1f8258b65da17fd82640f221c4134570d54079755018b67142045d4c202";
        String decrypt = AESCBCUtil.decrypt(body, DEFAULT_ENCODING_FORMAT, aesSecretKey, ivParameter);
        System.out.println("解密后: " + JSONUtil.formatJsonStr(decrypt));

    }
}
